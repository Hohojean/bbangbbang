package teamPro.bbangShuttle.vo;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class QnAVO {
  private int qnaNo;
  private String userID;
  private String qnaTitle;
  private String qnaContent;
  private String q_regDate;
  private String qnaAnswer;
  private String a_regDate;
}
