package zhujj.zm.activity;

import java.util.List;

import zhujj.baselibrary.activity.BaseActivity;
import zhujj.zm.db.bean.Book;
import zhujj.zm.db.dao.BookDao;

/**
 * 作者：朱建晶 on 2018/1/29 16:47
 * 邮箱：344951059@qq.com
 */

public class WriteBookMainActivity extends BaseActivity {

    @Override
    protected void findViews() {

    }

    @Override
    protected void addAction() {

    }

    @Override
    protected void initData() {

        List<Book> books = BookDao.queryAll();

    }
}
