package id.protection.interview.api.util;

public class Converter {

	public static String convertToString (Integer data) {
		String result = "";
		
		result = data == null ? "" : data.toString();
		
		return result;
	}
	public static Long parseToLong (String numeric) {
		Long result = null;
		
		result = numeric == null ? 0L : Long.valueOf(numeric);
		
		return result;
	}
	public static Long parseToLong (Double numeric) {
		Long result = null;
		
		result = numeric == null ? 0L : numeric.longValue();
		
		return result;
	}
	public static Double parseToDouble (String numeric) {
		Double result = null;
		
		result = (numeric == null || numeric.equalsIgnoreCase("")) ? null : Double.valueOf(numeric);
		
		return result;
	}
}
