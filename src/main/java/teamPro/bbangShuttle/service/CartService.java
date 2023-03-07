package teamPro.bbangShuttle.service;

import teamPro.bbangShuttle.vo.CartVO;
import teamPro.bbangShuttle.vo.ItemVO;

import java.lang.reflect.Member;
import java.util.List;

public interface CartService {

    List<CartVO> cartList();

    int cartSave(CartVO vo);

    int cartItemCount(CartVO vo);
}
