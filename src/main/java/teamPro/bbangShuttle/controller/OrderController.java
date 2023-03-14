package teamPro.bbangShuttle.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import teamPro.bbangShuttle.dto.OrderDTO;
import teamPro.bbangShuttle.security.TokenProvider;
import teamPro.bbangShuttle.service.OrderItemSerive;
import teamPro.bbangShuttle.service.OrderService;
import teamPro.bbangShuttle.vo.OrderVO;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;
    private final OrderItemSerive orderItemSerive;
    private final TokenProvider tokenProvider;

    @GetMapping
    public ResponseEntity<?> orderList(HttpServletRequest request) {
        String userID = tokenProvider.validateAndGetUserId(getTokenFromRequest(request));
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

    @PutMapping
    public void saveOrder(@RequestBody OrderVO vo) {

    }
    private String getTokenFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (!StringUtils.isEmpty(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
