# 连接数据库，并且读取html并进行写入
import pymysql

# 打开数据库连接
try:
    db = pymysql.connect(host='localhost', user='root', passwd='root', port=3306, database='bupt_law')
    print('连接成功！')
except:
    print('something wrong!')

# 使用 cursor() 方法创建一个游标对象 cursor
cursor = db.cursor()

with open('output.html', 'r', encoding='utf-8') as f:
    res = f.read()
    # print(res)
    sql = "insert into laws(html,title,xlwj,zdjg,sxx,sxrq,gbrq) values( % s,% s,% s,% s,% s,% s,% s)"
    # 执行插入语句
    cursor.execute(sql, (res, '最高人民法院、最高人民检察院关于办理危害生产安全刑事案件适用法律若干问题的解释（二）', '司法解释', '最高人民法院、最高人民检察院', '有效', '2022-12-19 00:00:00', '2022-12-15 00:00:00'))
    # 提交事务
    db.commit()

# 关闭游标和数据库连接
cursor.close()
db.close()
