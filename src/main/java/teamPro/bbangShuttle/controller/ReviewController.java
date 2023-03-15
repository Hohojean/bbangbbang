package teamPro.bbangShuttle.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import teamPro.bbangShuttle.dto.ReviewDTO;
import teamPro.bbangShuttle.service.ReviewService;
import teamPro.bbangShuttle.vo.ReviewVO;

import javax.servlet.http.HttpServletRequest;


@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewController {

  private final ReviewService service;

  // 리뷰 등록(C) - 사용자
  @PostMapping
  public ResponseEntity<?> reviewInsert(@AuthenticationPrincipal String userID, @RequestBody ReviewVO vo) {
    try {
      vo.setUserID(userID);
      service.insert(vo);
      log.info("** insert vo => "+vo);
      ReviewDTO<ReviewVO> response = ReviewDTO.<ReviewVO>builder()
          .reviewList(service.selectList())
          .build();
      log.info("** selectList => "+ service.selectList());
      return ResponseEntity.ok().body(response);
    } catch (Exception e) {
      log.info("** insert  => Exception "+e.getMessage());
      ReviewDTO<ReviewVO> response = ReviewDTO.<ReviewVO>builder()
          .error(e.getMessage())
          .build();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
  }

  // 리뷰 리스트 보기(R)
  // 모든 리뷰 리스트 보기 - 관리자 권한
  @GetMapping
  public ResponseEntity<?> reviewList(@AuthenticationPrincipal String userID, @RequestBody ReviewVO vo) {
    try {
      if ("admin".equals(userID)) {
        ReviewDTO<ReviewVO> response = ReviewDTO.<ReviewVO>builder()
            .reviewList(service.selectList())
            .build();
        return ResponseEntity.ok().body(response);
      } else {
        new Exception("Unauthorized access");
        return null;
      }
    } catch (Exception e) {
      log.info("** insert  => Exception "+e.getMessage());
      ReviewDTO<ReviewVO> response = ReviewDTO.<ReviewVO>builder()
          .error(e.getMessage())
          .build();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
  }

  // 리뷰 상세보기(R)
  @GetMapping("/{reviewNo}")
  public ResponseEntity<?> reviewDetail(@PathVariable int reviewNo, ReviewVO vo) {
    try {
      ReviewDTO<ReviewVO> response = ReviewDTO.<ReviewVO>builder()
          .reviewOne(service.selectOne(vo))
          .build();
      return ResponseEntity.ok().body(response);
    } catch (Exception e) {
      log.info("** insert  => Exception "+e.getMessage());
      ReviewDTO<ReviewVO> response = ReviewDTO.<ReviewVO>builder()
          .error(e.getMessage())
          .build();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
  }
  
  // 리뷰 수정 - 해당 리뷰 작성한 회원 일 경우
  @PatchMapping("/{reviewNo}")
  public ResponseEntity<?> reviewUpdate(@AuthenticationPrincipal String userID, @RequestBody ReviewVO vo) {
    try {
      if (vo.getUserID().equals(userID)) {
        service.update(vo);
        ReviewDTO<ReviewVO> response = ReviewDTO.<ReviewVO>builder()
            .reviewList(service.selectList())
            .build();
        return ResponseEntity.ok().body(response);
      } else {
        new Exception("Unauthorized access");
        return null;
      }
    } catch (Exception e) {
      ReviewDTO<ReviewVO> response = ReviewDTO.<ReviewVO>builder()
          .error(e.getMessage())
          .build();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
  }

  // 리뷰 삭제(D) - 해당 리뷰 작성한 회원 일 경우
  @DeleteMapping("/{reviewNo}")
  public ResponseEntity<?> reviewDelete(@AuthenticationPrincipal String userID, @PathVariable int reviewNo, ReviewVO vo) {
    try {
      if (vo.getUserID().equals(userID)) {
        service.delete(reviewNo);
        ReviewDTO<ReviewVO> response = ReviewDTO.<ReviewVO>builder()
            .reviewList(service.selectList())
            .build();
        return ResponseEntity.ok().body(response);
      } else {
        new Exception("Unauthorized access");
        return null;
      }
    } catch (Exception e) {
      ReviewDTO<ReviewVO> response = ReviewDTO.<ReviewVO>builder()
          .error(e.getMessage())
          .build();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
  }

}
