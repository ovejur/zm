package zhujj.zm.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import zhujj.baselibrary.activity.BaseActivity;
import zhujj.zm.MyApplication;
import zhujj.zm.R;
import zhujj.zm.db.bean.Book;
import zhujj.zm.db.bean.User;
import zhujj.zm.db.dao.UserDao;
import zhujj.zm.view.adapter.BookListAdapter;

/**
 * 作者：朱建晶 on 2018/1/29 16:47
 * 邮箱：344951059@qq.com
 */

public class  WriteBookMainActivity extends BaseActivity {

    private TextView add_book_btn;
    private ListView book_listview;
    private List<Book> books;
    private BookListAdapter bookListAdapter;

    @Override
    protected void findViews() {
        // 今日总字数 总收益  设置用户属性（增加用户表的属性  照片、单字价格等）
        setContentView(R.layout.activity_book_main);
        add_book_btn = (TextView) findViewById(R.id.add_book_btn);
        book_listview = (ListView) findViewById(R.id.book_listview);
    }

    @Override
    protected void addAction() {
        add_book_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToAddBook();
            }
        });
    }

    @Override
    protected void initData() {

        bookListAdapter = new BookListAdapter(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        checkUserAddreflushBookList();
    }

    private void checkUserAddreflushBookList() {
        if (MyApplication.STORE_BEAN.user != null) {
            List<User> users = UserDao.queryUser(MyApplication.STORE_BEAN.user);
            if (users.size() > 0) {
                User user = users.get(0);
                if (user.getCount() != null) {
                    showToastText("今日总码字:"+user.getCount());
                } else {
                    showToastText("今日总码字:0");
                }
                books = user.getBooks();
                bookListAdapter.reflushAdapter(books);
            } else {
//                showToastText("今日总码字:1");
            }
        } else {
//            showToastText("今日总码字:2");
        }
    }

    private void goToAddBook() {
        Intent intent = new Intent(this, AddBookActivity.class);
        startActivity(intent);
    }

}
