package p12.ocr.prestataire;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class PrestataireApplication {

	public static void main(String[] args) {
		SpringApplication.run(PrestataireApplication.class, args);
	}

}
