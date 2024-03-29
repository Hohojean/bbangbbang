package teamPro.bbangShuttle.service;

import org.springframework.stereotype.Service;
import teamPro.bbangShuttle.vo.ItemVO;

import java.util.List;
import java.util.Optional;


public interface ItemService {
    List<ItemVO> findAllItem();

    List<ItemVO> randList();

    Optional<ItemVO> ItemDetail(int itemNo);

    int save(ItemVO vo);

    int update(ItemVO vo);

    int delete(int itemNo);
}
