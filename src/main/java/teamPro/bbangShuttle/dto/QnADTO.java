package teamPro.bbangShuttle.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import teamPro.bbangShuttle.vo.QnAVO;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class QnADTO<T> {

  private String error;
  private List<T> qnaList;
  private QnAVO qnaOne;
}
