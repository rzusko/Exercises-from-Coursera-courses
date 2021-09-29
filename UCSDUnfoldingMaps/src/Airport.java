
public class Airport {
	private String city;
	private String country;
	private String code3;
	
	public String getCity() {
		return this.city;
	}
	
	public String getCountry() {
		return this.country;
	}

	public String getCode() {
		return this.code3;
	}
	
	public static String findAirportCode (String toFind, Airport[] airports) {
		int i = 0;
		
		while (i < airports.length) {
			Airport a = airports[i];
			String city = a.getCity();
			
			if (city.equals(toFind)) {
				return a.getCode();
			}
			
			i++;
		}
		
		
		return "Not found!!!";
	}
	
	public void selSort(int[] inArray) {
		for (int i = 0; i < inArray.length-1; i++) {
			int min = inArray[i];
			int minIndex = i;
			for (int j = i + 1; j < inArray.length; j++) {
				int curr = inArray[j]; 
				if (curr < min) {
					min = curr;
					minIndex = j;
				}
			}
			inArray[minIndex] = inArray[i];
			inArray[i] = min;
		}			
	}
	
	
}
