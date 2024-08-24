package ramune314159265.dateToXpBar;

import java.time.LocalDateTime;
import java.time.Year;

public class YearInfo {
	static public int getMonthDay() {
		LocalDateTime now = LocalDateTime.now();
		int month = now.getMonthValue();
		int day = now.getDayOfMonth();
		return month * 100 + day;
	}

	static public float getYearProgress() {
		Year year = Year.now();
		int yearLength = year.length();
		int yearPassed = LocalDateTime.now().getDayOfYear();
		return (float) yearPassed / yearLength;
	}
}
