import { useContext , useEffect } from "react";
import { Route, Routes } from "react-router-dom";
import { Layout } from "./pages/Layout";
import { LoginPage } from "./pages/LoginPage";
import { UserSignupPage } from "./pages/UserSignupPage";
import { Dashboard } from "./pages/HomePage";
import { RequestFormPage } from "./pages/Request";
import { RequireAuth } from "./components/RequireAuth";
import { NotFound } from "./pages/NotFound";
import { Unauthorized } from "./pages/Unauthorized";

import { AuthContext } from './context/AuthContext'

const ROLES = {
  User: 'ROLE_USER',
  Admin: 'ROLE_ADMIN'
}

export function App () {
  const { checkIsAuthenticated } = useContext(AuthContext)

  useEffect(() => {
    checkIsAuthenticated()
  }, [])

  return (
    <Routes>
      <Route path='/' element={<Layout />}>
        {/* public routes */}
        <Route path='login' element={<LoginPage />} />
        <Route path='signup' element={<UserSignupPage />} />
        <Route path='unauthorized' element={<Unauthorized />} />

        {/* protected routes - Roles: User and Admin */}
        <Route element={<RequireAuth allowedRoles={[ROLES.User, ROLES.Admin]} />}>
          <Route path='/solicitacoes' element={<RequestFormPage />} />
          <Route path='/home' element={<Dashboard />} />
          <Route path='/' element={<Dashboard />} />

        </Route>

        {/* protected routes - Role: Admin */}
        <Route element={<RequireAuth allowedRoles={[ROLES.Admin]} />} >
          <Route path='/externos' element={<Dashboard />} />
          <Route path='/funcionarios' element={<Dashboard />} />
        </Route>
        
        {/* catch all */}
        <Route path='*' element={<NotFound />} />

      </Route>
    </Routes>
  )
}
