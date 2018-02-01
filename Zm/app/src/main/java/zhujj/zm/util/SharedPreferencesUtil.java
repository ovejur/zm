package zhujj.zm.util;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

/**
 * 作者：朱建晶 on 2018/1/24 16:27
 * 邮箱：344951059@qq.com
 */

public class SharedPreferencesUtil {

    public static void saveUser(String userName, Context context) {
        SharedPreferences share = context.getSharedPreferences("zm", MODE_PRIVATE);
        SharedPreferences.Editor edit = share.edit();
        edit.putString("username", userName);
        edit.commit();
    }

    public static String getUser(Context context) {
        SharedPreferences share = context.getSharedPreferences("zm", MODE_PRIVATE);
        return share.getString("username", null);
    }
}
