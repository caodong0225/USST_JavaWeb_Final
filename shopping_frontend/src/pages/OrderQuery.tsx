import React, { useState } from 'react';
import { fetchOrder } from '../api';

function OrderQuery() {
  const [orderId, setOrderId] = useState(0);
  const [order, setOrder] = useState<any>(null);

  const handleQuery = async () => {
    try {
      const data = await fetchOrder(orderId);
      setOrder(data);
    } catch (error) {
      setOrder(null);
    }
  };

  return (
    <div className="container mt-4">
      <h1>查询订单</h1>
      <input
        type="number"
        placeholder="订单ID"
        value={orderId}
        onChange={(e) => setOrderId(Number(e.target.value))}
        className="form-control mb-2"
      />
      <button className="btn btn-primary" onClick={handleQuery}>查询订单</button>
      {order ? (
        <div className="mt-3">
          <p>订单ID: {order.id}</p>
          <p>商品ID: {order.goods_id}</p>
          <p>数量: {order.quantity}</p>
          <p>总价: ¥{order.total_price}</p>
          <p>下单时间: {order.order_time}</p>
        </div>
      ) : (
        <p className="mt-3">订单不存在</p>
      )}
    </div>
  );
}

export default OrderQuery;
