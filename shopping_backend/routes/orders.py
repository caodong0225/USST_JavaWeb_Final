from flask import Blueprint, request, jsonify
from models import db, Orders, OrderItems, Goods

orders_bp = Blueprint('orders', __name__)

# 创建订单
@orders_bp.route('/', methods=['POST'])
def create_order():
    data = request.json
    items = data.get('items', [])

    if not items:
        return jsonify({'error': '订单内容不能为空'}), 400

    new_order = Orders(user_id=1, status=1)
    db.session.add(new_order)
    db.session.commit()

    for item in items:
        goods_id = item.get('goods_id')
        quantity = item.get('quantity', 0)

        goods = Goods.query.get(goods_id)
        if not goods:
            return jsonify({'error': f'商品ID {goods_id} 不存在'}), 404

        if goods.stock < quantity:
            return jsonify({'error': f'商品 {goods.name} 库存不足'}), 400

        goods.stock -= quantity
        order_item = OrderItems(
            order_id=new_order.id,
            goods_id=goods_id,
            quantity=quantity,
            total_price=goods.price * quantity
        )
        db.session.add(order_item)

    db.session.commit()
    return jsonify({'message': '订单创建成功', 'order_id': new_order.id})

# 获取订单详情
@orders_bp.route('/<int:order_id>', methods=['GET'])
def get_order(order_id):
    order = Orders.query.get(order_id)
    if not order:
        return jsonify({'error': '订单不存在'}), 404

    order_items = [{
        'goods_id': item.goods_id,
        'goods_name': item.goods.name,
        'quantity': item.quantity,
        'total_price': float(item.total_price)
    } for item in order.order_items]

    return jsonify({
        'id': order.id,
        'user_id': order.user_id,
        'status': order.status,
        'order_time': order.order_time,
        'items': order_items
    })

# 获取所有订单
@orders_bp.route('/', methods=['GET'])
def get_all_orders():
    orders = Orders.query.all()
    result = []
    for order in orders:
        order_items = [{
            'goods_id': item.goods_id,
            'goods_name': item.goods.name,
            'quantity': item.quantity,
            'total_price': float(item.total_price)
        } for item in order.order_items]

        result.append({
            'id': order.id,
            'user_id': order.user_id,
            'status': order.status,
            'order_time': order.order_time,
            'items': order_items
        })
    return jsonify(result)

# 删除订单
@orders_bp.route('/<int:order_id>', methods=['DELETE'])
def delete_order(order_id):
    order = Orders.query.get(order_id)
    if not order:
        return jsonify({'error': '订单不存在'}), 404
    # 删除与该订单相关的 OrderItems
    order_items = OrderItems.query.filter_by(order_id=order_id).all()
    for item in order_items:
        db.session.delete(item)
    # 删除订单
    db.session.delete(order)
    db.session.commit()
    return jsonify({'message': f'订单 {order_id} 已成功删除'}), 200
