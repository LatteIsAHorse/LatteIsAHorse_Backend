package team.latte.LatteIsAHorse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class LatteIsAHorseApplication {

	public static void main(String[] args) {
		SpringApplication.run(LatteIsAHorseApplication.class, args);
	}

}
