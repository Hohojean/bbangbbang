package teamPro.bbangShuttle.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import teamPro.bbangShuttle.service.NoticeService;
import teamPro.bbangShuttle.vo.NoticeVO;

import java.util.List;

@RestController
@RequestMapping("notice")
@RequiredArgsConstructor
public class NoticeController {

  private final NoticeService noticeService;

  // ** list 출력
  @GetMapping("noticeList")
  @ResponseBody
  public List<NoticeVO> noticeList() {
    return noticeService.selectList();
  }
//  @GetMapping("noticeList.do")
//  public ModelAndView openBoardList() throws Exception{
//    //templates 폴더 아래있는 /boardList.html을 의미함. Thymeleaf와 같은 템플릿엔진을 사용할 경우 스프링 부트의 자동 설정 기능으로 '.html'과 같은 접미사 생략 가능
//    ModelAndView mv = new ModelAndView("/notice/noticeList");
//    //게시글 목록을 조회하기 위해 ServiceImpl 클래스의 selectBoardList 메서드 호출
//    List<NoticeVO> list = noticeService.selectList();
//    mv.addObject("list", list);
//
//    return mv;
//  }

  // ** selectOne
  @GetMapping("noticeOne")
  @ResponseBody
  public NoticeVO selectOne(NoticeVO vo){
    return noticeService.selectOne(vo);
  }

  // ** Insert
  @RequestMapping("openNoticeWrite.do")		//게시글 작성 화면 호출
  public String openNoticeWrite() {
    return "/noticeWrite";
  }



//  @RequestMapping("insertNotice.do")		//작성된 게시글 등록 기능 메소드, html의 form 태그 action에서 입력한 주소
//  public String insertNotice(@ModelAttribute BoardDto board) throws Exception{
//    noticeService.insertBoard(board);
//    return "redirect:/board/openBoardList.do";	//게시글 리스트로 이동
//  }

//  // ** Update
//  int update(NoticeVO vo);
//
//  // ** Delete
//  int delete(NoticeVO vo);
//
//  // ** 조회수 증가
//  int countUp(NoticeVO vo);
}
