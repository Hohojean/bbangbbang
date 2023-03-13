package teamPro.bbangShuttle.controller;

import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import teamPro.bbangShuttle.service.ItemService;
import teamPro.bbangShuttle.service.ReviewService;
import teamPro.bbangShuttle.vo.ItemVO;


import java.security.Security;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequiredArgsConstructor
@RequestMapping("/item")
public class ItemController {

    private final ItemService itemService;
    private final ReviewService reviewService;

    @GetMapping
    public Map<String, Object> itemList(){
        Map<String, Object> result = new ConcurrentHashMap<>();
        result.put("item", itemService.findAllItem());


        SecurityContextHolder.getContext().getAuthentication().getCredentials();
        return result;
    }

    @GetMapping("/{itemNo}")
    public Map<String, Object> itemDetail(@PathVariable int itemNo) {
        Map<String, Object> result = new ConcurrentHashMap<>();
        result.put("item", itemService.ItemDetail(itemNo));
        result.put("review",reviewService.itemReviewList(itemNo));

        return result;
    }

    @PutMapping
    public Map<String, Object> itemSave(@RequestBody ItemVO vo) {
        Map<String, Object> result = new ConcurrentHashMap<>();
        if(itemService.save(vo) > 0) {
            result.put("item", itemService.findAllItem());
        }

        return result;
    }

    @DeleteMapping
    public Map<String, Object> itemDelete(@RequestBody ItemVO vo) {
        Map<String, Object> result = new ConcurrentHashMap<>();
        if(itemService.delete(vo) > 0) {
            result.put("item", itemService.findAllItem());
        }

        return result;
    }

    @PatchMapping
    public Map<String, Object> itemUpdate(@RequestBody ItemVO vo) {
        Map<String, Object> result = new ConcurrentHashMap<>();
        if(itemService.update(vo) > 0) {
            result.put("item", itemService.findAllItem());
        }

        return result;
    }
}