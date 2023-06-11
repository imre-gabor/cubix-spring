package hu.cubix.airport.service;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PriceServiceTest {

	@InjectMocks
	PriceService priceService;
	
	@Mock
	DiscountService discountService;
	
	
	@Test
	void testGetFinalPrice() {
		//ARRANGE
		priceService = new PriceService(p -> 5);
		
		//ACT
		int result = priceService.getFinalPrice(100);
	
		//ASSERT
		assertThat(result).isEqualTo(95);
		
		//ANNIHILATE: empty this case
	}
	
	@Test
	void testGetFinalPrice2() throws Exception {
		
		when(discountService.getDiscountPercent(anyInt())).thenReturn(5);
		
		int result = priceService.getFinalPrice(100);
		
		assertThat(result).isEqualTo(95);
		verify(discountService).getDiscountPercent(100);
	}
	
}
