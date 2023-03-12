package teamPro.bbangShuttle.service;

import teamPro.bbangShuttle.paging.Criteria;
import teamPro.bbangShuttle.paging.SearchCriteria;
import teamPro.bbangShuttle.vo.ItemVO;
import teamPro.bbangShuttle.vo.NoticeVO;

import java.util.List;
import java.util.Optional;

public interface NoticeService {

  // ** SearchCriteria PageList
  List<NoticeVO> searchList(SearchCriteria cri);
  int searchTotalCount(SearchCriteria cri);

  // ** Criteria PageList
  List<NoticeVO> criList(Criteria cri);
  int criTotalCount();

  // ** selectList
  List<NoticeVO> selectList();

  // ** selectOne
  NoticeVO selectOne(NoticeVO vo);

  // ** Insert
  int insert(NoticeVO vo);

  // ** Update
  int update(NoticeVO vo);

  // ** Delete
  int delete(int noticeNo);

  // ** 조회수 증가
  int countUp(NoticeVO vo);

}
