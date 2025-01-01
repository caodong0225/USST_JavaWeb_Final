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

export const fetchOrder = async (id: number) => {
  const response = await axios.get(`${API_URL}/orders/query/${id}`);
  return response.data;
};
