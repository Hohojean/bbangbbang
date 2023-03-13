package teamPro.bbangShuttle.dto;

import lombok.*;
import teamPro.bbangShuttle.vo.NoticeVO;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class NoticeDTO<T> {

  private String error;
  private List<T> noticeList;
  private NoticeVO noticeOne;

}
