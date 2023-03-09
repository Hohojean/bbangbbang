package teamPro.bbangShuttle.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import teamPro.bbangShuttle.service.ItemService;
import teamPro.bbangShuttle.service.NoticeService;
import teamPro.bbangShuttle.vo.ItemVO;


import java.util.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/item")
public class ItemController {

    private final ItemService itemService;
    private final NoticeService noticeService;

    @GetMapping("/list")
    public String itemList() throws JsonProcessingException {
        Map<String, Object> objectMap = new HashMap<>();
        objectMap.put("item", itemService.findAllItem());
        objectMap.put("notice", noticeService.selectList());

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(objectMap);
        return json;
    }

    @GetMapping("/{itemNo}")
    public Optional<ItemVO> itemDetail(@PathVariable int itemNo) {
        return itemService.ItemDetail(itemNo);
    }

    @PostMapping("/insert")
    public ModelAndView itemSave(@RequestBody ItemVO vo) {
        ModelAndView mv = new ModelAndView();
        String uri = "redirect:/";
        if(itemService.save(vo) > 0) {
            uri = "redirect:/bbang/itemList";
        }
        mv.setViewName(uri);
        return mv;
    }

    @PostMapping("/delete")
    public ModelAndView itemDelete( ItemVO vo) {
        ModelAndView mv = new ModelAndView();
        String uri = "redirect:/";
        if(itemService.delete(vo) > 0) {
            uri = "redirect:/item/list";
        }
        mv.setViewName(uri);
        return mv;
    }

    @PostMapping("/update")
    public ModelAndView itemUpdate(@RequestBody ItemVO vo) {
        ModelAndView mv = new ModelAndView();
        String uri = "redirect:/";
        if(itemService.update(vo) > 0) {
            uri = "redirect:/item/"+vo.getItemNo();
        }
        mv.setViewName(uri);
        return mv;
    }

}