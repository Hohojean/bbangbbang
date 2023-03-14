package teamPro.bbangShuttle.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import teamPro.bbangShuttle.dto.ReviewDTO;
import teamPro.bbangShuttle.security.TokenProvider;
import teamPro.bbangShuttle.service.ReviewService;
import teamPro.bbangShuttle.vo.ReviewVO;

import javax.servlet.http.HttpServletRequest;


@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewController {

  private final ReviewService service;
  private final TokenProvider tokenProvider;

  // 리뷰 등록(C) - 사용자
  @PostMapping
  public ResponseEntity<?> reviewInsert(@AuthenticationPrincipal String userId, @RequestBody ReviewVO vo) {
    try {
      vo.setUserID(userId);
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
  // 모든 리뷰 리스트 보기
  @GetMapping
  public ResponseEntity<?> reviewList(@RequestBody ReviewVO vo, HttpServletRequest request) {
    try {
//      if (!"admin".equals(tokenProvider.validateAndGetUserId(getTokenFromRequest(request)))) {
//        throw new IllegalArgumentException("Unauthorized access");
//      }
      ReviewDTO<ReviewVO> response = ReviewDTO.<ReviewVO>builder()
            .reviewList(service.selectList())
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
  
  // 리뷰 업데이트
  @PatchMapping("/{reviewNo}")
  public ResponseEntity<?> reviewUpdate(@RequestBody ReviewVO vo) {
    try {
      service.update(vo);
      ReviewDTO<ReviewVO> response = ReviewDTO.<ReviewVO>builder()
          .reviewList(service.selectList())
          .build();
      return ResponseEntity.ok().body(response);
    } catch (Exception e) {
      ReviewDTO<ReviewVO> response = ReviewDTO.<ReviewVO>builder()
          .error(e.getMessage())
          .build();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
  }

  // 리뷰 삭제(D)
  @DeleteMapping("/{reviewNo}")
  public ResponseEntity<?> reviewDelete(@PathVariable int reviewNo) {
    try {
      service.delete(reviewNo);
      ReviewDTO<ReviewVO> response = ReviewDTO.<ReviewVO>builder()
          .reviewList(service.selectList())
          .build();
      return ResponseEntity.ok().body(response);
    } catch (Exception e) {
      ReviewDTO<ReviewVO> response = ReviewDTO.<ReviewVO>builder()
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
