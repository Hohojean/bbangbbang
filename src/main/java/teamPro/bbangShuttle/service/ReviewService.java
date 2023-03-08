package teamPro.bbangShuttle.service;

import teamPro.bbangShuttle.vo.ReviewVO;

import java.util.List;

public interface ReviewService {

  // ** selectList
  List<ReviewVO> selectList();

  // ** selectOne
  ReviewVO selectOne(ReviewVO vo);

  // ** Insert
  int insert(ReviewVO vo);

  // ** Update
  int update(ReviewVO vo);

  // ** Delete
  int delete(ReviewVO vo);

  int rinsert(ReviewVO vo);

}
