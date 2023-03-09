import { Suspense } from "react";
import {
  useRouteLoaderData,
  json,
  redirect,
  defer,
  Await,
} from "react-router-dom";

import NoticeItem from "../../../components/NoticeItem";
import NoticesList from "../../../components/NoticesList";
import { getAuthToken } from "../../../util/auth";

function NoticeDetailPage() {
  const { notice, notices } = useRouteLoaderData("notice-detail");

  return (
    <>
      <Suspense fallback={<p style={{ textAlign: "center" }}>Loading...</p>}>
        <Await resolve={notice}>
          {(loadedNotice) => <NoticeItem notice={loadedNotice} />}
        </Await>
      </Suspense>
      <Suspense fallback={<p style={{ textAlign: "center" }}>Loading...</p>}>
        <Await resolve={notices}>
          {(loadedNotices) => <NoticesList notices={loadedNotices} />}
        </Await>
      </Suspense>
    </>
  );
}

export default NoticeDetailPage;

async function loadNotice(id) {
  const response = await fetch("http://localhost:8080/notices/" + id);

  if (!response.ok) {
    throw json(
      { message: "Could not fetch details for selected Notice." },
      {
        status: 500,
      }
    );
  } else {
    const resData = await response.json();
    return resData.notice;
  }
}

async function loadNotices() {
  const response = await fetch("http://localhost:8080/notices");

  if (!response.ok) {
    // return { isError: true, message: 'Could not fetch notices.' };
    // throw new Response(JSON.stringify({ message: 'Could not fetch notices.' }), {
    //   status: 500,
    // });
    throw json(
      { message: "Could not fetch notices." },
      {
        status: 500,
      }
    );
  } else {
    const resData = await response.json();
    return resData.Notices;
  }
}

export async function loader({ request, params }) {
  const id = params.noticeId;

  return defer({
    notice: await loadNotice(id),
    notices: loadNotices(),
  });
}

export async function action({ params, request }) {
  const noticeId = params.noticeId;

  const token = getAuthToken();
  const response = await fetch("http://localhost:8080/notices/" + noticeId, {
    method: request.method,
    headers: {
      Authorization: "Bearer " + token,
    },
  });

  if (!response.ok) {
    throw json(
      { message: "Could not delete notice." },
      {
        status: 500,
      }
    );
  }
  return redirect("/notices");
}
