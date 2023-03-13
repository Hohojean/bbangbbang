package teamPro.bbangShuttle.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import teamPro.bbangShuttle.vo.OrderVO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    @PostMapping
    public void saveOrder(@RequestBody OrderVO vo) {

    }

}
