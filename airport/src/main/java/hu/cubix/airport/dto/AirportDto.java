package hu.cubix.airport.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PositiveOrZero;

public record AirportDto(@PositiveOrZero long id, @NotEmpty String name, @NotEmpty String iata) {
	public AirportDto() {
		this(0, null, null);
	}
}
