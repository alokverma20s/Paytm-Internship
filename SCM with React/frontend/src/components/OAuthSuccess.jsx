import { useEffect } from "react";
import { useNavigate } from "react-router-dom";

const OAuthSuccess = () => {
  const navigate = useNavigate();

  useEffect(() => {
    const handleAuth = async () => {
      // Get token from URL
      const params = new URLSearchParams(window.location.search);
      console.log(params.get("token"));

      const token = params.get("token");

      if (token) {
        try {
          // Simulating async storage (if needed in future)
          await new Promise((resolve) => setTimeout(resolve, 100)); 
          
          // Store token in localStorage
          localStorage.setItem("token", token);

          // Redirect to dashboard
          navigate("/user/profile");
        } catch (error) {
          console.error("Error handling authentication:", error);
          navigate("/login");
        }
      } else {
        // Redirect to login if token is missing
        navigate("/login");
      }
    };

    handleAuth();
  }, [navigate]);

  return <p>Redirecting...</p>;
};

export default OAuthSuccess;