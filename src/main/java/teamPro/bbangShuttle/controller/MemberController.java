package teamPro.bbangShuttle.controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import teamPro.bbangShuttle.dto.ResponseDTO;
import teamPro.bbangShuttle.dto.UserDTO;
import teamPro.bbangShuttle.security.TokenProvider;
import teamPro.bbangShuttle.service.MemberService;
import teamPro.bbangShuttle.vo.MemberVO;

import java.util.List;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class MemberController {
  private final MemberService service;
  private final TokenProvider tokenProvider;
  private final PasswordEncoder passwordEncoder;


//  @PostMapping("/signup")
//  public ResponseEntity<?> registerUser(@RequestBody MemberVO vo) {
//    // => insert 성공 : status OK & vo return
//    //           오류 : status Error & ResponseDTO 이용 Exception_Message
//    log.info("** registerUser 전송된 vo 확인 => " + vo);
//
//    // ** Form 에 정의하지 않은 컬럼값 추가
//    vo.setUserID("aa");
//    vo.setUserName("그린컴퓨터");
//    vo.setUserEmail("aa@naver.com");
//    vo.setUserPwd("12345!");
//    vo.setUserPhone("010-1234-5678");
//    vo.setUserAddr("경기도 성남시 분당구");
//    vo.setUserBirth("2002-02-02");
//    vo.setUserGender("남과여");
//
//    // ** Password_Encoding
//    vo.setUserPwd(passwordEncoder.encode(vo.getUserPwd()));
//
//    try {
//      service.insert(vo);
//      return ResponseEntity.ok().body(vo);
//    } catch (Exception e) {
//      log.info("** registerUser  => Exception " + e.toString());
//      // 예외가 나는 경우 bad 리스폰스 리턴.
//      ResponseDTO response = ResponseDTO.builder()
//          .error(e.getMessage())
//          .build();
//      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
//    } //try_catch
//  } //signup

  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@RequestBody MemberVO vo) {
    // => insert 성공 : status OK & vo return
    //           오류 : status Error & ResponseDTO 이용 Exception_Message
    log.info("** registerUser 전송된 vo 확인 => " + vo);

    // ** Form 에 정의하지 않은 컬럼값 추가
//    vo.setUserID("aa");
//    vo.setUserName("그린컴퓨터");
//    vo.setUserEmail("aa@naver.com");
//    vo.setUserPwd("12345!");
//    vo.setUserPhone("010-1234-5678");
//    vo.setUserAddr("경기도 성남시 분당구");
//    vo.setUserBirth("2002-02-02");
//    vo.setUserGender("남과여");

    // ** Password_Encoding
    vo.setUserPwd(passwordEncoder.encode(vo.getUserPwd()));

    try {
      service.insert(vo);
      return ResponseEntity.ok().body(vo);
    } catch (Exception e) {
      log.info("** registerUser  => Exception " + e.toString());
      // 예외가 나는 경우 bad 리스폰스 리턴.
      ResponseDTO response = ResponseDTO.builder()
          .error(e.getMessage())
          .build();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    } //try_catch
  } //signup

  @PostMapping(value = "/signin"
      , produces = "application/json;", consumes = "application/json;") // -> 400
  public ResponseEntity<?> authenticate(@RequestBody MemberVO vo) { // -> 400, 415
    //public ResponseEntity<?> authenticate(@RequestBody List<MemberVO> userList, MemberVO vo) { -> 400, 415
//  public ResponseEntity<?> authenticate(MemberVO vo) {
    // => Login 성공 : status OK & 토큰생성 & vo return
    //          오류 : status Error & ResponseDTO 이용 Exception_Message

    // => 400 오류 발생 Test
    //vo=userList.get(0);
    System.out.println("** signin => " + vo);
//    vo.setUserID("admin");
//    vo.setUserPwd("12345!");

    // 1) 입력받은 Password 보관
    String password = vo.getUserPwd();
    System.out.println("1111111 password=" + password);
    // 2) Service 실행
    // => id 일치 &  Password 확인
    vo = service.selectOne(vo);
    if (vo != null && passwordEncoder.matches(password, vo.getUserPwd())) {
      // 로그인 성공 -> 토큰생성
      final String token = tokenProvider.create(vo);
      final UserDTO userDTO = UserDTO.builder()
          .token(token)
          .id(vo.getUserID())
          .username(vo.getUserName())
          .build();
      System.out.println("22222성공 userDTO=" + userDTO);
      return ResponseEntity.ok().body(userDTO);
    } else {
      // 로그인 실패 (id, pssword 오류 구분하지 않음)
      ResponseDTO responseDTO = ResponseDTO.builder()
          .error("Login failed.")
          .build();
      System.out.println("33333실패 responseDTO=" + responseDTO);
      return ResponseEntity
          .status(HttpStatus.BAD_GATEWAY)  //502
          .body(responseDTO);
    }
  }//authenticate

  @GetMapping("/list")
    public List<MemberVO> list() {
    return service.selectList();
    }


}
