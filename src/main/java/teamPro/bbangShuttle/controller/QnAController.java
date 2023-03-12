package teamPro.bbangShuttle.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import teamPro.bbangShuttle.service.QnAService;
import teamPro.bbangShuttle.vo.QnAVO;
import java.util.List;
import java.util.Optional;

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
  public List<QnAVO> qnaInsert(@RequestBody QnAVO vo) {
    service.insert(vo);
    return service.selectList();
  }

  // 게시글 리스트 보기(R)
  @GetMapping("/qnalist")
  public List<QnAVO> qnaList() {
    return service.selectList();
  }

  // 게시글 상세보기(R)
  @GetMapping("qnadetail/{qnaNo}")
  public QnAVO qnaDetail(@RequestBody QnAVO vo) {
    return  service.selectOne(vo);
  }

  // 게시글 삭제(D)
  @GetMapping("qnadelete/{qnaNo}")
  public List<QnAVO> qnaDelete(@PathVariable int qnaNo) {
    service.delete(qnaNo);
    return service.selectList();
  }

  // 답글 등록 폼
  @GetMapping("rinsert")
  public ModelAndView rinsertForm(ModelAndView mv) {
    mv.setViewName("qna/rinsertForm");
    return mv;
  }

  // 답글 등록 기능
  @PostMapping("qnarinsert")
  public List<QnAVO> rinsert(@RequestBody QnAVO vo) {
    service.rinsert(vo);
    vo.setQna_step(vo.getQna_step()+1);
    vo.setQna_child(vo.getQna_child()+1);
    return service.selectList();

  } //rinsert


}
