import sys
import os
CURRENT_DIR = os.path.split(os.path.abspath(__file__))[0]  # 当前目录
config_path = CURRENT_DIR.rsplit('\\', 1)[0]  # 上三级目录
sys.path.append(config_path)

from models import db, Goods
from start import app
# 示例商品数据
example_goods = [
    {
        'name': '智能手机',
        'description': '高性能的智能手机，适合日常使用',
        'price': 3999.00,
        'stock': 100,
        'image_path': '/static/uploads/goods/smartphone.jpg'
    },
    {
        'name': '笔记本电脑',
        'description': '轻薄便携的办公笔记本',
        'price': 5999.00,
        'stock': 50,
        'image_path': '/static/uploads/goods/laptop.jpg'
    },
    {
        'name': '无线耳机',
        'description': '高品质音效的蓝牙无线耳机',
        'price': 299.00,
        'stock': 200,
        'image_path': '/static/uploads/goods/earphones.jpg'
    },
    {
        'name': '智能手表',
        'description': '监测健康数据的智能手表',
        'price': 1299.00,
        'stock': 150,
        'image_path': '/static/uploads/goods/smartwatch.jpg'
    },
    {
        'name': '台灯',
        'description': '适合阅读的护眼LED台灯',
        'price': 99.00,
        'stock': 500,
        'image_path': '/static/uploads/goods/lamp.jpg'
    },
]

# 插入数据
with app.app_context():
    for good in example_goods:
        new_good = Goods(
            name=good['name'],
            description=good['description'],
            price=good['price'],
            stock=good['stock'],
            image_path=good['image_path']
        )
        db.session.add(new_good)
    db.session.commit()
    print("示例商品已插入！")
