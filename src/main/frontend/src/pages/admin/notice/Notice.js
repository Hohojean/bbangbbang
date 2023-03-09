import { Suspense } from "react";
import { useLoaderData, json, defer, Await } from "react-router-dom";

import NoticeList from "../../../components/NoticeList";

// import NoticesList from "../components/EoticesList";

function NoticePage() {
  const { notices } = useLoaderData();
  console.log(notices);

  return (
    <Suspense fallback={<p style={{ textAlign: "center" }}>Loading...</p>}>
      <Await resolve={notices}>
        {(loadedEvents) => <NoticeList notices={loadedEvents} />}
      </Await>
    </Suspense>
  );
}

export default NoticePage;

async function loadNotice() {
  const response = await fetch("http://127.0.0.1:8080/noticelist");

  if (!response.ok) {
    // return { isError: true, message: 'Could not fetch notices.' };
    // throw new Response(JSON.stringify({ message: 'Could not fetch notices.' }), {
    //   status: 500,
    // });
    throw json(
      { message: "Could not fetch notice." },
      {
        status: 500,
      }
    );
  } else {
    const resData = await response.json();

    return resData;
  }
}

export function loader() {
  return defer({
    notices: loadNotice(),
  });
}