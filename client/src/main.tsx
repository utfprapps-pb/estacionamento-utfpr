import React from "react";
import ReactDOM from "react-dom/client";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import { App } from "./App";
import "./index.css";
// import "bootstrap/dist/css/bootstrap.min.css";
import "./assets/css/animate.min.css";
import "./assets/scss/light-bootstrap-dashboard-react.scss?v=2.0.0";
import "./assets/css/demo.css";
import { GoogleOAuthProvider } from "@react-oauth/google";
import { AuthProvider } from "./context/AuthContext";

ReactDOM.createRoot(document.getElementById("root") as HTMLElement).render(
  <React.StrictMode>
    <BrowserRouter>
        <AuthProvider>
          <GoogleOAuthProvider clientId="310109923674-la5thl4s4t0b2ajp6acdhq7tra74dn31.apps.googleusercontent.com">
            <Routes>
              <Route path="/*" element={<App />} />
            </Routes>
          </GoogleOAuthProvider>
        </AuthProvider>
    </BrowserRouter>
  </React.StrictMode>
);