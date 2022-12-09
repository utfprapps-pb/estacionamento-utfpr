import api from "axios";
import { Operator } from "../../commons/types";
import { API_BASE_URL } from "../../constants";

const save = (operator: Operator) => {
  return api.post(API_BASE_URL + "/users", operator);
};

const getOperators = () => {
  return api.get(API_BASE_URL + "/users");
};

const getOperator = (id: any) => {
  return api.get(API_BASE_URL + "/users/" + id);
};

//variavel chamada assim pois delete é palavra reservada kk
const deletar = (id: any) => {
  return api.delete(API_BASE_URL + "/users/delete/" + id);
};

const OperatorService = {
  getOperators,
  getOperator,
  deletar,
  save,
};

export default OperatorService;
