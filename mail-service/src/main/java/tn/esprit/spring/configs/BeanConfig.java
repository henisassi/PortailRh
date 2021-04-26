package tn.esprit.spring.configs;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {
	
	@Bean
	public ScheduledExecutorService getScheduledExecutorService() {
		return Executors.newScheduledThreadPool(20);
	}

}
