package teamPro.bbangShuttle.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import teamPro.bbangShuttle.mapper.ItemMapper;
import teamPro.bbangShuttle.service.ItemService;
import teamPro.bbangShuttle.service.ItemServiceImpl;

@Configuration
@RequiredArgsConstructor
public class MybatisConfig {

    private final ItemMapper itemMapper;

    @Bean
    public ItemService itemService() {
        return new ItemServiceImpl(itemMapper);
    }
}
