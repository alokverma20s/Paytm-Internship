import React, { useEffect, useState } from "react";
import { FaEarthAmericas } from "react-icons/fa6";
import { FaLinkedin } from "react-icons/fa";
import { FaEye, FaUserEdit } from "react-icons/fa";
import { MdDelete, MdFavorite, MdFavoriteBorder } from "react-icons/md";
import toast from "react-hot-toast";

import {
  deleteContact,
  getAllContact,
  searchContact,
  updateContact,
} from "../../services/operations/ContactAPI";
import defaultPicture from "../../assets/images/defaultProfile.jpg";
import ContactModal from "../../components/ContactModal";
import DeleteConfirmationModal from "../../components/DeleteConfirmationModal";
import { useNavigate } from "react-router-dom";

const ContactsPage = () => {
  const navigate = useNavigate();
  const [contacts, setContacts] = useState([]);
  const [searchField, setSearchField] = useState("name");
  const [searchValue, setSearchValue] = useState("");
  const [selectedContact, setSelectedContact] = useState(null);
  const [deleteId, setDeleteId] = useState(null);
  const [currentPage, setCurrentPage] = useState(0);
  const [totalPages, setTotalPages] = useState(1);
  const [pageSize, setPageSize] = useState(10);

  useEffect(() => {
    fetchContacts();
  }, [currentPage, pageSize]);

  const fetchContacts = async () => {
    try {
      const response = await getAllContact(currentPage, pageSize);
      setContacts(response.content);
      setCurrentPage(response.pageable.pageNumber);
      setTotalPages(response.totalPages);
    } catch (error) {
      console.error("Error fetching contacts:", error);
    }
  };

  const handleSearch = async (e) => {
    e.preventDefault();
    try {
      if (searchField === "") {
        toast.error("Please select a search field");
        return;
      } else if (searchValue === "") {
        toast.error("Please enter a search value");
        return;
      }
      // Implement API call for searching contacts based on `searchField` & `searchValue`
      console.log(`Searching for ${searchField}: ${searchValue}`);
      const response = await searchContact(
        searchField,
        searchValue,
        0,
        pageSize
      );
      setContacts(response.content);
    } catch (error) {
      console.error("Error during search:", error);
    }
  };

  const handleReset = () => {
    setSearchField("name");
    setSearchValue("");
    fetchContacts();
  };

  const deleteContactHandler = async () => {
    console.log("Delete Contact id: " + deleteId.id);

    const response = await deleteContact(deleteId.id);
    fetchContacts();
    console.log(response);
    setDeleteId(null);
  };

  const markNotFavorite = async (contact) => {
    contact.favorite = false;
    const response = await updateContact(contact);
    fetchContacts();
  };
  const markFavorite = async (contact) => {
    contact.favorite = true;
    const response = await updateContact(contact);
    fetchContacts();
  };

  const openContactModal = (contact) => setSelectedContact(contact);
  const closeContactModal = () => setSelectedContact(null);
  const openDeleteModal = (contact) => setDeleteId(contact);
  const closeDeleteModal = () => setDeleteId(null);

  return (
    <div className="sm:ml-72 pt-20 text-gray-950 dark:text-gray-50">
      <h1 className="text-5xl text-center">All Your Contacts</h1>
      <p className="text-center">List of all contacts...</p>

      {/* Search Bar */}
      <form
        onSubmit={handleSearch}
        onReset={handleReset}
        className="p-5 flex space-x-3"
      >
        <select
          value={searchField}
          onChange={(e) => setSearchField(e.target.value)}
          className="border dark:border-gray-500 p-2 rounded dark:bg-gray-700 dark:text-gray-300"
        >
          <option value="">Select Field</option>
          <option value="name">Name</option>
          <option value="phone">Phone</option>
          <option value="email">Email</option>
        </select>
        <input
          type="text"
          value={searchValue}
          onChange={(e) => setSearchValue(e.target.value)}
          placeholder="Search for users"
          className="border dark:border-gray-500 p-2 rounded dark:bg-gray-700 dark:text-gray-200"
        />
        <button
          type="submit"
          className="bg-gray-800 text-gray-200 hover:bg-gray-900 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm px-4 py-2 text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800"
        >
          Search
        </button>
        <button
          onClick={handleReset}
          type="reset"
          className="bg-red-500 text-gray-200 hover:bg-red-600 focus:ring-4 focus:outline-none focus:ring-red-300 font-medium rounded-lg text-sm px-4 py-2 text-center dark:bg-red-600 dark:hover:bg-red-700 dark:focus:ring-red-800"
        >
          Reset
        </button>
      </form>

      {/* Contacts Table */}
      <div className="overflow-x-auto">
        <table className="w-full text-sm text-gray-500 dark:text-white">
          <thead className="bg-gray-100 dark:bg-gray-700">
            <tr>
              <th className="p-3 text-start">Name & Email</th>
              <th className="p-3 text-start">Favorite</th>
              <th className="p-3 text-start">Phone</th>
              <th className="p-3 text-start">Links</th>
              <th className="p-3 text-start">Action</th>
            </tr>
          </thead>
          <tbody>
            {contacts?.length > 0 ? (
              contacts?.map((contact) => (
                <tr
                  key={contact?.id}
                  className="border-b hover:bg-gray-50 dark:hover:bg-gray-700"
                >
                  <td className="p-4 flex items-center">
                    <img
                      src={contact?.picture || defaultPicture}
                      alt="Profile"
                      className="w-10 h-10 rounded-full"
                    />
                    <div className="ml-3">
                      <p className="font-semibold">{contact.name}</p>
                      <p className="text-gray-500">{contact.email}</p>
                    </div>
                  </td>
                  <td className="p-3 text-2xl">
                    <button
                      onClick={() =>
                        contact?.favorite
                          ? markNotFavorite(contact)
                          : markFavorite(contact)
                      }
                    >
                      {contact?.favorite ? (
                        <MdFavorite className="cursor-pointer" />
                      ) : (
                        <MdFavoriteBorder className="cursor-pointer" />
                      )}
                    </button>
                  </td>
                  <td className="p-3">{contact?.phoneNumber}</td>
                  <td className="p-3 flex justify-start items-center gap-4">
                    {contact?.websiteLink && (
                      <a
                        href={contact?.websiteLink}
                        target="_blank"
                        rel="noopener noreferrer"
                      >
                        <FaEarthAmericas className="text-xl" />
                      </a>
                    )}
                    {contact?.linkedInLink && (
                      <a
                        href={contact?.linkedInLink}
                        target="_blank"
                        rel="noopener noreferrer"
                      >
                        <FaLinkedin className="text-xl" />
                      </a>
                    )}
                  </td>
                  <td className="p-3">
                    <button
                      title="Edit Contact"
                      onClick={() => navigate(`edit/${contact.id}`)}
                      className="mr-2"
                    >
                      <FaUserEdit className="text-xl" />
                    </button>
                    <button
                      title="Delete Contact"
                      onClick={() => openDeleteModal(contact)}
                      className="mr-2"
                    >
                      <MdDelete className="text-xl" />
                    </button>
                    <button
                      title="View Contact"
                      onClick={() => openContactModal(contact)}
                    >
                      <FaEye className="text-xl" />
                    </button>
                  </td>
                </tr>
              ))
            ) : (
              <tr>
                <td colSpan="4" className="text-center py-4">
                  No results found
                </td>
              </tr>
            )}
          </tbody>
        </table>
      </div>

      {/* Pagination */}
      <div className="flex justify-between items-center mt-4">
        {/* Page Size Selector */}
        <select
          value={pageSize}
          onChange={(e) => setPageSize(parseInt(e.target.value))}
          className="border p-2 rounded dark:bg-gray-700 dark:text-white"
        >
          {[1, 5, 10, 20, 30, 50].map((size) => (
            <option key={size} value={size}>
              {size}
            </option>
          ))}
        </select>

        {/* Pagination Controls */}
        <div className="flex space-x-2">
          <button
            onClick={() => setCurrentPage(currentPage - 1)}
            className="p-2 border rounded disabled:text-gray-400 disabled:dark:border-gray-500 disabled:dark:text-gray-500 cursor-pointer disabled:cursor-not-allowed"
            disabled={currentPage === 0}
          >
            Previous
          </button>

          {/* First Page */}
          {currentPage > 1 && (
            <>
              <button
                onClick={() => setCurrentPage(0)}
                className="px-4 py-2 border rounded"
              >
                1
              </button>
              {currentPage > 2 && <span className="px-2">...</span>}
            </>
          )}

          {/* Middle Pages */}
          {[...Array(totalPages)]
            .slice(
              Math.max(0, currentPage - 1),
              Math.min(totalPages, currentPage + 2)
            )
            .map((_, index) => {
              const page = Math.max(0, currentPage - 1) + index;
              return (
                <button
                  key={page}
                  onClick={() => setCurrentPage(page)}
                  className={`px-4 py-2 border rounded ${
                    currentPage === page
                      ? "bg-blue-500 text-white"
                      : "hover:bg-gray-200"
                  }`}
                >
                  {page + 1}
                </button>
              );
            })}

          {/* Last Page */}
          {currentPage < totalPages - 2 && (
            <>
              <span className="px-2">...</span>
              <button
                onClick={() => setCurrentPage(totalPages - 1)}
                className="px-4 py-2 border rounded"
              >
                {totalPages}
              </button>
            </>
          )}

          {/* Next Button */}

          <button
            onClick={() => setCurrentPage(currentPage + 1)}
            className="p-2 border rounded disabled:text-gray-400 disabled:dark:border-gray-500 disabled:dark:text-gray-500 cursor-pointer disabled:cursor-not-allowed"
            disabled={currentPage === totalPages - 1 || totalPages === 0}
          >
            Next
          </button>
        </div>
        <div className=""></div>
      </div>

      {/* Modals */}
      {selectedContact && (
        <ContactModal contact={selectedContact} onClose={closeContactModal} />
      )}
      {deleteId && (
        <DeleteConfirmationModal
          contact={deleteId}
          onClose={closeDeleteModal}
          onDelete={deleteContactHandler}
        />
      )}
    </div>
  );
};

export default ContactsPage;
