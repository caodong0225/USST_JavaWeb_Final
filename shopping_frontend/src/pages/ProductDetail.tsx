import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import { fetchProductDetail, createOrder } from '../api';
import Navbar from "../components/Navbar";

function ProductDetail() {
  const { id } = useParams();
  const [product, setProduct] = useState<any>(null);
  const [quantity, setQuantity] = useState(1);
  const [message, setMessage] = useState('');

  useEffect(() => {
    if (id) {
      fetchProductDetail(Number(id)).then(data => setProduct(data));
    }
  }, [id]);

  const handleOrder = async () => {
    if (!product) return;

    try {
      const response = await createOrder({ goods_id: product.id, quantity });
      setMessage(`订单创建成功，订单ID: ${response.order_id}`);
    } catch (error: any) {
      setMessage(error.response?.data?.error || '订单创建失败');
    }
  };

  if (!product) return <div>加载中...</div>;

  return (
    <>
    <Navbar />
    <div className="container mt-4">
      <h1>{product.name}</h1>
      <img src={product.image_path} alt={product.name} className="img-fluid mb-3" />
      <p>{product.description}</p>
      <p>价格: ¥{product.price}</p>
      <p>库存: {product.stock}</p>

      <div className="mt-4">
        <h4>下订单</h4>
        <div className="input-group mb-3" style={{ maxWidth: '300px' }}>
          <input
            type="number"
            className="form-control"
            value={quantity}
            min={1}
            max={product.stock}
            onChange={(e) => setQuantity(Number(e.target.value))}
          />
          <button className="btn btn-primary" onClick={handleOrder}>
            提交订单
          </button>
        </div>
        {message && <p className="text-success">{message}</p>}
      </div>
    </div>
    </>
  );
}

export default ProductDetail;
