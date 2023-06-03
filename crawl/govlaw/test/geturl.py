# -*- coding: utf-8 -*-
import os
import random
import requests
import urllib3
from selenium import webdriver
from time import sleep

from selenium.webdriver.common.by import By
import shutil  # 导入shutil模块
# 多线程
import concurrent.futures
from docx import Document

# 去掉warning   不检测证书
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


def get_safe_response(url, params):
    headers = {'User-Agent': random.choice(my_headers)}
    response = requests.get(url=url, headers=headers, verify=False, params=params)
    return response


def downLoadFile(url, save_path):
    options = webdriver.ChromeOptions()
    prefs = {'profile.default_content_settings.popups': 0, 'download.default_directory': save_path}
    options.add_experimental_option('prefs', prefs)
    # 隐藏浏览器窗口

    params = {'cmd': 'Page.setDownloadBehavior', 'params': {'behavior': 'allow', 'downloadPath': "d:\\download\\law"}}

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
    title = driver.find_element(By.CLASS_NAME, 'title')
    print(title.text)
    img = driver.find_element(By.CLASS_NAME, 'erweima')
    src = img.get_attribute('src')  # 获取img元素的src属性
    # print(src)  # 打印src
    old_name = src.split('/')[-1].split('.')[0]
    print(old_name)
    driver.find_element(By.ID, "downLoadFile").click()
    publish_time = driver.find_element(By.ID, 'gbrq').text
    sleep(1)
    # os.rename('C:/Users/11575/Downloads/' + old_name + '.docx',
    #           save_path + '[' + publish_time + ']' + title.text + '.docx')
    try:
        shutil.move("d:\\download\\law\\" + old_name + '.doc',
                    save_path + '[' + publish_time + ']' + title.text + '.doc')
    except:
        try:
            shutil.move("d:\\download\\law\\" + old_name + '.docx',
                        save_path + '[' + publish_time + ']' + title.text + '.docx')
        except Exception as e:
            print(e)
    driver.quit()


def mkdir(file_path):
    if not os.path.exists(file_path):
        os.makedirs(file_path)
        print('创建文件夹', file_path)


def get_total_page(url, params):
    response = get_safe_response(url, params)
    result = response.json()
    result = result['result']
    total = result['totalSizes']
    total_page = int(total / page_size) + 1
    # print(total_page)
    return total_page


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


if __name__ == '__main__':
    # url = 'https://flk.npc.gov.cn/detail2.html?ZmY4MDgxODE4NjljZGE0MDAxODZkZjRkNTE0NTdjMmU%3D'
    # save_path = 'd:\\download\\law'
    # downLoadFile(url, save_path)

    url = 'https://flk.npc.gov.cn/api/'
    types = ['flfg', 'xzfg', 'jcfg', 'sfjs', 'dfxfg']
    type_duizhao = {
        '法律法规': 'flfg',
        '行政法规': 'xzfg',
        '检查法规': 'jcfg',
        '司法解释': 'sfjs',
        '地方性法规': 'dfxfg'
    }
    page_num = 1
    page_size = 10
    for type in types:
        save_path = 'D:\\download/law/'
        type_path = ''
        for key, value in type_duizhao.items():
            if value == type:
                type_path = key
            path = save_path + type_path + '/'
            mkdir(path)

        params = {'type': type,
                  'page': page_num,
                  'size': page_size, }  # 要携带的参数，键为参数名，值为参数值
        while 1:
            try:
                total_page = get_total_page(url, params)
                break
            except:
                pass

        current_page = 1
        # 这里需要缩进 ！
        with concurrent.futures.ThreadPoolExecutor(max_workers=8) as executor:
            while current_page <= total_page:
                params = {'type': type,
                          'page': current_page,
                          'size': page_size, }  # 要携带的参数，键为参数名，值为参数值
                executor.submit(crawl_law, url,params,path)
                # crawl_law(url,params,path)

                current_page += 1
