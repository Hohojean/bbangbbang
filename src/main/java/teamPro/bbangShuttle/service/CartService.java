package teamPro.bbangShuttle.service;

import teamPro.bbangShuttle.vo.CartVO;
import teamPro.bbangShuttle.vo.ItemVO;

import java.lang.reflect.Member;
import java.util.List;

public interface CartService {

    List<CartVO> cartList(String userID);

    CartVO cartItem(CartVO vo);

    int cartSave(CartVO vo);

    int cartItemCount(CartVO vo);

    int cartItemDelete(CartVO vo);
}
