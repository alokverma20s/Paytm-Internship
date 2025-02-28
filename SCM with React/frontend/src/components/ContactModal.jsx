import React, { useRef } from "react";
import defaultProfile from "../assets/images/defaultProfile.jpg";

const ContactModal = ({ contact, onClose }) => {
  if (!contact) return null;

  const modalRef = useRef(null);

  // Close modal when clicking outside
  const handleOutsideClick = (e) => {
    if (modalRef.current && !modalRef.current.contains(e.target)) {
      onClose();
    }
  };

  return (
    <div
      className="fixed inset-0 z-50 flex items-center justify-center bg-black bg-opacity-50 dark:bg-opacity-70"
      onClick={handleOutsideClick}
    >
      <div
        ref={modalRef}
        className="relative w-full max-w-2xl rounded-lg bg-white p-6 shadow-lg dark:bg-gray-800 dark:text-white"
      >
        {/* Modal Header */}
        <div className="flex items-center justify-between border-b pb-4 dark:border-gray-700">
          <div>
            <h3 className="text-xl font-semibold">
              {contact?.name || "Unknown"}
            </h3>
            <p className="text-gray-600 dark:text-gray-300">{contact?.email}</p>
          </div>
          <button
            className="text-gray-500 hover:text-gray-800 dark:hover:text-gray-300"
            onClick={onClose}
          >
            ✕
          </button>
        </div>

        {/* Modal Body */}
        <div className="space-y-4 p-4">
          <div className="flex items-center space-x-4">
            <img
              src={contact?.picture || defaultProfile}
              alt="Profile"
              className="h-16 w-16 rounded-full"
            />
            <div>
              <h2 className="text-lg font-semibold">{contact?.name}</h2>
              <p className="text-sm text-gray-500 dark:text-gray-400">
                {contact?.email}
              </p>
            </div>
          </div>

          <p className="text-gray-700 dark:text-gray-300">
            <strong>Phone:</strong> {contact?.phoneNumber || "N/A"}
          </p>
          <p className="text-gray-700 dark:text-gray-300">
            <strong>Address:</strong> {contact?.address || "N/A"}
          </p>
          <p className="text-gray-700 dark:text-gray-300">
            <strong>Description:</strong> {contact?.description || "N/A"}
          </p>
          <p className="text-gray-700 dark:text-gray-300">
            <strong>Website:</strong>{" "}
            {contact?.websiteLink ? (
              <a
                href={contact.websiteLink}
                className="text-blue-600 underline dark:text-blue-400"
                target="_blank"
                rel="noopener noreferrer"
              >
                {contact.websiteLink}
              </a>
            ) : (
              "N/A"
            )}
          </p>
          <p className="text-gray-700 dark:text-gray-300">
            <strong>LinkedIn:</strong>{" "}
            {contact?.linkedInLink ? (
              <a
                href={contact.linkedInLink}
                className="text-blue-600 underline dark:text-blue-400"
                target="_blank"
                rel="noopener noreferrer"
              >
                {contact.linkedInLink}
              </a>
            ) : (
              "N/A"
            )}
          </p>

          <div className="flex items-center">
            <span className="text-gray-700 dark:text-gray-300">Favorite:</span>
            <input
              type="checkbox"
              checked={contact?.favorite || false}
              disabled
              className="ml-2 h-5 w-5 rounded border-gray-300 text-blue-600"
            />
          </div>
        </div>

        {/* Modal Footer */}
        <div className="flex justify-end border-t pt-4 dark:border-gray-700">
          <button
            className="rounded-lg bg-blue-700 px-5 py-2 text-white hover:bg-blue-800 focus:outline-none focus:ring-4"
            onClick={onClose}
          >
            Close
          </button>
        </div>
      </div>
    </div>
  );
};

export default ContactModal;
