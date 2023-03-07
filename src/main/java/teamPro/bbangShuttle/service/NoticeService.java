package teamPro.bbangShuttle.service;

import teamPro.bbangShuttle.vo.NoticeVO;

import java.util.List;

public interface NoticeService {

  // ** selectList
  List<NoticeVO> selectList();

  // ** selectOne
  NoticeVO selectOne(NoticeVO vo);

  // ** Insert
  int insert(NoticeVO vo);

  // ** Update
  int update(NoticeVO vo);

  // ** Delete
  int delete(NoticeVO vo);

  // ** 조회수 증가
  int countUp(NoticeVO vo);

}
