package hu.cubix.airport.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hu.cubix.airport.model.AirportUser;

public interface AirportUserRepository extends JpaRepository<AirportUser, String> {
	
}
