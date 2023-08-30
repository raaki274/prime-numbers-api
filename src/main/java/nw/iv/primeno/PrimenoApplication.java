package nw.iv.primeno;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages={"nw.iv.primeno",
		"nw.iv.primeno.rest.controller",
		"nw.iv.primeno.rest.calcmethod",
		"nw.iv.primeno.rest.calcmethod.impl",
		"nw.iv.primeno.utils",
		"nw.iv.primeno.exceptions",
		"nw.iv.primeno.test.unit",
		"nw.iv.primeno.test.integration"})

public class PrimenoApplication {

	public static void main(String[] args) {
		SpringApplication.run(PrimenoApplication.class, args);
	}

}
