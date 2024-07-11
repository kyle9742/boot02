package zerock.boot02;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Boot02Application {

    public static void main(String[] args) {
        SpringApplication.run(Boot02Application.class, args);
    }

}
