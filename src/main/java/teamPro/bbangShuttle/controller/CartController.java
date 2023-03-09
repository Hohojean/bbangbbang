package teamPro.bbangShuttle.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import teamPro.bbangShuttle.service.CartService;
import teamPro.bbangShuttle.vo.CartVO;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    @GetMapping("/{userID}")
    public List<CartVO> cartList(@PathVariable CartVO userID) {
        return cartService.cartList(userID);
    }

    @PostMapping("/list")
    public List<CartVO> cartCount(@RequestBody CartVO vo) {
        CartVO item = cartService.cartItem(vo);
        if(vo.getCartAmount() == 1 && item.getCartAmount() == 1) {
            cartService.cartItemDelete(vo);
        } else {
            cartService.cartItemCount(vo);
        }
        return cartService.cartList(vo);
    }

    @PostMapping("/insert")
    public List<CartVO> cartInsert(@RequestBody CartVO vo) {
        if(cartService.cartItem(vo) == null) {
            cartService.cartSave(vo);
        } else {
            vo.setCartAmount(vo.getCartAmount()+cartService.cartItem(vo).getCartAmount());
            cartService.cartItemCount(vo);
        }

        return cartService.cartList(vo);
    }

}
