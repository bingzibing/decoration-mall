package com.mydecoration.decorationmall.common.utils;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang.time.DateUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author chaibing
 * @Description
 * @create 2022/1/27 13:47
 */
public class DateUtil extends DateUtils {

    public final static long ONE_DAY_SECONDS = 86400;

    public final static long ONE_MINUTE_Millis = 60 * 1000;

    public final static long DAY_OF_HALF_YEAR = 180;

    /*
     * private static DateFormat dateFormat = null; private static DateFormat
     * longDateFormat = null; private static DateFormat dateWebFormat = null;
     */
    public final static String shortFormat = "yyyyMMdd";

    public final static String longFormat = "yyyyMMddHHmmss";

    public final static String webFormat = "yyyy-MM-dd";

    public final static String timeFormat = "HHmmss";

    public final static String timeFormat1 = "HHmm";

    public final static String monthFormat = "yyyyMM";

    public final static String chineseDtFormat = "yyyy年MM月dd日";

    public final static String newFormat = "yyyy-MM-dd HH:mm:ss";

    public final static String newFormat2 = "yyyy-MM-dd HH:mm";

    public final static String newFormat3 = "yyyy-MM-dd HH";

    public final static String FULL_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";

    public final static String FULL_DATE_FORMAT2 = "yyyyMMddHHmmssSSS";

    public final static String offlineTimeFormat = "yyyyMMdd";

    public final static String onlineTimeFormat = "yyyyMMddHH";

    public final static String noBlankFormat = "yyyyMMdd_HHmmss";
    public static long ONE_DAY_MILL_SECONDS = 86400000;

    static {
        /*
         * dateFormat = new SimpleDateFormat(shortFormat);
         * dateFormat.setLenient(false); longDateFormat = new
         * SimpleDateFormat(longFormat); longDateFormat.setLenient(false);
         * dateWebFormat = new SimpleDateFormat(webFormat);
         * dateWebFormat.setLenient(false);
         */
    }

    public static Date getNowDate() {
        return new Date();
    }

    public static Date getDate(long millsecond) {
        return new Date(millsecond);
    }

    public static Date getDateBySeconds(long second) {
        return getDate(second * 1000);
    }

    public static DateFormat getNewDateFormat(String pattern) {
        DateFormat df = new SimpleDateFormat(pattern);

        df.setLenient(false);
        return df;
    }

    public static void main(String[] args) {
        Date n = new Date();
        Date mm = formatDate(n, "yyyy-MM-dd");
        System.out.println(mm.toString());
        System.out.println(DateUtil.format(DateUtil.getStartTime(new Date()), DateUtil.onlineTimeFormat));
        System.out.println(DateUtil.format(DateUtil.getEndTimeOrNow(new Date()), DateUtil.onlineTimeFormat));
        System.out.println(getTodayString(shortFormat));
        System.out.println(getYesterdayString());
        System.out.println(getBeforeDayString(1, webFormat));
        System.out.println(getBeforeDayString(7, webFormat));
        Long orderamount = Double.valueOf(String.valueOf(0.0d)).longValue() * 10000L;
        System.out.println(orderamount);
        System.out.println(convert2NewFormat("20201017235959"));
        System.out.println(DateUtil.getWebDateString(DateUtil.addDays(new Date(), -7)));
        System.out.println(getEveryMonth("2020-02-29", "2020-02-28"));
        System.out.println(convert2OnlineFormat("2020-04-29"));
        System.out.println(isFirstDayOfMonth(new Date(2020, 5, 1)));
        System.out.println(getEveryHourDate("20200504"));
        System.out.println(getLastHourTime(new Date()));

        Date todayDate = new Date();
        Date yesterdayDate = DateUtil.addDays(todayDate, -1L);
        Date startDate = DateUtil.getStartTime(DateUtil.parseDateNewFormat("2020-05-03"));
        Date endDate = DateUtil.getEndTimeOrNow(DateUtil.parseDateNewFormat("2020-05-06"));
        System.out.println(yesterdayDate);
        System.out.println(endDate.after(yesterdayDate));
        System.out.println(yesterdayDate.after(startDate));
        System.out.println(getStartTime(todayDate).getTime());

        Date end = DateUtil.getEndTimeOrNow(DateUtil.parseDateNewFormat("2020-05-09"));
        System.out.println(isSameDay(n, end));
        System.out.println(getBeforeDayString(2, DateUtil.webFormat));
    }

    //日期格式转换，返回日期类型
    public static Date formatDate(Date date, String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        String dateString = formatter.format(date);
        ParsePosition pos = new ParsePosition(0);
        Date newDate = formatter.parse(dateString, pos);
        return newDate;
    }

    //日期格式转换，返回String
    public static String format(Date date, String format) {
        if (date == null) {
            return null;
        }

        return new SimpleDateFormat(format).format(date);
    }

    public static String formatByLong(long date, String format) {
        return new SimpleDateFormat(format).format(new Date(date));
    }

    public static String formatByString(String date, String format) {
        if (StringUtils.isNotBlank(date)) {
            return new SimpleDateFormat(format).format(new Date(NumberUtils
                    .toLong(date)));
        }
        return StringUtils.EMPTY;
    }

    public static String formatShortFormat(Date date) {
        if (date == null) {
            return null;
        }

        return new SimpleDateFormat(shortFormat).format(date);
    }

    public static Date parseDateNoTime(String sDate) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat(shortFormat);

        if ((sDate == null) || (sDate.length() < shortFormat.length())) {
            throw new ParseException("length too little", 0);
        }

        if (!StringUtils.isNumeric(sDate)) {
            throw new ParseException("not all digit", 0);
        }

        return dateFormat.parse(sDate);
    }

    /**
     * 获取一天的起始时间
     *
     * @param date
     * @return
     */
    public static Date getStartTime(Date date) {
        Calendar dateStart = Calendar.getInstance();
        dateStart.setTime(date);
        dateStart.set(Calendar.HOUR_OF_DAY, 0);
        dateStart.set(Calendar.MINUTE, 0);
        dateStart.set(Calendar.SECOND, 0);
        dateStart.set(Calendar.MILLISECOND, 0);
        return dateStart.getTime();
    }

    public static Date getEndTime(Date date) {
        Calendar dateEnd = Calendar.getInstance();
        dateEnd.setTime(date);
        dateEnd.set(Calendar.HOUR_OF_DAY, 23);
        dateEnd.set(Calendar.MINUTE, 59);
        dateEnd.set(Calendar.SECOND, 59);
        return dateEnd.getTime();
    }

    /**
     * 得到上个小时的开始时间
     *
     * @param date
     * @return
     */
    public static Date getLastHourTime(Date date) {
        Date lastHour = addHours(date, -1L);
        Calendar hourStart = Calendar.getInstance();
        hourStart.setTime(lastHour);
        hourStart.set(Calendar.MINUTE, 0);
        hourStart.set(Calendar.SECOND, 0);
        hourStart.set(Calendar.MILLISECOND, 0);
        return hourStart.getTime();
    }

    /**
     * 获取一天的结束时间,若date是今日则返回此时
     *
     * @param date
     * @return
     */
    public static Date getEndTimeOrNow(Date date) {

        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
        Date currentDate = new Date();
        String currentDateStr = sd.format(currentDate);
        String dateStr = sd.format(date);
        if (dateStr.compareTo(currentDateStr) >= 0) {
            return currentDate;
        }
        Calendar dateEnd = Calendar.getInstance();
        dateEnd.setTime(date);
        dateEnd.set(Calendar.HOUR_OF_DAY, 23);
        dateEnd.set(Calendar.MINUTE, 59);
        dateEnd.set(Calendar.SECOND, 59);
        return dateEnd.getTime();
    }

    public static Date parseDateNoTime(String sDate, String format)
            throws ParseException {
        if (StringUtils.isBlank(format)) {
            throw new ParseException("Null format. ", 0);
        }

        DateFormat dateFormat = new SimpleDateFormat(format);

        if ((sDate == null) || (sDate.length() < format.length())) {
            throw new ParseException("length too little", 0);
        }

        return dateFormat.parse(sDate);
    }

    public static Date parseDateNoTimeWithDelimit(String sDate, String delimit)
            throws ParseException {
        sDate = sDate.replaceAll(delimit, "");

        DateFormat dateFormat = new SimpleDateFormat(shortFormat);

        if ((sDate == null) || (sDate.length() != shortFormat.length())) {
            throw new ParseException("length not match", 0);
        }

        return dateFormat.parse(sDate);
    }

    public static Date parseDateLongFormat(String sDate) {
        DateFormat dateFormat = new SimpleDateFormat(longFormat);
        Date d = null;

        if ((sDate != null) && (sDate.length() == longFormat.length())) {
            try {
                d = dateFormat.parse(sDate);
            } catch (ParseException ex) {
                return null;
            }
        }

        return d;
    }

    public static Date parseDateNewFormat(String sDate) {
        Date d = parseDateHelp(sDate, newFormat);
        if (null != d) {
            return d;
        }
        d = parseDateHelp(sDate, newFormat2);
        if (null != d) {
            return d;
        }
        d = parseDateHelp(sDate, newFormat3);
        if (null != d) {
            return d;
        }
        d = parseDateHelp(sDate, webFormat);
        if (null != d) {
            return d;
        }
        d = parseDateHelp(sDate, shortFormat);
        if (null != d) {
            return d;
        }
        try {
            DateFormat dateFormat = new SimpleDateFormat(newFormat);
            return dateFormat.parse(sDate);
        } catch (ParseException ex) {
            return null;
        }
    }

    public static Date parseDateHelp(String sDate, String format) {
        if ((sDate != null) && (sDate.length() == format.length())) {
            try {
                DateFormat dateFormat = new SimpleDateFormat(format);
                return dateFormat.parse(sDate);
            } catch (ParseException ex) {
                return null;
            }
        }
        return null;
    }

    /**
     * 计算当前时间几小时之后的时间
     *
     * @param date
     * @param hours
     * @return
     */
    public static Date addHours(Date date, long hours) {
        return addMinutes(date, hours * 60);
    }

    /**
     * 计算当前时间几分钟之后的时间
     *
     * @param date
     * @param minutes
     * @return
     */
    public static Date addMinutes(Date date, long minutes) {
        return addSeconds(date, minutes * 60);
    }

    /**
     * @param date1
     * @param secs
     * @return
     */

    public static Date addSeconds(Date date1, long secs) {
        return new Date(date1.getTime() + (secs * 1000));
    }

    /**
     * 判断输入的字符串是否为合法的小时
     *
     * @param hourStr
     * @return true/false
     */
    public static boolean isValidHour(String hourStr) {
        if (!StringUtils.isEmpty(hourStr) && StringUtils.isNumeric(hourStr)) {
            int hour = new Integer(hourStr).intValue();

            return (hour >= 0) && (hour <= 23);
        }

        return false;
    }

    /**
     * 判断输入的字符串是否为合法的分或秒
     *
     * @param
     * @return true/false
     */
    public static boolean isValidMinuteOrSecond(String str) {
        if (!StringUtils.isEmpty(str) && StringUtils.isNumeric(str)) {
            int hour = new Integer(str).intValue();

            return (hour >= 0) && (hour <= 59);
        }

        return false;
    }

    /**
     * 取得新的日期
     *
     * @param date1 日期
     * @param days  天数
     * @return 新的日期
     */
    public static Date addDays(Date date1, long days) {
        return addSeconds(date1, days * ONE_DAY_SECONDS);
    }

    /**
     * 取得从当前开始多少天后的新日期
     *
     * @param days 天数
     * @return 新的日期
     */
    public static Date addDaysFromNow(long days) {
        return addSeconds(new Date(System.currentTimeMillis()), days
                * ONE_DAY_SECONDS);
    }

    public static String getTomorrowDateString(String sDate)
            throws ParseException {
        Date aDate = parseDateNoTime(sDate);

        aDate = addSeconds(aDate, ONE_DAY_SECONDS);

        return getDateString(aDate);
    }

    public static String getLongDateString(Date date) {
        DateFormat dateFormat = new SimpleDateFormat(longFormat);

        return getDateString(date, dateFormat);
    }

    public static String getNewFormatDateString(Date date) {
        DateFormat dateFormat = new SimpleDateFormat(newFormat);
        return getDateString(date, dateFormat);
    }

    public static String getFullDateFormat(Date date) {
        DateFormat dateFormat = new SimpleDateFormat(FULL_DATE_FORMAT);
        return getDateString(date, dateFormat);
    }

    public static String getSimpleFullDateFormat(Date date) {
        DateFormat dateFormat = new SimpleDateFormat(FULL_DATE_FORMAT2);
        return getDateString(date, dateFormat);
    }


    public static String getDateString(Date date, DateFormat dateFormat) {
        if (date == null || dateFormat == null) {
            return null;
        }

        return dateFormat.format(date);
    }

    public static String getYesterdayDateString(String sDate)
            throws ParseException {
        Date aDate = parseDateNoTime(sDate);

        aDate = addSeconds(aDate, -ONE_DAY_SECONDS);

        return getDateString(aDate);
    }

    /**
     * @return 当天的时间格式化为"yyyyMMdd"
     */
    public static String getDateString(Date date) {
        DateFormat df = getNewDateFormat(shortFormat);

        return df.format(date);
    }

    public static String getWebDateString(Date date) {
        DateFormat dateFormat = getNewDateFormat(webFormat);

        return getDateString(date, dateFormat);
    }

    /**
     * 取得“X年X月X日”的日期格式
     *
     * @param date
     * @return
     */
    public static String getChineseDateString(Date date) {
        DateFormat dateFormat = getNewDateFormat(chineseDtFormat);

        return getDateString(date, dateFormat);
    }

    public static String getTodayString(String outFormat) {
        DateFormat dateFormat = getNewDateFormat(outFormat);

        return getDateString(new Date(), dateFormat);
    }

    public static String getYesterdayString() {
        DateFormat dateFormat = getNewDateFormat(shortFormat);
        Date aDate = new Date();
        aDate = addSeconds(aDate, -ONE_DAY_SECONDS);
        return getDateString(aDate, dateFormat);
    }

    public static String getYesterdayString(String format) {
        DateFormat dateFormat = getNewDateFormat(format);
        Date aDate = new Date();
        aDate = addSeconds(aDate, -ONE_DAY_SECONDS);
        return getDateString(aDate, dateFormat);
    }

    public static String getTimeString(Date date) {
        DateFormat dateFormat = getNewDateFormat(timeFormat);

        return getDateString(date, dateFormat);
    }

    public static String getBeforeDayString(int days, String outFormat) {
        Date date = new Date(System.currentTimeMillis()
                - (ONE_DAY_MILL_SECONDS * days));
        DateFormat dateFormat = getNewDateFormat(outFormat);

        return getDateString(date, dateFormat);
    }

    public static String getBeforeDayString(Date date, int days, String outFormat) {
        Date date1 = new Date(date.getTime()
                - (ONE_DAY_MILL_SECONDS * days));
        DateFormat dateFormat = getNewDateFormat(outFormat);

        return getDateString(date1, dateFormat);
    }

    /**
     * 取得两个日期间隔毫秒数（日期1-日期2）
     *
     * @param one
     * @param two
     * @return 间隔毫秒数
     */
    public static long getDiffMilliseconds(Date one, Date two) {
        Calendar sysDate = new GregorianCalendar();

        sysDate.setTime(one);

        Calendar failDate = new GregorianCalendar();

        failDate.setTime(two);
        return (sysDate.getTimeInMillis() - failDate.getTimeInMillis());
    }

    /**
     * 取得两个日期间隔秒数（日期1-日期2）
     *
     * @param one 日期1
     * @param two 日期2
     * @return 间隔秒数
     */
    public static long getDiffSeconds(Date one, Date two) {
        Calendar sysDate = new GregorianCalendar();

        sysDate.setTime(one);

        Calendar failDate = new GregorianCalendar();

        failDate.setTime(two);
        return (sysDate.getTimeInMillis() - failDate.getTimeInMillis()) / 1000;
    }

    public static long getDiffMinutes(Date one, Date two) {
        Calendar sysDate = new GregorianCalendar();

        sysDate.setTime(one);

        Calendar failDate = new GregorianCalendar();

        failDate.setTime(two);
        return (sysDate.getTimeInMillis() - failDate.getTimeInMillis())
                / (60 * 1000);
    }

    /**
     * 取得两个日期的间隔天数
     *
     * @param one
     * @param two
     * @return 间隔天数
     */
    public static long getDiffDays(Date one, Date two) {
        Calendar sysDate = new GregorianCalendar();

        sysDate.setTime(one);

        Calendar failDate = new GregorianCalendar();

        failDate.setTime(two);
        return (sysDate.getTimeInMillis() - failDate.getTimeInMillis())
                / (24 * 60 * 60 * 1000);
    }

//    public static String getBeforeDayString(String dateString, int days) {
//        Date date;
//        DateFormat df = getNewDateFormat(shortFormat);
//
//        try {
//            date = df.parse(dateString);
//        } catch (ParseException e) {
//            date = new Date();
//        }
//
//        date = new Date(date.getTime() - (ONE_DAY_MILL_SECONDS * days));
//
//        return df.format(date);
//    }

    // 获取两个日期之间的每一天
    public static List<String> getErveryDate(String startDateStr, String endDateStr) {
        Date startDate = strToDate(startDateStr);
        Date endDate = strToDate(endDateStr);

        List<String> result = new ArrayList<>();
        Calendar calBegin = Calendar.getInstance();
        calBegin.setTime(startDate);
        Calendar calEnd = Calendar.getInstance();
        calEnd.setTime(endDate);
        result.add(dateToStr(calBegin.getTime()));
        while (endDate.after(calBegin.getTime())) {
            // 根据日历的规则，为给定的日历字段添加或减去指定的时间量
            calBegin.add(Calendar.DAY_OF_MONTH, 1);
            result.add(dateToStr(calBegin.getTime()));
        }

        return result;
    }

    // 获取两个日期之间的每个月
    public static List<String> getEveryMonth(String startDateStr, String endDateStr) {
        Date startDate = strToDate(startDateStr);
        Date endDate = strToDate(endDateStr);

        List<String> result = new ArrayList<>();
        Calendar calBegin = Calendar.getInstance();
        calBegin.setTime(startDate);
        Calendar calEnd = Calendar.getInstance();
        calEnd.setTime(endDate);
        calBegin.set(Calendar.DAY_OF_MONTH, 1);
        calEnd.set(Calendar.DAY_OF_MONTH, 2);
        while (calEnd.after(calBegin)) {
            // 根据日历的规则，为给定的日历字段添加或减去指定的时间量
            result.add(formatMonth(calBegin.getTime()));
            calBegin.add(Calendar.MONTH, 1);

        }
        return result;

    }

    // 获取该日小时列表
    public static List<String> getEveryHourDate(String str) {
        List<String> result = new ArrayList<>();

        for (int i = 0; i <= 23; i++) {
            if (i <= 9) {
                result.add(str + "0" + i);
            } else {
                result.add(str + i);
            }
        }
        return result;
    }

    public static boolean isFirstDayOfMonth(Date date) {

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        Calendar firstDayCal = Calendar.getInstance();
        firstDayCal.setTime(date);
        firstDayCal.set(Calendar.DAY_OF_MONTH, 1);
        return isSameDay(cal, firstDayCal);
    }

    // 将日期字符串转换为Date
    public static Date strToDate(String str) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = sdf.parse(str);
        } catch (ParseException ex) {
            return null;
        }
        return date;
    }

    // 将日期转为日期字符串
    public static String dateToStr(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(webFormat);
        return sdf.format(date);
    }

    // 将日期转为时间字符串
    public static String dateToTimeStr(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(timeFormat1);
        return sdf.format(date);
    }

    // 将日期转为月份字符串
    public static String dateToMonthStr(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(monthFormat);
        return sdf.format(date);
    }

    //根据日期计算周几
    public static String getWeekDay(int year,int month,int day) {
        if (month == 1) {
            month = 13;
            year--;
        }
        if (month == 2) {
            month = 14;
            year--;
        }
        int week = (day + 2 * month + 3 * (month + 1) / 5 + year + year / 4 - year / 100 + year / 400) % 7;
        String weekstr = "";
        switch (week) {
            case 0: weekstr = "周一"; break;
            case 1: weekstr = "周二"; break;
            case 2: weekstr = "周三"; break;
            case 3: weekstr = "周四"; break;
            case 4: weekstr = "周五"; break;
            case 5: weekstr = "周六"; break;
            case 6: weekstr = "周日"; break;
        }
        return weekstr;
    }

    public static boolean isValidShortDateFormat(String strDate) {
        if (strDate.length() != shortFormat.length()) {
            return false;
        }

        try {
            Integer.parseInt(strDate); // ---- 避免日期中输入非数字 ----
        } catch (Exception NumberFormatException) {
            return false;
        }

        DateFormat df = getNewDateFormat(shortFormat);

        try {
            df.parse(strDate);
        } catch (ParseException e) {
            return false;
        }

        return true;
    }

    public static boolean isValidShortDateFormat(String strDate,
                                                 String delimiter) {
        String temp = strDate.replaceAll(delimiter, "");

        return isValidShortDateFormat(temp);
    }

    /**
     * 判断表示时间的字符是否为符合yyyyMMddHHmmss格式
     *
     * @param strDate
     * @return
     */
    public static boolean isValidLongDateFormat(String strDate) {
        if (strDate.length() != longFormat.length()) {
            return false;
        }

        try {
            Long.parseLong(strDate); // ---- 避免日期中输入非数字 ----
        } catch (Exception NumberFormatException) {
            return false;
        }

        DateFormat df = getNewDateFormat(longFormat);

        try {
            df.parse(strDate);
        } catch (ParseException e) {
            return false;
        }

        return true;
    }

    /**
     * 判断表示时间的字符是否为符合yyyyMMddHHmmss格式
     *
     * @param strDate
     * @param delimiter
     * @return
     */
    public static boolean isValidLongDateFormat(String strDate, String delimiter) {
        String temp = strDate.replaceAll(delimiter, "");

        return isValidLongDateFormat(temp);
    }

    public static String getShortDateString(String strDate) {
        return getShortDateString(strDate, "-|/");
    }

    public static String getShortDateString(String strDate, String delimiter) {
        if (StringUtils.isBlank(strDate)) {
            return null;
        }

        String temp = strDate.replaceAll(delimiter, "");

        if (isValidShortDateFormat(temp)) {
            return temp;
        }

        return null;
    }

    public static String getShortFirstDayOfMonth() {
        Calendar cal = Calendar.getInstance();
        Date dt = new Date();

        cal.setTime(dt);
        cal.set(Calendar.DAY_OF_MONTH, 1);

        DateFormat df = getNewDateFormat(shortFormat);

        return df.format(cal.getTime());
    }

    public static String getWebTodayString() {
        DateFormat df = getNewDateFormat(webFormat);

        return df.format(new Date());
    }

    public static String getWebFirstDayOfMonth() {
        Calendar cal = Calendar.getInstance();
        Date dt = new Date();

        cal.setTime(dt);
        cal.set(Calendar.DAY_OF_MONTH, 1);

        DateFormat df = getNewDateFormat(webFormat);

        return df.format(cal.getTime());
    }

    public static String convert(String dateString, DateFormat formatIn,
                                 DateFormat formatOut) {
        try {
            Date date = formatIn.parse(dateString);

            return formatOut.format(date);
        } catch (ParseException e) {
            return "";
        }
    }

    public static String convertOnlineFormat2OfflineFormat(String dateString) {
        DateFormat online = getNewDateFormat(onlineTimeFormat);
        DateFormat offline = getNewDateFormat(offlineTimeFormat);

        return convert(dateString, online, offline);
    }

    public static String convert2OnlineFormat(String dateString) {

        DateFormat df1 = getNewDateFormat(webFormat);
        DateFormat df2 = getNewDateFormat(onlineTimeFormat);
        try {
            Date date = df1.parse(dateString);
            Date end = getEndTimeOrNow(date);
            return df2.format(end);
        } catch (ParseException e) {
            return "";
        }
    }

    public static String convert2WebFormat(String dateString) {
        DateFormat df1 = getNewDateFormat(shortFormat);
        DateFormat df2 = getNewDateFormat(webFormat);

        return convert(dateString, df1, df2);
    }

    public static String convert2NewFormat(String dateString) {
        DateFormat df1 = getNewDateFormat(longFormat);
        DateFormat df2 = getNewDateFormat(newFormat);

        return convert(dateString, df1, df2);
    }

    public static String convert2ChineseDtFormat(String dateString) {
        DateFormat df1 = getNewDateFormat(shortFormat);
        DateFormat df2 = getNewDateFormat(chineseDtFormat);

        return convert(dateString, df1, df2);
    }

    public static String convertFromWebFormat(String dateString) {
        DateFormat df1 = getNewDateFormat(shortFormat);
        DateFormat df2 = getNewDateFormat(webFormat);

        return convert(dateString, df2, df1);
    }

    public static boolean webDateNotLessThan(String date1, String date2) {
        DateFormat df = getNewDateFormat(webFormat);

        return dateNotLessThan(date1, date2, df);
    }

    /**
     * @param date1
     * @param date2
     * @param
     * @return
     */
    public static boolean dateNotLessThan(String date1, String date2,
                                          DateFormat format) {
        try {
            Date d1 = format.parse(date1);
            Date d2 = format.parse(date2);

            return !d1.before(d2);
        } catch (ParseException e) {
            return false;
        }
    }

    public static String getEmailDate(Date today) {
        String todayStr;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日HH:mm:ss");

        todayStr = sdf.format(today);
        return todayStr;
    }

    public static String getSmsDate(Date today) {
        String todayStr;
        SimpleDateFormat sdf = new SimpleDateFormat("MM月dd日HH:mm");

        todayStr = sdf.format(today);
        return todayStr;
    }

    public static String formatTimeRange(Date startDate, Date endDate,
                                         String format) {
        if ((endDate == null) || (startDate == null)) {
            return null;
        }

        String rt = null;
        long range = endDate.getTime() - startDate.getTime();
        long day = range
                / DateUtils.MILLIS_PER_DAY;
        long hour = (range % DateUtils.MILLIS_PER_DAY)
                / DateUtils.MILLIS_PER_HOUR;
        long minute = (range % DateUtils.MILLIS_PER_HOUR)
                / DateUtils.MILLIS_PER_MINUTE;

        if (range < 0) {
            day = 0;
            hour = 0;
            minute = 0;
        }

        rt = format.replaceAll("dd", String.valueOf(day));
        rt = rt.replaceAll("hh", String.valueOf(hour));
        rt = rt.replaceAll("mm", String.valueOf(minute));

        return rt;
    }

    public static String formatMonth(Date date) {
        if (date == null) {
            return null;
        }

        return new SimpleDateFormat(monthFormat).format(date);
    }

    /**
     * 获取系统日期的前一天日期，返回Date
     *
     * @return
     */
    public static Date getBeforeDate() {
        Date date = new Date();

        return new Date(date.getTime() - (ONE_DAY_MILL_SECONDS));
    }

    /**
     * 得到系统当前的时间
     *
     * @return
     */
    public static String currentTime(String format) {
        if (StringUtils.isBlank(format)) {
            return format(new Date(), newFormat);
        } else {
            return format(new Date(), format);
        }
    }

    public static String currentTime() {
        return format(new Date(), FULL_DATE_FORMAT);
    }

    /**
     * Returns true if endDate is after startDate or if startDate equals
     * endDate. Returns false if either value is null. If equalOK, returns true
     * if the dates are equal.
     **/
    public static boolean isValidDateRange(Date startDate, Date endDate,
                                           boolean equalOK) {
        // false if either value is null
        if (startDate == null || endDate == null) {
            return false;
        }

        if (equalOK) {
            // true if they are equal
            if (startDate.equals(endDate)) {
                return true;
            }
        }

        // true if endDate after startDate
        return endDate.after(startDate);
    }

    // 用在日历系统中，表示今天，明天，昨天
    public static boolean isToday(long time) {
        return isSameDay(new Date(time), new Date());
    }

    public static boolean isYesterday(long time) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTimeInMillis(time);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTimeInMillis(System.currentTimeMillis());

        return (cal1.get(Calendar.ERA) == cal2.get(Calendar.ERA)
                && cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) && cal1
                .get(Calendar.DAY_OF_YEAR) + 1 == cal2
                .get(Calendar.DAY_OF_YEAR));
    }

    public static boolean isTomorrow(long time) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTimeInMillis(time);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTimeInMillis(System.currentTimeMillis());

        return (cal1.get(Calendar.ERA) == cal2.get(Calendar.ERA)
                && cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) && cal1
                .get(Calendar.DAY_OF_YEAR) - 1 == cal2
                .get(Calendar.DAY_OF_YEAR));
    }

    /**
     * 检查指定的时间与当前时间的间隔是否大于interval
     *
     * @param time
     * @param interval
     * @return
     */
    public static boolean compareWithNow(long time, long interval) {
        return (System.currentTimeMillis() - time) > interval;
    }

    /**
     * 当前时间与指定时间比，还有几天
     *
     * @param
     * @param
     * @return
     */
    public static long getDiffDaysWithNow(long target) {
        long t1 = target - System.currentTimeMillis();
        if (t1 < 0) {
            return -1;
        }
        return t1 / (24 * 60 * 60 * 1000);
    }

    /**
     * <pre>
     * 指定时间据当前时间已过去多少天
     * 不足的一天的天数不算入结果
     * 如 2.99天--->2天
     * </pre>
     *
     * @param target
     * @return
     */
    public static long getPastDaysWithNow(long target) {
        long t1 = System.currentTimeMillis() - target;
        if (t1 < 0) {
            return -1;
        }
        return t1 / (24 * 60 * 60 * 1000);
    }

    /**
     * <pre>
     * 输入时间和当前时间比较
     * 多于24小时，--> X天
     * 多于1小时， --> X小时
     * 多于1分钟， --> X分钟
     * 多于1秒， --> X秒
     * 小于1秒， --> 0
     * 如果输入时间比当前时间小，--> 0
     * </pre>
     *
     * @param target
     * @return
     */
    public static String getDynamicLeftTime(long target) {
        long t1 = target - System.currentTimeMillis();
        if (t1 < 0) {
            return "0";
        }
        long days = t1 / (24 * 60 * 60 * 1000);
        if (days > 0) {
            return days + "天";
        }
        long hours = t1 / (60 * 60 * 1000);
        if (hours > 0) {
            return hours + "小时";
        }
        long minutes = t1 / (60 * 1000);
        if (minutes > 0) {
            return minutes + "分钟";
        }
        long seconds = t1 / (1000);
        if (seconds > 0) {
            return seconds + "秒";
        }
        return "0";
    }

    public static String getDynamicPassTime(long target) {
        String meaningfulTimeStr = null;
        long curTime = System.currentTimeMillis();
        long timeGap = (curTime - target) / 1000;
        if (timeGap > 60 * 60 * 24 * 2) {
            // 超过昨天前，显示日期
            DateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
            Date targetDate = new Date(target);
            meaningfulTimeStr = formater.format(targetDate);
        } else if (timeGap > 60 * 60 * 24 && timeGap <= 60 * 60 * 24 * 2) {// 小于2天，显示昨天
            meaningfulTimeStr = "昨天";
        } else if (timeGap > 60 * 60 && timeGap <= 60 * 60 * 24) { // 小于一天，显示x小时前
            Integer hourNum = (int) (timeGap / (60 * 60));
            meaningfulTimeStr = hourNum + "小时前";
        } else if (timeGap > 60 * 5 && timeGap <= 60 * 60) { // 小于一小时，显示x分钟前
            Integer minNum = (int) (timeGap / 60);
            meaningfulTimeStr = minNum + "分钟前";
        } else if (timeGap <= 60 * 5) { // 小于5分钟，显示刚刚
            meaningfulTimeStr = "刚刚";
        }

        return meaningfulTimeStr;

    }

    /**
     * 获取前一天日期
     *
     * @param date
     * @return
     */
    public static Date getBeforeDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        date = calendar.getTime();
        return date;
    }

    /**
     * 获取下一天
     *
     * @param date
     * @return
     */
    public static Date getNextDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        date = calendar.getTime();
        return date;
    }

    public static Long getSecondsByDate(Date date) {
        return date.getTime() / 1000;
    }

    public static boolean checkOverlap(Date t1, Date t2, Date t3, Date t4) {
        return t4.getTime() >= t1.getTime() && t3.getTime() <= t2.getTime();
    }

}

