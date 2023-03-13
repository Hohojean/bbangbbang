package teamPro.bbangShuttle.service;

import teamPro.bbangShuttle.vo.OrderItemVO;

import java.util.List;

public interface OrderItemSerive {

    List<OrderItemVO> orderItemList(OrderItemVO vo);

    OrderItemVO orderItemSelectOne(OrderItemVO vo);

    int save(OrderItemVO vo);
}
