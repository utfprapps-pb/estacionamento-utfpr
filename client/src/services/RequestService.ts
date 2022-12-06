import api from "axios";
import { StickerRequest } from "../commons/types";
import { API_BASE_URL } from "../constants";

const getBrands = () => {
  return api.get(API_BASE_URL + "/vehicle/listCarBrands");
};

const getModels = (brandCode: number) => {
  return api.get(API_BASE_URL + "/vehicle/listCarModels?brandCode=" + brandCode);
};

const save = (request: StickerRequest) => {
  return api.post(API_BASE_URL + "/stickerRequest", request);
};

const RequestService = {
  getBrands,
  getModels,
  save,
}

export default RequestService;