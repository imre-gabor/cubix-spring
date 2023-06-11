package hu.cubix.airport.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("prod")
public class PriceServiceProdIT {

	@Autowired
	PriceService priceService;
	
	@Test
	void testGetFinalPriceUnderLimit() throws Exception {
		int result = priceService.getFinalPrice(100);
		
		assertThat(result).isEqualTo(90);
	}
	
	@Test
	void testGetFinalPriceOverLimit() throws Exception {
		int result = priceService.getFinalPrice(2000);
		
		assertThat(result).isEqualTo(1700);
	}
}
