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