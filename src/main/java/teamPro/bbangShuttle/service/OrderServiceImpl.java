package teamPro.bbangShuttle.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import teamPro.bbangShuttle.mapper.OrderMapper;
import teamPro.bbangShuttle.vo.OrderVO;

import java.util.List;

@Service @Primary
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{
    private final OrderMapper orderMapper;

    @Override
    public List<OrderVO> orderListById(String userID) {
        return orderMapper.orderListById(userID);
    }

    @Override
    public int save(OrderVO vo) {
        return orderMapper.save(vo);
    }
}
