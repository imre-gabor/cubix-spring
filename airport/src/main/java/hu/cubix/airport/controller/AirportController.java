package hu.cubix.airport.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import hu.cubix.airport.dto.AirportDto;
import hu.cubix.airport.service.NonUniqueIataException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/airports")
public class AirportController {

	private Map<Long, AirportDto> airports = new HashMap<>();
	
	{
		airports.put(1L, new AirportDto(1, "Budapest Ferenc Liszt International", "BUD"));
	}
	
	@GetMapping
	public List<AirportDto> findAll(){
		return new ArrayList<>(airports.values());
	}
	
	@GetMapping("/{id}")
	public AirportDto findById(@PathVariable long id) {
		AirportDto airportDto = airports.get(id);
		if(airportDto == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		return airportDto;
	}
	
	@PostMapping
	public AirportDto create(@RequestBody @Valid AirportDto airport) {
		if(airports.containsKey(airport.getId()))
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

		throwIfNonUniqueIata(airport);
		airports.put(airport.getId(), airport);
		return airport;
	}

	private void throwIfNonUniqueIata(AirportDto airport) {
		if(airports.values().stream().anyMatch(a -> a.getIata().equals(airport.getIata())))
			throw new NonUniqueIataException();
	}
	
	
	@PutMapping("/{id}")
	public AirportDto update(@PathVariable long id, @RequestBody @Valid AirportDto airport
			/*,BindingResult bindingResult*/) {		
		airport.setId(id);
		if(!airports.containsKey(id))
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		throwIfNonUniqueIata(airport);
		
		airports.put(id, airport);
		return airport;
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable long id) {
		airports.remove(id);
	}
	
}
