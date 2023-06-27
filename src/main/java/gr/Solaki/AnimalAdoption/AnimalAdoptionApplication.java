package gr.Solaki.AnimalAdoption;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class AnimalAdoptionApplication {

	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.configure()
				.ignoreIfMissing()
				.load();

		dotenv.entries().forEach(entry -> {
			String key = entry.getKey();
			String value = entry.getValue();
			System.setProperty(key, value);
		});


		SpringApplication.run(AnimalAdoptionApplication.class, args);
	}

}
