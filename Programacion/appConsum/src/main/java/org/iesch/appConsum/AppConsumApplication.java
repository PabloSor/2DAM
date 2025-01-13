package org.iesch.appConsum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class AppConsumApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(AppConsumApplication.class, args);
	}

	@Autowired
	RestTemplate restTemplate;

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Hola");

		String resultado = getDataFromApi();
		System.out.println(resultado);
	}

	public String getDataFromApi(){
		String url = "http://localhost:8080/saluda";
		return restTemplate.getForObject(url, String.class);
	}
}
