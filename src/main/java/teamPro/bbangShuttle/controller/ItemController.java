package teamPro.bbangShuttle.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import teamPro.bbangShuttle.service.ItemService;
import teamPro.bbangShuttle.vo.ItemVO;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping("bbang/itemList")
    @ResponseBody
    public List<ItemVO> itemList() {
        return itemService.findAllItem();
    }
}
