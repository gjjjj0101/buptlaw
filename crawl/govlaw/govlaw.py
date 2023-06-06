import concurrent.futures
import os
from time import sleep
import pymysql
from bupt_law_func import get_safe_response, get_total_page, mkdir, is_exist_pid, downLoadFile, word2html, inserfields, \
    convert_doc_to_docx


# 获取十条数据，每条数据分别进行处理
def crawl_law(url, params, path):
    cnx = pymysql.connect(host='localhost', user='root', passwd='root', port=3306, database='bupt_law')
    cursor = cnx.cursor()
    response = get_safe_response(url, params)
    result = response.json()
    datas = result['result']['data']
    for data in datas:
        # 对于每一条进行操作

        title = data['title']
        # print(title)
        # 解析字段
        expiry = data['expiry']
        office = data['office']
        publish = data['publish']
        status = data['status']
        p_id = data['id']
        zi_url = data['url']
        level = data['type']

        if is_exist_pid(p_id, cursor):
            print('已存在')
        else:
            # 下载文件
            pkey = downLoadFile(zi_url, path)

            # 转换格式
            output = path + pkey + '.html'
            try:
                convert_doc_to_docx(path + pkey + '.doc', path + pkey + '.docx')
            except:
                print('doc2docx转换格式失败')
            try:
                word2html(path + pkey + '.docx', output)
            except:
                print('word2html转换格式失败')

            with open(output, 'r', encoding='utf-8') as f:
                res = f.read()
                # print(res)
                inserfields(cnx, cursor, expiry, level, office, status, publish, title, res, pkey, p_id)
            os.remove(output)
    cursor.close()
    cnx.close()


if __name__ == '__main__':

    url = 'https://flk.npc.gov.cn/api/'
    types = ['flfg', 'xzfg', 'jcfg', 'sfjs', 'dfxfg']
    type_duizhao = {
        'flfg': 'flfg',
        'xzfg': 'xzfg',
        'jcfg': 'jcfg',
        'sfjs': 'sfjs',
        'dfxfg': 'dfxfg'
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
                executor.submit(crawl_law, url, params, path)
                # crawl_law(url,params,path)

                current_page += 1
