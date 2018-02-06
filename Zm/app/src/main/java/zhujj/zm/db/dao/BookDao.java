package zhujj.zm.db.dao;

import java.util.List;

import zhujj.zm.MyApplication;
import zhujj.zm.db.bean.Book;

import static zhujj.zm.MyApplication.getDaoInstant;

/**
 * 作者：朱建晶 on 2018/1/23 10:07
 * 邮箱：344951059@qq.com
 */

public class BookDao {

    public static long insertBook(Book book) {
        return MyApplication.getDaoInstant().getBookDao().insertOrReplace(book);
    }

    public static void deleteBook(long id) {
        getDaoInstant().getBookDao().deleteByKey(id);
    }

    public static void updateBook(Book book) {
        getDaoInstant().getBookDao().update(book);
    }

    public static List<Book> queryAll() {
        return getDaoInstant().getBookDao().loadAll();
    }

    public static List<Book> queryUser(long uid) {
        return MyApplication.getDaoInstant().getBookDao().queryBuilder().where(zhujj.zm.db.bean.BookDao.Properties.Uid.eq(uid)).list();
    }
}
