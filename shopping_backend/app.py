from flask import Flask
from flask_sqlalchemy import SQLAlchemy
from routes.goods import goods_bp
from routes.orders import orders_bp
from routes.cart import cart_bp
from models import db
from flask_cors import CORS
app = Flask(__name__)
app.config.from_object('config.Config') # 一定是要config.Config！！！

# 初始化数据库
db.init_app(app)
app.config['CORS_HEADERS'] = 'Content-Type'
CORS(app, resources={r"/*": {"origins": "*"}})  # 允许所有来源跨域
app.url_map.strict_slashes = False  # 忽略斜杠差异，加这句是防止前端最后api没有'/'结果出现cors错误
# 初始化数据库
with app.app_context():
    # db.drop_all()
    db.create_all()
    print("数据库表已创建！")
# 注册蓝图
app.register_blueprint(goods_bp, url_prefix='/goods')
app.register_blueprint(orders_bp, url_prefix='/orders')
app.register_blueprint(cart_bp, url_prefix='/cart')

if __name__ == '__main__':
    app.run(host='0.0.0.0')
