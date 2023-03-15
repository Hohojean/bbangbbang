package teamPro.bbangShuttle.mapper;

import org.apache.ibatis.annotations.Mapper;
import teamPro.bbangShuttle.vo.OrderItemVO;

import java.util.List;

@Mapper
public interface OrderItemMapper {
    List<OrderItemVO> orderItemList(int orderNo);

    int save(OrderItemVO vo);
}
