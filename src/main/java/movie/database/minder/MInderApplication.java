package movie.database.minder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableJpaRepositories(basePackages = {"minder.authentication","movie.database.minder"})
@EntityScan(basePackages = {"minder.authentication","movie.database.minder"})
@ComponentScan(basePackages = {"minder.authentication","movie.database.minder"})
public class MInderApplication {
    public static void main(String[] args) {
        SpringApplication.run(MInderApplication.class, args);
    }

}
