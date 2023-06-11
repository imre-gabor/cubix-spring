package hu.cubix.airport.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import hu.cubix.airport.dto.AirportDto;
import hu.cubix.airport.model.Airport;

@Mapper(componentModel = "spring")
public interface AirportMapper {

	public AirportDto airportToDto(Airport airport);
	
	public List<AirportDto> airportsToDtos(List<Airport> airports);
	
	public Airport dtoToAirport(AirportDto airportDto);
	
}
