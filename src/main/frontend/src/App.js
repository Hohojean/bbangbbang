import { RouterProvider, createBrowserRouter } from "react-router-dom";

import EditEventPage from "./pages/EditEvent";
import ErrorPage from "./pages/Error";
import EventDetailPage, {
  loader as eventDetailLoader,
  action as deleteEventAction,
} from "./pages/EventDetail";
import EventsPage, { loader as eventsLoader } from "./pages/Events";
import EventsRootLayout from "./pages/EventsRoot";
import HomePage from "./pages/Home";
import NewEventPage from "./pages/NewEvent";
import RootLayout from "./pages/Root";
import { action as manipulateEventAction } from "./components/EventForm";
import NewsletterPage, { action as newsletterAction } from "./pages/Newsletter";
import AuthenticationPage, {
  action as authAction,
} from "./pages/Authentication";
import { action as logoutAction } from "./pages/Logout";
import { checkAuthLoader, tokenLoader } from "./util/auth";
import AdminRootLayout from "./pages/admin/AdminRoot";

import NoticePage, {
  loader as noticeLoader,
} from "./pages/admin/notice/Notice";
import CostomerServicePage from "./pages/admin/costomerService/CostomerService";
import InventoryManagePage from "./pages/admin/inventoryManage/InventoryManage";
import OrderListPage from "./pages/admin/salesManage/OrderList";
import ReturnListPage from "./pages/admin/salesManage/ReturnList";
import UserListPage from "./pages/admin/userList/UserList";

const router = createBrowserRouter([
  {
    path: "/",
    element: <RootLayout />,
    errorElement: <ErrorPage />,
    id: "root",
    loader: tokenLoader,
    children: [
      { index: true, element: <HomePage /> },
      {
        path: "admin",
        element: <AdminRootLayout />,
        children: [
          // {
          //   index: true,
          //   element: <EventsPage />,
          //   loader: eventsLoader,
          // },
          {
            path: "notice",
            element: <NoticePage />,
            loader: noticeLoader,
            // children: [
            //   {
            //     path: ":noticeId",
            //     id: "notice-detail",
            //     loader: noticeDetailLoader,
            //     children: [
            //       {
            //         index: true,
            //         element: <noticeDetailPage />,
            //         action: deleteNoticeAction,
            //       },
            //       {
            //         path: "edit",
            //         element: <EditNoticePage />,
            //         action: manipulateEventAction,
            //         loader: checkAuthLoader,
            //       },
            //     ],
            //   },
            // ],
          },
          {
            path: "costomerservice",
            element: <CostomerServicePage />,
          },
          {
            path: "inventorymanage",
            element: <InventoryManagePage />,
          },
          {
            path: "orderlist",
            element: <OrderListPage />,
          },
          {
            path: "returnlist",
            element: <ReturnListPage />,
          },
          {
            path: "userlist",
            element: <UserListPage />,
          },
        ],
      },

      {
        path: "events",
        element: <EventsRootLayout />,
        children: [
          {
            index: true,
            element: <EventsPage />,
            loader: eventsLoader,
          },
          {
            path: ":eventId",
            id: "event-detail",
            loader: eventDetailLoader,
            children: [
              {
                index: true,
                element: <EventDetailPage />,
                action: deleteEventAction,
              },
              {
                path: "edit",
                element: <EditEventPage />,
                action: manipulateEventAction,
                loader: checkAuthLoader,
              },
            ],
          },
          {
            path: "new",
            element: <NewEventPage />,
            action: manipulateEventAction,
          },
        ],
      },
      {
        path: "auth",
        element: <AuthenticationPage />,
        action: authAction,
      },
      {
        path: "newsletter",
        element: <NewsletterPage />,
        action: newsletterAction,
      },
      {
        path: "logout",
        action: logoutAction,
      },
    ],
  },
]);

function App() {
  return <RouterProvider router={router} />;
}

export default App;
