package teamPro.bbangShuttle.dto;

import lombok.*;
import teamPro.bbangShuttle.vo.QnAVO;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class QnADTO<T> {

  private String error;
  private List<T> qnaList;
  private QnAVO qnaOne;
}
