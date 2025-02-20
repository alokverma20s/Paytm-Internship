import React from "react";
import { useSelector } from "react-redux";
import defaultProfilePicture from "../../assets/images/defaultProfile.jpg";

const ProfilePage = () => {
  const user = useSelector((state) => {
    return state.auth.user;
  });
  return (
    <div className="flex min-h-screen items-center justify-center pt-20 sm:pl-64">
      <div className="w-full max-w-2xl rounded-lg bg-white p-6 shadow-lg dark:bg-gray-800">
        <div className="flex flex-col items-center text-gray-800 dark:text-gray-300">
          <img
            src={user?.profilePic || defaultProfilePicture}
            alt="Profile Photo"
            className="mb-4 h-44 w-44 rounded-full object-cover shadow-lg"
          />
          <h2 className="mb-2 text-2xl font-semibold">{user?.name}</h2>
          <p className="mb-2">{user?.email}</p>
          <p className="mb-2">{user?.phoneNumber}</p>
          <p className="mb-4 text-center">{user?.about}</p>
          <div className="flex w-full justify-between">
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
