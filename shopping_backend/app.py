from flask import Flask
from flask_sqlalchemy import SQLAlchemy
from routes.goods import goods_bp
from routes.orders import orders_bp
from models import db
from flask_cors import CORS


app = Flask(__name__)
app.config.from_object('config.Config') # 一定是要config.Config！！！

# 初始化数据库
db.init_app(app)
CORS(app)
# 初始化数据库
with app.app_context():
    # db.drop_all()
    db.create_all()
    print("数据库表已创建！")
# 注册蓝图
app.register_blueprint(goods_bp, url_prefix='/goods')
app.register_blueprint(orders_bp, url_prefix='/orders')

if __name__ == '__main__':
    app.run(debug=True)
