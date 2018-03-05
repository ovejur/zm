package zhujj.zm.db.dao;

import java.util.List;

import zhujj.zm.db.bean.Diary;

import static zhujj.zm.MyApplication.getDaoInstant;

/**
 * 作者：朱建晶 on 2018/3/5 23:25
 * 邮箱：344951059@qq.com
 */

public class DiaryDao {

    public static long insertDiary(Diary diary) {
        return getDaoInstant().getDiaryDao().insertOrReplace(diary);
    }

    public static void deleteDiary(long id) {
        getDaoInstant().getDiaryDao().deleteByKey(id);
    }

    public static void updateDiary(Diary diary) {
        getDaoInstant().getDiaryDao().update(diary);
    }

    public static List<Diary> queryAll() {
        return getDaoInstant().getDiaryDao().loadAll();
    }
}
