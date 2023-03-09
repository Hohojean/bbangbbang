package teamPro.bbangShuttle.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import teamPro.bbangShuttle.service.ReviewService;
import teamPro.bbangShuttle.vo.ReviewVO;


import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReviewController {

  private final ReviewService service; //서비스와 연결

  // 작성 폼 화면 띄우기
  @GetMapping("reviewform")
  public ModelAndView reviewWrite(ModelAndView mv) {
    mv.setViewName("review/reviewWrite");
    return mv;
  }

  // 작성 게시글 등록(C)
  @PostMapping("reviewinsert")		// html의 form 태그 action에서 입력한 주소
  public ModelAndView reviewInsert(ModelAndView mv, ReviewVO vo, RedirectAttributes rttr) {
    String uri="redirect:reviewlist";
    if ( service.insert(vo)>0 ) {
      rttr.addFlashAttribute("message", "~~ 새글 등록 성공 ~~");
    }else {
      mv.addObject("message", "~~ 새글 등록 실패, 다시 하세요 ~~");
      uri="review/reviewWrite" ;
    }
    mv.setViewName(uri);
    return mv;
  }

  // 게시글 리스트 보기(R)
  @GetMapping("reviewlist") //노테이션의 값으로 주소 지정
  public ModelAndView reviewList() {
    //templates 폴더 아래있는 /boardList.html을 의미함. Thymeleaf와 같은 템플릿엔진을 사용할 경우 스프링 부트의 자동 설정 기능으로 '.html'과 같은 접미사 생략 가능
    ModelAndView mv = new ModelAndView("review/reviewList");
    //게시글 목록을 조회하기 위해 ServiceImpl 클래스의 selectBoardList 메서드 호출
    List<ReviewVO> list = service.selectList();
    mv.addObject("list", list);
    return mv;
  }

  // 게시글 상세보기(R)
  @GetMapping("reviewdetail")
  public ModelAndView reviewDetail(ModelAndView mv, ReviewVO vo) {
    vo=service.selectOne(vo);
    mv.addObject("apple", vo);
    mv.setViewName("review/reviewDetail");
    return mv;
  }

  // 게시글 수정(U)
  @PostMapping("reviewupdate")
  public ModelAndView reviewUpdate(ModelAndView mv, ReviewVO vo, RedirectAttributes rttr) {
    String uri="redirect:reviewdetail?reviewNo="+vo.getReviewNo();

    if ( service.update(vo)>0 ) {
      // => 성공: message 처리
      rttr.addFlashAttribute("message", "~~ 글 수정 성공 ~~");
    } else {
      // => 실패: message, uri 처리
      mv.addObject("apple", vo);
      mv.addObject("message", "~~ 글 수정 실패, 다시 하세요 ~~");
      uri="review/reviewUpdate" ;
    }
    mv.setViewName(uri);
    return mv;
  }

  // 게시글 삭제(D)
  @GetMapping("reviewdelete")
  public ModelAndView reviewDelete(ModelAndView mv, ReviewVO vo, RedirectAttributes rttr) {
    // ** Service
    // => 성공 : redirect blist
    // => 실패 : redirect bdetail (seq가 필요)
    String uri="redirect:reviewlist";
    if ( service.delete(vo)>0 ) {
      // => 성공: message 처리
      rttr.addFlashAttribute("message", "~~ 글 삭제 성공 ~~");
    }else {
      // => 실패: message, redirect bdetail (seq가 필요)
      rttr.addFlashAttribute("message", "~~ 글 삭제 실패 ~~");
      uri="redirect:reviewdetail?reviewNo="+vo.getReviewNo();
    }
    mv.setViewName(uri);
    return mv;
  }

  // 답글 등록 폼
  @GetMapping("reviewrinsert")
  public ModelAndView rinsertForm(ModelAndView mv) {
    mv.setViewName("review/rinsertForm");
    return mv;
  }

  // 답글 등록 기능
  @PostMapping("reviewrinsert")
  public ModelAndView rinsert(ModelAndView mv, ReviewVO vo, RedirectAttributes rttr) {
    String uri="redirect:reviewlist";
    vo.setReview_step(vo.getReview_step()+1);
    vo.setReview_child(vo.getReview_child()+1);

    if ( service.rinsert(vo)>0 ) {
      rttr.addFlashAttribute("message", "~~ 댓글 등록 성공 ~~");
    }else {
      uri="review/rinsertForm";
      mv.addObject("message", "~~ 댓글 등록 실패, 다시 하세요 ~~");
    }
    mv.setViewName(uri);
    return mv;
  } //rinsert
  
}
