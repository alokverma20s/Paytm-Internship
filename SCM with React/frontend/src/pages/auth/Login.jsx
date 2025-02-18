import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { useDispatch } from "react-redux";

import { login } from "../../services/operations/AuthAPI";
import { useAuth } from "../../providers/AuthProvider";
import toast from "react-hot-toast";

const Login = () => {

  const { loginfuction } = useAuth();
  
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const navigate = useNavigate();
  const dispatch = useDispatch();

  const handleSubmit = async (e) => {
    e.preventDefault();
    // TODO: Validate the login credentials
    if(!email || !password) {
      toast.error("Please enter email and password");
      return;
    }
    await login(email, password, navigate, dispatch);
    loginfuction();
  };

  return (
    <div className="flex justify-center items-center mt-28">
      <div className="w-[90vw] md:w-[60vw] lg:w-1/2 xl:w-1/3">
        <div className="block p-6 border-t-[10px] border-green-700 bg-white rounded-xl shadow dark:bg-gray-800 dark:border-blue-700">
          <h5 className="mb-2 text-2xl font-bold tracking-tight text-gray-900 dark:text-white">
            Login Here
          </h5>
          <p className="font-normal text-gray-400 dark:text-gray-400">
            Start managing contacts on cloud ...
          </p>

          <form onSubmit={handleSubmit} className="mt-5" noValidate>
            <div className="mb-3">
              <label className="block mb-2 text-sm font-medium text-gray-900 dark:text-white">
                Email
              </label>
              <input
                type="email"
                value={email}
                onChange={(e) => setEmail(e.target.value)}
                className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                placeholder="example@gmail.com"
                required
              />
            </div>
            <div className="mb-3">
              <label className="block mb-2 text-sm font-medium text-gray-900 dark:text-white">
                Password
              </label>
              <input
                type="password"
                value={password}
                placeholder="Password"
                onChange={(e) => setPassword(e.target.value)}
                className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                required
              />
            </div>

            <div className="mb-3 flex justify-center space-x-3">
              <button
                type="submit"
                className="px-3 py-2 rounded bg-gray-800 hover:bg-gray-700 text-white dark:bg-blue-700 dark:hover:bg-blue-800"
              >
                Login
              </button>
              <button
                type="reset"
                className="px-3 py-2 rounded bg-red-500 hover:bg-red-400 text-white"
                onClick={() => {
                  setEmail("");
                  setPassword("");
                }}
              >
                Reset
              </button>
            </div>
          </form>
        </div>
      </div>
    </div>
  );
};

export default Login;
