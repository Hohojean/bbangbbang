package teamPro.bbangShuttle.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import teamPro.bbangShuttle.dto.NoticeDTO;
import teamPro.bbangShuttle.dto.ResponseDTO;
import teamPro.bbangShuttle.service.NoticeService;

import teamPro.bbangShuttle.vo.NoticeVO;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/notices")
public class NoticeController {

  private final NoticeService service; //서비스와 연결

  // 작성 게시글 등록(C) - 관리자 권한
  @PostMapping
  public ResponseEntity<?> noticeInsert(@RequestBody NoticeVO vo) {

    log.info("** insert 전송된 vo 확인 => "+vo);
    try {
      service.insert(vo);
      NoticeDTO<NoticeVO> response = NoticeDTO.<NoticeVO>builder()
          .noticeList(service.selectList())
          .build();
      return ResponseEntity.ok().body(response);
    } catch (Exception e) {
      log.info("** insert  => Exception "+e.getMessage());
      NoticeDTO<NoticeVO> response = NoticeDTO.<NoticeVO>builder()
          .error(e.getMessage())
          .build();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
  }

  // 게시글 리스트 보기(R)
  @GetMapping
  public ResponseEntity<?> noticeList() {
    try {
      NoticeDTO<NoticeVO> response = NoticeDTO.<NoticeVO>builder()
          .noticeList(service.selectList())
          .build();
      return ResponseEntity.ok().body(response);
    } catch (Exception e) {
      log.info("** insert  => Exception "+e.getMessage());
      NoticeDTO<NoticeVO> response = NoticeDTO.<NoticeVO>builder()
          .error(e.getMessage())
          .build();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
  }

  // 게시글 상세보기(R)
  @GetMapping("/{noticeNo}")
  public ResponseEntity<?> noticeDetail(@PathVariable int noticeNo, NoticeVO vo) {
    try {
      NoticeDTO<NoticeVO> response = NoticeDTO.<NoticeVO>builder()
          .noticeOne(service.selectOne(vo))
          .build();
      return ResponseEntity.ok().body(response);
    } catch (Exception e) {
      log.info("** insert  => Exception "+e.getMessage());
      NoticeDTO<NoticeVO> response = NoticeDTO.<NoticeVO>builder()
          .error(e.getMessage())
          .build();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
  }

  // 게시글 수정(U) - 관리자 권한
  @PutMapping("/{noticeNo}")
  public ResponseEntity<?> noticeUpdate(@RequestBody  NoticeVO vo) {
    try {
      service.update(vo);
      NoticeDTO<NoticeVO> response = NoticeDTO.<NoticeVO>builder()
          .noticeList(service.selectList())
          .build();
      return ResponseEntity.ok().body(response);
    } catch (Exception e) {
      NoticeDTO<NoticeVO> response = NoticeDTO.<NoticeVO>builder()
          .error(e.getMessage())
          .build();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
  }

  // 게시글 삭제(D) - 관리자 권한
  @DeleteMapping("/{noticeNo}")
  public ResponseEntity<?> noticeDelete(@PathVariable int noticeNo) {
    try {
      service.delete(noticeNo);
      NoticeDTO<NoticeVO> response = NoticeDTO.<NoticeVO>builder()
          .noticeList(service.selectList())
          .build();
      return ResponseEntity.ok().body(response);
    } catch (Exception e) {
      NoticeDTO<NoticeVO> response = NoticeDTO.<NoticeVO>builder()
          .error(e.getMessage())
          .build();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
  }
}
