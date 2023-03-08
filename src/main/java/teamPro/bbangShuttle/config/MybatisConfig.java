package teamPro.bbangShuttle.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import teamPro.bbangShuttle.mapper.CartMapper;
import teamPro.bbangShuttle.mapper.ItemMapper;
import teamPro.bbangShuttle.mapper.NoticeMapper;
import teamPro.bbangShuttle.mapper.QnAMapper;
import teamPro.bbangShuttle.mapper.ReviewMapper;
import teamPro.bbangShuttle.service.*;

@Configuration
@RequiredArgsConstructor
public class MybatisConfig {

    private final ItemMapper itemMapper;
    private final NoticeMapper noticeMapper;
    private final QnAMapper qnAMapper;
    private final ReviewMapper reviewMapper;

    private final CartMapper cartMapper;

    @Bean
    public ItemService itemService() {
        return new ItemServiceImpl(itemMapper);
    }

    @Bean
    public CartService cartService() {
        return new CartServiceImpl(cartMapper);
    }

    @Bean
    public NoticeService noticeService() {
        return new NoticeServiceImpl(noticeMapper);
    }

    @Bean
    public QnAService qnaService() {
        return new QnAServiceImpl(qnAMapper);
    }

    @Bean
    public ReviewService reviewService() {
        return new ReviewServiceImpl(reviewMapper);
    }
}
