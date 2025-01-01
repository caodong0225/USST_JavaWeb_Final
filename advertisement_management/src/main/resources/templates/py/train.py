import pymysql
import pandas as pd

# 连接 MySQL 数据库
connection = pymysql.connect(
    host='localhost',
    user='root',
    password='123456',
    database='adv'
)

# 读取 user_train_data 表
query = "SELECT age, gender, occupation, educationLevel, region, country, device, Fashion, Art, Entertainment, Education, Pets, Eco, Weather, Technology, Politics, Economy FROM user_train_data"
df = pd.read_sql(query, connection)

# 关闭连接
connection.close()

# 查看数据
print(df.head())