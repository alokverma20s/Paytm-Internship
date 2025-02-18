import toast from "react-hot-toast";
import { apiConnector } from "../APIConnectors";
import { contactEndpoint } from "../apis";

const {
  CREATE_CONTACT,
  DELETE_CONTACT,
  GET_ALL_CONTACTS,
  GET_CONTACT,
  SEARCH_CONTACT,
  UPDATE_CONTACT,
} = contactEndpoint;

// Create a new contact
export const createContact = async (contactData) => {
  try {
    const response = await apiConnector("post", CREATE_CONTACT, contactData);
    return response;
  } catch (error) {
    toast.error(error.message);
    console.error("Error creating contact:", error);
  }
};

export const getAllContact = async (
  page = 0,
  size = 10,
  sortBy = "name",
  direction = "asc"
) => {
  try {
    const response = await apiConnector(
      "get",
      GET_ALL_CONTACTS,
      null,
      {},
      { page: page, size: size, sortBy: sortBy, direction: direction }
    );
    return response.data;
  } catch (error) {
    toast.error(error.message);
    console.error("Error fetching contacts:", error);
  }
};
export const searchContact = async (
  field,
  value,
  page = 0,
  size = 10,
  sortBy = "name",
  direction = "asc"
) => {
  try {
    console.log(SEARCH_CONTACT);

    const response = await apiConnector(
      "post",
      SEARCH_CONTACT,
      { field, value },
      {},
      { page: page, size: size, sortBy: sortBy, direction: direction }
    );
    return response.data;
  } catch (error) {
    toast.error(error.message);
    console.error("Error fetching contacts:", error);
  }
};
export const deleteContact = async (id) => {
  try {
    console.log(SEARCH_CONTACT);

    const response = await apiConnector("delete", `${DELETE_CONTACT}/${id}`);
    return response.data;
  } catch (error) {
    toast.error(error.message);
    console.error("Error fetching contacts:", error);
  }
};

export const updateContact = async (contact) => {
  try {
    const response = await apiConnector("put", `${UPDATE_CONTACT}/${contact.id}`, contact);
    return response.data;
  } catch (error) {
    toast.error(error.message);
    console.error("Error fetching contacts:", error);
  }
};

export const getContact = async (id) => {
  try {
    const response = await apiConnector("get", `${GET_CONTACT}/${id}`);
    return response;
  } catch (error) {
    toast.error(error.message);
    console.error("Error fetching contacts:", error);
  }
}
