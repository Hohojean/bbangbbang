package teamPro.bbangShuttle.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import teamPro.bbangShuttle.dto.QnADTO;
import teamPro.bbangShuttle.security.TokenProvider;
import teamPro.bbangShuttle.service.QnAService;
import teamPro.bbangShuttle.vo.QnAVO;

import javax.servlet.http.HttpServletRequest;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/qnas")
public class QnAController {

  private final QnAService service;
  private final TokenProvider tokenProvider;

  // 질문글 등록(C) - 사용자
  @PostMapping
  public ResponseEntity<?> qnaInsert(@AuthenticationPrincipal String userId, @RequestBody QnAVO vo) {
    try {
      vo.setUserID(userId);
      service.insert(vo);
      log.info("** insert vo => "+vo);
      QnADTO<QnAVO> response = QnADTO.<QnAVO>builder()
          .qnaList(service.selectList())
          .build();
      log.info("** selectList => "+ service.selectList());
      return ResponseEntity.ok().body(response);
    } catch (Exception e) {
      log.info("** insert  => Exception "+e.getMessage());
      QnADTO<QnAVO> response = QnADTO.<QnAVO>builder()
          .error(e.getMessage())
          .build();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
  }

  // 질문글 리스트 보기(R)
  // id = 관리자 인 경우 모든 리스트
  // id= user 인 경우 id 검색 qnalis
  @GetMapping
  public ResponseEntity<?> qnaList(@AuthenticationPrincipal String userId, @RequestBody QnAVO vo, HttpServletRequest request) {
    try {
      QnADTO<QnAVO> response;
      if("admin".equals(tokenProvider.validateAndGetUserId(getTokenFromRequest(request)))){
        response = QnADTO.<QnAVO>builder()
            .qnaList(service.selectList())
            .build();
      } else {
        response = QnADTO.<QnAVO>builder()
            .qnaList(service.idList())
            .build();
      }
      return ResponseEntity.ok().body(response);
    } catch (Exception e) {
      log.info("** insert  => Exception "+e.getMessage());
      QnADTO<QnAVO> response = QnADTO.<QnAVO>builder()
          .error(e.getMessage())
          .build();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
  }

  // 질문글 상세보기(R)
  @GetMapping("/{qnaNo}")
  public ResponseEntity<?> qnaDetail(@PathVariable int qnaNo, QnAVO vo) {
    try {
      QnADTO<QnAVO> response = QnADTO.<QnAVO>builder()
          .qnaOne(service.selectOne(vo))
          .build();
      return ResponseEntity.ok().body(response);
    } catch (Exception e) {
      log.info("** insert  => Exception "+e.getMessage());
      QnADTO<QnAVO> response = QnADTO.<QnAVO>builder()
          .error(e.getMessage())
          .build();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
  }

  // 질문글 삭제(D)
  @DeleteMapping("/{qnaNo}")
  public ResponseEntity<?> qnaDelete(@PathVariable int qnaNo) {
    try {
      service.delete(qnaNo);
      QnADTO<QnAVO> response = QnADTO.<QnAVO>builder()
          .qnaList(service.selectList())
          .build();
      return ResponseEntity.ok().body(response);
    } catch (Exception e) {
      QnADTO<QnAVO> response = QnADTO.<QnAVO>builder()
          .error(e.getMessage())
          .build();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
  }

  // 답변 등록 - 관리자 권한
  @PostMapping("/ainsert/{qnaNo}")
  public ResponseEntity<?> ainsert(@PathVariable int qnaNo, @RequestBody QnAVO vo, HttpServletRequest request) {
    try {
//      if (!"admin".equals(tokenProvider.validateAndGetUserId(getTokenFromRequest(request)))) {
//        throw new IllegalArgumentException("Unauthorized access");
//      }
      service.ainsert(vo);
      QnADTO<QnAVO> response = QnADTO.<QnAVO>builder()
          .qnaOne(service.selectOne(vo))
          .build();
      return ResponseEntity.ok().body(response);
    } catch (Exception e) {
      log.info("** insert  => Exception "+e.getMessage());
      QnADTO<QnAVO> response = QnADTO.<QnAVO>builder()
          .error(e.getMessage())
          .build();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
  }

  private String getTokenFromRequest(HttpServletRequest request) {
    String bearerToken = request.getHeader("Authorization");
    if (!StringUtils.isEmpty(bearerToken) && bearerToken.startsWith("Bearer ")) {
      return bearerToken.substring(7);
    }
    return null;
  }
}
