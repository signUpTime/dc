package com.qq.common.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import com.qq.common.constants.CommonConstants;

public class TimeUtil {
	private TimeUtil() {
	}

	public static String timestampToString(Timestamp time, String formart) {
		try {
			DateFormat sdf = new SimpleDateFormat(formart);
			if (null == time) {
				return "";
			}
			return sdf.format(time);
		} catch (Exception e) {
			return "";
		}
	}

	public static Timestamp dateToTimeStamp(Date date) {
		if (null == date) {
			return null;
		}
		return new Timestamp(date.getTime());
	}

	public static Timestamp stringToTimeStamp(String date, String formart) {
		Timestamp ts = null;
		if (null == date || "".equals(date)) {
			return ts;
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat(formart);
		try {
			Date dateTime = dateFormat.parse(date);
			ts = new Timestamp(dateTime.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return ts;
	}

	/**
	 * @Title: WDateToTimeStamp
	 * @Description: WDate时间yyyy-MM-dd 默认为8点，此处将时间提前8小时，接受前台空间时间专用
	 * @param @param date
	 * @param @return
	 * @return Timestamp
	 * @throws
	 */
	public static Timestamp WDateToTimeStamp(Date date) {
		if (null == date) {
			return null;
		}
		Calendar Cal = java.util.Calendar.getInstance();
		Cal.setTime(date);
		Cal.add(java.util.Calendar.HOUR_OF_DAY, -8);
		return new Timestamp(Cal.getTime().getTime());
	}
	/**
	 * @Title: WDateToTimeStamp
	 * @Description: WDate时间yyyy-MM-dd 默认为8点，此处将时间提前8小时，接受前台空间时间专用
	 * @param @param date
	 * @param @return
	 * @return Timestamp
	 * @throws
	 */
	public static Timestamp WDateToTimeStamp(String date) {
		if (null == date) {
			return null;
		}
		return stringToTimeStamp(date, "yyyy-MM-dd");
	}

	// 负数为前 正式为后
	public static Date timeAround(Date date, int hour) {
		java.util.Calendar Cal = java.util.Calendar.getInstance();
		Cal.setTime(date);
		Cal.add(java.util.Calendar.HOUR_OF_DAY, hour);
		Date result = Cal.getTime();
		return result;
	}
	
	public static Timestamp timeAround(Timestamp time,int hour){
		java.util.Calendar Cal = java.util.Calendar.getInstance();
		Cal.setTime(time);
		Cal.add(java.util.Calendar.HOUR_OF_DAY, hour);
		return new Timestamp(Cal.getTime().getTime());
	}
	public static Map<String, String> defaultQueryTime() {
		Map<String, String> defaultTime = new HashMap<String, String>();
		Date startDate = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat(
				CommonConstants.DATE_FORMART_DATE_SPACES);
		String startTime = sdf.format(startDate);
		defaultTime.put("endTime", startTime);
		defaultTime.put("startTime", startTime);
		return defaultTime;
	}

	public static String dateToString(Date date, String formart) {
		SimpleDateFormat sdf = new SimpleDateFormat(formart);
		if(date==null){
			return "";
		}
		return sdf.format(date);
	}

	/**
	 * @Title: getWeek
	 * @Description: 根据日期获取星期几
	 * @param @param strDate
	 * @param @param formart
	 * @param @return
	 * @return String
	 * @throws
	 */
	public static String getWeek(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return new SimpleDateFormat("EEEE").format(calendar.getTime());
	}

	/**
	 * @Title: getMondayOfPreviousWeek
	 * @Description: 获取上周周一日期
	 * @param @param strDate
	 * @param @param formart
	 * @param @return
	 * @return Date
	 * @throws
	 */
	public static String getMondayOfPreviousWeek(String strDate, String formart) {
		Date date = null;
		SimpleDateFormat shortDateFormat = new SimpleDateFormat(formart);
		try {
			date = shortDateFormat.parse(strDate);
		} catch (ParseException e) {
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int day_of_week = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		if (day_of_week == 0) {
			day_of_week = 7;
		}
		calendar.add(Calendar.DATE, -day_of_week + 1);
		calendar.add(Calendar.DATE, -7);
		return shortDateFormat.format(calendar.getTime());
	}

	/**
	 * @Title: getMondayOfNextWeek
	 * @Description: 获取下周周一日期
	 * @param @param strDate
	 * @param @param formart
	 * @param @return
	 * @return Date
	 * @throws
	 */
	public static String getMondayOfNextWeek(String strDate, String formart) {
		Date date = null;
		SimpleDateFormat shortDateFormat = new SimpleDateFormat(formart);
		try {
			date = shortDateFormat.parse(strDate);
		} catch (ParseException e) {
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int day_of_week = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		if (day_of_week == 0) {
			day_of_week = 7;
		}
		calendar.add(Calendar.DATE, -day_of_week + 1);
		calendar.add(Calendar.DATE, +7);
		return shortDateFormat.format(calendar.getTime());
	}

	/**
	 * @Title: getMondayOfThisWeek
	 * @Description: 获取当周周一
	 * @param @param strDate
	 * @param @param formart
	 * @param @return
	 * @return Date
	 * @throws
	 */
	public static String getMondayOfThisWeek(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int day_of_week = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		if (day_of_week == 0) {
			day_of_week = 7;
		}
		calendar.add(Calendar.DATE, -day_of_week + 1);
		SimpleDateFormat shortDateFormat = new SimpleDateFormat(
				CommonConstants.DATE_FORMART_DATE_SPACES);
		return shortDateFormat.format(calendar.getTime());
	}

	public static Date getMondayDateOfThisWeek(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int day_of_week = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		if (day_of_week == 0) {
			day_of_week = 7;
		}
		calendar.add(Calendar.DATE, -day_of_week + 1);
		return calendar.getTime();
	}
	/** 
	* @Title: getSundayDateOfThisWeek 
	* @Description: 获取本周周日
	* @param @param dateStr
	* @param @return
	* @return Timestamp
	* @throws 
	*/
	public static Timestamp getSundayDateOfThisWeek(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int day_of_week = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		if (day_of_week == 0) {
			day_of_week = 7;
		}
		calendar.add(Calendar.DATE, -day_of_week + 7);
		return new Timestamp(calendar.getTime().getTime());
	}
	/** 
	* @Title: getMondayDateOfThisWeek 
	* @Description: 获取本周周一
	* @param @param dateStr
	* @param @return
	* @return Timestamp
	* @throws 
	*/
	public static Timestamp getMondayDateOfThisWeek(String dateStr) {
		Date date = null;
		SimpleDateFormat shortDateFormat = new SimpleDateFormat(CommonConstants.DATE_FORMART_DATE_SPACES);
		try {
			date = shortDateFormat.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int day_of_week = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		if (day_of_week == 0) {
			day_of_week = 7;
		}
		calendar.add(Calendar.DATE, -day_of_week + 1);
		return new Timestamp(calendar.getTime().getTime());
	}
	
	/** 
	* @Title: getSundayDateOfThisWeek 
	* @Description: 获取本周周日
	* @param @param dateStr
	* @param @return
	* @return Timestamp
	* @throws 
	*/
	public static Timestamp getSundayDateOfThisWeek(String dateStr) {
		Date date = null;
		SimpleDateFormat shortDateFormat = new SimpleDateFormat(CommonConstants.DATE_FORMART_DATE_SPACES);
		try {
			date = shortDateFormat.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int day_of_week = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		if (day_of_week == 0) {
			day_of_week = 7;
		}
		calendar.add(Calendar.DATE, -day_of_week + 7);
		return new Timestamp(calendar.getTime().getTime());
	}
	
	/** 
	* @Title: getMondayDateOfThisWeek 
	* @Description: 获取上周周一
	* @param @param dateStr
	* @param @return
	* @return Timestamp
	* @throws 
	*/
	public static Timestamp getMondayDateOfLastWeek(String dateStr) {
		Date date = null;
		SimpleDateFormat shortDateFormat = new SimpleDateFormat(CommonConstants.DATE_FORMART_DATE_SPACES);
		try {
			date = shortDateFormat.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int day_of_week = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		if (day_of_week == 0) {
			day_of_week = 7;
		}
		calendar.add(Calendar.DATE, -day_of_week + 1);
		calendar.add(Calendar.DATE, -7);
		return new Timestamp(calendar.getTime().getTime());
	}
	
	/** 
	* @Title: getSundayDateOfThisWeek 
	* @Description: 获取上周周日
	* @param @param dateStr
	* @param @return
	* @return Timestamp
	* @throws 
	*/
	public static Timestamp getSundayDateOfLastWeek(String dateStr) {
		Date date = null;
		SimpleDateFormat shortDateFormat = new SimpleDateFormat(CommonConstants.DATE_FORMART_DATE_SPACES);
		try {
			date = shortDateFormat.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int day_of_week = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		if (day_of_week == 0) {
			day_of_week = 7;
		}
		calendar.add(Calendar.DATE, -day_of_week + 7);
		calendar.add(Calendar.DATE, -7);
		return new Timestamp(calendar.getTime().getTime());
	}
	/**
	 * 获取第二天时间
	 */
	public static Date getFollowingDay(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, 1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}
	
	public static Date getFirstDayOfMonth(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, 0);
		cal.set(Calendar.DAY_OF_MONTH,1);
		return cal.getTime();
	}
	
	public static Date getLastDayOfMonth(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		return cal.getTime();
	}
	
	public static String getFirstDayOfMonth(String dateStr){
		Date date = null;
		SimpleDateFormat shortDateFormat = new SimpleDateFormat(CommonConstants.DATE_FORMART_MONTH_SPACES);
		try {
			date = shortDateFormat.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, 0);
		cal.set(Calendar.DAY_OF_MONTH,1);
		SimpleDateFormat sdf=new SimpleDateFormat(CommonConstants.DATE_FORMART_DATE_SPACES);
		return sdf.format(cal.getTime());
	}
	
	public static String getLastDayOfMonth(String dateStr){
		Date date = null;
		SimpleDateFormat shortDateFormat = new SimpleDateFormat(CommonConstants.DATE_FORMART_MONTH_SPACES);
		try {
			date = shortDateFormat.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		SimpleDateFormat sdf=new SimpleDateFormat(CommonConstants.DATE_FORMART_DATE_SPACES);
		return sdf.format(cal.getTime());
	}
	
	public static String getFirstMonday(String dateStr) {
		Date date = null;
		SimpleDateFormat shortDateFormat = new SimpleDateFormat(CommonConstants.DATE_FORMART_MONTH_SPACES);
		try {
			date = shortDateFormat.parse(dateStr);
		} catch (ParseException e) {
			return null;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		int i = 1;
		while (cal.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
			cal.set(Calendar.DAY_OF_MONTH, i++);
		}

		Date firstMonday = cal.getTime();
		SimpleDateFormat sdf=new SimpleDateFormat(CommonConstants.DATE_FORMART_DATE_SPACES);
		return sdf.format(firstMonday);
	}
	public static int getDaysBetween(Date startDate, Date endDate) {
		GregorianCalendar fromCalendar = new GregorianCalendar(); 

        GregorianCalendar toCalendar = new GregorianCalendar(); 

        fromCalendar.setTime(startDate); 

        toCalendar.setTime(endDate); 

		return (int)((toCalendar.getTimeInMillis()-fromCalendar.getTimeInMillis())/(1000*3600*24));
		}
	public static Date stringToDate(String dateStr, String dateFormartDate2) {
		Date date = null;
		SimpleDateFormat shortDateFormat = new SimpleDateFormat(
				dateFormartDate2);
		try {
			date = shortDateFormat.parse(dateStr);
		} catch (ParseException e) {
			return null;
		}
		return date;
	}

	public static String getFollowingDay(String beginDate) {
		Date date = null;
		SimpleDateFormat shortDateFormat = new SimpleDateFormat(CommonConstants.DATE_FORMART_DATE_SPACES);
		try {
			date = shortDateFormat.parse(beginDate);
		} catch (ParseException e) {
			return null;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, 1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		SimpleDateFormat sdf=new SimpleDateFormat(CommonConstants.DATE_FORMART_DATE_SPACES);
		return sdf.format(cal.getTime());
	}
	public static String getPreviousDay(String beginDate) {
		Date date = null;
		SimpleDateFormat shortDateFormat = new SimpleDateFormat(CommonConstants.DATE_FORMART_DATE_SPACES);
		try {
			date = shortDateFormat.parse(beginDate);
		} catch (ParseException e) {
			return null;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, -1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		SimpleDateFormat sdf=new SimpleDateFormat(CommonConstants.DATE_FORMART_DATE_SPACES);
		return sdf.format(cal.getTime());
	}
	public static Timestamp getTimesmorning(Timestamp time){
		Calendar cal = Calendar.getInstance();
		cal.setTime(time);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return new Timestamp(cal.getTime().getTime());
		}
	
	public static Date getStartOfDay(Date date){
		if(date == null){
			return null;
		}else{
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			return cal.getTime();
		}
	}
	public static Date getEndOfDay(Date date){
		if(date == null){
			return null;
		}else{
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.set(Calendar.HOUR_OF_DAY, 23);
			cal.set(Calendar.MINUTE, 59);
			cal.set(Calendar.SECOND, 59);
			return cal.getTime();
		}
	}
	
	public static Timestamp getStartTimeOfDay(Date date){
		if(date == null){
			return null;
		}else{
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			return new Timestamp(cal.getTime().getTime());
		}
	}
	public static Timestamp getEndTimeOfDay(Date date){
		if(date == null){
			return null;
		}else{
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.set(Calendar.HOUR_OF_DAY, 23);
			cal.set(Calendar.MINUTE, 59);
			cal.set(Calendar.SECOND, 59);
			return new Timestamp(cal.getTime().getTime());
		}
	}
	
	public static Date getStartOfMonth(Date date){
		if(date == null){
			return null;
		}else{
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.set(Calendar.DAY_OF_MONTH, 1);
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			return cal.getTime();
		}
	}
	public static Date getEndOfMonth(Date date){
		if(date == null){
			return null;
		}else{
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
			cal.set(Calendar.HOUR_OF_DAY, 23);
			cal.set(Calendar.MINUTE, 59);
			cal.set(Calendar.SECOND, 59);
			return cal.getTime();
		}
	}
	
	public static long getMinBetweenTime(Timestamp time1,Timestamp time2){
		return (time1.getTime()-time2.getTime())/60000;
	}
}
