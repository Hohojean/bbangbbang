package teamPro.bbangShuttle.mapper;

import org.apache.ibatis.annotations.Mapper;
import teamPro.bbangShuttle.vo.ItemVO;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ItemMapper {

    List<ItemVO> findAllItem();

    Optional<ItemVO> ItemDetail(ItemVO vo);

    int save(ItemVO vo);

    int update(ItemVO vo);

    int delete(ItemVO vo);
}
