package hu.cubix.airport.model;

import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Flight {

	@Id
	@GeneratedValue
	private long id;
	@ManyToOne
	private Airport takeoff;
	@ManyToOne
	private Airport landing;
	private String flightNumber;
	private LocalDateTime takeoffTime;
	
	public Flight() {	
	}
	
	public Flight(Airport takeoff, Airport landing, String flightNumber, LocalDateTime takeoffTime) {
		super();		
		this.takeoff = takeoff;
		this.landing = landing;
		this.flightNumber = flightNumber;
		this.takeoffTime = takeoffTime;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Airport getTakeoff() {
		return takeoff;
	}
	public void setTakeoff(Airport takeoff) {
		this.takeoff = takeoff;
	}
	public Airport getLanding() {
		return landing;
	}
	public void setLanding(Airport landing) {
		this.landing = landing;
	}
	public String getFlightNumber() {
		return flightNumber;
	}
	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}
	public LocalDateTime getTakeoffTime() {
		return takeoffTime;
	}
	public void setTakeoffTime(LocalDateTime takeoffTime) {
		this.takeoffTime = takeoffTime;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Flight other = (Flight) obj;
		return id == other.id;
	}
	
	
}
