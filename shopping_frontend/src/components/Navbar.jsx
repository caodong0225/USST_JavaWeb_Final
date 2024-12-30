import React from "react";
import { Container, Navbar, Nav, Form, FormControl, Button } from "react-bootstrap";

function Navigation() {
  return (
    <Navbar bg="dark" variant="dark" expand="lg">
      <Container>
        <Navbar.Brand href="#">USST-Onlineshop</Navbar.Brand>
        <Navbar.Toggle aria-controls="basic-navbar-nav" />
        <Navbar.Collapse id="basic-navbar-nav">
          <Nav className="me-auto">
            <Nav.Link href="/">首页</Nav.Link>
            <Nav.Link href="/products">产品</Nav.Link>
          </Nav>
          <Form className="d-flex">
            <FormControl
              type="search"
              placeholder="搜索商品"
              className="me-2"
              aria-label="搜索"
            />
            <Button variant="outline-light">搜索</Button>
          </Form>
        </Navbar.Collapse>
      </Container>
    </Navbar>
  );
}

export default Navigation;
