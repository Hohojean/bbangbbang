// import { useLoaderData } from 'react-router-dom';
import { Link } from "react-router-dom";

import classes from "./NoticeList.module.css";

function NoticeList({ notices }) {
  // const notices = useLoaderData();
  console.log(notices);

  return (
    <div className={classes.notice}>
      <ul className={classes.list}>
        {notices?.map((notice) => (
          <li key={notice.noticeNo} className={classes.item}>
            {/* <Link to={`/notice/${notice.id}`}> */}
            {/* <img src={notices.image} alt={notices.title} /> */}
            <div className={classes.content}>
              <h2>{notice.noticeTitle}</h2>
              <time>{notice.regDate}</time>
              <span>{notice.cnt}</span>
            </div>
            {/* </Link> */}
          </li>
        ))}
      </ul>
    </div>
  );
}

export default NoticeList;
