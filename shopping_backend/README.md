# Hello!😄😄
This is shopping backend!

# 使用技术
Made by Python Flask!

# 特色
使用了ORM，不用手动写SQL语句即可操作数据库！简单又安全！😍

# 使用方法

首先安装mysql（版本无要求，笔者用的版本是8.0）

安装教程可参考:

本体安装：https://blog.csdn.net/weixin_42678675/article/details/124090783
开防火墙：https://blog.csdn.net/lvqingyao520/article/details/81075094

然后本子项目的根目录创建`config.py`，内容为：
```
import os
from urllib.parse import quote_plus

password = 'password'  # 原始密码
encoded_password = quote_plus(password)  # 编码后的密码

class Config:
    SQLALCHEMY_DATABASE_URI = f'mysql://root:{encoded_password}@yourip:3306/shopping'
    SQLALCHEMY_TRACK_MODIFICATIONS = False
    UPLOAD_FOLDER = 'static/uploads/goods'

config = Config
```
随后运行`pip install -r requirements.txt`

最后运行`app.py`，数据库表会自动创建！

如果需要插入示例数据，可以使用以下两种方式之一：
  1. 运行 `/test/insert_data.py` ，自动插入测试数据。  
  2. 手动执行 `tables.sql` 文件中的 SQL 脚本。  