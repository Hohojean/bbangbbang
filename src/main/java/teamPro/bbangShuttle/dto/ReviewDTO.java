package teamPro.bbangShuttle.dto;

import lombok.*;
import teamPro.bbangShuttle.vo.NoticeVO;
import teamPro.bbangShuttle.vo.ReviewVO;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ReviewDTO<T> {

  private String error;
  private List<T> reviewList;
  private ReviewVO reviewOne;
}
