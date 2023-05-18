package hu.cubix.airport.service;

public class NonUniqueIataException extends RuntimeException {

	public NonUniqueIataException() {
		super("IATA should be unique");
	}
}
