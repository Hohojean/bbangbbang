package teamPro.bbangShuttle.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import teamPro.bbangShuttle.service.NoticeService;
import teamPro.bbangShuttle.vo.NoticeVO;

import java.util.List;


@Controller
public class NoticeController {

  @Autowired
  private NoticeService service; //서비스와 연결

  @RequestMapping("noticelist") //노테이션의 값으로 주소 지정
  public ModelAndView openBoardList() throws Exception {
    //templates 폴더 아래있는 /boardList.html을 의미함. Thymeleaf와 같은 템플릿엔진을 사용할 경우 스프링 부트의 자동 설정 기능으로 '.html'과 같은 접미사 생략 가능
    ModelAndView mv = new ModelAndView("/notice/noticeList");
    //게시글 목록을 조회하기 위해 ServiceImpl 클래스의 selectBoardList 메서드 호출
    List<NoticeVO> list = service.selectList();
    mv.addObject("list", list);

    return mv;
  }

  // 작성 폼 화면 띄우기
  @RequestMapping("noticeform")
  public String noticeWrite() {
    return "notice/noticeWrite";
  }

  // 작성 게시글 등록
  @RequestMapping("noticeinsert")		// html의 form 태그 action에서 입력한 주소
  public String noticeInsert(@ModelAttribute NoticeVO vo) throws Exception{
    service.insert(vo);
    return "redirect:noticelist";	//게시글 리스트로 이동
  }



}
