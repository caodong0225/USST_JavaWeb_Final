import React from "react";
import { Carousel } from "react-bootstrap";

function HeroSection() {
  return (
    <Carousel>
      <Carousel.Item>
        <img
          className="d-block w-100"
          src="https://via.placeholder.com/1200x400"
          alt="First slide"
        />
        <Carousel.Caption>
          <h3>欢迎来到我们的电商网站</h3>
          <p>探索最新的产品和优惠！</p>
        </Carousel.Caption>
      </Carousel.Item>
      <Carousel.Item>
        <img
          className="d-block w-100"
          src="https://via.placeholder.com/1200x400"
          alt="Second slide"
        />
        <Carousel.Caption>
          <h3>超值优惠</h3>
          <p>抓住限时折扣！</p>
        </Carousel.Caption>
      </Carousel.Item>
    </Carousel>
  );
}

export default HeroSection;
