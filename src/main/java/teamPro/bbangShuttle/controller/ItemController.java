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
@RequestMapping("/bbang")
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/itemList")
    @ResponseBody
    public List<ItemVO> itemList() {
        return itemService.findAllItem();
    }

    @GetMapping("/itemDetail/{itemNo}")
    @ResponseBody
    public Optional<ItemVO> itemDetail(@PathVariable int itemNo) {
        return itemService.ItemDetail(itemNo);
    }

    @PostMapping("/itemInsert")
    public ModelAndView itemSave(@RequestBody ItemVO vo) {
        ModelAndView mv = new ModelAndView();
        String uri = "redirect:/";
        if(itemService.save(vo) > 0) {
            uri = "redirect:/bbang/itemList";
        }
        mv.setViewName(uri);
        return mv;
    }

    @PostMapping("/itemDelete")
    public ModelAndView itemDelete(@RequestBody ItemVO vo) {
        ModelAndView mv = new ModelAndView();
        String uri = "redirect:/";
        if(itemService.delete(vo) > 0) {
            uri = "redirect:/bbang/itemList";
        }
        mv.setViewName(uri);
        return mv;
    }

    @PostMapping("/itemUpdate")
    public ModelAndView itemUpdate(@RequestBody ItemVO vo) {
        ModelAndView mv = new ModelAndView();
        String uri = "redirect:/";
        if(itemService.update(vo) > 0) {
            uri = "redirect:/bbang/itemDetail/"+vo.getItemNo();
        }
        mv.setViewName(uri);
        return mv;
    }

}