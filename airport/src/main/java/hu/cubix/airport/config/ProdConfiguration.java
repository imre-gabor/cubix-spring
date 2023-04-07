package hu.cubix.airport.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import hu.cubix.airport.service.DefaultDiscountService;
import hu.cubix.airport.service.DiscountService;
import hu.cubix.airport.service.SpecialDiscountService;

@Configuration
@Profile("prod")
public class ProdConfiguration {

	@Bean
	public DiscountService discountService() {
		return new SpecialDiscountService();
	}
}
