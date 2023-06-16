package hu.cubix.airport;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import hu.cubix.airport.service.AirportService;
import hu.cubix.airport.service.PriceService;

@SpringBootApplication
public class AirportApplication implements CommandLineRunner {
	
	@Autowired
	PriceService priceService;
	
	@Autowired
	AirportService airportService;

	public static void main(String[] args) {
		SpringApplication.run(AirportApplication.class, args);		
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(priceService.getFinalPrice(100));
		System.out.println(priceService.getFinalPrice(2000));
		airportService.createFlight(1, 202, "ABC123", LocalDateTime.now());
	}

}
