package nl.workshop2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Workshop2cSpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(Workshop2cSpringbootApplication.class, args);
	}
	
	/*
	 * Methode om data te zetten in je database, zoals nu in het mysql_workshop2_testdata.sql script
	 * 
	@Bean
	public CommandLineRunner dataLoader(AccountRepository accountRepo, KlantRepository klantRepo) {
		return new CommandLineRunner() {
			@Override
			public void run(String... args) throws Exception {
		
			//Te zetten data
			
			}
	}
	*/
}
