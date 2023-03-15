package teamPro.bbangShuttle.service;

import teamPro.bbangShuttle.vo.OrderItemVO;

import java.util.List;

public interface OrderItemSerive {
    List<OrderItemVO> orderItemList(int orderNo);

    int save(OrderItemVO vo);
}
