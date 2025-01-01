import React, { useEffect, useState } from 'react';
import { fetchGoods, searchGoods } from '../api';
import { Link } from 'react-router-dom';

function Home() {
  const [goods, setGoods] = useState<any[]>([]);
  const [searchQuery, setSearchQuery] = useState('');

  useEffect(() => {
    fetchGoods().then(data => setGoods(data));
  }, []);

  const handleSearch = async () => {
    if (!searchQuery.trim()) {
      fetchGoods().then(data => setGoods(data)); // 如果搜索为空，返回所有商品
      return;
    }
    try {
      const results = await searchGoods(searchQuery);
      setGoods(results);
    } catch (error) {
      setGoods([]); // 如果没有结果，清空商品列表
    }
  };

  return (
    <div className="container mt-4">
      <div className="d-flex justify-content-between align-items-center mb-4">
        <h1>商品列表</h1>
        <div className="input-group" style={{ maxWidth: '400px' }}>
          <input
            type="text"
            className="form-control"
            placeholder="搜索商品"
            value={searchQuery}
            onChange={(e) => setSearchQuery(e.target.value)}
          />
          <button className="btn btn-primary" onClick={handleSearch}>
            搜索
          </button>
        </div>
      </div>
      <div className="row">
        {goods.length > 0 ? (
          goods.map((item) => (
            <div className="col-md-4 mb-4" key={item.id}>
              <div className="card">
                <img src={item.image_path} className="card-img-top" alt={item.name} />
                <div className="card-body">
                  <h5 className="card-title">{item.name}</h5>
                  <p className="card-text">价格: ¥{item.price}</p>
                  <Link to={`/product/${item.id}`} className="btn btn-primary">查看详情</Link>
                </div>
              </div>
            </div>
          ))
        ) : (
          <p className="text-center">未找到匹配的商品</p>
        )}
      </div>
    </div>
  );
}

export default Home;
