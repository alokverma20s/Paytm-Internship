import { configureStore } from "@reduxjs/toolkit";
import authReducer from "./slices/authSlice"; // Import your slices

export const store = configureStore({
  reducer: {
    auth: authReducer, // Add reducers here
  },
});

export default store;