import { ChangeEvent, useContext, useEffect, useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { OperatorLogin } from "../../commons/types";
import { ButtonWithProgress } from "../../components/ButtonWithProgress";
import { Input } from "../../components/Input";
import { AuthContext } from "../../context/AuthContext";
import { CredentialResponse, GoogleLogin } from "@react-oauth/google";
import AuthService from "../../services/AuthService";
import "./style.css";

export function LoginPage() {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [apiError, setApiError] = useState("");
  const [pendingApiCall, setPendingApiCall] = useState(false);
  const { handleLogin, handleLoginSocial, loading } = useContext(AuthContext);
  const navigate = useNavigate();
  const [disableSubmit, setDisableSubmit] = useState(true);

  const onSuccess = (response: CredentialResponse) => {
    console.log(response.credential);

    if (response.credential) {
      handleLoginSocial(response.credential);
    }
  };

  useEffect(() => {
    setApiError("");
    setDisableSubmit(false);
    if (username === "" || password === "") {
      setDisableSubmit(true);
    }
  }, [username, password]);

  useEffect(() => {
    setPendingApiCall(loading);
    if (!loading && username) {
      setApiError("Usuário ou senha inválidos!");
    }
  }, [loading]);

  const onClickLogin = () => {
    setPendingApiCall(true);
    const operatorLogin: OperatorLogin = {
      username: username,
      password: password,
    };
    console.log(operatorLogin);
    AuthService.login(operatorLogin)
      .then((response) => {
        handleLogin(response.data);
        setPendingApiCall(false);
        navigate("/");
      })
      .catch((apiError) => {
        setApiError("Usuário ou senha inválidos!");
        setPendingApiCall(false);
      });
  };

  return (
    <main className="form-signin w-100 m-auto">
      <form>
        <div className="text-center">
          <h1 className="h3 mb-3 fw-normal">Autenticação</h1>
        </div>
        <div className="form-floating">
          <Input            
            label="Usuário"
            className="form-control"
            type="text"
            placeholder="username"
            value={username}
            onChange={(event: ChangeEvent<HTMLInputElement>) =>
              setUsername(event.target.value)
            }
            name="username"
            hasError={false}
            error=""
          />
        </div>
        <div className="form-floating">
          <Input
            label="Senha"
            className="form-control"
            type="password"
            placeholder="Your password"
            value={password}
            onChange={(event: ChangeEvent<HTMLInputElement>) =>
              setPassword(event.target.value)
            }
            name="password"
            hasError={false}
            error=""
          />
        </div>

        <div className="checkbox mb-3">
          <label>
            <input type="checkbox" value="remember-me" /> Lembrar
          </label>
        </div>
        {apiError && (
          <div className="col-12 mb-3">
            <div className="alert alert-danger">{apiError}</div>
          </div>
        )}
        <ButtonWithProgress
          className="w-100 btn btn-lg btn-primary mb-3"
          onClick={onClickLogin}
          disabled={pendingApiCall || disableSubmit}
          text="Autenticar"
          pendingApiCall={pendingApiCall}
        />
        <div className="mb-3">
          <GoogleLogin
            locale="pt-BR"
            onSuccess={onSuccess}
            onError={() => {
              setApiError("Falha ao autenticar-se com o Google");
              console.log("Login Failed");
            }}
          />
        </div>
        <div className="text-center">
          Não possui cadastro?{" "}
          <Link className="nav-link" to="/signup">
            Cadastrar-se
          </Link>
        </div>

        <p className="mt-5 mb-3 text-muted">&copy; 2010–2022</p>
      </form>
    </main>
  );
}