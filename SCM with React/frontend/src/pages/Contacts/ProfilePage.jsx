import React from "react";
import { useSelector } from "react-redux";
import defaultProfilePicture from "../../assets/images/defaultProfile.jpg"

const ProfilePage = () => {
  const user = useSelector((state) =>{
    return state.auth.user;
  });
  return (
    <div className="sm:pl-64 pt-20 min-h-screen flex justify-center items-center">
      <div className="bg-white dark:bg-gray-800 p-6 rounded-lg shadow-lg max-w-2xl w-full">
        <div className="flex flex-col items-center text-gray-800 dark:text-gray-300">
          <img
            src={user?.profilePic || defaultProfilePicture}
            alt="Profile Photo"
            className="w-44 h-44 rounded-full shadow-lg object-cover mb-4"
          />
          <h2 className="text-2xl font-semibold mb-2">{user?.name}</h2>
          <p className="mb-2">{user?.email}</p>
          <p className="mb-2">{user?.phoneNumber}</p>
          <p className="mb-4 text-center">{user?.about}</p>
          <div className="w-full flex justify-between">
            <p className="text-sm text-gray-500 dark:text-gray-400">
              Email Verified:{" "}
              <span className="font-medium text-gray-800 dark:text-gray-300">
                {user?.emailVerified ? "YES" : "NO"}
              </span>
            </p>
            <p className="text-sm text-gray-500 dark:text-gray-400">
              Phone Verified:{" "}
              <span className="font-medium text-gray-800 dark:text-gray-300">
                {user?.phoneVerified ? "YES" : "NO"}
              </span>
            </p>
          </div>
        </div>
      </div>
    </div>
  );
};

export default ProfilePage;
