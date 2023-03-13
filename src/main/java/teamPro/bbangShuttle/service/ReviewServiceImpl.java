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
  public List<ReviewVO> itemReviewList(int itemNo) {
    return mapper.itemReviewList(itemNo);
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
  public int delete(int reviewNo) {
    return mapper.delete(reviewNo);
  }

}
