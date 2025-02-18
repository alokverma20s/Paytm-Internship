import { Link } from "react-router-dom";
import { IoMdContact } from "react-icons/io";
import { RiContactsBook2Fill } from "react-icons/ri";
import { IoMdPersonAdd } from "react-icons/io";
import { IoLogOut } from "react-icons/io5";
import { useAuth } from "../providers/AuthProvider";
import { logout } from "../redux/slices/authSlice";
import { useDispatch } from "react-redux";

const Sidebar = () => {
  const { logoutfunction } = useAuth();
  const dispatch = useDispatch();
  const handleLogout = () => {
    logoutfunction();
    dispatch(logout());
  };
  return (
    <aside
      id="logo-sidebar"
      className="fixed top-0 left-0 z-40 w-64 h-screen transition-transform -translate-x-full sm:translate-x-0 bg-gray-50 dark:bg-gray-800 pt-20 px-3 py-4 overflow-y-auto"
      aria-label="Sidebar"
    >
      <div className="flex items-center ps-2.5 mb-5">
        <img
          src="https://flowbite.com/docs/images/logo.svg"
          className="h-6 me-3 sm:h-7"
          alt="Flowbite Logo"
        />
        <span className="self-center text-xl font-semibold whitespace-nowrap dark:text-white">
          Welcome to SCM
        </span>
      </div>
      <ul className="space-y-2 font-medium">
        <li>
          <Link
            to="/user/profile"
            className="flex items-center p-2 text-gray-900 rounded-lg dark:text-white hover:bg-gray-100 dark:hover:bg-gray-700 group"
          >
            <IoMdContact className="text-xl" />
            <span className="flex-1 ms-2 whitespace-nowrap">Profile</span>
          </Link>
        </li>
        <li>
          <Link
            to="/user/contacts"
            className="flex items-center p-2 text-gray-900 rounded-lg dark:text-white hover:bg-gray-100 dark:hover:bg-gray-700 group"
          >
            <RiContactsBook2Fill className="text-xl" />
            <span className="flex-1 ms-3 whitespace-nowrap">Contacts</span>
          </Link>
        </li>
        <li>
          <Link
            to="/user/contacts/add"
            className="flex items-center p-2 text-gray-900 rounded-lg dark:text-white hover:bg-gray-100 dark:hover:bg-gray-700 group"
          >
            <IoMdPersonAdd className="text-xl" />
            <span className="flex-1 ms-3 whitespace-nowrap">Add Contact</span>
          </Link>
        </li>
        <li>
          <button
            onClick={handleLogout}
            className="flex w-full justify-start items-center p-2 text-gray-900 rounded-lg dark:text-white hover:bg-gray-100 dark:hover:bg-gray-700 group"
          >
            <IoLogOut className="text-xl" />
            <span className="ms-3 whitespace-nowrap">Log out</span>
          </button>
        </li>
      </ul>
    </aside>
  );
};

export default Sidebar;
