package teamPro.bbangShuttle.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class NoticeVO {
  private int noticeNo;
  private String noticeTitle;
  private String noticeContent;
  private String regDate;
  private int cnt;
}
