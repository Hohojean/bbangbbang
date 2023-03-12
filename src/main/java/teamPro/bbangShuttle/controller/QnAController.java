package teamPro.bbangShuttle.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import teamPro.bbangShuttle.dto.QnADTO;
import teamPro.bbangShuttle.service.QnAService;
import teamPro.bbangShuttle.vo.QnAVO;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/qnas")
public class QnAController {

  private final QnAService service; //서비스와 연결

  // 작성 게시글 등록(C)
  @PostMapping
  public ResponseEntity<?> qnaInsert(@RequestBody QnAVO vo) {
    try {
      service.insert(vo);
      QnADTO<QnAVO> response = QnADTO.<QnAVO>builder()
          .qnaList(service.selectList())
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

  // 게시글 리스트 보기(R)
  @GetMapping
  public ResponseEntity<?> qnaList() {
    try {
      QnADTO<QnAVO> response = QnADTO.<QnAVO>builder()
          .qnaList(service.selectList())
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

  // 게시글 상세보기(R)
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

  // 게시글 삭제(D)
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

  // 답글 등록 기능 - 관리자 권한
  @PostMapping("/rinsert")
  public ResponseEntity<?> qnarInsert(@RequestBody QnAVO vo) {
      vo.setQna_step(vo.getQna_step()+1);
      vo.setQna_child(vo.getQna_child()+1);
    try {
      service.rinsert(vo);
      QnADTO<QnAVO> response = QnADTO.<QnAVO>builder()
          .qnaList(service.selectList())
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


}
