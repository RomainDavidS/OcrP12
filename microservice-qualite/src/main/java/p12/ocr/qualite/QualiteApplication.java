package p12.ocr.qualite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class QualiteApplication {

	public static void main(String[] args) {
		SpringApplication.run(QualiteApplication.class, args);
	}

}
