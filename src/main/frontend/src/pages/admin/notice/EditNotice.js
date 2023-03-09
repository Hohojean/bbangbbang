import { useRouteLoaderData } from "react-router-dom";

import EventForm from "../components/EventForm";

function EditNoticePage() {
  const data = useRouteLoaderData("event-detail");
  console.log(data);

  return <EventForm method="patch" event={data.event} />;
}

export default EditNoticePage;
