import java.util.Calendar;
import java.util.GregorianCalendar;


public class DateUtil {
	private final static int DAY_START = 0;
	private final static int DAY_END = 1;
	private final static int MTH_START = 3;
	private static int day;
	private static int month;
	private static int year;
	private static int mth_end;
	private static int year_start;
	private static int year_end;
	
	public static GregorianCalendar convertFromDMY(String dd_mm_yy) {
		String temp;
		temp = dd_mm_yy.substring(DAY_START, DAY_END);
		day = Integer.parseInt(temp);
		if(dd_mm_yy.charAt(mth_end)=='-') {
			//so 22-1-1999
			mth_end = MTH_START;
			month = Integer.parseInt(dd_mm_yy.substring(MTH_START, mth_end));
			year_start = 5;
			year_end = 8;
			year = Integer.parseInt(dd_mm_yy.substring(year_start, year_end));
		}
		else {
			//22-10-1999
			mth_end = 4;
			month = Integer.parseInt(dd_mm_yy.substring(MTH_START, mth_end));
			year_start = 6;
			year_end = 9;
			year = Integer.parseInt(dd_mm_yy.substring(year_start, year_end));					
		}
		GregorianCalendar cal = new GregorianCalendar(day,month,year);
		return cal;
	}
	
	public static String format(GregorianCalendar date) {
		String dateStr = Calendar.DAY_OF_MONTH + "-" + Calendar.MONTH + "-" + Calendar.YEAR;
		return dateStr;
		
	}
}
