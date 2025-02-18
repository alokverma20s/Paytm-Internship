const BASE_URL = import.meta.env.VITE_BASE_URL

export const authEndpoint ={
  LOGIN_API:`${BASE_URL}/auth/login`,
  SIGNUP_API: `${BASE_URL}/auth/register`
} ;

export const contactEndpoint = {
  GET_ALL_CONTACTS: `${BASE_URL}/api/contacts`,
  CREATE_CONTACT: `${BASE_URL}/api/contacts/add`,
  SEARCH_CONTACT: `${BASE_URL}/api/contacts/search`,
  UPDATE_CONTACT: `${BASE_URL}/api/contacts`,
  GET_CONTACT: `${BASE_URL}/api/contacts`,
  DELETE_CONTACT: `${BASE_URL}/api/contacts`
}

export const userEndpoint = {
  GET_USER_INFO: `${BASE_URL}/user/profile`,
}
