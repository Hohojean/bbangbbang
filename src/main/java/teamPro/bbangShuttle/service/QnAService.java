package teamPro.bbangShuttle.service;

import teamPro.bbangShuttle.vo.QnAVO;

import java.util.List;
import java.util.Optional;

public interface QnAService {
  // ** selectList
  List<QnAVO> selectList();

  // ** selectOne
  Optional<QnAVO> selectOne(int qnaNo);

  // ** Insert
  int insert(QnAVO vo);

  // ** Delete
  int delete(int qnaNo);

  int rinsert(QnAVO vo);

}
