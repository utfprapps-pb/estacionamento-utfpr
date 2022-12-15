import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { StickerRequest } from "../../commons/types";
import RequestService from "../../services/RequestService";
import RequestFormPage from "./Form";

const RequestFormPageHandler = () => {
  const navigate = useNavigate();

  const handleSubmit = async (values: any, requestId: string) => {
    values.preventDefault();

    var select = document!.querySelector("select");
    var option = select!.children[select!.selectedIndex];
    var brandSelectedName = option.textContent;

    const request: StickerRequest = {
      id: (requestId != "0" ? requestId : ""),
      name: values.target[0].value,
      operatorApprover: null,
      operatorRequester: null,
      vehicle: {
        brand: values.target[1].value,
        brandName: brandSelectedName,
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
        const stickerRequest: StickerRequest = {
          ...response.data,
        }
        console.log(values.target[6].files[0]);
        const formData = new FormData();
        formData.append('file', values.target[6].files[0]);
        const blob = new Blob([JSON.stringify(stickerRequest)], {
          type: 'application/json'
        });
        formData.append('stickerRequest', blob);
        RequestService.uploadFile(formData)
          .then((response) => {
            console.log('salvou o arquivo');
            navigate("/solicitacoes");
          })
          .catch((error) => {
            if (error.response.data && error.response.data.validationErrors) {
              console.log(error.response.data.validationErrors);
            } else {
              console.log("Falha ao salvar o RequestFile.");
            }
          });
      })
      .catch((apiError: { response: { data: { validationErrors: any } } }) => {
        if (apiError.response.data && apiError.response.data.validationErrors) {
        }
      });
    
  };
  return (
    <>
      <RequestFormPage handleSubmit={handleSubmit} />
    </>
  );
};

export default RequestFormPageHandler;
