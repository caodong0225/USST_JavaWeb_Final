import axios from 'axios';
import FingerprintJS from '@fingerprintjs/fingerprintjs';
let visitorId: string = "";

export const API_URL = 'http://10.100.164.36:5000'; // 后端地址

export const AD_SERVER_URL = "http://10.100.164.38:8080/user-predict/get-preferences";

// 创建一个初始化的 Promise
const fingerprintPromise = FingerprintJS.load().then(fp =>
    fp.get().then(result => {
        visitorId = result.visitorId;
        console.log("指纹是:", visitorId);
        return visitorId; // 返回指纹
    })
);

export const getVisitorId = async () => {
    if (visitorId) {
        return visitorId; // 如果已经生成，直接返回
    }
    return fingerprintPromise; // 如果还未生成，等待 Promise
};

// 获取商品列表
export const fetchGoods = async () => {
  const response = await axios.get(`${API_URL}/goods`);
  return response.data;
};

// 获取商品详情
export const fetchProductDetail = async (id: number) => {
  const response = await axios.get(`${API_URL}/goods/${id}`);
  return response.data;
};

// 创建订单
export const createOrder = async (data: { items: { goods_id: number; quantity: number }[] }) => {
  const response = await axios.post(`${API_URL}/orders`, data);
  return response.data;
};

// 获取所有订单
export const fetchOrders = async () => {
  const response = await axios.get(`${API_URL}/orders/`);
  return response.data;
};

// 按订单号获取订单
export const fetchOrderById = async (id: number) => {
  const response = await axios.get(`${API_URL}/orders/${id}`);
  return response.data;
};

// 搜索商品
export const searchGoods = async (query: string) => {
  const response = await axios.get(`${API_URL}/goods/search?q=${query}`);
  return response.data;
};

// 删除订单
export const deleteOrder = async (id: number) => {
  const response = await axios.delete(`${API_URL}/orders/${id}`);
  return response.data;
};

// 购物车相关 API
// 获取购物车内容
export const getCart = async () => {
  const response = await axios.get(`${API_URL}/cart`);
  return response.data;
};

// 添加商品到购物车
export const addToCart = async (goods_id: number, quantity: number = 1) => {
  const response = await axios.post(`${API_URL}/cart`, { goods_id, quantity });
  return response.data;
};

// 更新购物车商品数量
export const updateCartItem = async (cart_id: number, quantity: number) => {
  const response = await axios.put(`${API_URL}/cart/${cart_id}`, { quantity });
  return response.data;
};

// 删除购物车商品
export const deleteCartItem = async (cart_id: number) => {
  const response = await axios.delete(`${API_URL}/cart/${cart_id}`);
  return response.data;
};

// 清空购物车
export const clearCart = async () => {
  const response = await axios.delete(`${API_URL}/cart`);
  return response.data;
};

// 提交购物车为订单
export const createOrderFromCart = async () => {
  const response = await axios.post(`${API_URL}/cart/orders`);
  return response.data;
};
