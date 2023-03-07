package teamPro.bbangShuttle.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import teamPro.bbangShuttle.vo.CartVO;

import java.util.List;

@Service @Primary
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    @Override
    public List<CartVO> cartList() {
        return null;
    }

    @Override
    public int cartSave(CartVO vo) {
        return 0;
    }

    @Override
    public int cartItemCount(CartVO vo) {
        return 0;
    }
}
