package teamPro.bbangShuttle.service;

import teamPro.bbangShuttle.vo.OrderVO;

import java.util.List;

public interface OrderService {
    List<OrderVO> orderListById(OrderVO vo);

    List<OrderVO> orderListByAll();

    int save(OrderVO vo);
}
