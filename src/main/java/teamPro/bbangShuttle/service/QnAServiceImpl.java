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
  @Override
  public List<QnAVO> idList() {
    return mapper.idList();
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
  public int delete(int qnaNo) {
    return mapper.delete(qnaNo);
  }

  // ** Reply_Insert
  @Override
  public int ainsert(QnAVO vo) {
    return mapper.ainsert(vo);
  }
}

