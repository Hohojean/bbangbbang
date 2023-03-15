package teamPro.bbangShuttle.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import teamPro.bbangShuttle.dto.OrderDTO;
import teamPro.bbangShuttle.dto.OrderItemDTO;
import teamPro.bbangShuttle.dto.SaveOrderDTO;
import teamPro.bbangShuttle.service.CartService;
import teamPro.bbangShuttle.service.OrderItemSerive;
import teamPro.bbangShuttle.service.OrderService;
import teamPro.bbangShuttle.vo.CartVO;
import teamPro.bbangShuttle.vo.OrderItemVO;
import teamPro.bbangShuttle.vo.OrderVO;


@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;
    private final OrderItemSerive orderItemSerive;
    private final CartService cartService;

    @GetMapping
    public ResponseEntity<?> orderList(@AuthenticationPrincipal String userID) {
        try {
                OrderDTO<OrderVO> response = OrderDTO.<OrderVO>builder()
                        .orderList(orderService.orderListById(userID))
                        .build();
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            OrderDTO<OrderVO> response = OrderDTO.<OrderVO>builder()
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
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            OrderItemDTO<OrderItemVO> response = OrderItemDTO.<OrderItemVO>builder()
                    .error(e.getMessage())
                    .build();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PutMapping
    public ResponseEntity<?> saveOrder(@RequestBody SaveOrderDTO<CartVO> dto,
                          @AuthenticationPrincipal String userID) {
        try {
            OrderItemVO vo = new OrderItemVO();
            orderService.save(dto.getOrderVO());
            for (CartVO cartvo:dto.getOrderItemList()) {
                vo.setOrderNo(dto.getOrderVO().getOrderNo());
                vo.setItemAmount(cartvo.getCartAmount());
                vo.setItemName(cartvo.getItemName());
                vo.setItemNo(cartvo.getItemNo());
                vo.setItemPrice(cartvo.getItemPrice());
                orderItemSerive.save(vo);
            }
            cartService.cartEmpty(userID);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            OrderItemDTO<OrderItemVO> response = OrderItemDTO.<OrderItemVO>builder()
                    .error(e.getMessage())
                    .build();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
        }
    }
