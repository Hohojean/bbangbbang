package teamPro.bbangShuttle.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import teamPro.bbangShuttle.service.ItemService;
import teamPro.bbangShuttle.vo.ItemVO;


import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping("bbang/itemList")
    @ResponseBody
    public List<ItemVO> itemList() {
        return itemService.findAllItem();
    }

    @GetMapping("bbang/itemDetail/{itemNo}")
    @ResponseBody
    public Optional<ItemVO> itemDetail(@PathVariable int itemNo) {
        return itemService.ItemDetail(itemNo);
    }
}