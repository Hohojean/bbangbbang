package teamPro.bbangShuttle.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import teamPro.bbangShuttle.mapper.NoticeMapper;
import teamPro.bbangShuttle.paging.Criteria;
import teamPro.bbangShuttle.paging.SearchCriteria;
import teamPro.bbangShuttle.vo.NoticeVO;

import java.util.List;
import java.util.Optional;

@Service
@Primary
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {

  private final NoticeMapper mapper;


  // ** selectList
  @Override
  public List<NoticeVO> selectList() {
    return mapper.selectList();
  }
  // ** selectOne
  @Override
  public NoticeVO selectOne(NoticeVO vo) {
    return mapper.selectOne(vo);
  }
  // ** Insert
  @Override
  public int insert(NoticeVO vo) {
    return mapper.insert(vo);
  }
  // ** Update
  @Override
  public int update(NoticeVO vo) {
    return mapper.update(vo);
  }
  // ** Delete
  @Override
  public int delete(int noticeNo) {
    return mapper.delete(noticeNo);
  }
  // ** 조회수 증가
  @Override
  public int countUp(NoticeVO vo) {
    return mapper.countUp(vo);
  }
}

