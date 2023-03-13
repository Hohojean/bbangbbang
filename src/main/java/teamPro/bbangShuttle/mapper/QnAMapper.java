package teamPro.bbangShuttle.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import teamPro.bbangShuttle.vo.NoticeVO;
import teamPro.bbangShuttle.vo.QnAVO;

import java.util.List;

@Mapper
@Repository
public interface QnAMapper {

  // ** selectList
  List<QnAVO> selectList();
  List<QnAVO> idList();

  // ** selectOne
  QnAVO selectOne(QnAVO vo);

  // ** Insert
  int insert(QnAVO vo);

  // ** Delete
  int delete(int qnaNo);

  int rinsert(QnAVO vo);
  int stepUpdate(QnAVO vo);

}
