package Assignment4;

import java.util.Formatter;

public class Job implements Comparable<Job> {

	private String mName;
	int mDay, mMonth, mYear, mHr, mMin;

	public Job(String name, int month, int day, int year, int hr, int min) {
		mName = name;
		mDay = day;
		mMonth = month;
		mYear = year;
		mHr = hr;
		mMin = min;
	}

	public String getName() {
		return mName;
	}

	@Override
	public int compareTo(Job s) {
		// higher value return negative cuz they go first
		if (mYear != s.mYear) {
			return mYear > s.mYear ? 1 : -1;
		} else if (mMonth != s.mMonth) {
			return mMonth > s.mMonth ? 1 : -1;
		} else if (mDay != s.mDay) {
			return mDay > s.mDay ? 1 : -1;
		} else if (mHr != s.mHr) {
			return mHr > s.mHr ? 1 : -1;
		} else if (mMin != s.mMin) {
			return mMin > s.mMin ? 1 : -1;
		} else
			return 0;
	}

	@Override
	public String toString() {
		String mDate = mMonth + "/" + mDay + "/" + mYear;
		String mTime = mHr + ":" + mMin;
		
		String template = "%-50s %-15s %-6s ";
		StringBuilder stringBuilder = new StringBuilder();
		Formatter formatter = new Formatter(stringBuilder);
		formatter.format(template, mName, mDate,mTime);
		formatter.close();
		return stringBuilder.toString();
	}
}
