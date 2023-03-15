package teamPro.bbangShuttle.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import teamPro.bbangShuttle.dto.ResponseDTO;
import teamPro.bbangShuttle.dto.UserDTO;
import teamPro.bbangShuttle.security.TokenProvider;
import teamPro.bbangShuttle.service.MemberService;
import teamPro.bbangShuttle.vo.MemberVO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class MemberController {
  private final MemberService service;
  private final TokenProvider tokenProvider;
  private final PasswordEncoder passwordEncoder;

  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@RequestBody MemberVO vo) {
    // => insert 성공 : status OK & vo return
    //           오류 : status Error & ResponseDTO 이용 Exception_Message
    log.info("** registerUser 전송된 vo 확인 => " + vo);

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
          .build();
      System.out.println("22222성공 userDTO=" + userDTO);
      System.out.println("userDTO = " + userDTO);
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


  @PostMapping("/logout")
  public UsernamePasswordAuthenticationToken logout(HttpServletRequest request) {
//    String token = getTokenFromRequest(request);
//    if (token == null) {
//      return ResponseEntity.badRequest().build();
//    }
//    boolean result = tokenProvider.invalidateToken(token);
//    if (result) {
//      return ResponseEntity.ok().build();
//    } else {
//      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//    }
//    System.out.println(request.getHeader("Authorization").substring(7));
//    System.out.println(token);
//    System.out.println("->>"+currentUser.getUserID());
    SecurityContextHolder.clearContext();
    return new UsernamePasswordAuthenticationToken(null, null);
  }// loginOut

  private String getTokenFromRequest(HttpServletRequest request) {
    String bearerToken = request.getHeader("Authorization");
    if (!StringUtils.isEmpty(bearerToken) && bearerToken.startsWith("Bearer ")) {
      return bearerToken.substring(7);
    }
    return null;
  }
  
  @GetMapping("/list")
  public List<MemberVO> list(MemberVO currentUser) {
    return service.selectList();
  }

  @GetMapping("/{userID}")
  public MemberVO detail(@PathVariable String userID,MemberVO vo) throws NotFoundException {
    MemberVO user = service.selectOne(vo);
    if (user == null) {
      throw new NotFoundException("Member not found with id: " + userID);
    }
    return user;
  }


  @PatchMapping
  public ResponseEntity<?> update(@PathVariable String userID,@AuthenticationPrincipal String loginID, MemberVO vo) {
    try {
      vo.setUserPwd(passwordEncoder.encode(vo.getUserPwd()));
      vo.setUserID(userID);
      if(userID.equals(loginID)) {
        service.update(vo);
        return ResponseEntity.ok().build();
      } else {
        new Exception("ID 불일치");
      }
//        return ResponseEntity.ok().body("update ok!");
    } catch (Exception e) {
      log.error("Failed to update user with id {}", e);
      ResponseDTO response = ResponseDTO.builder()
          .error(e.getMessage())
          .build();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
    return null;
  } // Update


  @DeleteMapping("/{userID}")
  public ResponseEntity<?> delete(@PathVariable String userID,@AuthenticationPrincipal String loginID ,MemberVO vo) {
    try {
      vo.setUserID(userID);
      System.out.println("userID = "+userID+", login =>"+loginID);
      if ("admin".equals(loginID)) {
        service.delete(vo);
        return ResponseEntity.ok().body("delete ok!");
      } else {
        new Exception("not admin");
      }

    } catch (Exception e) {
      log.error("Failed to delete user with id {}", vo, e);
      ResponseDTO response = ResponseDTO.builder()
          .error(e.getMessage())
          .build();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
    return null;
  } // Delete


}
