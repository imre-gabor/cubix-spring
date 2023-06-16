package hu.cubix.airport.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hu.cubix.airport.model.Flight;

public interface FlightRepository extends JpaRepository<Flight, Long>{

}
