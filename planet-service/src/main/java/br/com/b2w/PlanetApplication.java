package br.com.b2w;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.client.RestTemplate;


/**
 * @author bdiasti
 * 
 * Classe responsável pelo bootstrap de uma aplicação Spring boot
 *
 */
@EnableMongoRepositories
@EnableDiscoveryClient
@SpringBootApplication
public class PlanetApplication {

	private static final Logger logger = LoggerFactory.getLogger(PlanetApplication.class);
	
	public static void main(String[] args) {
		logger.info("Iniciando aplicação Spring boot....");
		SpringApplication.run(PlanetApplication.class, args);
	}

	@Bean
	public RestTemplate getRestTemplate() {
	   return new RestTemplate();
	}
	
}
