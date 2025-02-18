import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { signup } from "../../services/operations/AuthAPI";

const Signup = () => {
  const [formData, setFormData] = useState({
    name: "",
    email: "",
    password: "",
    phoneNumber: "",
    about: "",
  });

  const navigate = useNavigate();

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = async(e) => {
    e.preventDefault();
    await signup(formData);
    navigate("/login");
  };

  return (
    <div className="flex justify-center items-center my-28">
      <div className="w-[90vw] md:w-[60vw] lg:w-1/2 xl:w-1/3">
        <div className="block p-6 border-t-[10px] border-green-700 bg-white rounded-xl shadow dark:bg-gray-800 dark:border-blue-700">
          <h5 className="mb-2 text-2xl font-bold tracking-tight text-gray-900 dark:text-white">
            Signup Here
          </h5>
          <p className="font-normal text-gray-400 dark:text-gray-400">
            Start managing contacts on cloud ...
          </p>

          <form onSubmit={handleSubmit} className="mt-5">
            <div className="mb-3">
              <label className="block mb-2 text-sm font-medium text-gray-900 dark:text-white">
                Name
              </label>
              <input
                type="text"
                name="name"
                value={formData.name}
                onChange={handleChange}
                required
                className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:text-white"
              />
            </div>

            <div className="mb-3">
              <label className="block mb-2 text-sm font-medium text-gray-900 dark:text-white">
                Email
              </label>
              <input
                type="email"
                name="email"
                value={formData.email}
                onChange={handleChange}
                required
                className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:text-white"
              />
            </div>

            <div className="mb-3">
              <label className="block mb-2 text-sm font-medium text-gray-900 dark:text-white">
                Password
              </label>
              <input
                type="password"
                name="password"
                value={formData.password}
                onChange={handleChange}
                required
                className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:text-white"
              />
            </div>

            <div className="mb-3">
              <label className="block mb-2 text-sm font-medium text-gray-900 dark:text-white">
                Contact Number
              </label>
              <input
                type="text"
                name="phoneNumber"
                value={formData.phoneNumber}
                onChange={handleChange}
                required
                className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:text-white"
              />
            </div>

            <div className="mb-3">
              <label className="block mb-2 text-sm font-medium text-gray-900 dark:text-white">
                About
              </label>
              <textarea
                name="about"
                rows="6"
                value={formData.about}
                onChange={handleChange}
                className="block p-2.5 w-full text-sm text-gray-900 bg-gray-50 rounded-lg border border-gray-300 dark:bg-gray-700 dark:border-gray-600 dark:text-white"
                placeholder="Write here..."
              ></textarea>
            </div>

            <div className="mb-3 flex justify-center space-x-3">
              <button
                type="submit"
                className="px-3 py-2 rounded bg-gray-800 text-white dark:bg-blue-800"
              >
                Signup
              </button>
              <button
                type="reset"
                onClick={() =>
                  setFormData({
                    name: "",
                    email: "",
                    password: "",
                    phoneNumber: "",
                    about: "",
                  })
                }
                className="px-3 py-2 rounded bg-red-600 text-white dark:bg-orange-800"
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

export default Signup;
