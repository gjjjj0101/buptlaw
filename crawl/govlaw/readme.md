 # BuptLaw

## 数据获取思路

1. post请求，发送网址+type+page获得url
2. 在返回结果中提取字段，包括法律位阶、制定机关、时效性、实行日期、公布日期
3. selenium模拟浏览器进入url，模拟点击下载word文件
4. 将word文件保存到指定目录，如果已经存在则不下载
5. 将此word文件转为html，并与2中获得的字段一起，存入mysql数据库



## 代码实现细节
### 文件说明
#### bupt_law_func.py
存放此爬虫中用到的各种功能函数
- word2html 格式转换。word到html
- convert_doc_to_docx 格式转换，从doc转为docx，解决pandoc识别问题
- mkdir 创建文件夹，没有则创建按
- inserfields 插入字段，向数据库中插入名称、html、时效性、发布日期等
- is_exist_pid 判断数据库中是否存在，根据id判断
- get_total_page 获取总条数，计算总页数
- downLoadFile 根据url，利用Selenium模拟浏览器点击下载
- get_safe_response 发送请求
- 




### 数据库字段与网络响应字段对应表

发送请求后，获得响应，在响应中的result进行提取

|数据库字段 |响应字段|
|-------|---|
| title |title|
| zdjg |office|
|sxx|status|
|sxrq|expiry|
|gbrq|publish|
|xlwj|level|





---------------------------分割线--------------------------------------------------
---------------------------------

## 数据库与后端对接

### laws数据库字段说明

| 字段名   | 类型  | 注释             |
|-------|-----|----------------|
| html  |text| 存储word转换后的html |
| title |varchar| 法律条令名称         |
| zdjg  |varvhar| 制定机关           |
| sxx   |varchar| 时效性            |
|sxrq|varchar| 施行日期           |
|gbrq|varchar| 公布日期           |
|xlwj|varchar| 效力位阶           |

### 导出格式

将2条数据导出后，每种文件的大小

| 拓展名    | 说明                 | 文件大小   | 占用空间     |
|:-------|--------------------|--------|----------|
| dbf    | 数据库格式              | 4.75kB | 占用8kB    |
| csv文件  | utf-8编码   默认打开方式乱码 | 99.5kB | 占用100kB  |
| xlsx文件 | utf-8编码   默认打开没有乱码 | 13.5KB | 占用16.0KB |
| sql文件  |                    | 100KB  | 占用104KB  |
| json文件 |                    | 100KB  | 占用104KB  |

