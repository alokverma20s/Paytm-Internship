import { useEffect, useState } from "react";
import { Route, Routes } from "react-router-dom";
import { Toaster } from "react-hot-toast";

import Login from "./pages/auth/Login";
import Navbar from "./components/Navbar";
import Sidebar from "./components/Sidebar";
import Signup from "./pages/auth/Signup";
import ProtectedRoute from "./providers/ProtectedRoute";
import ProfilePage from "./pages/Contacts/ProfilePage";
import ContactsPage from "./pages/Contacts/ContactsPage";
import AddContact from "./pages/Contacts/AddContact";
import { useAuth } from "./providers/AuthProvider";
import { useSelector } from "react-redux";

function App() {
  const isAuthenticated = useSelector((state) => state.auth.isAuthenticated);

  const [darkMode, setDarkMode] = useState(
    localStorage.getItem("theme") === "dark",
  );

  // Toggle dark mode
  useEffect(() => {
    if (darkMode) {
      document.documentElement.classList.add("dark");
      localStorage.setItem("theme", "dark");
    } else {
      console.log("Light Mode");
      document.documentElement.classList.remove("dark");
      localStorage.setItem("theme", "light");
    }
  }, [darkMode]);
  return (
    <>
      <Navbar setDarkMode={setDarkMode} darkMode={darkMode} />
      {isAuthenticated && <Sidebar />}
      <Routes>
        <Route path="/" element={<Login />} />
        <Route path="/login" element={<Login />} />
        <Route path="/register" element={<Signup />} />

        {/* Protected Routes */}
        <Route
          path="/user/profile"
          element={
            <ProtectedRoute>
              <ProfilePage />
            </ProtectedRoute>
          }
        />
        <Route
          path="/user/contacts"
          element={
            <ProtectedRoute>
              <ContactsPage />
            </ProtectedRoute>
          }
        />
        <Route
          path="/user/contacts/add"
          element={
            <ProtectedRoute>
              <AddContact />
            </ProtectedRoute>
          }
        />
        <Route
          path="/user/contacts/edit/:id"
          element={
            <ProtectedRoute>
              <AddContact />
            </ProtectedRoute>
          }
        />
      </Routes>
      <Toaster />
    </>
  );
}

export default App;
