package zhujj.zm;

import android.database.sqlite.SQLiteDatabase;

import java.io.Serializable;

import zhujj.baselibrary.BaseApplication;
import zhujj.zm.db.bean.DaoMaster;
import zhujj.zm.db.bean.DaoSession;

/**
 * 作者：朱建晶 on 2018/1/22 10:31
 * 邮箱：344951059@qq.com
 */

public class MyApplication extends BaseApplication {

    private static DaoSession daoSession;

    public static Store STORE_BEAN = new Store();

    @Override
    public void onCreate() {
        super.onCreate();
        setupDatabase();
    }

    /**
     * 配置数据库
     */
    private void setupDatabase() {
        //创建数据库zm.db"
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "zm.db", null);
        //获取可写数据库
        SQLiteDatabase db = helper.getWritableDatabase();
        //获取数据库对象
        DaoMaster daoMaster = new DaoMaster(db);
        //获取Dao对象管理者
        daoSession = daoMaster.newSession();
    }

    public static class Store implements Serializable {
        public String user;
    }

    public static DaoSession getDaoInstant() {
        return daoSession;
    }

}
