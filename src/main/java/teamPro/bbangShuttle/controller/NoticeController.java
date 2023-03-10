package teamPro.bbangShuttle.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import teamPro.bbangShuttle.service.NoticeService;
import teamPro.bbangShuttle.vo.ItemVO;
import teamPro.bbangShuttle.vo.NoticeVO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;


@RestController
@RequiredArgsConstructor
public class NoticeController {

  private final NoticeService service; //서비스와 연결

  // 페이징 처리
//  @GetMapping("noticepaging")
//  public ModelAndView noticePaging(ModelAndView mv, SearchCriteria cri, PageMaker pageMaker) {
//    cri.setSnoEno();
//
//    mv.addObject("list", service.searchList(cri)); // ver02
//
//    pageMaker.setCriteria(cri);
//    pageMaker.setTotalRowsCount(service.searchTotalCount(cri)); // ver02: 조건과 일치하는 Rows 갯수
//    mv.addObject("pageMaker", pageMaker);
//
//    mv.setViewName("/notice/nCriList");
//    return mv;
//  }


  // 작성 폼 화면 띄우기
  @GetMapping("noticeform")
  public ModelAndView noticeWrite(ModelAndView mv) {
    mv.setViewName("notice/noticeWrite");
    return mv;
  }

  // 작성 게시글 등록(C)
  @PostMapping("noticeinsert")		// html의 form 태그 action에서 입력한 주소
  public List<NoticeVO> noticeInsert(@ModelAttribute NoticeVO vo) {
    service.insert(vo);
    return service.selectList();
  }

  // 게시글 리스트 보기(R)
  @GetMapping("/noticelist")
  public List<NoticeVO> noticeList() {
    return service.selectList();
  }

  // 게시글 상세보기(R)
  @GetMapping("noticedetail/{noticeNo}")
  public Optional<NoticeVO> noticeDetail(@PathVariable int noticeNo, NoticeVO vo) {
    if (service.countUp(vo)>0) vo.setCnt(vo.getCnt()+1); // 조회수 증가
    return  service.selectOne(noticeNo);
  }

  // 게시글 수정(U)
  @PostMapping("noticeupdate/{noticeNo}")
  public Optional<NoticeVO> noticeUpdate(@PathVariable int noticeNo, @RequestBody  NoticeVO vo) {
    service.update(noticeNo, vo);
    return service.selectOne(noticeNo);
  }

  // 게시글 삭제(D)
  @DeleteMapping("noticedetail/{noticeNo}")
  public void noticeDelete(@PathVariable int noticeNo) {
    service.delete(noticeNo);
//        return null;
//     return service.selectList();
  }
}
