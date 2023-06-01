import concurrent.futures
import os.path
import time
from GetUrl import GetUrl
from GetOneHtml import GetOneHtml


# 法律 XA010x   1-8
# 行政法规 XC020x 1-3
# 司法解释 XG040x 1-3
# 部门规章 XE030x 1-4
# 军事法规规章 XQ090x 1-3
# 团体规定  XI05 200
# 行业规定 XK06 200
def get_html(j, name, i):
    gu = GetUrl(j, name, i)
    my_dict = gu.get_urls()
    if not my_dict:
        pass
    else:
        chrome_driver = GetOneHtml()
        chrome_driver.open_browser('https://www.pkulaw.com/')
        for key, url in my_dict.items():
            if os.path.exists('data/' + name + '/' + key + '.html'):
                continue
            # url key path
            chrome_driver.open_new_tab(url, key, 'data/' + name + '/')
            chrome_driver.close_tab()
            # print(key)
            time.sleep(1)


if __name__ == '__main__':

    target_title = {'法律': 8, '行政法规': 3, '司法解释': 3, '部门规章': 4, '军事法规规章': 3, '团体规定': 1,
                    '行业规定': 0}
    thread_num = 8
    for name, value in target_title.items():
        for i in range(1, value + 1):
            with concurrent.futures.ThreadPoolExecutor(max_workers=thread_num) as executor:
                for j in range(1, 201):
                    time.sleep(1)
                    executor.submit(get_html, j, name, i)
