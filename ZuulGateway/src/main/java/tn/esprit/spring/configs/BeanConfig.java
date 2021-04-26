package tn.esprit.spring.configs;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@Component
public class BeanConfig {
	
	@Bean
	public String getString() { // @value
		return new String();
	}

}
