import React, { useEffect, useState } from "react";
import { fetchGoods, searchGoods, addToCart , getVisitorId } from "../api";
import Navbar from "../components/Navbar";
import Toast from "../components/Toast";
import { Link } from "react-router-dom";
import axios from "axios";
import { AD_SERVER_URL } from "../api";

function Home() {
  const [goods, setGoods] = useState<any[]>([]);
  const [searchQuery, setSearchQuery] = useState("");
  const [toastMessage, setToastMessage] = useState<string | null>(null);
  const [ad, setAd] = useState<any>(null); // 广告数据
  const [adLoading, setAdLoading] = useState(true); // 广告加载状态
  const [showAd, setShowAd] = useState(true); // 控制广告显示/隐藏

  useEffect(() => {
    fetchGoods().then((data) => setGoods(data));
    fetchAd();
  }, []);

  useEffect(() => {
    document.title = "USST小超市"; // 设置页面标题
  }, []);

const fetchAd = async () => {
    setAdLoading(true); // 开始加载广告
    try {
        const fingerprint = await getVisitorId(); // 确保获取到指纹
        const response = await axios.post(AD_SERVER_URL, {
            userName: null,
            age: null,
            gender: null,
            occupation: null,
            education_level: null,
            region: null,
            country: null,
            device: null,
            preference: null,
            fingerprint: fingerprint,
        });
        setAd(response.data);
    } catch (error) {
        console.error("无法获取广告:", error);
    } finally {
        setAdLoading(false); // 加载完成
    }
};

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

  const handleCloseAd = () => {
    setShowAd(false); // 隐藏广告
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

      {/* 浮动广告 */}
      {showAd && (
        <div
          className="fixed-bottom p-3"
          style={{
            width: "300px",
            right: "20px",
            bottom: "20px",
            zIndex: 1000,
            position: "fixed",
          }}
        >
          {adLoading ? (
            // 显示加载占位符
            <div
              style={{
                width: "100%",
                height: "200px",
                backgroundColor: "#f0f0f0",
                borderRadius: "8px",
                textAlign: "center",
                lineHeight: "200px",
                fontSize: "14px",
                color: "#999",
              }}
            >
              加载广告中...
            </div>
          ) : ad && ad.adImgUrl && ad.adUrl ? (
            // 显示广告
            <div style={{ position: "relative" }}>
              <button
                onClick={handleCloseAd}
                style={{
                  position: "absolute",
                  top: "10px",
                  right: "10px",
                  background: "red",
                  color: "white",
                  border: "none",
                  borderRadius: "50%",
                  width: "25px",
                  height: "25px",
                  fontSize: "14px",
                  cursor: "pointer",
                }}
              >
                ×
              </button>
              <a href={ad.adUrl} target="_blank" rel="noopener noreferrer">
                <img
                  src={ad.adImgUrl}
                  alt={ad.adName || "广告"}
                  style={{ width: "100%", borderRadius: "8px" }}
                />
              </a>
            </div>
          ) : (
            // 显示广告失败占位
            <div
              style={{
                width: "100%",
                height: "200px",
                backgroundColor: "#ffc0cb",
                borderRadius: "8px",
                textAlign: "center",
                lineHeight: "200px",
                fontSize: "14px",
                color: "#fff",
              }}
            >
              无法加载广告
            </div>
          )}
        </div>
      )}
    </div>
  );
}

export default Home;
