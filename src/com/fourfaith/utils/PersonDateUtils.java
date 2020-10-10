package com.fourfaith.utils;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类
 */
public class PersonDateUtils {
	
	// 获取当前年份(全局)
	static Calendar now = Calendar.getInstance();
	static int currentYear = now.get(Calendar.YEAR);
	
	// 1-12第一天和最后一天
	public static String start_1 = currentYear + "-01-01 00:00:00";
	public static String end_1 = currentYear + "-01-31 23:59:59";
	
	public static String start_3 = currentYear + "-03-01 00:00:00";
	public static String end_3 = currentYear + "-03-31 23:59:59";

	public static String start_4 = currentYear + "-04-01 00:00:00";
	public static String end_4 = currentYear + "-04-30 23:59:59";

	public static String start_5 = currentYear + "-05-01 00:00:00";
	public static String end_5 = currentYear + "-05-31 23:59:59";

	public static String start_6 = currentYear + "-06-01 00:00:00";
	public static String end_6 = currentYear + "-06-30 23:59:59";

	public static String start_7 = currentYear + "-07-01 00:00:00";
	public static String end_7 = currentYear + "-07-31 23:59:59";

	public static String start_8 = currentYear + "-08-01 00:00:00";
	public static String end_8 = currentYear + "-08-31 23:59:59";

	public static String start_9 = currentYear + "-09-01 00:00:00";
	public static String end_9 = currentYear + "-09-30 23:59:59";

	public static String start_10 = currentYear + "-10-01 00:00:00";
	public static String end_10 = currentYear + "-10-31 23:59:59";

	public static String start_11 = currentYear + "-11-01 00:00:00";
	public static String end_11 = currentYear + "-11-30 23:59:59";

	public static String start_12 = currentYear + "-12-01 00:00:00";
	public static String end_12 = currentYear + "-12-31 23:59:59";
	/**
	 * string转date
	 * @param dateStr
	 * @param pattern
	 * 日期格式
	 */
	public static Date StringToDate(String dateStr, String pattern) {
		Date date = null;
		try {
			SimpleDateFormat df = new SimpleDateFormat(pattern);
			date = df.parse(dateStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * date转string
	 * @param date
	 * @param pattern
	 * 日期格式
	 */
	public static String DateToString(Date date, String pattern) {
		String dateStr = null;
		try {
			SimpleDateFormat df = new SimpleDateFormat(pattern);
			dateStr = df.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dateStr;
	}

	/**
	 * date转Calendar
	 * @param date
	 */
	public static Calendar DateToCalendar(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal;
	}

	/**
	 * 设置日期为当天的最小时间
	 * @param date
	 */
	public static Date setMinTimeForDate(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	/**
	 * 设置日期为当天的最大时间
	 * @param date
	 */
	public static Date setMaxTimeForDate(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 999);
		return cal.getTime();
	}

	/**
	 * 设置日期为当月的最小时间
	 * @param date
	 */
	public static Date setMinTimeForMonthDate(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DATE, 1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	/**
	 * 设置日期为当月最大时间
	 * @param date
	 */
	public static Date setMaxTimeForMonthDate(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 999);
		return cal.getTime();
	}

	/**
	 * 设置日期为当年的最小时间
	 * @param date
	 */
	public static Date setMinTimeForYearDate(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DATE, 1);
		cal.set(Calendar.MONTH, 0);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	/**
	 * 设置日期为当年最大时间
	 * @param date
	 */
	public static Date setMaxTimeForYearDate(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));
		cal.set(Calendar.MONTH, cal.getActualMaximum(Calendar.MONTH));
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 999);
		return cal.getTime();
	}

	/**
	 * 判断两时间是否相等
	 * @param date1
	 * @param date2
	 */
	public static boolean isEquals(Date date1, Date date2) {
		boolean flag = false;
		if (DateToCalendar(date1).equals(DateToCalendar(date2))) {
			flag = true;
		}
		return flag;
	};

	/**
	 * 判断date1是否在date2之后(date1>date2)
	 * @param date1
	 * @param date2
	 */
	public static boolean isMoreThan(Date date1, Date date2) {
		boolean flag = false;
		if (DateToCalendar(date1).after(DateToCalendar(date2))) {
			flag = true;
		}
		return flag;
	};

	/**
	 * 判断date1是否在date2之前(date1<date2)
	 * @param date1
	 * @param date2
	 */
	public static boolean isLessThan(Date date1, Date date2) {
		boolean flag = false;
		if (DateToCalendar(date1).before(DateToCalendar(date2))) {
			flag = true;
		}
		return flag;
	};

	/**
	 * 判断两时间是否在同一小时
	 * @param date1
	 * @param date2
	 */
	public static boolean isSameHour(Date date1, Date date2) {
		String pattern = "yyyyMMddHH";
		return DateToString(date1, pattern)
				.equals(DateToString(date2, pattern));
	};

	/**
	 * 判断两时间是否在同日
	 * @param date1
	 * @param date2
	 */
	public static boolean isSameDay(Date date1, Date date2) {
		String pattern = "yyyyMMdd";
		return DateToString(date1, pattern)
				.equals(DateToString(date2, pattern));
	};

	/**
	 * 判断两时间是否在同月
	 * @param date1
	 * @param date2
	 */
	public static boolean isSameMonth(Date date1, Date date2) {
		String pattern = "yyyyMM";
		return DateToString(date1, pattern).equals(DateToString(date2, pattern));
	};

	/**
	 * 判断两时间是否在同年
	 * @param date1
	 * @param date2
	 */
	public static boolean isSameYear(Date date1, Date date2) {
		String pattern = "yyyy";
		return DateToString(date1, pattern)
				.equals(DateToString(date2, pattern));
	};

	/**
	 * 判断两时间是否在1小时内(含1小时)
	 * @param beginDate
	 * @param endDate
	 */
	public static boolean in1Hours(Date beginDate, Date endDate) {
		boolean flag = false;
		Calendar cal1 = DateToCalendar(beginDate);
		Calendar cal2 = DateToCalendar(endDate);
		cal1.add(Calendar.HOUR_OF_DAY, 1);
		if (cal1.after(cal2) || cal1.equals(cal2)) {
			flag = true;
		}
		return flag;
	};

	/**
	 * 判断两时间是否在同一个月内(含)
	 * @param beginDate
	 * @param endDate
	 */
	public static boolean in1Month(Date beginDate, Date endDate) {
		boolean flag = false;
		Calendar cal1 = DateToCalendar(beginDate);
		Calendar cal2 = DateToCalendar(endDate);

		if (cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH)) {
			flag = true;
		}
		return flag;
	};

	/**
	 * 判断两时间是否在24小时内(含24小时)
	 * @param beginDate
	 * @param endDate
	 */
	public static boolean in24Hours(Date beginDate, Date endDate) {
		boolean flag = false;
		Calendar cal1 = DateToCalendar(beginDate);
		Calendar cal2 = DateToCalendar(endDate);
		cal1.add(Calendar.DATE, 1);
		if (cal1.after(cal2) || cal1.equals(cal2)) {
			flag = true;
		}
		return flag;
	};

	/**
	 * 判断时间是否为整点(分钟为0)
	 * @param date
	 */
	public static boolean isOnTheHour(Date date) {
		boolean flag = false;
		Calendar cal1 = DateToCalendar(date);
		if (cal1.get(Calendar.MINUTE) == 0 && cal1.get(Calendar.SECOND) == 0) {
			flag = true;
		}
		return flag;
	};

	/**
	 * 判断时间是否为一天的开始(小时、分钟为0)
	 * @param date
	 */
	public static boolean isOnTheDay(Date date) {
		boolean flag = false;
		Calendar cal1 = DateToCalendar(date);
		if (cal1.get(Calendar.HOUR_OF_DAY) == 0 && isOnTheHour(date)) {
			flag = true;
		}
		return flag;
	};

	/**
	 * 根据参数设置时间
	 * @param year
	 * @param month
	 * @param day
	 * @param hour
	 * @param minute
	 */
	public static Date getDate(int year, int month, int day, int hour,
			int minute) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month - 1);
		cal.set(Calendar.DATE, day);
		cal.set(Calendar.HOUR_OF_DAY, hour);
		cal.set(Calendar.MINUTE, minute);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	public static Date addSeconds(Date date, int amount) {
		return add(date, 13, amount);
	}

	/**
	 * 叠加特定的时间属性值
	 * @param date
	 * @param calendarField
	 * @param amount
	 */
	public static Date add(Date date, int calendarField, int amount) {
		if (date == null) {
			throw new IllegalArgumentException("The date must not be null");
		} else {
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			c.add(calendarField, amount);
			return c.getTime();
		}
	}

	/**
	 * 设置特定的时间属性值
	 * @param date
	 * @param calendarField
	 * @param amount
	 */
	public static Date set(Date date, int calendarField, int value) {
		if (date == null) {
			throw new IllegalArgumentException("The date must not be null");
		} else {
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			c.set(calendarField, value);
			return c.getTime();
		}
	}
	
	/**
	 * @Title: getCurrentYear
	 * @Description: 获取当前时间 年
	 * @return: void
	 * @Author: zhaojx
	 * @Date: 2017年7月25日 上午10:25:36
	 */
	public static int getCurrentYear(){
        Calendar now = Calendar.getInstance();
        int year = now.get(Calendar.YEAR);
        return year;
	}
	
	/**
	 * @Title: getCurrentMouth
	 * @Description: 获取当前时间 月
	 * @return: void
	 * @Author: zhaojx
	 * @Date: 2017年7月25日 上午10:25:36
	 */
	public static int getCurrentMouth(){
        Calendar now = Calendar.getInstance();
        int mouth = now.get(Calendar.MONTH) + 1;
        return mouth;
	}
	
	/**
	 * @Title: getCurrentDay
	 * @Description: 获取当前时间 日
	 * @return: void
	 * @Author: zhaojx
	 * @Date: 2017年7月25日 上午10:25:36
	 */
	public static int getCurrentDay(){
        Calendar now = Calendar.getInstance();
        int day = now.get(Calendar.DAY_OF_MONTH);
        return day;
	}
	
	/**
	 * @Title: getCurrentHour
	 * @Description: 获取当前时间 时
	 * @return: void
	 * @Author: zhaojx
	 * @Date: 2017年7月25日 上午10:25:36
	 */
	public static int getCurrentHour(){
        Calendar now = Calendar.getInstance();
        int hour = now.get(Calendar.HOUR_OF_DAY);
        return hour;
	}
	
	/**
	 * @Title: getCurrentMinute
	 * @Description: 获取当前时间 分
	 * @return: void
	 * @Author: zhaojx
	 * @Date: 2017年7月25日 上午10:25:36
	 */
	public static int getCurrentMinute(){
        Calendar now = Calendar.getInstance();
        int minute = now.get(Calendar.MINUTE);
        return minute;
	}
	
	/**
	 * @Title: getCurrentSecond
	 * @Description: 获取当前时间 秒
	 * @return: void
	 * @Author: zhaojx
	 * @Date: 2017年7月25日 上午10:25:36
	 */
	public static int getCurrentSecond(){
        Calendar now = Calendar.getInstance();
        int second = now.get(Calendar.SECOND);
        return second;
	}
	
	/**
	 * @Title: formatDateString
	 * @Description: 格式化日期
	 * @return: String
	 * @Author: zhaojx
	 * @Date: 2017年7月25日 上午10:41:23
	 */
	public static String formatDateString(){
		Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
	}
	
	public static void main(String[] args) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		Date date = new Date();    
		System.out.println("sss:" + sdf.format(date));
		
		Date getDate = Calendar.getInstance().getTime();
	
		Calendar cal = Calendar.getInstance();
		Date now = new Date(cal.get(Calendar.YEAR));
		System.out.println("year:" + now);
		System.out.println("当前时间:" + getDate);
	}
	
	
	
	
	//计算两时间相差分钟，小时，天
	public static long getDatePoor(String sign ,Date endDate, Date startDate) {
		long time = 0;
	    long nd = 1000 * 24 * 60 * 60;
	    long nh = 1000 * 60 * 60;
	    long nm = 1000 * 60;
	    long diff = endDate.getTime() - startDate.getTime();
	    if(sign.equals("day")){
	    	time = diff / nd;
	    }else if(sign.equals("hour")){
	    	time = diff % nd / nh;
	    }else if(sign.equals("minute")){
	    	time = diff % nd % nh / nm;
	    }
	    return time;
	}
	
	
}