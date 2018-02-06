package zhujj.zm.activity;

import android.widget.TextView;

import zhujj.baselibrary.activity.BaseActivity;
import zhujj.zm.R;
import zhujj.zm.db.bean.Book;

/**
 * 作者：朱建晶 on 2018/2/6 16:54
 * 邮箱：344951059@qq.com
 */

public class BookChapteractivity extends BaseActivity {

    private Book book;
    private TextView book_name;

    @Override
    protected void findViews() {
        setContentView(R.layout.activty_book_chapter);
        book_name = (TextView) findViewById(R.id.book_name);
    }

    @Override
    protected void addAction() {

    }

    @Override
    protected void initData() {
        book = (Book) getIntent().getSerializableExtra("book");
        book_name.setText(book.getName());
    }
}
