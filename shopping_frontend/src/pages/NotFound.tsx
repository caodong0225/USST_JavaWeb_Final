import React from 'react';
import { Link } from 'react-router-dom';

function NotFound() {
  return (
    <div className="not-found-container">
      <h1>404</h1>
      <p>抱歉，您访问的页面不存在。</p>
      <Link to="/" className="btn btn-primary mt-3">
        返回首页
      </Link>
    </div>
  );
}

export default NotFound;
