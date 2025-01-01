import React, { useEffect, useState } from 'react';

interface ToastProps {
  message: string;
  onClose: () => void;
}

const Toast: React.FC<ToastProps> = ({ message, onClose }) => {
  const [visible, setVisible] = useState(false); // 初始状态为不可见
  const [animateOut, setAnimateOut] = useState(false);

  useEffect(() => {
    // 短暂延迟后设置为可见状态，触发进入动画
    const enterTimeout = setTimeout(() => setVisible(true), 50);

    // 设置退出动画和销毁定时器
    const exitTimeout = setTimeout(() => setAnimateOut(true), 4500); // 4.5 秒后触发退出动画
    const cleanupTimeout = setTimeout(() => onClose(), 5000); // 5 秒后完全移除组件

    return () => {
      clearTimeout(enterTimeout);
      clearTimeout(exitTimeout);
      clearTimeout(cleanupTimeout);
    };
  }, [onClose]);

  return (
    <div
      className={`toast-container ${visible ? 'toast-enter' : ''} ${animateOut ? 'toast-exit' : ''}`}
      style={{
        position: 'fixed',
        bottom: '20px',
        right: '20px',
        zIndex: 1050,
        maxWidth: '300px',
      }}
    >
      <div className="toast-body bg-success text-white p-3 rounded">
        {message}
      </div>
    </div>
  );
};

export default Toast;
