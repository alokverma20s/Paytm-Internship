import axios from "axios";

const BASE_URL = import.meta.env.VITE_BASE_URL;

export const axiosInstance = axios.create({
  baseURL: BASE_URL, // Set base URL globally
  headers: { "Content-Type": "application/json" }, // Default headers
});

export const apiConnector = async (
  method,
  url,
  bodyData = null,
  customHeaders = {},
  params = null
) => {
  try {
    const token = localStorage.getItem("token");
    var headers = customHeaders;
    if (!url.startsWith(`${BASE_URL}/auth`)) {
      headers = {
        ...(token && { Authorization: `Bearer ${token}` }), // Add token if available
      };
    }

    const response = await axiosInstance({
      method,
      url,
      data: bodyData,
      headers,
      params,
    });

    return response;
  } catch (error) {
    if(error.response?.data.message.includes("Duplicate entry")){
      throw new Error("User already Exists"); // Re-throw error for handling in API calls
    }
    else{
      console.log("API Error:", error.response?.data.message);
      throw new Error("Server Error")
    }
  }
};
