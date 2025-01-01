import React from 'react';
import { NavLink } from 'react-router-dom';

function Navbar() {
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
        </ul>
      </div>
    </nav>
  );
}

export default Navbar;
