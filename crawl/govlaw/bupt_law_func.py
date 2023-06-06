import os
import random
import shutil
import subprocess
import requests
import urllib3
from selenium import webdriver
from selenium.webdriver.common.by import By
from time import sleep
from docx import Document

urllib3.disable_warnings(urllib3.exceptions.InsecureRequestWarning)

my_headers = [
    "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.95 Safari/537.36",
    "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/35.0.1916.153 Safari/537.36",
    "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:30.0) Gecko/20100101 Firefox/30.0",
    "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.75.14 (KHTML, like Gecko) Version/7.0.3 Safari/537.75.14",
    "Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.2; Win64; x64; Trident/6.0)",
    'Mozilla/5.0 (Windows; U; Windows NT 5.1; it; rv:1.8.1.11) Gecko/20071127 Firefox/2.0.0.11',
    'Opera/9.25 (Windows NT 5.1; U; en)',
    'Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; .NET CLR 1.1.4322; .NET CLR 2.0.50727)',
    'Mozilla/5.0 (compatible; Konqueror/3.5; Linux) KHTML/3.5.5 (like Gecko) (Kubuntu)',
    'Mozilla/5.0 (X11; U; Linux i686; en-US; rv:1.8.0.12) Gecko/20070731 Ubuntu/dapper-security Firefox/1.5.0.12',
    'Lynx/2.8.5rel.1 libwww-FM/2.14 SSL-MM/1.4.1 GNUTLS/1.2.9',
    "Mozilla/5.0 (X11; Linux i686) AppleWebKit/535.7 (KHTML, like Gecko) Ubuntu/11.04 Chromium/16.0.912.77 Chrome/16.0.912.77 Safari/535.7",
    "Mozilla/5.0 (X11; Ubuntu; Linux i686; rv:10.0) Gecko/20100101 Firefox/10.0 "
]


# 获取response
def get_safe_response(url, params):
    headers = {'User-Agent': random.choice(my_headers)}
    response = requests.get(url=url, headers=headers, verify=False, params=params)
    return response


# 点击下载文件
def downLoadFile(url, save_path):
    options = webdriver.ChromeOptions()
    prefs = {'profile.default_content_settings.popups': 0, 'download.default_directory': save_path}
    options.add_experimental_option('prefs', prefs)
    # 隐藏浏览器窗口
    options.add_argument("--headless")
    options.add_argument("--window-size=1920,1080")
    options.add_experimental_option("prefs", {
        "download.default_directory": "d:\\download\\law",
        "download.prompt_for_download": False,
        "download.directory_upgrade": True,
        "safebrowsing_for_trusted_sources_enabled": False
    })

    driver = webdriver.Chrome(chrome_options=options)
    # 允许无头下载
    driver.execute_cdp_cmd('Page.setDownloadBehavior', {'behavior': 'allow', 'downloadPath': "d:\\download\\law"})
    driver.get(url)
    img = driver.find_element(By.CLASS_NAME, 'erweima')
    src = img.get_attribute('src')  # 获取img元素的src属性
    # print(src)  # 打印src
    old_name = src.split('/')[-1].split('.')[0]
    print(old_name)
    driver.find_element(By.ID, "downLoadFile").click()
    sleep(1)
    # os.rename('C:/Users/11575/Downloads/' + old_name + '.docx',
    #           save_path + '[' + publish_time + ']' + title.text + '.docx')
    try:
        shutil.move("d:\\download\\law\\" + old_name + '.doc',
                    save_path + old_name + '.doc')
    except:
        try:
            shutil.move("d:\\download\\law\\" + old_name + '.docx',
                        save_path + old_name + '.docx')
        except Exception as e:
            print(e)
    driver.quit()
    return old_name


# 获取总页数
def get_total_page(url, params):
    response = get_safe_response(url, params)
    result = response.json()
    result = result['result']
    total = result['totalSizes']
    page_size = 10
    total_page = int(total / page_size) + 1
    # print(total_page)
    return total_page


# 控制逻辑，已存在则不爬取
def crawl_law(url, params, path):
    while 1:
        try:
            response = get_safe_response(url, params)
            break
        except:
            pass
    result = response.json()
    datas = result['result']['data']
    for data in datas:
        # publish_time = data['publish']
        # publish_time = (str(publish_time)[:10])
        # title = '[' + publish_time + ']' + data['title']
        title = data['title']
        # print(title)
        if os.path.exists('./data/地方性法规/[2023-01-31]徐州市电梯安全管理条例.docx'):
            print(1)
        if os.path.exists(path + title + '.doc'):
            print('已存在')
        elif os.path.exists(path + title + '.docx'):
            print('已存在')
        else:
            try:
                downLoadFile(data['url'], path)
                break
            except:
                pass


# 将word转为html
def word2html(input, output):
    input_file = input
    output_file = output

    subprocess.run(['pandoc', input_file, '-o', output_file, '-s'])


# 判断数据库中是否已经存在
def is_exist_pid(pkey, cursor):
    query = "SELECT * FROM laws WHERE p_id = %s"
    cursor.execute(query, (pkey,))
    if cursor.fetchone():
        return True
    else:
        return False


# 插入字段到数据库
def inserfields(cnx, cursor, expiry, level, office, status, publish, title, res, pkey, p_id):
    sql = "insert into laws(html,title,level,office,status,expiry,publish,pkey,p_id) values( % s,% s,% s,% s,% s,% s,% s,% s,% s)"
    # 执行插入语句
    cursor.execute(sql, (
        res, title, level, office, status, expiry, publish, pkey, p_id))
    # 提交事务
    cnx.commit()


# 创建文件夹
def mkdir(file_path):
    if not os.path.exists(file_path):
        os.makedirs(file_path)
        print('创建文件夹', file_path)


def convert_doc_to_docx(doc_file, docx_file):
    # 打开 DOC 文件
    doc = Document(doc_file)

    # 创建一个新的 DOCX 文件
    docx = Document()

    # 复制 DOC 文件的内容到 DOCX 文件
    for element in doc.element.body:
        docx.element.body.append(element)

    # 保存 DOCX 文件
    docx.save(docx_file)


if __name__ == '__main__':
    pass
