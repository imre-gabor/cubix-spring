package hu.cubix.airport.service;

import org.springframework.data.jpa.domain.Specification;

import hu.cubix.airport.model.Airport_;
import hu.cubix.airport.model.Flight;
import hu.cubix.airport.model.Flight_;

public class FlightSpecifications {

	public static Specification<Flight> hasId(long id){
		return (root, cq, cb) -> cb.equal(root.get(Flight_.id), id); 
	}
	
	public static Specification<Flight> flightNumberStartsWith(String prefix){
		return (root, cq, cb) -> cb.like(cb.lower(root.get(Flight_.flightNumber)), prefix.toLowerCase() + "%"); 
	}
	
	public static Specification<Flight> flightHasTakeoffId(long takeoffId){
		return (root, cq, cb) -> cb.equal(root.get(Flight_.takeoff).get(Airport_.id), takeoffId); 
	}
}
