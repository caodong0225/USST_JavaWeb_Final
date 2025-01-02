from flask import Blueprint, request, jsonify
from models import db, Cart, Goods, Orders, OrderItems

cart_bp = Blueprint('cart', __name__)

# 添加商品到购物车
@cart_bp.route('/', methods=['POST'])
def add_to_cart():
    data = request.json
    goods_id = data.get('goods_id')
    quantity = data.get('quantity', 1)

    # 检查商品是否存在
    goods = Goods.query.get(goods_id)
    if not goods:
        return jsonify({'error': '商品不存在'}), 404

    # 检查库存是否充足
    if goods.stock < quantity:
        return jsonify({'error': '库存不足'}), 400

    # 检查购物车中是否已有该商品
    cart_item = Cart.query.filter_by(goods_id=goods_id, user_id=1).first()
    if cart_item:
        cart_item.quantity += quantity
    else:
        cart_item = Cart(goods_id=goods_id, quantity=quantity, user_id=1)
        db.session.add(cart_item)

    db.session.commit()
    return jsonify({'message': '商品已加入购物车'})

# 获取购物车内容
@cart_bp.route('/', methods=['GET'])
def get_cart():
    cart_items = Cart.query.filter_by(user_id=1).all()
    result = [{
        'id': item.id,
        'goods_id': item.goods_id,
        'name': item.goods.name,
        'price': float(item.goods.price),
        'quantity': item.quantity,
        'total_price': float(item.goods.price) * item.quantity
    } for item in cart_items]
    return jsonify(result)

# 更新购物车商品数量
@cart_bp.route('/<int:cart_id>', methods=['PUT'])
def update_cart_item(cart_id):
    data = request.json
    new_quantity = data.get('quantity')

    if new_quantity is None or new_quantity <= 0:
        return jsonify({'error': '数量必须大于 0'}), 400

    cart_item = Cart.query.filter_by(id=cart_id, user_id=1).first()
    if not cart_item:
        return jsonify({'error': '购物车商品不存在'}), 404

    # 检查库存是否充足
    if cart_item.goods.stock < new_quantity:
        return jsonify({'error': '库存不足'}), 400

    cart_item.quantity = new_quantity
    db.session.commit()
    return jsonify({'message': '购物车已更新'})

# 删除购物车商品
@cart_bp.route('/<int:cart_id>', methods=['DELETE'])
def delete_cart_item(cart_id):
    cart_item = Cart.query.filter_by(id=cart_id, user_id=1).first()
    if not cart_item:
        return jsonify({'error': '购物车商品不存在'}), 404

    db.session.delete(cart_item)
    db.session.commit()
    return jsonify({'message': '商品已从购物车移除'})

# 清空购物车
@cart_bp.route('/', methods=['DELETE'])
def clear_cart():
    cart_items = Cart.query.filter_by(user_id=1).all()
    for item in cart_items:
        db.session.delete(item)
    db.session.commit()
    return jsonify({'message': '购物车已清空'})

@cart_bp.route('/orders', methods=['POST'])
def create_order_from_cart():
    cart_items = Cart.query.filter_by(user_id=1).all()
    if not cart_items:
        return jsonify({'error': '购物车为空'}), 400

    # 检查库存是否足够
    for item in cart_items:
        goods = Goods.query.get(item.goods_id)
        if not goods:
            return jsonify({'error': f'商品ID {item.goods_id} 不存在'}), 404

        if goods.stock < item.quantity:
            return jsonify({'error': f'商品 {goods.name} 库存不足'}), 400

    # 创建订单
    new_order = Orders(user_id=1, status=1)  # user_id 固定为 1
    db.session.add(new_order)
    db.session.commit()  # 提交订单主信息，确保有订单 ID

    # 更新库存并添加商品到订单商品表
    for item in cart_items:
        goods = Goods.query.get(item.goods_id)
        goods.stock -= item.quantity  # 减少库存

        # 创建订单商品记录
        order_item = OrderItems(
            order_id=new_order.id,
            goods_id=item.goods_id,
            quantity=item.quantity,
            total_price=item.quantity * goods.price
        )
        db.session.add(order_item)

        # 删除购物车中的商品
        db.session.delete(item)

    # 提交所有更改
    db.session.commit()

    return jsonify({'message': '订单创建成功', 'order_id': new_order.id})

