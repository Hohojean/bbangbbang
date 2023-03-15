package teamPro.bbangShuttle.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import teamPro.bbangShuttle.dto.QnADTO;
import teamPro.bbangShuttle.service.QnAService;
import teamPro.bbangShuttle.vo.QnAVO;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/qnas")
public class QnAController {

  private final QnAService service;

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
  // id= user 인 경우 id 검색
  @GetMapping
  public ResponseEntity<?> qnaList(@AuthenticationPrincipal String userID) {
    try {
      QnADTO<QnAVO> response;
      if("admin".equals(userID)) {
       response = QnADTO.<QnAVO>builder()
            .qnaList(service.selectList())
            .build();
      } else {
        response = QnADTO.<QnAVO>builder()
            .qnaList(service.idList(userID))
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
  public ResponseEntity<?> qnaDelete(@AuthenticationPrincipal String userID, @PathVariable int qnaNo, QnAVO vo) {
    try {
//      if (vo.getUserID().equals(userID)) {
        service.delete(qnaNo);
        QnADTO<QnAVO> response = QnADTO.<QnAVO>builder()
            .qnaList(service.selectList())
            .build();
        return ResponseEntity.ok().body(response);
//      } else {
//        new Exception("Unauthorized access");
//        return null;
//      }
    } catch (Exception e) {
      QnADTO<QnAVO> response = QnADTO.<QnAVO>builder()
          .error(e.getMessage())
          .build();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
  }

  // 답변 등록 - 관리자 권한
  @PostMapping("/ainsert/{qnaNo}")
  public ResponseEntity<?> ainsert(@AuthenticationPrincipal String userID, @PathVariable int qnaNo, @RequestBody QnAVO vo) {
    try {
      if ("admin".equals(userID)) {
        service.ainsert(vo);
        QnADTO<QnAVO> response = QnADTO.<QnAVO>builder()
            .qnaOne(service.selectOne(vo))
            .build();
        return ResponseEntity.ok().body(response);
      } else {
        new Exception("Unauthorized access");
        return null;
      }
    } catch (Exception e) {
      log.info("** insert  => Exception "+e.getMessage());
      QnADTO<QnAVO> response = QnADTO.<QnAVO>builder()
          .error(e.getMessage())
          .build();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
  }

}
