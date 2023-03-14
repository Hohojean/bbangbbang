package teamPro.bbangShuttle.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import teamPro.bbangShuttle.mapper.CartMapper;
import teamPro.bbangShuttle.vo.CartVO;

import java.util.List;

@Service @Primary
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartMapper cartMapper;

    @Override
    public List<CartVO> cartList(String userID) {
        return cartMapper.cartList(userID);
    }

    @Override
    public CartVO cartItem(CartVO vo) {
        return cartMapper.cartItem(vo);
    }

    @Override
    public int cartSave(CartVO vo) {
        return cartMapper.cartSave(vo);
    }

    @Override
    public int cartItemCount(CartVO vo) {
        return cartMapper.cartItemCount(vo);
    }

    @Override
    public int cartItemDelete(CartVO vo) {
        return cartMapper.cartItemDelete(vo);
    }


}
