package teamPro.bbangShuttle.vo;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class ReviewVO {
  private int reviewNo;
  private String userID;
  private int itemNo;
  private String reviewTitle;
  private String reviewContent;
  private String regDate;
  private int reviewStar;
  private String imgName;
  private int cnt;
}