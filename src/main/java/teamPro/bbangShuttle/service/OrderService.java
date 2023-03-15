package teamPro.bbangShuttle.service;

import teamPro.bbangShuttle.vo.OrderVO;

import java.util.List;

public interface OrderService {
    List<OrderVO> orderListById(String userID);

    int save(OrderVO vo);
}
