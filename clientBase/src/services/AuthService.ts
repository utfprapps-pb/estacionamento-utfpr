import { Operator, OperatorLogin } from "../commons/types";
import { api } from "../lib/axios";

const signup = (operator: Operator) => {
  return api.post("/users", operator);
};

const login = (operator: OperatorLogin) => {
  return api.post("/login", operator);
};

const getCurrentOperator = () => {
  return ''; //JSON.parse(localStorage.getItem("user"));
};

const isAuthenticated = () => {
  return localStorage.getItem("token") ? true : false;
};

const AuthService = {
  signup,
  login,
  getCurrentOperator,
  isAuthenticated,
};

export default AuthService;
