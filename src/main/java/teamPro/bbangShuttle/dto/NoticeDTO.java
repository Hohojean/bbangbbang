package teamPro.bbangShuttle.dto;

import lombok.*;
import teamPro.bbangShuttle.vo.NoticeVO;

import java.util.List;

@Builder
@NoArgsConstructor // 파라미터가 없는 기본 생성자
@AllArgsConstructor // 모든 파라미터를 받는 생성자
@Getter
@Setter
@ToString
public class NoticeDTO<T> {

  private String error;
  private List<T> noticeList;
  private NoticeVO noticeOne;

}
