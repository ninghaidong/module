package com.xbtx.mallmodel;


import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * @Description: 时间工具类
 * @Author : Mawei
 */
public class TimeUtils {

    private static SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static SimpleDateFormat FORMAT_CH = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
    private static SimpleDateFormat FORMAT_CH_MONTH_DAY = new SimpleDateFormat("MM月dd日");
    private static SimpleDateFormat FORMAT_Y_M_D = new SimpleDateFormat("yyyy-MM-dd");
    public static SimpleDateFormat YRSF = new SimpleDateFormat("MM-dd HH:mm");
    private static SimpleDateFormat sfm = new SimpleDateFormat("HH:mm:ss");
    private static SimpleDateFormat sf = new SimpleDateFormat("HH:mm");
    private static SimpleDateFormat MDHM = new SimpleDateFormat("MM-dd HH:mm");

    /**
     * 时分秒转成字符串 (时分秒)
     * 00:00:00
     * time  单位秒 s
     *
     * @param isShowZeroHour 当小时为0时是否显示
     */
    public static String getHms(long time, boolean isShowZeroHour) {
        if (!isShowZeroHour && time <= 3600) {
            return getMs(time, true);
        }
        String hms = "";
        long hour = 0l, minutes = 0l, seconds = 0l;
        hour = time / 3600;
        minutes = (time / 60) % 60;
        seconds = time % 60;
        hms = String.format("%02d:%02d:%02d", hour, minutes, seconds);
        return hms;
    }

    public static String getDhms(long time) {
        String dhms = "";
        long day = 0l, hour = 0l, minutes = 0, seconds = 0l;
        day = time / 3600 / 24;
        hour = (time / 3600) % 24;
        minutes = (time / 60) % 60;
        seconds = time % 60;
        if (day > 0) {
            dhms = String.format("%d天%02d:%02d:%02d", day, hour, minutes, seconds);
        } else {
            String.format("%02d:%02d:%02d", hour, minutes, seconds);
        }
        return dhms;
    }

    /**
     * 时间转化成字符串 （分秒）
     * 00:00
     *
     * @param isShowZeroMinute 当分钟为0时是否显示
     */
    public static String getMs(long time, boolean isShowZeroMinute) {
        String ms;
        long minutes, seconds;
        minutes = (time / 60) % 60;
        seconds = time % 60;
        ms = String.format("%02d:%02d", minutes, seconds);
        if (!isShowZeroMinute && time <= 60) {
            return String.format("%02d", seconds);
        }
        return ms;
    }

    /**
     * 时间字符串转某月某日
     *
     * @param time yyyy-MM-dd HH:mm:ss
     */
    public static String getMonthDay(String time) {
        return getMonthDay(getTime(time));
    }

    /**
     * 得到某月某日 MM-dd
     *
     * @param time 时间戳
     */
    public static String getMonthDay(long time) {
        if (time <= 0) {
            return "";
        }
        Date date = new Date(time);
        String res = FORMAT_CH_MONTH_DAY.format(date);
        return res;

    }

    /**
     * 得到某月某日 MM-dd
     *
     * @param time 时间戳
     */
    public static String getMonthDay1(long time) {
        if (time <= 0) {
            return "";
        }
        Date date = new Date(time);
        String res = sfm.format(date);
        return res;

    }
    /**
     * 得到某月某日 MM-dd
     *
     * @param time 时间戳
     */
    public static String getYMD(long time) {
        if (time <= 0) {
            return "";
        }
        Date date = new Date(time);
        String res = FORMAT.format(date);
        return res;

    }
    /**
     *
     */
    public static String getMDHM(long time) {
        if (time <= 0) {
            return "";
        }
        Date date = new Date(time);
        String res = MDHM.format(date);
        return res;
    }

    /**
     * 得到某月某日 MM-dd HH:SS
     *
     * @param time 时间戳
     */
    public static String getYrsf(long time) {
        if (time <= 0) {
            return "";
        }
        Date date = new Date(time);
        String res = YRSF.format(date);
        return res;
    }

    public static String getSf(long time) {
        if (time <= 0) {
            return "";
        }
        Date date = new Date(time);
        String res = sf.format(date);
        return res;
    }

    /**
     * 时间字符串转为时间戳
     *
     * @param time yyyy-MM-dd HH:mm:ss
     */
    public static long getTime(String time) {
        long res = 0L;
        try {
            Date date = FORMAT.parse(time);
            res = date.getTime();
            return res;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return res;
    }

    /**
     * 时间字符串转为时间戳
     * @param time HH:mm:ss 转时间戳
     */
    public static long getSfmLong(String time) {
        long res = 0L;
        try {
            Date date = sfm.parse(time.toString());
            res = date.getTime();
            return res;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return res;
    }

    /**
     * 得到时或分
     *
     * @second 秒
     */
    public static String getHm(int second) {
        int hour;
        if (second >= 3600) {
            hour = second / 3600;
            return hour + "时";
        } else {
            return (second / 60 == 0 ? 1 : second / 60) + "分";
        }
    }

    /**
     * 得到时或分
     *
     * @second 秒
     */
    public static String getHm(long time) {
        long hour;
        long second = time / 1000;
        if (second >= 3600) {
            hour = second / 3600;
            return hour + "小时";
        } else {
            return (second / 60 == 0 ? 1 : second / 60) + "分钟";
        }
    }

    /**
     * 获取时分倒计时
     *
     * @param time 秒
     * @return 00:23
     */
    public static String secToTime(int time) {
        String timeStr = null;
        int hour = 0;
        int minute = 0;
        if (time <= 0)
            return "00:00";
        else {
            minute = time / 60;
            if (minute < 60) {
                timeStr = "00:" + unitFormat(minute);
            } else {
                hour = minute / 60;
                if (hour > 99)
                    return "99:59:59";
                minute = minute % 60;
                timeStr = unitFormat(hour) + ":" + unitFormat(minute);
            }
        }
        return timeStr;
    }

    public static String unitFormat(int i) {
        String retStr = null;
        if (i >= 0 && i < 10)
            retStr = "0" + Integer.toString(i);
        else
            retStr = "" + i;
        return retStr;
    }


    /**
     * 返回指定pattern样的日期时间字符串。
     *
     * @param dt
     * @param pattern
     * @return 如果时间转换成功则返回结果，否则返回空字符串""
     * @author 即时通讯网([url = http : / / www.52im.net]http : / / www.52im.net[ / url])
     * https://www.cnblogs.com/imstudy/p/10423239.html
     */

    public static String getTimeString(Date dt, String pattern) {

        try {
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);//"yyyy-MM-dd HH:mm:ss"
            sdf.setTimeZone(TimeZone.getDefault());
            return sdf.format(dt);
        } catch (Exception e) {
            return "";
        }
    }


    /**
     * 仿照微信中的消息时间显示逻辑，将时间戳（单位：毫秒）转换为友好的显示格式.
     * <p>
     * 1）7天之内的日期显示逻辑是：今天、昨天(-1d)、前天(-2d)、星期？（只显示总计7天之内的星期数，即<=-4d）；<br>
     * 2）7天之外（即>7天）的逻辑：直接显示完整日期时间。
     *
     * @param srcDate         要处理的源日期时间对象
     * @param mustIncludeTime true表示输出的格式里一定会包含“时间:分钟”，否则不包含（参考微信，不包含时分的情况，用于首页“消息”中显示时）
     * @return 输出格式形如：“10:30”、“昨天 12:04”、“前天 20:51”、“星期二”、“2019/2/21 12:09”等形式
     * @author 即时通讯网([url = http : / / www.52im.net]http : / / www.52im.net[ / url])
     * @since 4.5
     */

    public static String getTimeStringAutoShort2(Date srcDate, boolean mustIncludeTime) {
        String ret = "";

        try {
            GregorianCalendar gcCurrent = new GregorianCalendar();
            gcCurrent.setTime(new Date());
            int currentYear = gcCurrent.get(GregorianCalendar.YEAR);
            int currentMonth = gcCurrent.get(GregorianCalendar.MONTH) + 1;
            int currentDay = gcCurrent.get(GregorianCalendar.DAY_OF_MONTH);

            GregorianCalendar gcSrc = new GregorianCalendar();
            gcSrc.setTime(srcDate);
            int srcYear = gcSrc.get(GregorianCalendar.YEAR);
            int srcMonth = gcSrc.get(GregorianCalendar.MONTH) + 1;
            int srcDay = gcSrc.get(GregorianCalendar.DAY_OF_MONTH);

            // 要额外显示的时间分钟
            String timeExtraStr = (mustIncludeTime ? " " + getTimeString(srcDate, "HH:mm") : "");

            // 当年
            if (currentYear == srcYear) {
                long currentTimestamp = gcCurrent.getTimeInMillis();
                long srcTimestamp = gcSrc.getTimeInMillis();

                // 相差时间（单位：毫秒）
                long delta = (currentTimestamp - srcTimestamp);

                // 当天（月份和日期一致才是）
                if (currentMonth == srcMonth && currentDay == srcDay) {
                    // 时间相差60秒以内
                    if (delta < 60 * 1000)
                        ret = "刚刚";
                        // 否则当天其它时间段的，直接显示“时:分”的形式
                    else
                        ret = getTimeString(srcDate, "HH:mm");
                } else {
                    // 当年 && 当天之外的时间（即昨天及以前的时间）
                    // 昨天（以“现在”的时候为基准-1天）
                    GregorianCalendar yesterdayDate = new GregorianCalendar();
                    yesterdayDate.add(GregorianCalendar.DAY_OF_MONTH, -1);

                    // 前天（以“现在”的时候为基准-2天）
                    GregorianCalendar beforeYesterdayDate = new GregorianCalendar();
                    beforeYesterdayDate.add(GregorianCalendar.DAY_OF_MONTH, -2);

                    // 用目标日期的“月”和“天”跟上方计算出来的“昨天”进行比较，是最为准确的（如果用时间戳差值

                    // 的形式，是不准确的，比如：现在时刻是2019年02月22日1:00、而srcDate是2019年02月21日23:00，

                    // 这两者间只相差2小时，直接用“delta/(3600 * 1000)” > 24小时来判断是否昨天，就完全是扯蛋的逻辑了）

                    if (srcMonth == (yesterdayDate.get(GregorianCalendar.MONTH) + 1)
                            && srcDay == yesterdayDate.get(GregorianCalendar.DAY_OF_MONTH)) {
                        ret = "昨天" + timeExtraStr;// -1d

                    } else if (srcMonth == (beforeYesterdayDate.get(GregorianCalendar.MONTH) + 1)
                            && srcDay == beforeYesterdayDate.get(GregorianCalendar.DAY_OF_MONTH)) {
                        // “前天”判断逻辑同上
                        ret = "前天" + timeExtraStr;// -2d

                    } else {
                        // 跟当前时间相差的小时数
                        long deltaHour = (delta / (3600 * 1000));

                        // 如果小于 7*24小时就显示星期几
                        if (deltaHour < 7 * 24) {

                            String[] weekday = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};

                            // 取出当前是星期几
                            String weedayDesc = weekday[gcSrc.get(GregorianCalendar.DAY_OF_WEEK) - 1];
                            ret = weedayDesc + timeExtraStr;
                        } else {
                            // 否则直接显示完整日期时间
                            ret = getTimeString(srcDate, "yyyy/M/d") + timeExtraStr;
                        }
                    }

                }

            } else {
                ret = getTimeString(srcDate, "yyyy/M/d") + timeExtraStr;
            }
        } catch (Exception e) {
            System.err.println("【DEBUG-getTimeStringAutoShort】计算出错：" + e.getMessage() + " 【NO】");
        }

        return ret;

    }

    /**
     * 根据Date 提取月
     */
    public static int getMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int month = cal.get(Calendar.MONTH);
        return month + 1;
    }

    /**
     * 将短时间格式字符串转换为时间 yyyy-MM-dd
     * 2019-07-19 17:28:24  转 date
     *
     * @param strDate
     * @return
     */
    public static Date strToDate(String strDate) {
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = FORMAT.parse(strDate, pos);
        return strtodate;
    }

    /**
     * 时间戳转换    2019-07-19 17:28:24
     */
    public static String getDateToString(long milSecond) {
        Date date = new Date(milSecond);
        return FORMAT.format(date);
    }

    /**
     * 时间戳转换    2019-07-19 17:28:24
     */
    public static String getYMDString(long milSecond) {
        Date date = new Date(milSecond);
        return FORMAT_Y_M_D.format(date);
    }


    /**
     * 时间戳转换    2019-07-19 17:28:24
     */
    public static String getSFM(long milSecond) {
        Date date = new Date(milSecond);
        return sfm.format(date);
    }


    public static long getHMS(long timetemp) {
        Calendar calendar=Calendar.getInstance();
        calendar.setTimeInMillis(timetemp);
        SimpleDateFormat fmat=new SimpleDateFormat("HH:mm:ss");
        String str=fmat.format(calendar.getTime());

        long time = 0;

        String[] split = str.split(":");
        long s = Integer.valueOf(split[0]) * 60 * 60 * 1000;
        long f = Integer.valueOf(split[1]) * 60 * 1000;
        long m = Integer.valueOf(split[2]) * 1000;

        return (s + f + m);
    }
}
