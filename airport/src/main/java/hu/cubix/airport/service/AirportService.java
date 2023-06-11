package hu.cubix.airport.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import hu.cubix.airport.model.Airport;

@Service
public class AirportService {

	private Map<Long, Airport> airports = new HashMap<>();
	
	
	public Airport create(Airport airport) {
		if(findById(airport.getId()) != null) {
			return null;
		}
		return save(airport);
	}
	
	public Airport update(Airport airport) {
		if(findById(airport.getId()) == null) {
			return null;
		}
		return save(airport);
	}
	
	public Airport save(Airport airport) {
		throwIfNonUniqueIata(airport);
		airports.put(airport.getId(), airport);
		return airport;
	}
	
	private void throwIfNonUniqueIata(Airport airport) {
		if(airports.values().stream().anyMatch(a -> a.getIata().equals(airport.getIata())))
			throw new NonUniqueIataException();
	}
	
	public List<Airport> findAll(){
		return new ArrayList<>(airports.values());
	}
	
	public Airport findById(long id) {
		return airports.get(id);
	}
	
	public void delete(long id) {
		airports.remove(id);
	}
}
