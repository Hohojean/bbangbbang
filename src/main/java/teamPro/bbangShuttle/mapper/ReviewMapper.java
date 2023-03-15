package teamPro.bbangShuttle.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import teamPro.bbangShuttle.vo.ReviewVO;

import java.util.List;

@Mapper
@Repository
public interface ReviewMapper {
  // ** selectList
  List<ReviewVO> selectList();
  List<ReviewVO> itemReviewList(int itemNo);
  List<ReviewVO> idReviewList(String userId);

  // ** selectOne
  ReviewVO selectOne(ReviewVO vo);

  // ** Insert
  int insert(ReviewVO vo);

  // ** Update
  int update(ReviewVO vo);

  // ** Delete
  int delete(int reviewNo);

}
