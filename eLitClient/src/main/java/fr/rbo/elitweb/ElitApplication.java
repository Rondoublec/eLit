package fr.rbo.elitweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ElitApplication {

	public static void main(String[] args) {
		SpringApplication.run(ElitApplication.class, args);
	}

}
