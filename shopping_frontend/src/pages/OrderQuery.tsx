import React, { useEffect, useState } from 'react';
import { fetchOrders, fetchOrderById, deleteOrder } from '../api';
import Navbar from '../components/Navbar';

function OrderQuery() {
  const [orders, setOrders] = useState<any[]>([]);
  const [searchQuery, setSearchQuery] = useState('');
  const [message, setMessage] = useState('');

  // 获取所有订单
  useEffect(() => {
    fetchOrders().then(data => setOrders(data));
  }, []);

  // 搜索订单
  const handleSearch = async () => {
    if (!searchQuery.trim()) {
      fetchOrders().then(data => setOrders(data));
      return;
    }
    try {
      const order = await fetchOrderById(Number(searchQuery));
      setOrders([order]);
    } catch (error) {
      setOrders([]);
    }
  };

  // 删除订单
  const handleDelete = async (orderId: number) => {
    try {
      await deleteOrder(orderId);
      setOrders(orders.filter(order => order.id !== orderId)); // 删除成功后更新订单列表
      setMessage(`订单 ${orderId} 已成功删除`);
    } catch (error: any) {
      setMessage(error.response?.data?.error || '删除订单失败');
    }
  };

  return (
    <div>
      <Navbar />
      <div className="container mt-4">
        <div className="d-flex justify-content-between align-items-center mb-4">
          <h1>订单列表</h1>
          <div className="input-group" style={{ maxWidth: '400px' }}>
            <input
              type="text"
              className="form-control"
              placeholder="按订单号搜索订单"
              value={searchQuery}
              onChange={(e) => setSearchQuery(e.target.value)}
            />
            <button className="btn btn-primary" onClick={handleSearch}>
              搜索
            </button>
          </div>
        </div>

        {message && <p className="text-success">{message}</p>}

        {/* 订单列表 */}
        <div className="row">
          {orders.length > 0 ? (
            orders.map((order) => (
              <div className="col-md-6 mb-4" key={order.id}>
                <div className="card">
                  <div className="card-body">
                    <h5 className="card-title">订单ID: {order.id}</h5>
                    <p className="card-text">商品ID: {order.goods_id}</p>
                    <p className="card-text">数量: {order.quantity}</p>
                    <p className="card-text">总价: ¥{order.total_price}</p>
                    <p className="card-text">下单时间: {order.order_time}</p>
                    {/* 删除按钮 */}
                    <button
                      className="btn btn-danger w-100 mt-3"
                      onClick={() => handleDelete(order.id)}
                    >
                      删除订单
                    </button>
                  </div>
                </div>
              </div>
            ))
          ) : (
            <p className="text-center">未找到匹配的订单</p>
          )}
        </div>
      </div>
    </div>
  );
}

export default OrderQuery;
