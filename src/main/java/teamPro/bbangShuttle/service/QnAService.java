package teamPro.bbangShuttle.service;

import teamPro.bbangShuttle.vo.QnAVO;

import java.util.List;

public interface QnAService {
  // ** selectList
  List<QnAVO> selectList();

  // ** selectOne
  QnAVO selectOne(QnAVO vo);

  // ** Insert
  int insert(QnAVO vo);

  // ** Delete
  int delete(int qnaNo);

  int rinsert(QnAVO vo);
}
