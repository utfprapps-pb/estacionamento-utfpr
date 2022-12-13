import { useContext, useEffect, useState } from "react";
import RequestService from "../../services/RequestService";
import PropTypes from 'prop-types';
// react-bootstrap components
import { Button, Card, Form, Container, Row, Col } from "react-bootstrap";
import { AuthContext } from "../../context/AuthContext";
import { useNavigate } from "react-router-dom";

const RequestFormPage = (props: any) => {
  const { handleSubmit } = props;
  const [brands, setBrands]: any = useState([]);
  const [models, setModels]: any = useState([]);
  const [years, setYears]: any = useState([]);
  const [formData, setFormData] = useState({
    name: "",
    vehicle: {
      brand: 0,
      model: 0,
      year: "",
      licensePlate: "",
      color: "",
    },
    requesterMessage: ""
  });
  const navigate = useNavigate();
  const requestId: string = location.pathname.split("/").pop() ?? "";
  const { authenticatedUser } = useContext(AuthContext);
  var hasAdminPermission = false;

  useEffect(() => {
      RequestService.getModels(formData.vehicle.brand).then((response: any) => {
          setModels(response.data.carBrandModelDTO);
          setYears(response.data.anos);
      });
  }, [formData.vehicle.brand]);

  
  function checkAdminPermission(){
    hasAdminPermission = authenticatedUser?.authorities.some(it => it.authority == "ROLE_ADMIN") ?? false;
  }

  const handleInputChange = (event: any) => {
    console.log(event.target);
    const { name, value } = event.target;
    if (name != 'name' && name != 'requesterMessage') {
      const vehicle = {...formData.vehicle, [name]: value};
      setFormData({ ...formData, ['vehicle']: vehicle });
    } else {
      setFormData({ ...formData, [name]: value });
    }
  };

  const backClickHandler = () => {
    navigate('/solicitacoes')
  }

  useEffect(() => {
    console.log(requestId);
    if (requestId != '0') {
    RequestService.getRequest(requestId)
      .then((response: any) => {
        const request = {
          id: response.data.id,
          name: response.data.name,
          vehicle: {
            brand: response.data.vehicle.brand,
            model: response.data.vehicle.model,
            year: response.data.vehicle.year,
            licensePlate: response.data.vehicle.licensePlate,
            color: response.data.vehicle.color,
          },
          requesterMessage: response.data.requesterMessage
        };
        console.log(request);
        setFormData(request);
      });
    } else {
      const vehicle = {
        brand: 1,
        model: 1,
        year: "",
        licensePlate: "",
        color: "",
      }
      setFormData({...formData, ['vehicle']: vehicle})
    }
  }, [requestId]);


  useEffect(() => {
    checkAdminPermission();

    RequestService.getBrands().then((response: any) => {
      setBrands(response.data);
    });

  }, []);

    



  return (
    <>
      <Container fluid>
        <Row>
          <Col md="12">
            <Card>
              <Card.Header>
                <Card.Title as="h4">Solicitação de Adesivo</Card.Title>
              </Card.Header>
              <Card.Body>
                <Form onSubmit={(values) => handleSubmit(values, requestId)}>
                  <Row>
                    <Col className="pr-1" md="5">
                      <Form.Group>
                        <label>Nome</label>
                        <Form.Control
                          name="name"
                          placeholder="Nome da solicitação"
                          type="text"
                          value={formData.name}
                          onChange={handleInputChange}
                        ></Form.Control>
                      </Form.Group>
                    </Col>
                    <Col className="px-1" md="3">
                      <Form.Group>
                        <label>Marca</label>
                        <Form.Select
                          name="brand"
                          onChange={handleInputChange}
                          value={formData.vehicle.brand}
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
                        <Form.Select 
                          name="model" 
                          value={formData.vehicle.model}
                          onChange={handleInputChange}>
                          {models.map((model: any) => (
                              <option value={model.value}>{model.label}</option>
                            ))}
                        </Form.Select>
                      </Form.Group>
                    </Col>
                  </Row>
                  <Row>
                    <Col className="pl-1" md="5">
                      <Form.Group>
                        <label>Placa</label>
                        <Form.Control
                          name="licensePlate"
                          placeholder="Placa do veículo"
                          type="text"
                          value={formData.vehicle.licensePlate}
                          onChange={handleInputChange} />
                      </Form.Group>
                    </Col>
                    <Col className="pr-1" md="3">
                      <Form.Group>
                        <label>Cor</label>
                        <Form.Control
                          name="color"
                          placeholder="Cor do veículo"
                          type="text"
                          value={formData.vehicle.color}
                          onChange={handleInputChange}
                        ></Form.Control>
                      </Form.Group>
                    </Col>
                    <Col className="pl-1" md="4">
                      <Form.Group>
                        <label>Ano</label>
                        <Form.Select 
                          name="year"
                          value={formData.vehicle.year}
                          onChange={handleInputChange}>
                          {years.map((year: any) => (
                              <option value={year.value}>{year.label}</option>
                            ))}
                        </Form.Select>
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
                    <Col md="12">
                      <Form.Group>
                        <label>Observação</label>
                        <Form.Control
                          cols={80}
                          name="requesterMessage"
                          placeholder="Descreva uma observação para solicitação se necessário."
                          rows={4}
                          as="textarea"
                          value={formData.requesterMessage}
                          onChange={handleInputChange}
                        ></Form.Control>
                      </Form.Group>
                    </Col>
                  </Row>
                  <Button
                    className="btn-fill pull-right m-2"
                    onClick={backClickHandler}
                  >
                    Voltar
                  </Button>
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
