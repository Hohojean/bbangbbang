package teamPro.bbangShuttle.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import teamPro.bbangShuttle.service.ItemService;
import teamPro.bbangShuttle.vo.ItemVO;


import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/item")
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/list")
    public List<ItemVO> itemList() {
        return itemService.findAllItem();
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
    public ModelAndView itemDelete(@RequestBody ItemVO vo) {
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