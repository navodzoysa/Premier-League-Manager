package models;

import java.io.Serializable;

public class DateTime implements Serializable {
	private int day;
	private int month;
	private int year;
	private int hour;
	private int minute;

	public DateTime() {}

	public DateTime(int day, int month, int year, int hour, int minute) {
		this.day = day;
		this.month = month;
		this.year = year;
		this.hour = hour;
		this.minute = minute;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getDay() {
		return day;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getMonth() {
		return month;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getYear() {
		return year;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public int getHour() {
		return hour;
	}

	public void setMinute(int minute) {
		this.minute = minute;
	}

	public int getMinute() {
		return minute;
	}
	
	@Override
	public String toString() {
		return day + "/" + month + "/" + year + " at " + hour + ":" + minute;
	}
}
