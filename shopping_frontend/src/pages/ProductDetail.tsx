import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import { fetchProductDetail } from '../api';

function ProductDetail() {
  const { id } = useParams();
  const [product, setProduct] = useState<any>(null);

  useEffect(() => {
    if (id) {
      fetchProductDetail(Number(id)).then(data => setProduct(data));
    }
  }, [id]);

  if (!product) return <div>加载中...</div>;

  return (
    <div className="container mt-4">
      <h1>{product.name}</h1>
      <img src={product.image_path} alt={product.name} className="img-fluid" />
      <p>{product.description}</p>
      <p>价格: ¥{product.price}</p>
      <p>库存: {product.stock}</p>
    </div>
  );
}

export default ProductDetail;
