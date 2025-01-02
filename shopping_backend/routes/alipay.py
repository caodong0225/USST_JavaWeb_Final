from flask import Blueprint, jsonify, request
from alipay import AliPay
from alipay_config import AlipayConfig
from models import db, Orders, OrderItems
import hashlib
import base64

backend = "localhost:5000"
frontend="localhost:5173"
secret_key = "114514"  # 自定义密钥

pay_bp = Blueprint('pay', __name__)

# 初始化支付宝
alipay = AliPay(
    appid=AlipayConfig.APP_ID,
    app_notify_url=None,  # 支付结果通知 URL，可为空
    app_private_key_string=AlipayConfig.APP_PRIVATE_KEY,
    alipay_public_key_string=AlipayConfig.ALIPAY_PUBLIC_KEY,
    sign_type="RSA2",  # 使用 RSA2 签名
    debug=AlipayConfig.DEBUG  # True 表示沙箱环境
)

@pay_bp.route('/create_order', methods=['POST'])
def create_payment():
    data = request.json
    order_id = data.get('order_id')

    if not order_id:
        return jsonify({'error': '订单ID不能为空'}), 400

    # 查询订单
    order = Orders.query.get(order_id)
    if not order:
        return jsonify({'error': '订单不存在'}), 404

    # 计算订单总金额
    total_amount = sum(item.total_price for item in order.order_items)

    # 生成 return_url，带有验证参数
    signature = hashlib.sha256(f"{order_id}:{secret_key}".encode()).hexdigest()
    return_url = f"http://{frontend}/payment?order_id={order_id}&signature={signature}"

    # 生成支付链接
    order_string = alipay.api_alipay_trade_page_pay(
        out_trade_no=order.id,
        total_amount=str(total_amount),
        subject=f"USST小超市订单支付（订单号：{order.id}）",
        return_url=return_url
    )

    payment_url = f"{AlipayConfig.ALIPAY_GATEWAY}?{order_string}"
    return jsonify({'payment_url': payment_url})

@pay_bp.route('/check', methods=['POST'])
def check_payment():
    data = request.json
    order_id = data.get('order_id')
    signature = data.get('signature')

    if not order_id or not signature:
        return jsonify({'error': '参数缺失'}), 400

    # 验证签名
    expected_signature = hashlib.sha256(f"{order_id}:{secret_key}".encode()).hexdigest()
    if signature != expected_signature:
        return jsonify({'error': '无效的签名'}), 403

    # 查询订单
    order = Orders.query.get(order_id)
    if not order:
        return jsonify({'error': '订单不存在'}), 404

    # 假设这里是从支付宝服务器验证支付结果
    # 示例中省略了与支付宝的真实验证交互，直接标记支付成功
    if order.status != 2:  # 如果订单未支付
        order.status = 2  # 更新订单状态为已支付
        db.session.commit()

    return jsonify({'message': '支付成功', 'order_id': order_id, 'status': order.status}), 200
