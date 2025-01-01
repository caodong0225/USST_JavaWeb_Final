from flask import Blueprint, request, jsonify
from models import db, Orders, Goods

orders_bp = Blueprint('orders', __name__)

@orders_bp.route('/', methods=['POST'])
def create_order():
    data = request.json
    goods_id = data.get('goods_id')
    quantity = data.get('quantity')

    good = Goods.query.get(goods_id)
    if not good:
        return jsonify({'error': '商品不存在'}), 404

    if good.stock < quantity:
        return jsonify({'error': '库存不足'}), 400

    total_price = good.price * quantity
    order = Orders(goods_id=goods_id, quantity=quantity, total_price=total_price)

    good.stock -= quantity
    db.session.add(order)
    db.session.commit()

    return jsonify({'message': '订单创建成功', 'order_id': order.id})

@orders_bp.route('/<int:order_id>', methods=['GET'])
def get_order(order_id):
    order = Orders.query.get(order_id)
    if order:
        return jsonify({
            'id': order.id,
            'goods_id': order.goods_id,
            'quantity': order.quantity,
            'total_price': str(order.total_price),
            'order_time': order.order_time,
            'goods_name': order.goods.name,
            'goods_price': str(order.goods.price)
        })
    return jsonify({'error': '订单不存在'}), 404

@orders_bp.route('/', methods=['GET'])
def get_all_orders():
    orders = Orders.query.all()  # 查询所有订单
    result = [{
        'id': order.id,
        'goods_id': order.goods_id,
        'quantity': order.quantity,
        'total_price': float(order.total_price),
        'order_time': order.order_time
    } for order in orders]
    return jsonify(result)

@orders_bp.route('/<int:order_id>', methods=['DELETE'])
def delete_order(order_id):
    order = Orders.query.get(order_id)
    if not order:
        return jsonify({'error': '订单不存在'}), 404

    # 删除订单
    db.session.delete(order)
    db.session.commit()
    return jsonify({'message': f'订单 {order_id} 已成功删除'}), 200
