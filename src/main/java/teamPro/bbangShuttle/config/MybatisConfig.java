package teamPro.bbangShuttle.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import teamPro.bbangShuttle.mapper.ItemMapper;
import teamPro.bbangShuttle.mapper.NoticeMapper;
import teamPro.bbangShuttle.mapper.QnAMapper;
import teamPro.bbangShuttle.service.*;

@Configuration
@RequiredArgsConstructor
public class MybatisConfig {

    private final ItemMapper itemMapper;
    private final NoticeMapper noticeMapper;
    private final QnAMapper qnAMapper;

    @Bean
    public ItemService itemService() {
        return new ItemServiceImpl(itemMapper);
    }

    @Bean
    public NoticeService noticeService() {
        return new NoticeServiceImpl(noticeMapper);
    }

    @Bean
    public QnAService qnaService() {
        return new QnAServiceImpl(qnAMapper);
    }
}
