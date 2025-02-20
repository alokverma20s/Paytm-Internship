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

  const handleSubmit = async (e) => {
    e.preventDefault();
    await signup(formData);
    navigate("/login");
  };

  return (
    <div className="my-28 flex items-center justify-center">
      <div className="w-[90vw] md:w-[60vw] lg:w-1/2 xl:w-1/3">
        <div className="block rounded-xl border-t-[10px] border-green-700 bg-white p-6 shadow dark:border-blue-700 dark:bg-gray-800">
          <h5 className="mb-2 text-2xl font-bold tracking-tight text-gray-900 dark:text-white">
            Signup Here
          </h5>
          <p className="font-normal text-gray-400 dark:text-gray-400">
            Start managing contacts on cloud ...
          </p>

          <form onSubmit={handleSubmit} className="mt-5">
            <div className="mb-3">
              <label className="mb-2 block text-sm font-medium text-gray-900 dark:text-white">
                Name
              </label>
              <input
                type="text"
                name="name"
                value={formData.name}
                onChange={handleChange}
                required
                className="w-full rounded-lg border border-gray-300 bg-gray-50 p-2.5 text-sm text-gray-900 dark:border-gray-600 dark:bg-gray-700 dark:text-white"
              />
            </div>

            <div className="mb-3">
              <label className="mb-2 block text-sm font-medium text-gray-900 dark:text-white">
                Email
              </label>
              <input
                type="email"
                name="email"
                value={formData.email}
                onChange={handleChange}
                required
                className="w-full rounded-lg border border-gray-300 bg-gray-50 p-2.5 text-sm text-gray-900 dark:border-gray-600 dark:bg-gray-700 dark:text-white"
              />
            </div>

            <div className="mb-3">
              <label className="mb-2 block text-sm font-medium text-gray-900 dark:text-white">
                Password
              </label>
              <input
                type="password"
                name="password"
                value={formData.password}
                onChange={handleChange}
                required
                className="w-full rounded-lg border border-gray-300 bg-gray-50 p-2.5 text-sm text-gray-900 dark:border-gray-600 dark:bg-gray-700 dark:text-white"
              />
            </div>

            <div className="mb-3">
              <label className="mb-2 block text-sm font-medium text-gray-900 dark:text-white">
                Contact Number
              </label>
              <input
                type="text"
                name="phoneNumber"
                value={formData.phoneNumber}
                onChange={handleChange}
                required
                className="w-full rounded-lg border border-gray-300 bg-gray-50 p-2.5 text-sm text-gray-900 dark:border-gray-600 dark:bg-gray-700 dark:text-white"
              />
            </div>

            <div className="mb-3">
              <label className="mb-2 block text-sm font-medium text-gray-900 dark:text-white">
                About
              </label>
              <textarea
                name="about"
                rows="6"
                value={formData.about}
                onChange={handleChange}
                className="block w-full rounded-lg border border-gray-300 bg-gray-50 p-2.5 text-sm text-gray-900 dark:border-gray-600 dark:bg-gray-700 dark:text-white"
                placeholder="Write here..."
              ></textarea>
            </div>

            <div className="mb-3 flex justify-center space-x-3">
              <button
                type="submit"
                className="rounded bg-gray-800 px-3 py-2 text-white dark:bg-blue-800"
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
                className="rounded bg-red-600 px-3 py-2 text-white dark:bg-orange-800"
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
