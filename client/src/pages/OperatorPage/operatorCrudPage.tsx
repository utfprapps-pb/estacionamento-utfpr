import React, { useEffect, useState } from "react";
import { Form, Button, Container, Row, Col } from "react-bootstrap";
import { useLocation, useNavigate } from "react-router-dom";
import OperatorService from "./operatorService";

const OperatorCrud = () => {
  const [formData, setFormData] = useState({
    name: "",
    username: "",
    password: "",
    surename: "",
    email: "",
    address: "",
    complement: "",
    zipcode: "",
    neighborhood: "",
    city: "",
    documentFileName: "",
  });

  const location = useLocation();

  const idUsuario: string = location.pathname.split("/").pop() ?? "";
  const navigate = useNavigate();

  useEffect(() => {
    OperatorService.getOperator(idUsuario)
    .then(response => {
        setFormData({
          name: response.data.name,
          username: response.data.username,
          password: response.data.password,
          surename: response.data.surename,
          email: response.data.email,
          address: response.data.address,
          complement: response.data.complement,
          zipcode: response.data.zipcode,
          neighborhood: response.data.neighborhood,
          city: response.data.city,
          documentFileName: response.data.documentFileName,
        });
      });
  }, [idUsuario]);

  const handleInputChange = (event: any) => {
    const { name, value } = event.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleDelete = () => {
    OperatorService.deletar(idUsuario).then((data) => {
      navigate("/usuarios");
    });
  }

  const handleSubmit = (event: any) => {
    event.preventDefault();

    OperatorService.save({
      id: idUsuario,
      ...formData,
    }).then((data) => {
      navigate("/usuarios");
    });
  };

  return (
    <Container>
      <Form onSubmit={handleSubmit}>
        <Row>
          <Col>
            <Form.Group>
              <Form.Label htmlFor="name">Nome:</Form.Label>
              <Form.Control
                type="text"
                id="name"
                name="name"
                value={formData.name}
                onChange={handleInputChange}
              />
            </Form.Group>
          </Col>
          <Col>
            <Form.Group>
              <Form.Label htmlFor="username">Username:</Form.Label>
              <Form.Control
                type="text"
                id="username"
                name="username"
                value={formData.username}
                onChange={handleInputChange}
              />
            </Form.Group>
          </Col>
          <Col>
            <Form.Group>
              <Form.Label htmlFor="password">Senha:</Form.Label>
              <Form.Control
                type="password"
                id="password"
                name="password"
                value={formData.password}
                onChange={handleInputChange}
              />
            </Form.Group>
          </Col>
        </Row>
        <Row>
          <Col>
            <Form.Group>
              <Form.Label htmlFor="surename">Sobrenome:</Form.Label>
              <Form.Control
                type="text"
                id="surename"
                name="surename"
                value={formData.surename}
                onChange={handleInputChange}
              />
            </Form.Group>
          </Col>

          <Col>
            <Form.Group>
              <Form.Label htmlFor="email">Email:</Form.Label>
              <Form.Control
                type="email"
                id="email"
                name="email"
                value={formData.email}
                onChange={handleInputChange}
              />
            </Form.Group>
          </Col>
        </Row>

        <Row>
          <Col>
            <Form.Group>
              <Form.Label htmlFor="address">Endereço:</Form.Label>
              <Form.Control
                type="text"
                id="address"
                name="address"
                value={formData.address}
                onChange={handleInputChange}
              />
            </Form.Group>
          </Col>

          <Col>
            <Form.Group>
              <Form.Label htmlFor="complement">Complemento:</Form.Label>
              <Form.Control
                type="text"
                id="complement"
                name="complement"
                value={formData.complement}
                onChange={handleInputChange}
              />
            </Form.Group>
          </Col>

          <Col>
            <Form.Group>
              <Form.Label htmlFor="zipcode">CEP:</Form.Label>
              <Form.Control
                type="text"
                id="zipcode"
                name="zipcode"
                value={formData.zipcode}
                onChange={handleInputChange}
              />
            </Form.Group>
          </Col>
        </Row>

        <Form.Group>
          <Form.Label htmlFor="neighborhood">Bairro:</Form.Label>
          <Form.Control
            type="text"
            id="neighborhood"
            name="neighborhood"
            value={formData.neighborhood}
            onChange={handleInputChange}
          />
        </Form.Group>

        <Form.Group>
          <Form.Label htmlFor="city">Cidade:</Form.Label>
          <Form.Control
            type="text"
            id="city"
            name="city"
            value={formData.city}
            onChange={handleInputChange}
          />
        </Form.Group>

        <Form.Group>
          <Form.Label htmlFor="documentFileName">CPF:</Form.Label>
          <Form.Control
            type="text"
            id="documentFileName"
            name="documentFileName"
            value={formData.documentFileName}
            onChange={handleInputChange}
          />
        </Form.Group>

        <Button type="submit">Salvar</Button>
        <Button variant="danger" onClick={handleDelete}>
          Deletar
        </Button>
      </Form>
    </Container>
  );
};

export default OperatorCrud;
