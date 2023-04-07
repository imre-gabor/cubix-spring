package hu.cubix.airport.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "airport")
@Component
public class AirportConfigurationProperties {
	
	private Discount discount;	

	public Discount getDiscount() {
		return discount;
	}


	public void setDiscount(Discount discount) {
		this.discount = discount;
	}


	public static class Discount {
		
		private Special special;
		
		public Special getSpecial() {
			return special;
		}

		public void setSpecial(Special special) {
			this.special = special;
		}

		public static class Special{
			private int limit;
			private int lowerPercent;
			private int higherPercent;
			
			public int getLimit() {
				return limit;
			}
			public void setLimit(int limit) {
				this.limit = limit;
			}
			public int getLowerPercent() {
				return lowerPercent;
			}
			public void setLowerPercent(int lowerPercent) {
				this.lowerPercent = lowerPercent;
			}
			public int getHigherPercent() {
				return higherPercent;
			}
			public void setHigherPercent(int higherPercent) {
				this.higherPercent = higherPercent;
			}			
			
		}
		
	}
	
}
