import { NavLink, useRouteLoaderData } from "react-router-dom";

import classes from "./AdminNavigation.module.css";

function AdminNavigation() {
  return (
    <header className={classes.header}>
      <nav>
        <ul className={classes.list}>
          <li>
            <NavLink
              to="/admin/notice"
              className={({ isActive }) =>
                isActive ? classes.active : undefined
              }
              end
            >
              공지사항
            </NavLink>
          </li>

          <li>
            <NavLink
              to="/admin/costomerservice"
              className={({ isActive }) =>
                isActive ? classes.active : undefined
              }
            >
              고객서비스
            </NavLink>
          </li>

          <li>
            <NavLink
              to="/admin/inventorymanage"
              className={({ isActive }) =>
                isActive ? classes.active : undefined
              }
            >
              재고관리
            </NavLink>
          </li>
          <li>
            <NavLink
              to="/admin/orderlist"
              className={({ isActive }) =>
                isActive ? classes.active : undefined
              }
            >
              주문관리
            </NavLink>
          </li>
          <li>
            <NavLink
              to="/admin/returnlist"
              className={({ isActive }) =>
                isActive ? classes.active : undefined
              }
            >
              판매관리
            </NavLink>
          </li>
          <li>
            <NavLink
              to="/admin/userlist"
              className={({ isActive }) =>
                isActive ? classes.active : undefined
              }
            >
              회원관리
            </NavLink>
          </li>
        </ul>
      </nav>
    </header>
  );
}

export default AdminNavigation;
