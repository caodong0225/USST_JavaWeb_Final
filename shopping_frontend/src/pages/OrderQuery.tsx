import React, { useEffect, useState } from 'react';
import { fetchOrders, fetchOrderById } from '../api';

function OrderQuery() {
  const [orders, setOrders] = useState<any[]>([]);
  const [searchQuery, setSearchQuery] = useState('');

  // 获取所有订单
  useEffect(() => {
    fetchOrders().then(data => setOrders(data));
  }, []);

  // 搜索订单
  const handleSearch = async () => {
    if (!searchQuery.trim()) {
      // 如果搜索框为空，重新获取所有订单
      fetchOrders().then(data => setOrders(data));
      return;
    }
    try {
      const order = await fetchOrderById(Number(searchQuery));
      setOrders([order]); // 将结果作为单个数组元素展示
    } catch (error) {
      setOrders([]); // 如果订单不存在，清空列表
    }
  };

  return (
    <div>
      {/* 搜索框 */}
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
