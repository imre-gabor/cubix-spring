package hu.cubix.airport.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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
import hu.cubix.airport.mapper.AirportMapper;
import hu.cubix.airport.model.Airport;
import hu.cubix.airport.service.AirportService;
import hu.cubix.airport.service.NonUniqueIataException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/airports")
public class AirportController {
	
	@Autowired
	private AirportService airportService;
	
	@Autowired
	private AirportMapper airportMapper;
	
	@GetMapping
	public List<AirportDto> findAll(){
		List<Airport> allAirports = airportService.findAll();
		return airportMapper.airportsToDtos(allAirports);
	}
	
	@GetMapping("/{id}")
	public AirportDto findById(@PathVariable long id) {
		Airport airport = airportService.findById(id);
		if(airport == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		return airportMapper.airportToDto(airport);
	}
	
	@PostMapping
	public AirportDto create(@RequestBody @Valid AirportDto airportDto) {
		
		Airport airport = airportMapper.dtoToAirport(airportDto);
		Airport savedAirport = airportService.create(airport);
		
		if(savedAirport == null)
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);		
		
		return airportMapper.airportToDto(savedAirport);
	}

	
	
	
	@PutMapping("/{id}")
	public AirportDto update(@PathVariable long id, @RequestBody @Valid AirportDto airportDto
			/*,BindingResult bindingResult*/) {		
		airportDto = new AirportDto(airportDto.id(), airportDto.name(), airportDto.iata());
		Airport airport = airportMapper.dtoToAirport(airportDto);
		Airport updatedAirport = airportService.update(airport);
		
		if(updatedAirport == null)
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		
		return airportMapper.airportToDto(updatedAirport);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable long id) {
		airportService.delete(id);
	}
	
}
