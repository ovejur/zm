package zhujj.zm.db.dao;

import java.util.List;

import zhujj.zm.MyApplication;
import zhujj.zm.db.bean.Chapter;

import static zhujj.zm.MyApplication.getDaoInstant;

/**
 * 作者：朱建晶 on 2018/2/8 15:51
 * 邮箱：344951059@qq.com
 */

public class ChapterDao {
    public static long insertBook(Chapter chapter) {
        return getDaoInstant().getChapterDao().insertOrReplace(chapter);
    }
    public static void updateChapter(Chapter chapter) {
        getDaoInstant().getChapterDao().update(chapter);
    }
    public static List<Chapter> queryAll() {
        return getDaoInstant().getChapterDao().loadAll();
    }
    public static List<Chapter> queryChaptersById(long bid) {
        return MyApplication.getDaoInstant().getChapterDao().queryBuilder().where(zhujj.zm.db.bean.ChapterDao.Properties.Bid.eq(bid)).list();
    }
}
