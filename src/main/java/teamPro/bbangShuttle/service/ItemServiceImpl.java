package teamPro.bbangShuttle.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import teamPro.bbangShuttle.mapper.ItemMapper;
import teamPro.bbangShuttle.vo.ItemVO;

import java.util.List;
import java.util.Optional;

@Service
@Primary @RequiredArgsConstructor
public class ItemServiceImpl implements ItemService{

    private final ItemMapper itemmapper;

    @Override
    public List<ItemVO> findAllItem() {
        return itemmapper.findAllItem();
    }

    @Override
    public Optional<ItemVO> ItemDetail(int itemNo) {
        return itemmapper.ItemDetail(itemNo);
    }

    @Override
    public int save(ItemVO vo) {
        return itemmapper.save(vo);
    }

    @Override
    public int update(ItemVO vo) {
        return itemmapper.update(vo);
    }

    @Override
    public int delete(ItemVO vo) {
        return itemmapper.delete(vo);
    }
}
