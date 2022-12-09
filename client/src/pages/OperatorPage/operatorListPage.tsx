import React, { useState, useEffect } from "react";
import axios from "axios";
import { ListGroup, Card, Button, Table } from "react-bootstrap";
import { Operator } from "../../commons/types";
import { useNavigate } from "react-router-dom";
import OperatorService from "./operatorService";

const OperatorList = () => {
  const [operators, setOperators]: any = useState([]);

  const navigate = useNavigate();

  useEffect(() => {
    OperatorService.getOperators().then((response) =>
      setOperators(response.data)
    );
  }, []);

  const handleEdit = (id: string) => {
    navigate(`/usuarios/${id}`);
  };

  return (
    <Table>
      <thead>
        <tr>
          <th>Editar</th>
          <th>Name</th>
          <th>Username</th>
          <th>Email</th>
          <th>Endereço</th>
          <th>Complemento</th>
          <th>Zip</th>
          <th>Bairro</th>
          <th>Cidade</th>
          <th>CPF</th>
        </tr>
      </thead>
      <tbody>
        {operators.map((operator: Operator) => (
          <tr key={operator.id}>
            <td>
              <Button onClick={() => handleEdit(operator.id)}>Editar</Button>
            </td>
            <td>{operator.name}</td>
            <td>{operator.username}</td>
            <td>{operator.email}</td>
            <td>{operator.address}</td>
            <td>{operator.complement}</td>
            <td>{operator.zipcode}</td>
            <td>{operator.neighborhood}</td>
            <td>{operator.city}</td>
            <td>{operator.documentFileName}</td>
          </tr>
        ))}
      </tbody>
    </Table>
  );
};

export default OperatorList;
