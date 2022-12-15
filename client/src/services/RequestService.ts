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

const getRequest = (requestId: string) => {
  return api.get(API_BASE_URL + `/stickerRequest/${requestId}`);
};

const getRequests = () => {
  return api.get(API_BASE_URL + "/stickerRequest");
};

const remove = (requestId: string) => {
  return api.delete(API_BASE_URL +  `/stickerRequest/${requestId}`)
};

const uploadFile = (formData: FormData) => {
  return api.post(API_BASE_URL + "/stickerRequestFile/upload", formData);
}

const RequestService = {
  getBrands,
  getModels,
  getRequests,
  save,
  getRequest,
  remove,
  uploadFile,
}

export default RequestService;