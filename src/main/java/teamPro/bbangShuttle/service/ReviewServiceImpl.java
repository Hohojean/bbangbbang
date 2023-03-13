package teamPro.bbangShuttle.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import teamPro.bbangShuttle.mapper.ReviewMapper;
import teamPro.bbangShuttle.vo.ReviewVO;

import java.util.List;

@Service
@Primary
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

  private final ReviewMapper mapper;

  // ** selectList
  @Override
  public List<ReviewVO> selectList() {
    return mapper.selectList();
  }
  @Override
  public List<ReviewVO> itemReviewList() {
    return mapper.itemReviewList();
  }
  // ** selectOne
  @Override
  public ReviewVO selectOne(ReviewVO vo) {
    return mapper.selectOne(vo);
  }
  // ** Insert
  @Override
  public int insert(ReviewVO vo) {
    return mapper.insert(vo);
  }

  // ** Update
  @Override
  public int update(ReviewVO vo) {
    return mapper.update(vo);
  }

  // ** Delete
  @Override
  public int delete(ReviewVO vo) {
    return mapper.delete(vo);
  }

  // ** Reply_Insert
  @Override
  public int rinsert(ReviewVO vo) {
    if (mapper.rinsert(vo) > 0) {
      // stepUpdate
      System.out.println("** stepUpdate Count => " + mapper.stepUpdate(vo));
      return 1;
    } else return 0;
  }
}
