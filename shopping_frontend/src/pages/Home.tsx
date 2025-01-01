import React, { useEffect, useState } from "react";
import { fetchGoods, searchGoods, addToCart } from "../api";
import Navbar from "../components/Navbar";
import Toast from "../components/Toast";
import { Link } from "react-router-dom";

function Home() {
  const [goods, setGoods] = useState<any[]>([]);
  const [searchQuery, setSearchQuery] = useState("");
  const [toastMessage, setToastMessage] = useState<string | null>(null);

  useEffect(() => {
    fetchGoods().then((data) => setGoods(data));
  }, []);

  const handleSearch = async () => {
    if (!searchQuery.trim()) {
      fetchGoods().then((data) => setGoods(data));
      return;
    }
    try {
      const results = await searchGoods(searchQuery);
      setGoods(results);
    } catch (error) {
      setGoods([]);
    }
  };

  const handleAddToCart = async (goodsId: number) => {
    try {
      await addToCart(goodsId, 1);
      setToastMessage("商品已加入购物车");
    } catch (error) {
      setToastMessage("加入购物车失败");
    }
  };

  return (
    <div>
      <Navbar />
      <div className="container mt-4">
        <div className="d-flex justify-content-between align-items-center mb-4">
          <h1>商品列表</h1>
          <div className="input-group" style={{ maxWidth: "400px" }}>
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
                  <img
                    src={item.image_path}
                    className="card-img-top"
                    alt={item.name}
                  />
                  <div className="card-body">
                    <h5 className="card-title">{item.name}</h5>
                    <div className="d-flex justify-content-between">
                      <p className="card-text">价格: ¥{item.price}</p>
                      <p className="card-text stock">
                        库存: {item.stock > 0 ? item.stock : "已售罄"}
                      </p>
                    </div>
                    <div className="d-flex">
                      <Link
                        to={`/product/${item.id}`}
                        className="btn btn-primary flex-grow-1 me-2"
                      >
                        查看详情
                      </Link>
                      <button
                        className="btn btn-success flex-grow-1"
                        onClick={() => handleAddToCart(item.id)}
                      >
                        加入购物车
                      </button>
                    </div>
                  </div>
                </div>
              </div>
            ))
          ) : (
            <p className="text-center">未找到匹配的商品</p>
          )}
        </div>
      </div>

      {/* Toast 显示 */}
      {toastMessage && (
        <Toast message={toastMessage} onClose={() => setToastMessage(null)} />
      )}
    </div>
  );
}

export default Home;
