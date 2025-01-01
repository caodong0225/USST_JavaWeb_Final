import React, { useEffect, useState } from 'react';
import { NavLink } from 'react-router-dom';
import { getCart } from '../api';

function Navbar() {
  const [cartCount, setCartCount] = useState(0);

  // 获取购物车数量
  useEffect(() => {
    const fetchCartCount = async () => {
      try {
        const cartItems = await getCart();
        const totalQuantity = cartItems.reduce((sum: number, item: any) => sum + item.quantity, 0);
        setCartCount(totalQuantity);
      } catch (error) {
        console.error('Failed to fetch cart count:', error);
      }
    };

    fetchCartCount();
  }, []);

  return (
    <nav
      className="navbar navbar-expand-lg navbar-light bg-white shadow-sm mb-4"
      style={{ padding: '1rem' }}
    >
      <div className="container-fluid">
        <NavLink to="/" className="navbar-brand">
          USST小超市
        </NavLink>
        <ul className="navbar-nav me-auto mb-2 mb-lg-0">
          <li className="nav-item">
            <NavLink
              to="/"
              className={({ isActive }) =>
                `nav-link ${isActive ? 'active fw-bold' : ''}`
              }
            >
              首页
            </NavLink>
          </li>
          <li className="nav-item">
            <NavLink
              to="/order-query"
              className={({ isActive }) =>
                `nav-link ${isActive ? 'active fw-bold' : ''}`
              }
            >
              订单
            </NavLink>
          </li>
          <li className="nav-item">
            <NavLink
              to="/cart"
              className={({ isActive }) =>
                `nav-link d-flex align-items-center ${isActive ? 'active fw-bold' : ''}`
              }
            >
              购物车
              {cartCount > 0 && (
                <span
                  className="badge bg-primary text-white ms-2"
                  style={{ fontSize: '0.8rem' }}
                >
                  {cartCount}
                </span>
              )}
            </NavLink>
          </li>
        </ul>
      </div>
    </nav>
  );
}

export default Navbar;
