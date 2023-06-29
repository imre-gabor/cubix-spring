package hu.cubix.airport.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.validation.constraints.Size;

//@NamedQuery(
//	name = "Airport.countByIata",
//	query = "SELECT COUNT(a) FROM Airport a WHERE a.iata = :iata"
//)
//@NamedQuery(
//		name = "Airport.countByIataAndIdNot",
//		query = "SELECT COUNT(a) FROM Airport a WHERE a.iata = :iata AND a.id != :id"
//)

@Entity
public class Airport {

	@Id
	@GeneratedValue
	private long id;	
	private String name;
	
	@Size(min = 2, max = 5)
	private String iata;
	
	public Airport() {
	}
	
	public Airport(String name, String iata) {
		this(0, name, iata);
	}
	
	public Airport(long id, String name, String iata) {
		super();
		this.id = id;
		this.name = name;
		this.iata = iata;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIata() {
		return iata;
	}
	public void setIata(String iata) {
		this.iata = iata;
	}

}
