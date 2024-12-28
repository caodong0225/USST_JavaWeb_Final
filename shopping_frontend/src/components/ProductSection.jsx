import React from "react";
import { Card, Button, Container, Row, Col } from "react-bootstrap";

function ProductSection() {
  const products = [
    { id: 1, title: "产品 1", description: "这是产品 1 的描述", price: "¥99", image: "https://via.placeholder.com/200" },
    { id: 2, title: "产品 2", description: "这是产品 2 的描述", price: "¥199", image: "https://via.placeholder.com/200" },
    { id: 3, title: "产品 3", description: "这是产品 3 的描述", price: "¥299", image: "https://via.placeholder.com/200" },
  ];

  return (
    <Container className="my-5" id="products">
      <h2 className="text-center mb-4">热销产品</h2>
      <Row>
        {products.map((product) => (
          <Col key={product.id} md={4} className="mb-4">
            <Card>
              <Card.Img variant="top" src={product.image} />
              <Card.Body>
                <Card.Title>{product.title}</Card.Title>
                <Card.Text>{product.description}</Card.Text>
                <h5>{product.price}</h5>
                <Button variant="primary">购买</Button>
              </Card.Body>
            </Card>
          </Col>
        ))}
      </Row>
    </Container>
  );
}

export default ProductSection;
