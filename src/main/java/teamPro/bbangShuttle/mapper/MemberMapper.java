package teamPro.bbangShuttle.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import teamPro.bbangShuttle.vo.MemberVO;

import java.util.List;

@Mapper
public interface MemberMapper {
//  @Select("select * from user")
  List<MemberVO> getUsers();

  @Insert("insert into user values( #{userID},#{userName},#{userEmail},#{userPwd}, #{userPhone},#{userAddr},#{userBirth},#{userGender})")
  int Insert(MemberVO vo);

  MemberVO selectOne(MemberVO vo);

  int update(MemberVO vo);

  int delete(MemberVO vo);
}
