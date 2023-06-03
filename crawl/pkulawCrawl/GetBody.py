from bs4 import BeautifulSoup

soup = BeautifulSoup(open('assets/0acd9e7b17573811bdfb.html', encoding='utf-8'), 'html.parser')

body = soup.find("div", id="divFullText")

links = body.find_all("span", class_="TiaoYinV2")
 

for link in links:
    empty_line = soup.new_tag('br')  # 创建新的空行标签
    link.replace_with(empty_line)  # 将原来的链接替换成空行标签

with open('new_file.html', 'w', encoding='utf-8') as file:
    # 写入div标签的内容
    file.write(body.prettify())