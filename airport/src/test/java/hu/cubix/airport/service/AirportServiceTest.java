package hu.cubix.airport.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import hu.cubix.airport.model.Airport;
import hu.cubix.airport.model.Flight;
import hu.cubix.airport.repository.AirportRepository;
import hu.cubix.airport.repository.FlightRepository;

@SpringBootTest
@AutoConfigureTestDatabase
public class AirportServiceTest {

	@Autowired
	AirportService airportService;
	
	@Autowired
	FlightRepository flightRepository;
	
	@Autowired
	AirportRepository airportRepository;
	
	@Test
	void testCreateFlight() throws Exception {
		//ARRANGE
		long takeoffId = airportRepository.save(new Airport("airport1", "aaaa")).getId();
		long landingId = airportRepository.save(new Airport("airport2", "bbbbb")).getId();
		LocalDateTime now = LocalDateTime.now();
		String flightNumber = "AAA";
		
		//ACT
		Flight flight = airportService.createFlight(takeoffId, landingId, flightNumber, now);
		
		//ASSERT
		Flight savedFlight = flightRepository.findById(flight.getId()).get();
		assertThat(savedFlight.getFlightNumber()).isEqualTo(flightNumber);
		assertThat(savedFlight.getTakeoff().getId()).isEqualTo(takeoffId);
		assertThat(savedFlight.getLanding().getId()).isEqualTo(landingId);
		assertThat(savedFlight.getTakeoffTime()).isCloseTo(now, within(1, ChronoUnit.MICROS));
	}
	
}
