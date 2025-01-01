import axios from 'axios';

const API_URL = 'http://localhost:5000'; // 后端地址

export const fetchGoods = async () => {
  const response = await axios.get(`${API_URL}/goods`);
  return response.data;
};

export const fetchProductDetail = async (id: number) => {
  const response = await axios.get(`${API_URL}/goods/${id}`);
  return response.data;
};

export const createOrder = async (data: { goods_id: number; quantity: number }) => {
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

export const searchGoods = async (query: string) => {
    const response = await axios.get(`${API_URL}/goods/search?q=${query}`);
    return response.data;
  };
  
// 删除订单
export const deleteOrder = async (id: number) => {
  const response = await axios.delete(`${API_URL}/orders/${id}`);
  return response.data;
};
