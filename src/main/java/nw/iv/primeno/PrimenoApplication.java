package nw.iv.primeno;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages={"nw.iv.primeno",
		"nw.iv.primeno.rest.controller",
		"nw.iv.primeno.rest.calcmethod",
		"nw.iv.primeno.rest.calcmethod.impl",
		"nw.iv.primeno.exceptions",
		"nw.iv.primeno.ut",
		"nw.iv.primeno.integration"})
@EnableCaching
public class PrimenoApplication {

	public static void main(String[] args) {
		SpringApplication.run(PrimenoApplication.class, args);
	}

}
