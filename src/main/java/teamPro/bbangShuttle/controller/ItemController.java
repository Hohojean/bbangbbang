package teamPro.bbangShuttle.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import teamPro.bbangShuttle.service.ItemService;
import teamPro.bbangShuttle.vo.ItemVO;


import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequiredArgsConstructor
@RequestMapping("/item")
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/list")
    public Map<String, Object> itemList() throws JsonProcessingException {
        Map<String, Object> result = new ConcurrentHashMap<>();
        result.put("item", itemService.findAllItem());

        return result;
    }

    @GetMapping("/{itemNo}")
    public Map<String, Object> itemDetail(@PathVariable int itemNo) {
        Map<String, Object> result = new ConcurrentHashMap<>();
        result.put("item", itemService.ItemDetail(itemNo));

        return result;
    }

    @PostMapping("/insert")
    public Map<String, Object> itemSave(@RequestBody ItemVO vo) {
        Map<String, Object> result = new ConcurrentHashMap<>();
        if(itemService.save(vo) > 0) {
            result.put("item", itemService.findAllItem());
        }

        return result;
    }

    @PostMapping("/delete")
    public Map<String, Object> itemDelete(@RequestBody ItemVO vo) {
        Map<String, Object> result = new ConcurrentHashMap<>();
        if(itemService.delete(vo) > 0) {
            result.put("item", itemService.findAllItem());
        }

        return result;
    }

    @PostMapping("/update")
    public Map<String, Object> itemUpdate(@RequestBody ItemVO vo) {
        Map<String, Object> result = new ConcurrentHashMap<>();
        if(itemService.update(vo) > 0) {
            result.put("item", itemService.findAllItem());
        }

        return result;
    }
}