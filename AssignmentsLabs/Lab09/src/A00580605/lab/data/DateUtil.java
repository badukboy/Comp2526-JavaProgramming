package A00580605.lab.data;

import java.text.DateFormat;
import java.util.GregorianCalendar;

/**
 * @author Steve Lo, A00580605
 * 
 */
public class DateUtil {

	public static GregorianCalendar convertFromDMY(String dd_mm_yy) {
		String[] elements = dd_mm_yy.split("-");
		if (elements == null || elements.length != 3) {
			return null;
		}

		int year = Integer.parseInt(elements[2]);
		int month = Integer.parseInt(elements[1]) - 1; // GregorianCalendar
														// expects 0-based month
		int dayOfMonth = Integer.parseInt(elements[0]);
		GregorianCalendar date = new GregorianCalendar(year, month, dayOfMonth);
		return date;

	}
	
	public static String convertToDMY(GregorianCalendar date) {
		String convertedDate = "";
		String tempDate;
		DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.SHORT);
		tempDate = dateFormat.format(date.getTime());
		for (int i=0; i<tempDate.length(); i++) {
			if(tempDate.charAt(i)=='/') 
				convertedDate += "-";
			else
				convertedDate += tempDate.charAt(i);
		}
		return convertedDate;
	}

	public static String format(GregorianCalendar date) {
		DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM);
		return dateFormat.format(date.getTime());
	}
}