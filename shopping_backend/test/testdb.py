import sys
import os
CURRENT_DIR = os.path.split(os.path.abspath(__file__))[0]  # 当前目录
config_path = CURRENT_DIR.rsplit('\\', 1)[0]  # 上三级目录
sys.path.append(config_path)

from flask import Flask
from models import db
import config

app = Flask(__name__)
app.config.from_object('config.Config')
db.init_app(app)

def test_connection():
    try:
        with app.app_context():
            # 尝试执行一个简单的数据库查询
            db.session.execute('SELECT 1')
            print('数据库连接成功！')
    except Exception as e:
        print('数据库连接失败！')
        print(f'错误信息: {str(e)}')

if __name__ == '__main__':
    test_connection()