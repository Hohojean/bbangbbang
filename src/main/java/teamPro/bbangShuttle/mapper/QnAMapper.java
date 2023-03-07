package teamPro.bbangShuttle.mapper;

import teamPro.bbangShuttle.vo.QnAVO;

import java.util.List;

public interface QnAMapper {
  // ** selectList
  List<QnAVO> selectList();

  // ** selectOne
  QnAVO selectOne(QnAVO vo);

  // ** Insert
  int insert(QnAVO vo);

  // ** Delete
  int delete(QnAVO vo);

}
