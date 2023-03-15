package teamPro.bbangShuttle.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import teamPro.bbangShuttle.dto.OrderDTO;
import teamPro.bbangShuttle.dto.OrderItemDTO;
import teamPro.bbangShuttle.service.OrderItemSerive;
import teamPro.bbangShuttle.service.OrderService;
import teamPro.bbangShuttle.vo.CartVO;
import teamPro.bbangShuttle.vo.OrderItemVO;
import teamPro.bbangShuttle.vo.OrderVO;

import java.util.List;


@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;
    private final OrderItemSerive orderItemSerive;

    @GetMapping
    public ResponseEntity<?> orderList(@AuthenticationPrincipal String userID) {
        log.info(orderService.orderListById(userID));
        OrderDTO<OrderVO> response;
        try {
            if(userID == "admin") {
                response = OrderDTO.<OrderVO>builder()
                        .orderList(orderService.orderListByAll())
                        .build();
            } else {
            response = OrderDTO.<OrderVO>builder()
                    .orderList(orderService.orderListById(userID))
                    .build();
            }
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            response = OrderDTO.<OrderVO>builder()
                    .error(e.getMessage())
                    .build();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/{orderNo}")
    public ResponseEntity<?> orderList(@AuthenticationPrincipal String userID, @PathVariable int orderNo) {
        try {
               OrderItemDTO<OrderItemVO> response = OrderItemDTO.<OrderItemVO>builder()
                        .orderItemList(orderItemSerive.orderItemList(orderNo))
                        .build();
               log.info(response.getOrderItemList());
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            OrderItemDTO<OrderItemVO> response = OrderItemDTO.<OrderItemVO>builder()
                    .error(e.getMessage())
                    .build();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

//    @PutMapping
//    public void saveOrder(@RequestBody OrderVO ordervo,
//                          @RequestBody List<CartVO> cartvo,
//                          @AuthenticationPrincipal String userID) {
//        ordervo.setUserID(userID);
//        orderService.save(ordervo);
//        }
//    }
}
