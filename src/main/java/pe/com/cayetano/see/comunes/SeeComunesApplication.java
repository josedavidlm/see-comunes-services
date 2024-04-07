package pe.com.cayetano.see.comunes;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@OpenAPIDefinition
@EnableDiscoveryClient
public class SeeComunesApplication {

	public static void main(String[] args) {
		SpringApplication.run(SeeComunesApplication.class, args);
	}

}
