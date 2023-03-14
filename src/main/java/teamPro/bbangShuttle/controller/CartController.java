package teamPro.bbangShuttle.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import teamPro.bbangShuttle.dto.CartDTO;
import teamPro.bbangShuttle.service.CartService;
import teamPro.bbangShuttle.vo.CartVO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    @GetMapping
    public ResponseEntity<?> cartList(@PathVariable CartVO userID) {
        try {
            CartDTO<CartVO> response = CartDTO.<CartVO>builder()
                    .cartList(cartService.cartList(userID))
                    .build();
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            CartDTO<CartVO> response = CartDTO.<CartVO>builder()
                    .error(e.getMessage())
                    .build();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PatchMapping
    public ResponseEntity<?> cartCount(@RequestBody CartVO vo) {
        try {
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
    public ResponseEntity<?> cartInsert(@RequestBody CartVO vo) {
        try {
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
