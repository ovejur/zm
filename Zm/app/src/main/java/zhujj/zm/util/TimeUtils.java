package zhujj.zm.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 作者：朱建晶 on 2018/2/7 14:58
 * 邮箱：344951059@qq.com
 */

public class TimeUtils {

    public static String timedateYMD(String time) {
        String timeString = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        long  l = Long.valueOf(time);
        timeString = sdf.format(new Date(l));//单位秒
        return timeString;
    }
}
