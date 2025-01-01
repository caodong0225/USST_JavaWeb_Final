import React, { useState } from 'react';
import { createOrder } from '../api';

function Order() {
  const [goodsId, setGoodsId] = useState(0);
  const [quantity, setQuantity] = useState(1);
  const [message, setMessage] = useState('');

  const handleSubmit = async () => {
    try {
      const response = await createOrder({ goods_id: goodsId, quantity });
      setMessage(`订单创建成功，订单ID: ${response.order_id}`);
    } catch (error) {
      setMessage('订单创建失败');
    }
  };

  return (
    <div className="container mt-4">
      <h1>创建订单</h1>
      <input
        type="number"
        placeholder="商品ID"
        value={goodsId}
        onChange={(e) => setGoodsId(Number(e.target.value))}
        className="form-control mb-2"
      />
      <input
        type="number"
        placeholder="数量"
        value={quantity}
        onChange={(e) => setQuantity(Number(e.target.value))}
        className="form-control mb-2"
      />
      <button className="btn btn-primary" onClick={handleSubmit}>提交订单</button>
      {message && <p className="mt-3">{message}</p>}
    </div>
  );
}

export default Order;
