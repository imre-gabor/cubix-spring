package hu.cubix.airport.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hu.cubix.airport.model.Airport;

public interface AirportRepository extends JpaRepository<Airport, Long>{

	Long countByIata(String iata);
	
	Long countByIataAndIdNot(String iata, Long id);
	
}
