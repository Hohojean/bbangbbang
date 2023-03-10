package teamPro.bbangShuttle.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import teamPro.bbangShuttle.mapper.MemberMapper;
import teamPro.bbangShuttle.vo.MemberVO;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
  private final MemberMapper memberMapper;

  @Override
  public List<MemberVO> selectList() {
    return memberMapper.getUsers();
  }

  @Override
  public MemberVO selectOne(MemberVO vo) {
    return memberMapper.selectOne(vo);
  }

  @Override
  public int insert(MemberVO vo) {
    return memberMapper.Insert(vo);
  }

  @Override
  public int update(MemberVO vo) {
    return 0;
  }

  @Override
  public int delete(MemberVO vo) {
    return 0;
  }
}
