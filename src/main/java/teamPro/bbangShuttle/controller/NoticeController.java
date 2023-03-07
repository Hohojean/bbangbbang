package teamPro.bbangShuttle.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import teamPro.bbangShuttle.paging.PageMaker;
import teamPro.bbangShuttle.paging.SearchCriteria;
import teamPro.bbangShuttle.service.NoticeService;
import teamPro.bbangShuttle.vo.NoticeVO;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;


@RestController
@RequiredArgsConstructor
public class NoticeController {

  private final NoticeService service; //서비스와 연결

  // 페이징 처리
  @GetMapping("noticepaging")
  public ModelAndView noticePaging(ModelAndView mv, SearchCriteria cri, PageMaker pageMaker) {
    // 1) Criteria 처리
    // => rowsPerPage, currPage 값은 Parameter 로 전달 : 자동으로 set
    // => 그러므로 currPage 를 이용해서 setSnoEno 만 하면됨.
    cri.setSnoEno();

    // ** ver02
    // => SearchCriteria: searchType, keyword 는 Parameter로 전달되어 자동으로 set

    // 2) Service 처리
    //mv.addObject("banana", service.criList(cri));  // ver01
    mv.addObject("list", service.searchList(cri)); // ver02

    // 3) View 처리 => PageMaker
    // => cri, totalRowsCount (DB에서 읽어와야함)
    pageMaker.setCriteria(cri);
    //pageMaker.setTotalRowsCount(service.criTotalCount());     // ver01: 전체 Rows 갯수
    pageMaker.setTotalRowsCount(service.searchTotalCount(cri)); // ver02: 조건과 일치하는 Rows 갯수
    mv.addObject("pageMaker", pageMaker);

    mv.setViewName("/notice/nCriList");
    return mv;
  }


  // 작성 폼 화면 띄우기
  @GetMapping("noticeform")
  public ModelAndView noticeWrite(ModelAndView mv) {
    mv.setViewName("notice/noticeWrite");
    return mv;
  }

  // 작성 게시글 등록(C)
  @PostMapping("noticeinsert")		// html의 form 태그 action에서 입력한 주소
  public ModelAndView noticeInsert(ModelAndView mv, NoticeVO vo, RedirectAttributes rttr) {
    String uri="redirect:noticelist";
    if ( service.insert(vo)>0 ) {
      rttr.addFlashAttribute("message", "~~ 새글 등록 성공 ~~");
    }else {
      mv.addObject("message", "~~ 새글 등록 실패, 다시 하세요 ~~");
      uri="notice/noticeWrite" ;
    }
    mv.setViewName(uri);
    return mv;
  }

  // 게시글 리스트 보기(R)
  @GetMapping("noticelist") //노테이션의 값으로 주소 지정
  public ModelAndView noticeList() {
    //templates 폴더 아래있는 /boardList.html을 의미함. Thymeleaf와 같은 템플릿엔진을 사용할 경우 스프링 부트의 자동 설정 기능으로 '.html'과 같은 접미사 생략 가능
    ModelAndView mv = new ModelAndView("notice/noticeList");
    //게시글 목록을 조회하기 위해 ServiceImpl 클래스의 selectBoardList 메서드 호출
    List<NoticeVO> list = service.selectList();
    mv.addObject("list", list);
    return mv;
  }

  // 게시글 상세보기(R)
  @GetMapping("noticedetail")
  public ModelAndView noticeDetail(HttpServletRequest request, ModelAndView mv, NoticeVO vo) {

    // => 조회수 증가 : 테이블의 cnt=cnt+1
    //    - 글보는이(loginID)와 글쓴이가 다를때
    //	  - 글보는이(loginID)가 "admin" 이 아닌경우
    //    - 수정요청이 아닌경우

    vo=service.selectOne(vo);
//    if ( vo!=null ) {
//      // => 로그인 id 확인
//      String loginID = (String)request.getSession().getAttribute("loginID");
//      if ( !vo.getId().equals(loginID) &&
//          !"admin".equals(loginID) &&
//          !"U".equals(request.getParameter("jCode")) ) {
        // => 조회수 증가
        if ( service.countUp(vo)>0 ) vo.setCnt(vo.getCnt()+1);
//      } //if_증가조건
//    } //조회수 증가

    mv.addObject("apple", vo);
    // ** View 처리
    // => Update 요청이면 bupdateForm.jsp
    String uri = "notice/noticeDetail";
    if ( "U".equals(request.getParameter("jCode")) ) {
      uri = "notice/noticeUpdate";
    }
    mv.setViewName(uri);
    return mv;
  }

  // 게시글 수정(U)
  @PostMapping("noticeupdate")
  public ModelAndView noticeUpdate(ModelAndView mv, NoticeVO vo, RedirectAttributes rttr) {
    String uri="redirect:noticedetail?noticeNo="+vo.getNoticeNo();

    if ( service.update(vo)>0 ) {
      // => 성공: message 처리
      rttr.addFlashAttribute("message", "~~ 글 수정 성공 ~~");
    } else {
      // => 실패: message, uri 처리
      mv.addObject("apple", vo);
      mv.addObject("message", "~~ 글 수정 실패, 다시 하세요 ~~");
      uri="notice/noticeUpdate" ;
    }
    mv.setViewName(uri);
    return mv;
  }

  // 게시글 삭제(D)
  @GetMapping("noticedelete")
  public ModelAndView noticeDelete(ModelAndView mv, NoticeVO vo, RedirectAttributes rttr) {
    // ** Service
    // => 성공 : redirect blist
    // => 실패 : redirect bdetail (seq가 필요)
    String uri="redirect:noticelist";
    if ( service.delete(vo)>0 ) {
      // => 성공: message 처리
      rttr.addFlashAttribute("message", "~~ 글 삭제 성공 ~~");
    }else {
      // => 실패: message, redirect bdetail (seq가 필요)
      rttr.addFlashAttribute("message", "~~ 글 삭제 실패 ~~");
      uri="redirect:noticedetail?noticeNo="+vo.getNoticeNo();
    }
    mv.setViewName(uri);
    return mv;
  }


}
