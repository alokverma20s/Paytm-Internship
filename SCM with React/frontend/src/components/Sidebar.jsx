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
      className="fixed left-0 top-0 z-40 h-screen w-64 -translate-x-full overflow-y-auto bg-gray-50 px-3 py-4 pt-20 transition-transform dark:bg-gray-800 sm:translate-x-0"
      aria-label="Sidebar"
    >
      <div className="mb-5 flex items-center ps-2.5">
        <img
          src="https://flowbite.com/docs/images/logo.svg"
          className="me-3 h-6 sm:h-7"
          alt="Flowbite Logo"
        />
        <span className="self-center whitespace-nowrap text-xl font-semibold dark:text-white">
          Welcome to SCM
        </span>
      </div>
      <ul className="space-y-2 font-medium">
        <li>
          <Link
            to="/user/profile"
            className="group flex items-center rounded-lg p-2 text-gray-900 hover:bg-gray-100 dark:text-white dark:hover:bg-gray-700"
          >
            <IoMdContact className="text-xl" />
            <span className="ms-2 flex-1 whitespace-nowrap">Profile</span>
          </Link>
        </li>
        <li>
          <Link
            to="/user/contacts"
            className="group flex items-center rounded-lg p-2 text-gray-900 hover:bg-gray-100 dark:text-white dark:hover:bg-gray-700"
          >
            <RiContactsBook2Fill className="text-xl" />
            <span className="ms-3 flex-1 whitespace-nowrap">Contacts</span>
          </Link>
        </li>
        <li>
          <Link
            to="/user/contacts/add"
            className="group flex items-center rounded-lg p-2 text-gray-900 hover:bg-gray-100 dark:text-white dark:hover:bg-gray-700"
          >
            <IoMdPersonAdd className="text-xl" />
            <span className="ms-3 flex-1 whitespace-nowrap">Add Contact</span>
          </Link>
        </li>
        <li>
          <button
            onClick={handleLogout}
            className="group flex w-full items-center justify-start rounded-lg p-2 text-gray-900 hover:bg-gray-100 dark:text-white dark:hover:bg-gray-700"
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
