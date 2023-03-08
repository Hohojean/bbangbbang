package teamPro.bbangShuttle.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import teamPro.bbangShuttle.service.QnAService;
import teamPro.bbangShuttle.vo.QnAVO;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class QnAController {

  private final QnAService service; //서비스와 연결

  // 작성 폼 화면 띄우기
  @GetMapping("qnaform")
  public ModelAndView qnaWrite(ModelAndView mv) {
    mv.setViewName("qna/qnaWrite");
    return mv;
  }

  // 작성 게시글 등록(C)
  @PostMapping("qnainsert")		// html의 form 태그 action에서 입력한 주소
  public ModelAndView qnaInsert(ModelAndView mv, QnAVO vo, RedirectAttributes rttr) {
    String uri="redirect:qnalist";
    if ( service.insert(vo)>0 ) {
      rttr.addFlashAttribute("message", "~~ 새글 등록 성공 ~~");
    }else {
      mv.addObject("message", "~~ 새글 등록 실패, 다시 하세요 ~~");
      uri="qna/qnaWrite" ;
    }
    mv.setViewName(uri);
    return mv;
  }

  // 게시글 리스트 보기(R)
  @GetMapping("qnalist") //노테이션의 값으로 주소 지정
  public ModelAndView qnaList() {
    //templates 폴더 아래있는 /boardList.html을 의미함. Thymeleaf와 같은 템플릿엔진을 사용할 경우 스프링 부트의 자동 설정 기능으로 '.html'과 같은 접미사 생략 가능
    ModelAndView mv = new ModelAndView("qna/qnaList");
    //게시글 목록을 조회하기 위해 ServiceImpl 클래스의 selectBoardList 메서드 호출
    List<QnAVO> list = service.selectList();
    mv.addObject("list", list);
    return mv;
  }

  // 게시글 상세보기(R)
  @GetMapping("qnadetail")
  public ModelAndView qnaDetail(ModelAndView mv, QnAVO vo) {
    vo=service.selectOne(vo);
    mv.addObject("apple", vo);
    mv.setViewName("qna/qnaDetail");
    return mv;
  }


  // 게시글 삭제(D)
  @GetMapping("qnadelete")
  public ModelAndView qnaDelete(ModelAndView mv, QnAVO vo, RedirectAttributes rttr) {
    // ** Service
    // => 성공 : redirect blist
    // => 실패 : redirect bdetail (seq가 필요)
    String uri="redirect:qnalist";
    if ( service.delete(vo)>0 ) {
      // => 성공: message 처리
      rttr.addFlashAttribute("message", "~~ 글 삭제 성공 ~~");
    }else {
      // => 실패: message, redirect bdetail (seq가 필요)
      rttr.addFlashAttribute("message", "~~ 글 삭제 실패 ~~");
      uri="redirect:qnadetail?qnaNo="+vo.getQnaNo();
    }
    mv.setViewName(uri);
    return mv;
  }

  // 답글 등록 폼
  @GetMapping("qnarinsert")
  public ModelAndView rinsertForm(ModelAndView mv) {
    mv.setViewName("qna/rinsertForm");
    return mv;
  }

  // 답글 등록 기능
  @PostMapping("qnarinsert")
  public ModelAndView rinsert(ModelAndView mv, QnAVO vo, RedirectAttributes rttr) {
    String uri="redirect:qnalist";
    vo.setQna_step(vo.getQna_step()+1);
    vo.setQna_child(vo.getQna_child()+1);

    if ( service.rinsert(vo)>0 ) {
      rttr.addFlashAttribute("message", "~~ 댓글 등록 성공 ~~");
    }else {
      uri="qna/rinsertForm";
      mv.addObject("message", "~~ 댓글 등록 실패, 다시 하세요 ~~");
    }
    mv.setViewName(uri);
    return mv;
  } //rinsert


}
