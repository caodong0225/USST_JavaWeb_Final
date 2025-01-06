import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { fetchProductDetail, createOrder, addToCart , getVisitorId } from "../api"; // 引入 addToCart API
import Navbar from "../components/Navbar";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import { AD_SERVER_URL } from "../api";

function ProductDetail() {
  const { id } = useParams();
  const [product, setProduct] = useState<any>(null);
  const [quantity, setQuantity] = useState(1);
  const [message, setMessage] = useState("");

  const navigate = useNavigate();

  useEffect(() => {
    if (id) {
      fetchProductDetail(Number(id)).then((data) => {
        setProduct(data);
        recordInterest(data.category);
      });
    }
  }, [id]);

  const recordInterest = async (preference: string) => {
    try {
      const fingerprint = await getVisitorId(); // 确保获取到指纹
      await axios.post(AD_SERVER_URL, {
        userName: null,
        age: null,
        gender: null,
        occupation: null,
        education_level: null,
        region: null,
        country: null,
        device: null,
        preference: preference,
        fingerprint: fingerprint,
      });
    } catch (error) {
      console.error("记录用户兴趣失败:", error);
    }
  };

  const handleOrder = async () => {
    if (!product) return;

    try {
      const response = await createOrder({
        items: [
          {
            goods_id: product.id,
            quantity: quantity,
          },
        ],
      });
      navigate(`/payment?order_id=${response.order_id}`);
    } catch (error: any) {
      setMessage(error.response?.data?.error || "订单创建失败");
    }
  };

  const handleAddToCart = async () => {
    if (!product) return;

    try {
      const response = await addToCart(product.id, quantity); // 调用加入购物车的 API
      setMessage("商品已加入购物车");
    } catch (error: any) {
      setMessage(error.response?.data?.error || "加入购物车失败");
    }
  };

  const incrementQuantity = () => {
    if (quantity < product.stock) {
      setQuantity(quantity + 1);
    }
  };

  const decrementQuantity = () => {
    if (quantity > 1) {
      setQuantity(quantity - 1);
    }
  };

  if (!product) return <div>加载中...</div>;

  return (
    <>
      <Navbar />
      <div className="container mt-4">
        <h1>{product.name}</h1>
        <img
          src={product.image_path}
          alt={product.name}
          className="img-fluid mb-3"
        />
        <p>{product.description}</p>
        <p>价格: ¥{product.price}</p>
        <p>库存: {product.stock}</p>

        <div className="mt-4">
          <h4>操作</h4>
          <div className="input-group mb-3" style={{ maxWidth: "300px" }}>
            <button
              className="btn btn-outline-secondary"
              onClick={decrementQuantity}
              disabled={quantity <= 1}
            >
              -
            </button>
            <input
              type="number"
              className="form-control text-center"
              value={quantity}
              min={1}
              max={product.stock}
              onChange={(e) => setQuantity(Number(e.target.value))}
            />
            <button
              className="btn btn-outline-secondary"
              onClick={incrementQuantity}
              disabled={quantity >= product.stock}
            >
              +
            </button>
          </div>
          <div className="d-flex mt-2">
            <button
              className="btn btn-primary me-2 flex-grow-1"
              onClick={handleOrder}
            >
              提交订单
            </button>
            <button
              className="btn btn-success me-2 flex-grow-1"
              onClick={handleAddToCart}
            >
              加入购物车
            </button>
          </div>
          {message && <p className="text-success mt-3">{message}</p>}
        </div>
      </div>
    </>
  );
}

export default ProductDetail;
