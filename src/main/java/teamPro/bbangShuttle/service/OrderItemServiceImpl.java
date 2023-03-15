package teamPro.bbangShuttle.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import teamPro.bbangShuttle.mapper.OrderItemMapper;
import teamPro.bbangShuttle.vo.OrderItemVO;

import java.util.List;

@Service @Primary
@RequiredArgsConstructor
public class OrderItemServiceImpl implements OrderItemSerive{
    private final OrderItemMapper orderItemMapper;

    @Override
    public List<OrderItemVO> orderItemList(int orderNo) {
        return orderItemMapper.orderItemList(orderNo);
    }

    @Override
    public int save(OrderItemVO vo) {
        return orderItemMapper.save(vo);
    }
}
