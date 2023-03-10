package teamPro.bbangShuttle.vo;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class MemberVO {
  String userID;
  String userName;
  String userEmail;
  String userPwd;
  String userPhone;
  String userAddr;
  String userBirth;
  String userGender;

}
