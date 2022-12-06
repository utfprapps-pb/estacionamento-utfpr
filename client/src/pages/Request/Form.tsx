import { useEffect, useState } from "react";
import RequestService from "../../services/RequestService";
import PropTypes from 'prop-types';
// react-bootstrap components
import { Button, Card, Form, Container, Row, Col } from "react-bootstrap";

const RequestFormPage = (props: any) => {
  const { handleSubmit } = props;
  const [brands, setBrands]: any = useState([]);
  const [selectedBrand, setSelectedBrand]: any = useState(1);
  const [models, setModels]: any = useState([]);

  useEffect(() => {
    RequestService.getModels(selectedBrand).then((response: any) => {
      setModels(response.data);
    });
  }, [selectedBrand]);

  useEffect(() => {
    RequestService.getBrands().then((response: any) => {
      setBrands(response.data);
    });
  }, []);

  return (
    <>
      <Container fluid>
        <Row>
          <Col md="10">
            <Card>
              <Card.Header>
                <Card.Title as="h4">Solicitação de Adesivo</Card.Title>
              </Card.Header>
              <Card.Body>
                <Form onSubmit={handleSubmit}>
                  <Row>
                    <Col className="pr-1" md="5">
                      <Form.Group>
                        <label>Nome</label>
                        <Form.Control
                          name="name"
                          placeholder="Nome da solicitação"
                          type="text"
                        ></Form.Control>
                      </Form.Group>
                    </Col>
                    <Col className="px-1" md="3">
                      <Form.Group>
                        <label>Marca</label>
                        <Form.Select
                          name="brand"
                          onChange={(e) => setSelectedBrand(e.target.value)}
                        >
                          {brands.map((brand: any) => (
                            <option value={brand.value}>{brand.label}</option>
                          ))}
                        </Form.Select>
                      </Form.Group>
                    </Col>
                    <Col className="pl-1" md="4">
                      <Form.Group>
                        <label>Modelo</label>
                        <Form.Select name="model">
                          {models.map((model: any) => (
                            <option value={model.value}>{model.label}</option>
                          ))}
                        </Form.Select>
                      </Form.Group>
                    </Col>
                  </Row>
                  <Row>
                    <Col className="pr-1" md="6">
                      <Form.Group>
                        <label>Cor</label>
                        <Form.Control
                          name="Color"
                          placeholder="Cor do veículo"
                          type="text"
                        ></Form.Control>
                      </Form.Group>
                    </Col>
                    <Col className="pl-1" md="6">
                      <Form.Group>
                        <label>Placa</label>
                        <Form.Control
                          name="licensePlate"
                          placeholder="Placa do veículo"
                          type="text"
                        ></Form.Control>
                      </Form.Group>
                    </Col>
                  </Row>
                  <Row>
                    <Col className="pl-1" md="12">
                      <Form.Group>
                        <label>Documentos</label>
                        <Form.Control name="files" type="file"></Form.Control>
                      </Form.Group>
                    </Col>
                  </Row>
                  <Row>
                    <Col className="pb-2" md="12">
                      <Form.Group>
                        <label>Observação</label>
                        <Form.Control
                          cols={80}
                          name="requesterMessage"
                          placeholder="Descreva uma observação para solicitação se necessário."
                          rows={4}
                          as="textarea"
                        ></Form.Control>
                      </Form.Group>
                    </Col>
                  </Row>
                  <Button
                    className="btn-fill pull-right"
                    type="submit"
                  >
                    Solicitar Adesivo
                  </Button>
                  <div className="clearfix"></div>
                </Form>
              </Card.Body>
            </Card>
          </Col>
        </Row>
      </Container>
    </>
  );
}

RequestFormPage.propTypes = {
  handleSubmit: PropTypes.func.isRequired,
}

export default RequestFormPage;
