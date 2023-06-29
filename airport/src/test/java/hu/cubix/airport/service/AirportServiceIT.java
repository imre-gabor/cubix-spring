package hu.cubix.airport.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
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
public class AirportServiceIT {

	@Autowired
	AirportService airportService;
	
	@Autowired
	FlightRepository flightRepository;
	
	@Autowired
	AirportRepository airportRepository;
	
	@BeforeEach
	public void init() {
		flightRepository.deleteAllInBatch();
		airportRepository.deleteAllInBatch();
	}
	
	@Test
	void testCreateFlight() throws Exception {
		//ARRANGE
		long takeoffId = createAirport("airport1", "aaaa");
		long landingId = createAirport("airport2", "bbbbb");
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

	private long createAirport(String name, String iata) {
		return airportRepository.save(new Airport(name, iata)).getId();
	}
	
	@Test
	void testFlightSearchByFlightIdAndTakeoffId() {
		
		long takeoffId = createAirport("airport1", "aaaa");
		long landingId = createAirport("airport2", "bbbbb");
		Flight flight1 = airportService.createFlight(takeoffId, landingId, "ABC123", LocalDateTime.now());
		Flight flight2 = airportService.createFlight(takeoffId, landingId, "ABC234", LocalDateTime.now());
		Flight flight3 = airportService.createFlight(landingId, takeoffId, "ABC567", LocalDateTime.now());
		Flight flight4 = airportService.createFlight(takeoffId, landingId, "BBC123", LocalDateTime.now());
		
		Flight example = new Flight(new Airport(takeoffId, null, null), null, "ABC", null);
		List<Flight> foundFlights = airportService.findFlightsByExample(example);
		
		assertThat(foundFlights).containsExactlyInAnyOrder(flight1, flight2);
	}
	
	@Test
	void testFlightSearchByFlightId() {
		
		long takeoffId = createAirport("airport1", "aaaa");
		long landingId = createAirport("airport2", "bbbbb");
		Flight flight1 = airportService.createFlight(takeoffId, landingId, "ABC123", LocalDateTime.now());
		Flight flight2 = airportService.createFlight(takeoffId, landingId, "ABC234", LocalDateTime.now());
		Flight flight3 = airportService.createFlight(landingId, takeoffId, "ABC567", LocalDateTime.now());
		Flight flight4 = airportService.createFlight(takeoffId, landingId, "BBC123", LocalDateTime.now());
		
		Flight example = new Flight(null, null, "ABC", null);
		List<Flight> foundFlights = airportService.findFlightsByExample(example);
		
		assertThat(foundFlights).containsExactlyInAnyOrder(flight1, flight2, flight3);
	}
	
}
