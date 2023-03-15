package teamPro.bbangShuttle.service;

import teamPro.bbangShuttle.vo.CartVO;

import java.util.List;

public interface CartService {
    List<CartVO> cartList(String userID);

    CartVO cartItem(CartVO vo);

    int cartSave(CartVO vo);

    int cartItemCount(CartVO vo);

    int cartItemDelete(CartVO vo);
    int cartEmpty(String userID);
}
