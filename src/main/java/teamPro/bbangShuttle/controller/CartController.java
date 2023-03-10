package teamPro.bbangShuttle.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import teamPro.bbangShuttle.service.CartService;
import teamPro.bbangShuttle.vo.CartVO;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    @GetMapping("/{userID}")
    public Map<String, Object> cartList(@PathVariable CartVO userID) {
        Map<String, Object> result = new ConcurrentHashMap<>();
        result.put("cart", cartService.cartList(userID));
        return result;
    }

    @PostMapping("/count")
    public Map<String, Object> cartCount(@RequestBody CartVO vo) {
        Map<String, Object> result = new ConcurrentHashMap<>();
        CartVO item = cartService.cartItem(vo);
        if(vo.getCartAmount() == 1 && item.getCartAmount() == 1) {
            cartService.cartItemDelete(vo);
        } else {
            cartService.cartItemCount(vo);
        }
        result.put("cart",cartService.cartList(vo));
        return result;
    }

    @PostMapping("/insert")
    public Map<String, Object> cartInsert(@RequestBody CartVO vo) {
        Map<String, Object> result = new ConcurrentHashMap<>();
        if(cartService.cartItem(vo) == null) {
            cartService.cartSave(vo);
        } else {
            vo.setCartAmount(vo.getCartAmount()+cartService.cartItem(vo).getCartAmount());
            cartService.cartItemCount(vo);
        }
        result.put("cart", cartService.cartList(vo));

        return result;
    }

}
