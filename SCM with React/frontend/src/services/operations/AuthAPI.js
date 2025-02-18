import toast from "react-hot-toast";
import { apiConnector } from "../APIConnectors";
import { authEndpoint, userEndpoint } from "../apis";
import { loginSuccess } from "../../redux/slices/authSlice"; // Import your Redux action

const { LOGIN_API, SIGNUP_API } = authEndpoint;

export const login = async (email, password, navigate, dispatch) => {
  return toast.promise(
    apiConnector("post", LOGIN_API, { email, password })
      .then((response) => {
        if (response.status === 200) {
          const { token, user } = response.data;

          // Store token in local storage
          localStorage.setItem("token", token);

          // Dispatch the loginSuccess action
          dispatch(loginSuccess({ token, user }));

          // Navigate to user profile
          navigate("/user/profile");

          return response.data; // Success case
        } else {
          throw new Error("Unexpected response");
        }
      })
      .catch((error) => {
        console.error("Login Error:", error);

        if (error.response) {
          // Backend responded with an error status
          if (error.response.status === 401) {
            throw new Error("Invalid credentials");
          } else if (error.response.status >= 500) {
            throw new Error("Server error");
          }
        } else if (error.request) {
          // Request was made but no response received
          throw new Error("No response from server");
        } else {
          // Other errors
          throw new Error("Unexpected error");
        }
      }),
    {
      loading: "Logging in...",
      success: "Login successful!",
      error: (error) => error.message, // Display the specific error message
    }
  );
};

export const signup = async (body) => {
  return toast.promise(
    apiConnector("post", SIGNUP_API, body)
      .then((response) => {
        if (response.status !== 201) {
          throw new Error("Unexpected response");
        }
      })
      .catch((error) => {
        console.error("Signup Error:", error);

        if (error.response) {
          // Backend responded with an error status
          if (error.response.status === 401) {
            throw new Error("User already exists");
          } else if (error.response.status >= 500) {
            console.log(response);
            
            throw new Error(response);
          }
        } else if (error.request) {
          // Request was made but no response received
          throw new Error("No response from server");
        } else {
          // Other errors
          throw error;
        }
      }),
    {
      loading: "Signing up...",
      success: "Registration successful!",
      error: (error) => error.message, // Display the specific error message
    }
  );
};

//  Validate token
export const validateToken = async (navigate) => {
  const token = localStorage.getItem("token");

  if (token) {
    try {
      const response = await apiConnector("get", userEndpoint.GET_USER_INFO);
      return response.data;
    } catch (error) {
      // If token is invalid, remove it from local storage and navigate to login page
      localStorage.removeItem("token");
      localStorage.removeItem("user");
      navigate("/login");
    }
  } else {
    navigate("/login");
  }
};
