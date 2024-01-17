package fr.app.usermanager;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * UserManagerApplication
 *
 * @author Amine HADIDI
 */

@SpringBootApplication
public class UserManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserManagerApplication.class, args);
	}
	@Bean
    ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
