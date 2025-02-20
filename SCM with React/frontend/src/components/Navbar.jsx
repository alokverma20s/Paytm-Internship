import { useState } from "react";
import { Link } from "react-router-dom";
import { MdDarkMode, MdLightMode } from "react-icons/md";

import Logo from "../assets/images/telephone.png";
import { useAuth } from "../providers/AuthProvider";
import { useDispatch } from "react-redux";
import { logout } from "../redux/slices/authSlice";
const Navbar = ({ setDarkMode, darkMode }) => {
  const dispatch = useDispatch();
  const [isMenuOpen, setIsMenuOpen] = useState(false);
  const { isAuthenticated } = useAuth();
  const { logoutfunction } = useAuth();

  const toggleMenu = () => {
    setIsMenuOpen((prev) => !prev);
  };

  const closeMenu = () => {
    setIsMenuOpen(false);
  };
  const handleLogout = () => {
    logoutfunction();
    dispatch(logout());
  };

  return (
    <nav className="fixed top-0 z-50 w-full border-gray-900 bg-white shadow-sm dark:bg-gray-900">
      <div className="mx-auto flex max-w-screen-xl flex-wrap items-center justify-between p-4">
        {/* Brand */}
        <Link
          to="/"
          className="flex items-center space-x-3 rtl:space-x-reverse"
        >
          <img src={Logo} className="h-8 rounded-full shadow" alt="Logo" />
          <span className="self-center whitespace-nowrap text-2xl font-semibold dark:text-white">
            SCM
          </span>
        </Link>

        {/* Right-side Buttons */}
        <div className="flex gap-4 space-x-3 md:order-2 md:space-x-0 rtl:space-x-reverse">
          {/* Theme Toggle Button */}
          <button
            onClick={() => {
              setDarkMode(!darkMode);
              closeMenu();
            }}
            className="rounded-lg bg-gray-200 px-3 py-2 dark:bg-gray-600"
          >
            {darkMode ? (
              <div className="flex items-center justify-center gap-2 text-white">
                <MdLightMode />
                <span>Light</span>
              </div>
            ) : (
              <div className="flex items-center justify-center gap-2 text-gray-900">
                <MdDarkMode />
                <span>Dark</span>
              </div>
            )}
          </button>

          {isAuthenticated ? (
            <div className="flex gap-3">
              <Link
                to="/user/profile"
                onClick={closeMenu}
                className="hidden rounded-lg bg-gray-800 px-4 py-2 text-center text-sm font-medium text-white hover:bg-gray-900 focus:outline-none focus:ring-4 focus:ring-blue-300 dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800 md:block"
              >
                Profile
              </Link>
              <Link
                to="/login"
                onClick={handleLogout}
                className="hidden rounded-lg bg-gray-800 px-4 py-2 text-center text-sm font-medium text-white hover:bg-gray-900 focus:outline-none focus:ring-4 focus:ring-blue-300 dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800 md:block"
              >
                Logout
              </Link>
            </div>
          ) : (
            <div className="flex gap-3">
              <Link
                to="/login"
                onClick={closeMenu}
                className="hidden rounded-lg bg-gray-800 px-4 py-2 text-center text-sm font-medium text-white hover:bg-gray-900 focus:outline-none focus:ring-4 focus:ring-blue-300 dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800 md:block"
              >
                Login
              </Link>
              <Link
                to="/register"
                onClick={closeMenu}
                className="hidden rounded-lg bg-gray-800 px-4 py-2 text-center text-sm font-medium text-white hover:bg-gray-900 focus:outline-none focus:ring-4 focus:ring-blue-300 dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800 md:block"
              >
                Signup
              </Link>
            </div>
          )}

          {/* Mobile Menu Toggle Button */}
          <button
            onClick={toggleMenu}
            className="inline-flex h-10 w-10 items-center justify-center rounded-lg p-2 text-sm text-gray-500 hover:bg-gray-100 focus:outline-none focus:ring-2 focus:ring-gray-200 dark:text-gray-400 dark:hover:bg-gray-700 dark:focus:ring-gray-600 md:hidden"
          >
            <span className="sr-only">Open main menu</span>
            <svg
              className="h-5 w-5"
              aria-hidden="true"
              xmlns="http://www.w3.org/2000/svg"
              fill="none"
              viewBox="0 0 17 14"
            >
              <path
                stroke="currentColor"
                strokeLinecap="round"
                strokeLinejoin="round"
                strokeWidth="2"
                d="M1 1h15M1 7h15M1 13h15"
              />
            </svg>
          </button>
        </div>

        {/* Navigation Menu */}
        <div
          className={`${
            isMenuOpen ? "block" : "hidden"
          } w-full items-center justify-between md:order-1 md:flex md:w-auto`}
        >
          <ul className="mt-4 flex flex-col rounded-lg border border-gray-100 bg-gray-50 p-4 font-medium dark:border-gray-700 dark:bg-gray-800 md:mt-0 md:flex-row md:space-x-8 md:border-0 md:bg-white md:p-0 md:dark:bg-gray-900 rtl:space-x-reverse">
            <li>
              <Link
                to="/home"
                onClick={closeMenu}
                className="block rounded bg-blue-700 px-3 py-2 text-white md:bg-transparent md:p-0 md:text-blue-700 md:dark:text-blue-500"
              >
                Home
              </Link>
            </li>
            <li>
              <Link
                to="/about"
                onClick={closeMenu}
                className="block rounded px-3 py-2 text-gray-900 hover:bg-gray-100 dark:border-gray-700 dark:text-white dark:hover:bg-gray-700 dark:hover:text-white md:p-0 md:hover:bg-transparent md:hover:text-blue-700 md:dark:hover:bg-transparent md:dark:hover:text-blue-500"
              >
                About
              </Link>
            </li>
            <li>
              <Link
                to="/services"
                onClick={closeMenu}
                className="block rounded px-3 py-2 text-gray-900 hover:bg-gray-100 dark:border-gray-700 dark:text-white dark:hover:bg-gray-700 dark:hover:text-white md:p-0 md:hover:bg-transparent md:hover:text-blue-700 md:dark:hover:bg-transparent md:dark:hover:text-blue-500"
              >
                Services
              </Link>
            </li>
            <li>
              <Link
                to="/contact"
                onClick={closeMenu}
                className="block rounded px-3 py-2 text-gray-900 hover:bg-gray-100 dark:border-gray-700 dark:text-white dark:hover:bg-gray-700 dark:hover:text-white md:p-0 md:hover:bg-transparent md:hover:text-blue-700 md:dark:hover:bg-transparent md:dark:hover:text-blue-500"
              >
                Contact
              </Link>
            </li>
            <li>
              <div className="flex items-center justify-center gap-4 space-x-3 md:hidden md:space-x-0">
                <Link
                  to="/login"
                  onClick={closeMenu}
                  className="w-1/2 rounded-lg bg-gray-800 px-4 py-2 text-center text-sm font-medium text-white hover:bg-gray-900 focus:outline-none focus:ring-4 focus:ring-blue-300 dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800"
                >
                  Login
                </Link>
                <Link
                  to="/register"
                  onClick={closeMenu}
                  className="w-1/2 rounded-lg bg-gray-800 px-4 py-2 text-center text-sm font-medium text-white hover:bg-gray-900 focus:outline-none focus:ring-4 focus:ring-blue-300 dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800"
                >
                  Signup
                </Link>
              </div>
            </li>
          </ul>
        </div>
      </div>
    </nav>
  );
};

export default Navbar;
