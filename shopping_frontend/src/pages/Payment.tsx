import React, { useEffect, useState } from "react";
import { useSearchParams, useNavigate } from "react-router-dom";
import axios from "axios";
import Navbar from "../components/Navbar";
import { API_URL } from "../api";

const Payment = () => {
  const [searchParams] = useSearchParams();
  const [orderDetails, setOrderDetails] = useState<any>(null);
  const [loading, setLoading] = useState(true);
  const [isPaymentSuccess, setIsPaymentSuccess] = useState<boolean | null>(
    null
  );
  const [message, setMessage] = useState("");
  const navigate = useNavigate();

  const orderId = searchParams.get("order_id");
  const signature = searchParams.get("signature");

  useEffect(() => {
    if (orderId && signature) {
      // 验证支付结果
      verifyPayment(orderId, signature);
    } else if (orderId) {
      // 获取订单详情
      fetchOrderDetails(orderId);
    }
  }, [orderId, signature]);

  const fetchOrderDetails = async (orderId: string) => {
    try {
      const response = await axios.get(`${API_URL}/orders/${orderId}`);
      setOrderDetails(response.data);
      setLoading(false);
    } catch (error) {
      console.error("无法获取订单详情:", error);
      setMessage("无法获取订单详情");
      setLoading(false);
    }
  };

  const verifyPayment = async (orderId: string, signature: string) => {
    try {
      const response = await axios.post(`${API_URL}/alipay/check`, {
        order_id: orderId,
        signature,
      });
      setMessage(response.data.message);
      setIsPaymentSuccess(response.data.message === "支付成功");
      setLoading(false);
    } catch (error) {
      console.error("支付验证失败:", error);
      setMessage("支付验证失败，请重试");
      setIsPaymentSuccess(false);
      setLoading(false);
    }
  };

  const handlePayment = async () => {
    try {
      const response = await axios.post(`${API_URL}/alipay/create_order`, {
        order_id: orderId,
      });
      window.location.href = response.data.payment_url; // 跳转到支付宝支付页面
    } catch (error) {
      console.error("无法生成支付链接:", error);
      setMessage("无法生成支付链接，请重试");
    }
  };

  if (loading) {
    return <div className="text-center mt-5">加载中...</div>;
  }

  if (isPaymentSuccess !== null) {
    const total_amount = searchParams.get("total_amount");
    return (
    <>
    <Navbar />
      <div className="container mt-5 text-center">
        {isPaymentSuccess ? (
          <div>
            <div className="mb-4">
              <i className="bi bi-check-circle-fill text-success" style={{ fontSize: "4rem" }}></i>
            </div>
            <h2>✅</h2>
            <p>订单号:{orderId}</p>
            <p>支付金额:{total_amount}</p>
            <p>{message}</p>
            <button
              className="btn btn-primary mt-3"
              onClick={() => navigate("/")}
            >
              返回首页
            </button>
          </div>
        ) : (
          <div>
            <div className="mb-4">
              <i className="bi bi-x-circle-fill text-danger" style={{ fontSize: "4rem" }}></i>
            </div>
            <h2>❌</h2>
            <p>订单号:{orderId}</p>
            <p>支付金额:{total_amount}</p>
            <p>{message}</p>
            <button
              className="btn btn-danger mt-3"
              onClick={() => navigate("/payment?order_id=" + orderId)}
            >
              重新支付
            </button>
            <button
              className="btn btn-secondary mt-3 ms-2"
              onClick={() => navigate("/")}
            >
              返回首页
            </button>
          </div>
        )}
      </div>
      </>
    );
  }

  return (
    <>
    <Navbar />
    <div className="container mt-4">
      {orderDetails ? (
        <div>
          <h2>订单详情</h2>
          <p>订单号: {orderDetails.id}</p>
          <p>总金额: ¥{orderDetails.total_amount}</p>
          <ul>
            {orderDetails.items.map((item: any) => (
              <li key={item.goods_id}>
                {item.goods_name} x {item.quantity} - ¥{item.total_price}
              </li>
            ))}
          </ul>
          <button className="btn btn-primary" onClick={handlePayment}>
            支付
          </button>
        </div>
      ) : (
        <div>等待验证支付结果...</div>
      )}
    </div>
    </>
  );
};

export default Payment;
