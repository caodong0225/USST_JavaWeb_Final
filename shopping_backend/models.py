from flask_sqlalchemy import SQLAlchemy

db = SQLAlchemy()

# 商品表模型
class Goods(db.Model):
    __tablename__ = 'goods'

    id = db.Column(db.Integer, primary_key=True, autoincrement=True)
    name = db.Column(db.String(100), nullable=False, comment='商品名称')
    description = db.Column(db.Text, comment='商品描述')
    price = db.Column(db.Numeric(10, 2), nullable=False, default=0.00, comment='商品价格')
    stock = db.Column(db.Integer, nullable=False, default=0, comment='库存')
    image_path = db.Column(db.String(255), comment='商品图片存储路径')

# 订单表模型
class Orders(db.Model):
    __tablename__ = 'orders'

    id = db.Column(db.Integer, primary_key=True, autoincrement=True)
    goods_id = db.Column(db.Integer, db.ForeignKey('goods.id'), nullable=False, comment='商品ID')
    quantity = db.Column(db.Integer, nullable=False, default=1, comment='购买数量')
    total_price = db.Column(db.Numeric(10, 2), nullable=False, comment='总价')
    order_time = db.Column(db.DateTime, server_default=db.func.now(), comment='下单时间')

    goods = db.relationship('Goods', backref=db.backref('orders', lazy=True))
