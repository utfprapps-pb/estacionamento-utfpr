import api from "axios";
import { API_BASE_URL } from "../constants";

const getBrands = () => {
  return api.get(API_BASE_URL + "/vehicle/listCarBrands");
};

const getModels = (brandCode: number) => {
  return api.get(API_BASE_URL + "/vehicle/listCarModels?brandCode=" + brandCode);
};

const RequestService = {
  getBrands,
  getModels,
}

export default RequestService;