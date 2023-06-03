import random
import re
import time
import requests
from bs4 import BeautifulSoup
from GetOneHtml import GetOneHtml


class GetUrl:
    def __init__(self, page_index, type, index=1):
        self.myheaders = [
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
        self.baseurl = 'https://pkulaw.com'
        # 法律 XA010x   1-8
        # 行政法规 XC020x 1-3
        # 司法解释 XG040x 1-3
        # 部门规章 XE030x 1-4
        # 军事法规规章 XQ090x 1-3
        # 团体规定  XI05 200
        # 行业规定 XK06 200
        duizhao = {
            '法律': 'XA010',
            '行政法规': 'XC020',
            '司法解释': 'XG040',
            '部门规章': 'XE030',
            '军事法规规章': 'XQ090',
            '团体规定': 'XI05',
            '行业规定': 'XK06'
        }

        for key, value in duizhao.items():
            if key == type:
                target_type = value
        if len(target_type) == 4:
            pass
        else:
            target_type += str(index)
        if page_index >= 21:
            page_index += 1
        self.payload = {
            'Menu': 'law',
            'Keywords': '',
            'SearchKeywordType': 'Title',
            'MatchType': 'Exact',
            'RangeType': 'Piece',
            'Library': 'chl',
            'ClassFlag': 'chl',
            'GroupLibraries': '',
            'QueryOnClick': False,
            'AfterSearch': False,
            'PreviousLib': 'chl',
            'pdfStr': '',
            'pdfTitle': '',
            'IsSynonymSearch': 'false',
            'RequestFrom': '',
            'LastLibForChangeColumn': 'chl',
            'IsAdv': False,
            'ClassCodeKey': ',,,,,,',
            'Aggs.RelatedPrompted': '',
            'Aggs.EffectivenessDic': target_type,
            'Aggs.SpecialType': '',
            'Aggs.IssueDepartment': '',
            'Aggs.TimelinessDic': '',
            'Aggs.Category': '',
            'Aggs.IssueDate': '',
            'GroupByIndex': 2,
            'OrderByIndex': 0,
            'ShowType': 'Default',
            'GroupValue': '',
            'TitleKeywords': '',
            'FullTextKeywords': '',
            'Pager.PageIndex': page_index - 1,
            'RecordShowType': 'List',
            'Pager.PageIndex': page_index - 1,
            'Pager.PageSize': 10,
            'QueryBase64Request': '',
            'VerifyCodeResult': 'YmRmYl8xMzc=',
            'isEng': 'chinese',
            'OldPageIndex': page_index - 2 if page_index >= 0 else 0,
            'newPageIndex': '',
            'IsShowListSummary': '',
            'X - Requested - With': 'XMLHttpRequest'
        }

    def get_urls(self):
        headers = {'User-Agent': random.choice(self.myheaders)}
        response = requests.post(self.baseurl + '/law/search/RecordSearch', headers=headers, data=self.payload)
        soup = BeautifulSoup(response.content, 'html.parser')
        lists = soup.find_all('div', ['class', 't'])
        my_dict = {}
        for list in lists:
            try:
                h4 = list.find('h4')
                href = h4.findChild('a')['href']
                url = self.baseurl + href
                match = re.search(r"/chl/(\w+)\.html", href)
                key = match.group(1)
                print(key)
                # print(h4.text)
                # print(url)
                my_dict[key] = url
                # break
                # response = get_safe_response(url)
            except:
                pass
        return my_dict


if __name__ == '__main__':
    # 页码 第n小项
    gu = GetUrl(1, '法律',8)
    my_dict = gu.get_urls()
    if not my_dict:
        pass
    else:
        chrome_driver = GetOneHtml()
        chrome_driver.open_browser('https://www.pkulaw.com/')
        for key, url in my_dict.items():
            # url key path
            chrome_driver.open_new_tab(url, key, 'data/')
            chrome_driver.close_tab()
            # print(key)
            time.sleep(1)
