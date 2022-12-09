import { Operator, OperatorLogin, OperatorSignup } from "../commons/types";
import api from "axios";
import { API_BASE_URL } from "../constants";

const signup = (operator: OperatorSignup) => {
  return api.post(API_BASE_URL + "/users", operator);
};

const login = (operator: OperatorLogin) => {
  return api.post(API_BASE_URL + "/login", operator);
};

const getCurrentUser = () => {
  return ''; //JSON.parse(localStorage.getItem("user"));
};

const isAuthenticated = () => {
  return localStorage.getItem("token") ? true : false;
};

const AuthService = {
  signup,
  login,
  getCurrentUser,
  isAuthenticated,
};

export default AuthService;
