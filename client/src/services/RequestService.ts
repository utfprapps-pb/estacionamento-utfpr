import api from "axios";
import { API_BASE_URL } from "../constants";

const getBrands = () => {
  return api.get(API_BASE_URL + "/vehicle/listCarBrands");
};

const RequestService = {
  getBrands,
}

export default RequestService;