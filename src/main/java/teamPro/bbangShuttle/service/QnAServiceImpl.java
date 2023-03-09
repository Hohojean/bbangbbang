package teamPro.bbangShuttle.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import teamPro.bbangShuttle.mapper.QnAMapper;
import teamPro.bbangShuttle.vo.QnAVO;

import java.util.List;
@Service
@Primary
@RequiredArgsConstructor
public class QnAServiceImpl implements QnAService {

  private final QnAMapper mapper;

  // ** selectList
  @Override
  public List<QnAVO> selectList() {
    return mapper.selectList();
  }
  // ** selectOne
  @Override
  public QnAVO selectOne(QnAVO vo) {
    return mapper.selectOne(vo);
  }
  // ** Insert
  @Override
  public int insert(QnAVO vo) {
    return mapper.insert(vo);
  }

  // ** Delete
  @Override
  public int delete(QnAVO vo) {
    return mapper.delete(vo);
  }

  // ** Reply_Insert
  @Override
  public int rinsert(QnAVO vo) {
    if ( mapper.rinsert(vo)>0 ) {
      // stepUpdate
      System.out.println("** stepUpdate Count => "+mapper.stepUpdate(vo));
      return 1 ;
    } else return 0;
  }
}
