import React, { useContext, useEffect, useState } from "react";

// react-bootstrap components
import {
  Badge,
  Button,
  Card,
  Navbar,
  Nav,
  Table,
  Container,
  Row,
  Col,
} from "react-bootstrap";
import { useNavigate } from "react-router-dom";
import { AuthContext } from "../../context/AuthContext";
import RequestService from "../../services/RequestService";

const Request = () => {
  const [requests, setRequests]: any = useState([]);

  const { authenticatedUser } = useContext(AuthContext);
  var hasAdminPermission = false;

  const navigate = useNavigate();

  const updateStatus = (values: any) => {
    values.map((value: any) => {
      switch (value.status) {
        case "IN_ANALYSIS":
          value.status = "Em Análise";
          break;
        case "INCOMPLETE":
          value.status = "Incompleta";
          break;
        case "APPROVED":
          value.status = "Aprovada";
          break;
        case "DENIED":
          value.status = "Negada";
          break;
        default:
          value.status = "";
      }
    });
    return values;
  };

  function checkAdminPermission() {
    hasAdminPermission =
      authenticatedUser?.authorities.some(
        (it) => it.authority == "ROLE_ADMIN"
      ) ?? false;
  }

  const loadRequests = () => {
    RequestService.getRequests().then((response: any) => {
      const requests = updateStatus(response.data);
      setRequests(requests);
    });
  };

  const editClickHandler = (id: string) => {
    navigate(`${id}`);
  };

  const removeClickHandler = (id: string) => {
    RequestService.remove(id).then(() => {
      loadRequests();
    });
  };

  const reviewClickHandler = (id: string) => {
    navigate(`revisar/${id}`);
  }

  useEffect(() => {
    checkAdminPermission();

    /*setRequests([])
        RequestService.getRequests()
            .then((res: any) => {
                res.data.forEach((req: any) => {
                    console.log(req.vehicle.brand)
                    RequestService.getModels(req.vehicle.brand)
                    .then((ress: any) => {
                        ress.data.carBrandModelDTO.map((model: any) => {
                            if(req.vehicle.model == model.value) {
                                console.log('aqui')
                                req.modelName = model.label,
                                requests.push(req);
                                console.log(requests);
                            }
                    });
                });
                });
            });*/

    loadRequests();
  }, []);

  return (
    <>
      <Container fluid>
        <Row>
          <Col md="12">
            <Card className="strpied-tabled-with-hover">
              <Card.Header>
                <Card.Title className="d-flex justify-content-between" as="h4">
                  Solicitações de adesivos
                  <Button
                    className="btn-fill pull-right"
                    onClick={() => navigate("0")}
                  >
                    Solicitar
                  </Button>
                </Card.Title>
              </Card.Header>
              <Card.Body className="table-full-width table-responsive px-0">
                <Table className="table-hover table-striped">
                  <thead>
                    <tr>
                      <th className="border-0">Número</th>
                      <th className="border-0">Nome</th>
                      <th className="border-0">Placa</th>
                      <th className="border-0">Veículo</th>
                      <th className="border-0">Situação</th>
                      <th className="border-0">Ações</th>
                    </tr>
                  </thead>
                  <tbody>
                    {
                      (console.log(requests),
                      requests.map((req: any) => {
                        return (
                          <tr>
                            <td>{req.stickerNumber}</td>
                            <td>{req.name}</td>
                            <td>{req.vehicle.licensePlate}</td>
                            <td>{req.vehicle.brandName}</td>
                            <td>{req.status}</td>
                            <td>
                              <Button
                                variant="secondary"
                                onClick={() => editClickHandler(req.id)}
                              >
                                Editar
                              </Button>
                              <Button
                                variant="danger"
                                onClick={() => removeClickHandler(req.id)}
                              >
                                Excluir
                              </Button>
                              <Button variant="success" onClick={() => reviewClickHandler(req.id)}>
                                Auditoria
                              </Button>
                            </td>
                          </tr>
                        );
                      }))
                    }
                  </tbody>
                </Table>
              </Card.Body>
            </Card>
          </Col>
        </Row>
      </Container>
    </>
  );
};

export default Request;
