import React, { useEffect, useState } from 'react';
import { fetchGoods } from '../api';
import { Link } from 'react-router-dom';

function Home() {
  const [goods, setGoods] = useState<any[]>([]);

  useEffect(() => {
    fetchGoods().then(data => setGoods(data));
  }, []);

  return (
    <div className="container mt-4">
      <h1>商品列表</h1>
      <div className="row">
        {goods.map((item) => (
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
        ))}
      </div>
    </div>
  );
}

export default Home;
