package nl.workshop2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Workshop2cSpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(Workshop2cSpringbootApplication.class, args);
	}
	
	/*
	 * TODO onderstaande methode implementeren met data uit testdata.sql. Vergeet niet alle benodigde repo's te zetten.
	 * Voordeel: de DB wordt elke keer als de app start opgebouwd en alle aanpassingen in het domein kunnen zo dus meegenomen worden.
	 */
	
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
