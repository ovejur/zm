package zhujj.zm.activity;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import zhujj.baselibrary.activity.BaseActivity;
import zhujj.zm.MyApplication;
import zhujj.zm.R;
import zhujj.zm.db.bean.Book;
import zhujj.zm.db.bean.User;
import zhujj.zm.db.dao.BookDao;
import zhujj.zm.view.ConfirmDialog;
import zhujj.zm.view.adapter.BookListAdapter;

/**
 * 作者：朱建晶 on 2018/1/29 16:47
 * 邮箱：344951059@qq.com
 */

public class WriteBookMainActivity extends BaseActivity {

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
        book_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                goToChapter(books.get(i));
            }
        });
    }

    @Override
    protected void initData() {

        bookListAdapter = new BookListAdapter(this);
        book_listview.setAdapter(bookListAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkUserAddreflushBookList();
    }

    private void checkUserAddreflushBookList() {
        if (MyApplication.STORE_BEAN.user != null) {
            User user = MyApplication.STORE_BEAN.user;
            if (user.getCount() != null) {
                showToastText("今日总码字:" + user.getCount());
            } else {
                showToastText("今日总码字:0");
            }
            books = BookDao.queryUser(user.getId());
            Collections.sort(books, new Comparator<Book>() {
                @Override
                public int compare(Book book, Book t1) {
                    return -1*book.getUpdateTime().compareTo(t1.getUpdateTime());
                }
            });
            bookListAdapter.reflushAdapter(books);
        } else {
            View.OnClickListener onClickListener = new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    gotoLoginActivity();
                }
            };
            showChooseDialog("登录信息失效，请重新登录", onClickListener);
        }
    }

    private void goToAddBook() {
        Intent intent = new Intent(this, AddBookActivity.class);
        startActivity(intent);
    }

    private void goToChapter(Book book) {
        Intent intent = new Intent(this, BookChapteractivity.class);
        intent.putExtra("book", book);
        startActivity(intent);
    }

    private void gotoLoginActivity() {
        Intent intent = new Intent(this, WriteBookMainActivity.class);
        startActivity(intent);
        finish();
    }

    private void showChooseDialog(String errorMsg, View.OnClickListener onClickListener) {
        ConfirmDialog confirmDialog = new ConfirmDialog(this, "提示", errorMsg,
                "确认", onClickListener, "取消", new View.OnClickListener() {

            @Override
            public void onClick(View v) {

            }
        });
        confirmDialog.show();
    }

}
