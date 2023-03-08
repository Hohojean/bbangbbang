package teamPro.bbangShuttle.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import teamPro.bbangShuttle.service.CartService;
import teamPro.bbangShuttle.vo.CartVO;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bbang")
public class CartController {

    private final CartService cartService;

    @GetMapping("/cart/{userID}")
    public List<CartVO> cartList(@PathVariable CartVO userID) {
        return cartService.cartList(userID);
    }

    @PostMapping("/cart")
    public List<CartVO> cartCount(@RequestBody CartVO vo) {
        CartVO item = cartService.cartItem(vo);
        if(vo.getCartAmount() == 1 && item.getCartAmount() == 1) {
            cartService.cartItemDelete(vo);
        } else {
            cartService.cartItemCount(vo);
        }
        return cartService.cartList(vo);
    }

}
