export interface OperatorLogin {
  username: string;
  password: string;
}

export interface Operator {
  name: string;
  username: string;
  password: string;
}

export interface AuthenticationResponse {
  token: string;
  user: AuthenticatedUser;
}

export interface AuthenticatedUser {
  name: string;
  username: string;
  authorities: Authorities[];
}

export interface Authorities {
  authority: string;
}

export interface Vehicle {
   brand: String;
   model: String;
   year: String;
   licensePlate: String;
   color: String;
   documentFileName: String;
   operator: Operator | null;
}

export interface StickerRequest {
   name: string;
   operatorRequester: Operator | null;
   vehicle: Vehicle;
   operatorApprover: Operator | null;
   status: any;
   requesterMessage: string;
   approverMessage: string;
   stickerNumber: number;
}
