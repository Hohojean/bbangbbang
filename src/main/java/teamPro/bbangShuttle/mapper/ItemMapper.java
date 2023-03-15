package teamPro.bbangShuttle.mapper;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.ibatis.annotations.Mapper;
import teamPro.bbangShuttle.vo.ItemVO;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ItemMapper {
    List<ItemVO> findAllItem();

    List<ItemVO> randList();

    Optional<ItemVO> ItemDetail(int itemNo);

    int save(ItemVO vo);

    int update(ItemVO vo);

    int delete(ItemVO vo);

}
