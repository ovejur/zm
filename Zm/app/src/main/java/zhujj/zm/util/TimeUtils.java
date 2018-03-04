package zhujj.zm.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * 作者：朱建晶 on 2018/2/7 14:58
 * 邮箱：344951059@qq.com
 */

public class TimeUtils {

    public static String timedateYMD(String time) {
        String timeString = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            long  l = Long.valueOf(time);
            timeString = sdf.format(new Date(l));//单位秒
        } catch (Exception e) {
            e.printStackTrace();
        }

        return timeString;
    }

    public static String dateYMDToTime(String dateString) {
        SimpleDateFormat sdr = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss",
                Locale.CHINA);
        Date date;
        String times = null;
        try {
            date = sdr.parse(dateString);
            long l = date.getTime();
            String stf = String.valueOf(l);
            times = stf;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return times;
    }
}
