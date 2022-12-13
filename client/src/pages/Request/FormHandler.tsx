import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { StickerRequest } from "../../commons/types";
import RequestService from "../../services/RequestService";
import RequestFormPage from "./Form"

const RequestFormPageHandler = () => {

    const navigate = useNavigate();

    
    const handleSubmit = async (values: any, requestId: string) => {
        values.preventDefault();
        const request: StickerRequest = {
          id: requestId =! '0' ? requestId : "",
          name: values.target[0].value,
          operatorApprover: null,
          operatorRequester: null,
          vehicle: {
            brand: values.target[1].value,
            model: values.target[2].value,
            year: values.target[5].value,
            licensePlate: values.target[3].value,
            color: values.target[4].value,
            documentFileName: "",
            operator: null,
          },
          status: "IN_ANALYSIS",
          requesterMessage: values.target[7].value,
          approverMessage: "",
          stickerNumber: 0,
        };
    
        RequestService.save(request)
          .then((response: any) => {
            navigate("/");
          })
          .catch((apiError: { response: { data: { validationErrors: any } } }) => {
            if (apiError.response.data && apiError.response.data.validationErrors) {
            }
          });
      }
    return (
        <>
          <RequestFormPage handleSubmit={handleSubmit} />
        </>
    )
};

export default RequestFormPageHandler;