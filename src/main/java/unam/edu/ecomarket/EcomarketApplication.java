package unam.edu.ecomarket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class EcomarketApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcomarketApplication.class, args);
	}

}
