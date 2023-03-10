package teamPro.bbangShuttle.service;

import teamPro.bbangShuttle.vo.MemberVO;

import java.util.List;

public interface MemberService {

  // 1. selectList
  List<MemberVO> selectList();

  // 2. selectOne : Detail
  MemberVO selectOne(MemberVO vo);

  // 3. insert
  int insert(MemberVO vo);

  // 4. update
  int update(MemberVO vo);

  // 5. delete
  int delete(MemberVO vo);

}

