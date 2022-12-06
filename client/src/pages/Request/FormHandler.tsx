import { useNavigate } from "react-router-dom";
import { StickerRequest } from "../../commons/types";
import RequestService from "../../services/RequestService";
import RequestFormPage from "./form"

const RequestFormPageHandler = () => {

    const navigate = useNavigate();
    
    const handleSubmit = (values: any) => {
        values.preventDefault();
        const request: StickerRequest = {
          name: values.target[0].value,
          operatorApprover: null,
          operatorRequester: null,
          vehicle: {
            brand: values.target[1].value,
            model: values.target[2].value,
            year: "",
            licensePlate: values.target[4].value,
            color: values.target[3].value,
            documentFileName: "",
            operator: null,
          },
          status: "Em Análise",
          requesterMessage: values.target[6].value,
          approverMessage: "",
          stickerNumber: 0,
        };
        console.log(request);
    
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