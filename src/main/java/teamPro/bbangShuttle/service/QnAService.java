package teamPro.bbangShuttle.service;

import teamPro.bbangShuttle.vo.QnAVO;

import java.util.List;

public interface QnAService {
  // ** selectList
  List<QnAVO> selectList();
  List<QnAVO> idList(String userID);

  // ** selectOne
  QnAVO selectOne(QnAVO vo);

  // ** Insert
  int insert(QnAVO vo);

  // ** Delete
  int delete(int qnaNo);

  int ainsert(QnAVO vo);
}
