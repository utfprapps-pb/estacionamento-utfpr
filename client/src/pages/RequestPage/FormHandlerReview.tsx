import { useNavigate } from "react-router-dom";
import { StickerRequest } from "../../commons/types";
import RequestService from "../../services/RequestService";
import RequestReviewFormPage from "./FormReview";

const RequestReviewFormPageHandler = () => {
  const navigate = useNavigate();

  const handleSubmit = async (
    values: any,
    requestId: string,
    formData: any
  ) => {
    values.preventDefault();

    var select = document!.querySelector("select");
    var option = select!.children[select!.selectedIndex];
    var brandSelectedName = option.textContent;

    const request: StickerRequest = {
      id: requestId != "0" ? requestId : "",
      name: values.target[0].value,
      operatorApprover: null,
      operatorRequester: formData.operatorRequester,
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
      status: values.target[8].value,
      requesterMessage: values.target[7].value,
      approverMessage: values.target[9].value,
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
  };
  return (
    <>
      <RequestReviewFormPage handleSubmit={handleSubmit} />
    </>
  );
};

export default RequestReviewFormPageHandler;
