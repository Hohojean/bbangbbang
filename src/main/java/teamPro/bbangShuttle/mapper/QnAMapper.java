package teamPro.bbangShuttle.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import teamPro.bbangShuttle.vo.QnAVO;

import java.util.List;

@Mapper
@Repository
public interface QnAMapper {
  // ** selectList
  List<QnAVO> selectList();

  // ** selectOne
  QnAVO selectOne(QnAVO vo);

  // ** Insert
  int insert(QnAVO vo);

  // ** Delete
  int delete(QnAVO vo);

  int rinsert(QnAVO vo);
  int stepUpdate(QnAVO vo);

}
