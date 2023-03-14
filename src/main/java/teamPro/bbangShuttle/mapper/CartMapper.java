package teamPro.bbangShuttle.mapper;

import org.apache.ibatis.annotations.Mapper;
import teamPro.bbangShuttle.vo.CartVO;

import java.util.List;

@Mapper
public interface CartMapper {
    List<CartVO> cartList(String userID);
    CartVO cartItem(CartVO vo);

    int cartSave(CartVO vo);

    int cartItemCount(CartVO vo);

    int cartItemDelete(CartVO vo);
}
