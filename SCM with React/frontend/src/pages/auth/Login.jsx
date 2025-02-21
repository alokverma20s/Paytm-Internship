import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { useDispatch, useSelector } from "react-redux";

import { login } from "../../services/operations/AuthAPI";
import { useAuth } from "../../providers/AuthProvider";
import toast from "react-hot-toast";

const Login = () => {
  const navigate = useNavigate();
  const user = useSelector((state) => {
      return state.auth.user;
    });

  if(user){
    navigate("/user/profile")
  }
  
  const { loginfuction } = useAuth();
  const baseUrl = import.meta.env.VITE_BASE_URL;

  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const dispatch = useDispatch();

  const handleSubmit = async (e) => {
    e.preventDefault();
    // TODO: Validate the login credentials
    if (!email || !password) {
      toast.error("Please enter email and password");
      return;
    }
    await login(email, password, navigate, dispatch);
    loginfuction();
  };

  const githubLogin = async () => {
    window.location.href = `${baseUrl}/oauth2/authorization/github`
  };

  return (
    <div className="mt-28 flex items-center justify-center">
      <div className="w-[90vw] md:w-[60vw] lg:w-1/2 xl:w-1/3">
        <div className="block rounded-xl border-t-[10px] border-green-700 bg-white p-6 shadow dark:border-blue-700 dark:bg-gray-800">
          <h5 className="mb-2 text-2xl font-bold tracking-tight text-gray-900 dark:text-white">
            Login Here
          </h5>
          <p className="font-normal text-gray-400 dark:text-gray-400">
            Start managing contacts on cloud ...
          </p>

          <form onSubmit={handleSubmit} className="mt-5" noValidate>
            <div className="mb-3">
              <label className="mb-2 block text-sm font-medium text-gray-900 dark:text-white">
                Email
              </label>
              <input
                type="email"
                value={email}
                onChange={(e) => setEmail(e.target.value)}
                className="block w-full rounded-lg border border-gray-300 bg-gray-50 p-2.5 text-sm text-gray-900 focus:border-blue-500 focus:ring-blue-500 dark:border-gray-600 dark:bg-gray-700 dark:text-white dark:placeholder-gray-400 dark:focus:border-blue-500 dark:focus:ring-blue-500"
                placeholder="example@gmail.com"
                required
              />
            </div>
            <div className="mb-3">
              <label className="mb-2 block text-sm font-medium text-gray-900 dark:text-white">
                Password
              </label>
              <input
                type="password"
                value={password}
                placeholder="Password"
                onChange={(e) => setPassword(e.target.value)}
                className="block w-full rounded-lg border border-gray-300 bg-gray-50 p-2.5 text-sm text-gray-900 focus:border-blue-500 focus:ring-blue-500 dark:border-gray-600 dark:bg-gray-700 dark:text-white dark:placeholder-gray-400 dark:focus:border-blue-500 dark:focus:ring-blue-500"
                required
              />
            </div>

            <div className="mb-3 flex justify-center space-x-3">
              <button
                type="submit"
                className="rounded bg-gray-800 px-3 py-2 text-white hover:bg-gray-700 dark:bg-blue-700 dark:hover:bg-blue-800"
              >
                Login
              </button>
              <button
                type="reset"
                className="rounded bg-red-500 px-3 py-2 text-white hover:bg-red-400"
                onClick={() => {
                  setEmail("");
                  setPassword("");
                }}
              >
                Reset
              </button>
            </div>
          </form>

          <button
            onClick={githubLogin}
            className="mt-6 flex w-full items-center justify-center"
          >
            <img
              src="https://img.shields.io/badge/GitHub-100000?style=for-the-badge&logo=github&logoColor=white"
              alt="GitHub Login"
              className="h-12 rounded-full"
            />
          </button>
        </div>
      </div>
    </div>
  );
};

export default Login;
