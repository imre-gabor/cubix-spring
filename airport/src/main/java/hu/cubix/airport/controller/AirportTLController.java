package hu.cubix.airport.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import hu.cubix.airport.dto.AirportDto;

@Controller
public class AirportTLController {

	private List<AirportDto> airports = new ArrayList<>();
	
	{
		airports.add(new AirportDto(1, "Budapest Ferenc Liszt International", "BUD"));
	}
	

	@GetMapping("/")
	public String home(Map<String, Object> model) {
		model.put("airports", airports);
		model.put("newAirport", new AirportDto());
		return "index";
	}
	
	
	@PostMapping("/airport")
	public String createAirport(AirportDto airport) {
		airports.add(airport);
		return "redirect:/";
	}
}
