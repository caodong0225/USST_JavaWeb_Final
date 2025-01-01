import React, { useEffect, useState } from "react";
import { getCart, deleteCartItem, createOrderFromCart } from "../api";
import Navbar from "../components/Navbar";

function Cart() {
  const [cart, setCart] = useState<any[]>([]);
  const [message, setMessage] = useState("");

  useEffect(() => {
    getCart().then(setCart);
  }, []);

  const handleDelete = async (cartId: number) => {
    try {
      await deleteCartItem(cartId);
      setCart(cart.filter((item) => item.id !== cartId));
      setMessage("商品已从购物车移除");
    } catch {
      setMessage("移除失败");
    }
  };

  const handleSubmitOrder = async () => {
    try {
      const response = await createOrderFromCart();
      setCart([]); // 清空前端购物车
      setMessage(`订单创建成功，订单ID: ${response.order_id}`);
    } catch (error) {
      setMessage("订单创建失败，请重试");
    }
  };

  const totalPrice = cart.reduce((acc, item) => acc + item.quantity * item.price, 0);

  return (
    <div>
      <Navbar />
      <div className="container mt-4">
        <h1>购物车</h1>
        {message && <p className="text-success">{message}</p>}
        <div>
          {cart.map((item) => (
            <div key={item.id} className="d-flex justify-content-between align-items-center mb-3">
              <span>{item.name}</span>
              <span>数量: {item.quantity}</span>
              <button className="btn btn-danger" onClick={() => handleDelete(item.id)}>
                移除
              </button>
            </div>
          ))}
          <h3>总价: ¥{totalPrice}</h3>
          <button className="btn btn-primary" onClick={handleSubmitOrder}>
            提交订单
          </button>
        </div>
      </div>
    </div>
  );
}

export default Cart;
