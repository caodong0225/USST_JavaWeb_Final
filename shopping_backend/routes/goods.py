from flask import Blueprint, jsonify, request
from models import db, Goods

goods_bp = Blueprint('goods', __name__)

@goods_bp.route('/', methods=['GET'])
def get_all_goods():
    goods = Goods.query.all()
    result = [{
        'id': good.id,
        'name': good.name,
        'description': good.description,
        'price': str(good.price),
        'stock': good.stock,
        'image_path': good.image_path
    } for good in goods]
    return jsonify(result)

@goods_bp.route('/<int:goods_id>', methods=['GET'])
def get_goods_details(goods_id):
    good = Goods.query.get(goods_id)
    if good:
        return jsonify({
            'id': good.id,
            'name': good.name,
            'description': good.description,
            'price': str(good.price),
            'stock': good.stock,
            'image_path': good.image_path
        })
    return jsonify({'error': '商品不存在'}), 404
# 搜索商品接口
@goods_bp.route('/search', methods=['GET'])
def search_goods():
    query = request.args.get('q', '')  # 获取查询参数
    if not query:
        return jsonify({'error': '搜索关键字不能为空'}), 400

    goods = Goods.query.filter(Goods.name.like(f'%{query}%')).all()
    if not goods:
        return jsonify({'message': '未找到匹配的商品'}), 404

    result = [{
        'id': good.id,
        'name': good.name,
        'description': good.description,
        'price': str(good.price),
        'stock': good.stock,
        'image_path': good.image_path
    } for good in goods]

    return jsonify(result)