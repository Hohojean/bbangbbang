import { Outlet } from "react-router-dom";

import AdminNavigation from "../../components/AdminNavigation";

function AdminRootLayout() {
  return (
    <>
      <AdminNavigation />
      <Outlet />
    </>
  );
}

export default AdminRootLayout;
