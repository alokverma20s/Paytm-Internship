import { createContext, useContext, useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { useDispatch } from "react-redux";
import { validateToken } from "../services/operations/AuthAPI";
import {
  loginSuccess,
  logout as logoutAction,
} from "../redux/slices/authSlice";

// Create Auth Context
const AuthContext = createContext();

// Auth Provider Component
export const AuthProvider = ({ children }) => {
  const navigate = useNavigate();
  const dispatch = useDispatch();

  const [isAuthenticated, setIsAuthenticated] = useState(
    !!localStorage.getItem("token"), // Convert to boolean
  );

  useEffect(() => {
    const fetchUser = async () => {
      const token = localStorage.getItem("token");
      if (token) {
        try {
          const userData = await validateToken(navigate, dispatch); // API call to validate token
          dispatch(loginSuccess({ token, user: userData })); // Save to Redux
          setIsAuthenticated(true);
        } catch (error) {
          console.error("Failed to fetch user:", error);
          logout(); // Logout user if token is invalid
        }
      }
    };

    fetchUser();
  }, [dispatch, navigate]);

  // Login function
  const loginfuction = (token, user) => {
    setIsAuthenticated(true);
  };

  // Logout function
  const logoutfunction = () => {
    setIsAuthenticated(false);
    navigate("/login");
  };

  return (
    <AuthContext.Provider
      value={{ isAuthenticated, loginfuction, logoutfunction }}
    >
      {children}
    </AuthContext.Provider>
  );
};

// Custom hook to use auth context
export const useAuth = () => useContext(AuthContext);
