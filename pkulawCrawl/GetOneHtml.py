import time

from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.support.wait import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC


class GetOneHtml:
    def __init__(self):
        self.driver = None

    def open_browser(self, url):
        options = webdriver.ChromeOptions()
        options.add_argument('--headless')
        self.driver = webdriver.Chrome(options=options)
        self.driver.get(url)

    def open_new_tab(self, url, key, path):
        self.driver.execute_script(f"window.open('{url}');")
        self.driver.switch_to.window(self.driver.window_handles[-1])
        WebDriverWait(self.driver, 10).until(EC.presence_of_element_located((By.TAG_NAME, 'body')))  # 等待页面加载完成
        html = self.driver.page_source
        with open(path + key + '.html', 'w', encoding='utf-8') as f:
            f.write(html)

    def close_tab(self):
        self.driver.close()
        self.driver.switch_to.window(self.driver.window_handles[-1])

    def close_browser(self):
        self.driver.quit()


if __name__ == '__main__':
    chrome_driver = GetOneHtml()
    chrome_driver.open_browser('https://www.baidu.com/')
    chrome_driver.open_new_tab('https://www.google.com/')
    chrome_driver.close_tab()
    time.sleep(2)
    chrome_driver.close_browser()
