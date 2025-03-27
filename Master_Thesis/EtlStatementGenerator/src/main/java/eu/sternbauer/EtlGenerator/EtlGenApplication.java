package eu.sternbauer.EtlGenerator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class EtlGenApplication {

	public static void main(String[] args) {
		SpringApplication.run(EtlGenApplication.class, args);
	}

}
