import { useContext } from "react";
import { Link, NavLink, useNavigate } from "react-router-dom";
import { AuthContext } from "../../context/AuthContext";
import logo from "../../assets/utfpr-logo.png";

export function NavBar() {
  const { authenticated, handleLogout } = useContext(AuthContext);
  const navigate = useNavigate();

  const onClickLogout = () => {
    handleLogout();
    navigate('login');
  }

  return (
    <div className="bg-white shadow-sm mb-2">
      <div className="container">
        <nav className="navbar navbar-light navbar-expand">
          <Link to="/" className="navbar-brand">
            <img src={logo} width="60" alt="UTFPR" />
          </Link>
          {authenticated && (
          <ul className="navbar-nav me-auto mb-2 mb-md-0">
            <li className="nav-item">
              <NavLink
                to="/"
                className={(navData) =>
                  navData.isActive ? "nav-link active" : "nav-link"
                }
              >
                Home
              </NavLink>
            </li>
            <li className="nav-item">
              <NavLink
                to="/solicitacoes"
                className={(navData) =>
                  navData.isActive ? "nav-link active" : "nav-link"
                }
              >
                Solicitacoes
              </NavLink>
            </li>
            <li className="nav-item">
              <NavLink
                to="/externos"
                className={(navData) =>
                  navData.isActive ? "nav-link active" : "nav-link"
                }
              >
                Externo
              </NavLink>
            </li>

            <li className="nav-item">
              <NavLink
                to="/funcionarios"
                className={(navData) =>
                  navData.isActive ? "nav-link active" : "nav-link"
                }
              >
              Funcionarios
              </NavLink>
            </li>

            <li className="nav-item">
              <NavLink
                to="/infracao"
                className={(navData) =>
                  navData.isActive ? "nav-link active" : "nav-link"
                }
              >
              Justificar Infração
              </NavLink>
            </li>

            <li className="nav-item">
              <button className="btn btn-light" onClick={onClickLogout}>
                &times; Sair
              </button>
            </li>
          </ul>)
          }
        </nav>
      </div>
    </div>
  );
}
