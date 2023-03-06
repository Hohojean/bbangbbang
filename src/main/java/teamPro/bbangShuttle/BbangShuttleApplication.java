package teamPro.bbangShuttle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import teamPro.bbangShuttle.config.MybatisConfig;

@SpringBootApplication
@Import(MybatisConfig.class)
public class BbangShuttleApplication {

	public static void main(String[] args) {
		SpringApplication.run(BbangShuttleApplication.class, args);
	}

}
