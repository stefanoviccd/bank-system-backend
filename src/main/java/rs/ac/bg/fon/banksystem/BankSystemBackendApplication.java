package rs.ac.bg.fon.banksystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class BankSystemBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankSystemBackendApplication.class, args);
	}

}
