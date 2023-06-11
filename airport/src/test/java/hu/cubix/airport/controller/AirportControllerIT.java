package hu.cubix.airport.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.reactive.server.WebTestClient;

import hu.cubix.airport.dto.AirportDto;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class AirportControllerIT {
	
	private static final String API_AIRPORTS = "/api/airports";
	
	@Autowired
	WebTestClient webTestClient;

	@Test
	void testThatCreatedAirportIsListed() {
		List<AirportDto> airportsBefore = getAllAirports();		
		long newId = 100;
		if(airportsBefore.size() > 0) {
			newId = airportsBefore.get((airportsBefore.size() - 1)).id() + 1;
		}
		AirportDto newAirport = new AirportDto(newId, "test name", "test iata");
		
		createAirport(newAirport);		
		List<AirportDto> airportsAfter = getAllAirports();
		
		assertThat(airportsAfter.subList(0, airportsBefore.size()))
			.usingRecursiveFieldByFieldElementComparator()
			.containsExactlyElementsOf(airportsBefore);
		assertThat(airportsAfter.get(airportsAfter.size() - 1))
			.usingRecursiveComparison()
			.isEqualTo(newAirport);			
	}

	private void createAirport(AirportDto newAirport) {
		webTestClient
		.post()
		.uri(API_AIRPORTS)
		.bodyValue(newAirport)
		.exchange()
		.expectStatus().isOk();		
	}

	private List<AirportDto> getAllAirports() {
		List<AirportDto> allAirports = webTestClient
		.get()
		.uri(API_AIRPORTS)
		.exchange()
		.expectStatus().isOk()
		.expectBodyList(AirportDto.class)
		.returnResult()
		.getResponseBody();
		
		Collections.sort(allAirports, Comparator.comparing(AirportDto::id));
		
		return allAirports;
	}
}
