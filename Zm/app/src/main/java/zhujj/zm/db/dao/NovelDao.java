package zhujj.zm.db.dao;

import java.util.List;

import zhujj.zm.db.bean.Novel;

import static zhujj.zm.MyApplication.getDaoInstant;

/**
 * 作者：朱建晶 on 2018/3/4 16:01
 * 邮箱：344951059@qq.com
 */

public class NovelDao {

    public static long insertNovel(Novel novel) {
        return getDaoInstant().getNovelDao().insertOrReplace(novel);
    }

    public static void deleteNovel(long id) {
        getDaoInstant().getNovelDao().deleteByKey(id);
    }

    public static void updateNovel(Novel novel) {
        getDaoInstant().getNovelDao().update(novel);
    }

    public static List<Novel> queryAll() {
        return getDaoInstant().getNovelDao().loadAll();
    }

}
