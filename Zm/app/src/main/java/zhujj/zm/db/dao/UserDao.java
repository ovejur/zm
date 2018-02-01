package zhujj.zm.db.dao;

import java.util.List;

import zhujj.zm.MyApplication;
import zhujj.zm.db.bean.User;

import static zhujj.zm.MyApplication.getDaoInstant;

/**
 * 作者：朱建晶 on 2018/1/25 11:40
 * 邮箱：344951059@qq.com
 */

public class UserDao {

    public static long insertUser(User user) {
        return MyApplication.getDaoInstant().getUserDao().insertOrReplace(user);
    }
    public static List<User> queryAll() {
        return getDaoInstant().getUserDao().loadAll();
    }

    public static List<User> queryUser(String username) {
        return MyApplication.getDaoInstant().getUserDao().queryBuilder().where(zhujj.zm.db.bean.UserDao.Properties.Name.eq(username)).list();
    }

}
