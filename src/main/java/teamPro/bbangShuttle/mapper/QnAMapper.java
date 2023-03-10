package teamPro.bbangShuttle.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import teamPro.bbangShuttle.vo.QnAVO;

import java.util.List;
import java.util.Optional;

@Mapper
@Repository
public interface QnAMapper {
  // ** selectList
  List<QnAVO> selectList();

  // ** selectOne
  Optional<QnAVO> selectOne(int qnaNo);

  // ** Insert
  int insert(QnAVO vo);

  // ** Delete
  int delete(int qnaNo);

  int rinsert(QnAVO vo);
  int stepUpdate(QnAVO vo);

}
