package br.com.pp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * Start da aplicacao Spring Boot.
 * 
 * @author George
 *
 */
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@SpringBootApplication
public class FrontEndPP {

	public static void main(String[] args) {
		SpringApplication.run(FrontEndPP.class, args);
	}
}
