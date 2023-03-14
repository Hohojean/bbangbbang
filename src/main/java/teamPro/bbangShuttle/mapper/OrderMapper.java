package teamPro.bbangShuttle.mapper;

import org.apache.ibatis.annotations.Mapper;
import teamPro.bbangShuttle.vo.OrderVO;

import java.util.List;

@Mapper
public interface OrderMapper {

    List<OrderVO> orderListById(String userID);

    List<OrderVO> orderListByAll();

    int save(OrderVO vo);

}
