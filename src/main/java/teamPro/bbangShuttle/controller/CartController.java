package teamPro.bbangShuttle.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import teamPro.bbangShuttle.dto.CartDTO;
import teamPro.bbangShuttle.service.CartService;
import teamPro.bbangShuttle.vo.CartVO;


@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;

    @GetMapping
    public ResponseEntity<?> cartList(@AuthenticationPrincipal String userID) {

        try {
            log.info(userID);
            log.info(cartService.cartList(userID));
            CartDTO<CartVO> response = CartDTO.<CartVO>builder()
                    .cartList(cartService.cartList(userID))
                    .build();
            log.info(response);
            log.info("11");
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            CartDTO<CartVO> response = CartDTO.<CartVO>builder()
                    .error(e.getMessage())
                    .build();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PatchMapping
    public ResponseEntity<?> cartCount(@RequestBody CartVO vo,@AuthenticationPrincipal String userID) {
        try {
            vo.setUserID(userID);
        CartVO item = cartService.cartItem(vo);
        if(vo.getCartAmount() == 1 && item.getCartAmount() == 1) {
            cartService.cartItemDelete(vo);
        } else {
            cartService.cartItemCount(vo);
        }
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            CartDTO<CartVO> response = CartDTO.<CartVO>builder()
                    .error(e.getMessage())
                    .build();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PutMapping
    public ResponseEntity<?> cartInsert(@RequestBody CartVO vo,@AuthenticationPrincipal String userID) {
        try {
        vo.setUserID(userID);
            if(cartService.cartItem(vo) == null) {
                cartService.cartSave(vo);
            } else {
                vo.setCartAmount(vo.getCartAmount()+cartService.cartItem(vo).getCartAmount());
                cartService.cartItemCount(vo);
            }
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            CartDTO<CartVO> response = CartDTO.<CartVO>builder()
                    .error(e.getMessage())
                    .build();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }

    }

}
