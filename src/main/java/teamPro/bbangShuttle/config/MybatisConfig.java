package teamPro.bbangShuttle.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import teamPro.bbangShuttle.mapper.ItemMapper;
import teamPro.bbangShuttle.mapper.NoticeMapper;
import teamPro.bbangShuttle.service.ItemService;
import teamPro.bbangShuttle.service.ItemServiceImpl;
import teamPro.bbangShuttle.service.NoticeService;
import teamPro.bbangShuttle.service.NoticeServiceImpl;

@Configuration
@RequiredArgsConstructor
public class MybatisConfig {

    private final ItemMapper itemMapper;
    private final NoticeMapper noticeMapper;

    @Bean
    public ItemService itemService() {
        return new ItemServiceImpl(itemMapper);
    }

    @Bean
    public NoticeService noticeService() {
        return new NoticeServiceImpl(noticeMapper);
    }
}
