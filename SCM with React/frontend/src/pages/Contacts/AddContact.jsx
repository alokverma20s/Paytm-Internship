import React, { useEffect, useState } from "react";
import {
  createContact,
  getContact,
  updateContact,
} from "../../services/operations/ContactAPI";
import toast from "react-hot-toast";
import { useNavigate, useParams } from "react-router-dom";

const AddContact = () => {
  let { id } = useParams();
  const navigate = useNavigate();
  const [contact, setContact] = useState({
    name: "",
    email: "",
    phoneNumber: "",
    address: "",
    description: "",
    websiteLink: "",
    linkedInLink: "",
    favorite: false,
  });
  console.log(id);

  useEffect(() => {
    if (id) {
      const fetchContacts = async () => {
        try {
          const response = await getContact(id);

          if (response?.status === 200 && response?.data) {
            setContact(response.data); // Ensure we have valid data
          } else {
            throw new Error("Failed to fetch contact");
          }
        } catch (error) {
          toast.error(error?.message || "Failed to fetch contact");
          console.error("Error fetching contact:", error);
          setContact({}); // Ensure we don't set an undefined/null value
          navigate("/user/contacts"); // Redirect if error occurs
        }
      };

      fetchContacts(); // Call the async function
    }
  }, [id, navigate]);

  const [message, setMessage] = useState("Hello");

  const handleChange = (e) => {
    const { name, value, type, checked } = e.target;
    setContact({
      ...contact,
      [name]: type === "checkbox" ? checked : value,
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      if (id) {
        contact.id = id;
        await updateContact(contact);
        toast.success("Contact updated successfully!");
        navigate("/user/contacts");
        return;
      }
      const response = await createContact(contact);
      console.log(response);

      if (response.status === 201) {
        toast.success(response.data);
        setContact({
          name: "",
          email: "",
          phoneNumber: "",
          address: "",
          description: "",
          websiteLink: "",
          linkedInLink: "",
          favorite: false,
        });
        navigate("/user/contacts");
      } else {
        toast.error("Failed to add contact.");
      }
    } catch (error) {
      toast.error("Error: " + error.message);
    }
  };
  const handleReset = () =>
    setContact({
      name: "",
      email: "",
      phoneNumber: "",
      address: "",
      description: "",
      websiteLink: "",
      linkedInLink: "",
      favorite: false,
    });

  return (
    <div className="mb-10 flex min-h-screen items-center justify-center pt-20 sm:ml-64">
      <div className="w-[90vw] md:w-[60vw] lg:w-[60%] xl:w-[50%]">
        <div className="block rounded-xl border-t-[10px] border-green-700 bg-white p-6 shadow dark:border-blue-700 dark:bg-gray-800">
          <h5 className="mb-2 text-2xl font-bold tracking-tight text-gray-900 dark:text-white">
            Add New Contact
          </h5>
          <p className="font-normal text-gray-400 dark:text-gray-400">
            This contact will be stored in the cloud...
          </p>

          <form
            onSubmit={handleSubmit}
            onReset={handleReset}
            className="mt-5"
            noValidate
          >
            {/* Name */}
            <div className="mb-3">
              <label className="mb-2 block text-sm font-medium text-gray-900 dark:text-white">
                Contact Name:
              </label>
              <input
                type="text"
                name="name"
                value={contact.name}
                onChange={handleChange}
                className="block w-full rounded-lg border border-gray-300 bg-gray-50 p-2.5 text-sm text-gray-900 focus:border-blue-500 focus:ring-blue-500 dark:border-gray-600 dark:bg-gray-700 dark:text-white dark:placeholder-gray-400 dark:focus:border-blue-500 dark:focus:ring-blue-500"
                placeholder="Enter name"
                required
              />
            </div>

            {/* Email */}
            <div className="mb-3">
              <label className="mb-2 block text-sm font-medium text-gray-900 dark:text-white">
                Contact Email:
              </label>
              <input
                type="email"
                name="email"
                value={contact.email}
                onChange={handleChange}
                className="block w-full rounded-lg border border-gray-300 bg-gray-50 p-2.5 text-sm text-gray-900 focus:border-blue-500 focus:ring-blue-500 dark:border-gray-600 dark:bg-gray-700 dark:text-white dark:placeholder-gray-400 dark:focus:border-blue-500 dark:focus:ring-blue-500"
                placeholder="example@gmail.com"
                required
              />
            </div>

            {/* Phone Number */}
            <div className="mb-3">
              <label className="mb-2 block text-sm font-medium text-gray-900 dark:text-white">
                Contact Phone:
              </label>
              <input
                type="text"
                name="phoneNumber"
                value={contact.phoneNumber}
                onChange={handleChange}
                className="block w-full rounded-lg border border-gray-300 bg-gray-50 p-2.5 text-sm text-gray-900 focus:border-blue-500 focus:ring-blue-500 dark:border-gray-600 dark:bg-gray-700 dark:text-white dark:placeholder-gray-400 dark:focus:border-blue-500 dark:focus:ring-blue-500"
                placeholder="Enter phone number"
                required
              />
            </div>

            {/* Address */}
            <div className="mb-3">
              <label className="mb-2 block text-sm font-medium text-gray-900 dark:text-white">
                Contact Address:
              </label>
              <textarea
                name="address"
                value={contact.address}
                onChange={handleChange}
                className="block w-full resize-none rounded-lg border border-gray-300 bg-gray-50 p-2.5 text-sm text-gray-900 focus:border-blue-500 focus:ring-blue-500 dark:border-gray-600 dark:bg-gray-700 dark:text-white dark:placeholder-gray-400 dark:focus:border-blue-500 dark:focus:ring-blue-500"
                placeholder="Enter address"
                rows="3"
                required
              />
            </div>

            {/* Description */}
            <div className="mb-3">
              <label className="mb-2 block text-sm font-medium text-gray-900 dark:text-white">
                Contact Description:
              </label>
              <textarea
                name="description"
                value={contact.description}
                onChange={handleChange}
                className="block w-full resize-none rounded-lg border border-gray-300 bg-gray-50 p-2.5 text-sm text-gray-900 focus:border-blue-500 focus:ring-blue-500 dark:border-gray-600 dark:bg-gray-700 dark:text-white dark:placeholder-gray-400 dark:focus:border-blue-500 dark:focus:ring-blue-500"
                placeholder="Write about the contact"
                rows="3"
              />
            </div>

            {/* Social Links */}
            <div className="mb-3 grid grid-cols-2 gap-3">
              <div className="">
                <label className="mb-2 block text-sm font-medium text-gray-900 dark:text-white">
                  Website Link:
                </label>
                <input
                  type="text"
                  name="websiteLink"
                  value={contact.websiteLink}
                  onChange={handleChange}
                  className="block w-full rounded-lg border border-gray-300 bg-gray-50 p-2.5 text-sm text-gray-900 focus:border-blue-500 focus:ring-blue-500 dark:border-gray-600 dark:bg-gray-700 dark:text-white dark:placeholder-gray-400 dark:focus:border-blue-500 dark:focus:ring-blue-500"
                  placeholder="Website URL"
                />
              </div>
              <div className="">
                <label className="mb-2 block text-sm font-medium text-gray-900 dark:text-white">
                  Linkedin Link:
                </label>
                <input
                  type="text"
                  name="linkedInLink"
                  value={contact.linkedInLink}
                  onChange={handleChange}
                  className="block w-full rounded-lg border border-gray-300 bg-gray-50 p-2.5 text-sm text-gray-900 focus:border-blue-500 focus:ring-blue-500 dark:border-gray-600 dark:bg-gray-700 dark:text-white dark:placeholder-gray-400 dark:focus:border-blue-500 dark:focus:ring-blue-500"
                  placeholder="LinkedIn URL"
                />
              </div>
            </div>

            {/* Favorite Checkbox */}
            <div className="mb-3 flex items-center">
              <input
                id="favoriteCheck"
                type="checkbox"
                name="favorite"
                checked={contact.favorite}
                onChange={handleChange}
                className="h-4 w-4 rounded border-gray-300 text-blue-600"
              />
              <label
                htmlFor="favoriteCheck"
                className="ml-2 text-sm font-medium text-gray-900 dark:text-gray-300"
              >
                Mark as favorite
              </label>
            </div>

            {/* Buttons */}
            <div className="flex justify-center space-x-3">
              <button
                type="submit"
                className="rounded bg-gray-800 px-3 py-2 text-white hover:bg-gray-700 dark:bg-blue-700 dark:hover:bg-blue-800"
              >
                Add Contact
              </button>
              <button
                type="reset"
                onClick={handleReset}
                className="rounded bg-red-500 px-3 py-2 text-white hover:bg-red-400"
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

export default AddContact;
