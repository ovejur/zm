package zhujj.zm.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;

import zhujj.baselibrary.activity.BaseActivity;
import zhujj.zm.MyApplication;
import zhujj.zm.R;
import zhujj.zm.db.bean.Book;
import zhujj.zm.db.dao.BookDao;
import zhujj.zm.view.ConfirmDialog;

/**
 * 作者：朱建晶 on 2018/2/5 16:31
 * 邮箱：344951059@qq.com
 */

public class AddBookActivity extends BaseActivity {

    protected static final float FLIP_DISTANCE = 50;
    GestureDetector mDetector;
    private int inputType = 1;
    private RelativeLayout add_book_name_re, add_book_intro_re, add_book_img_re;
    private EditText add_book_name, add_book_intro;

    @Override
    protected void findViews() {

        setContentView(R.layout.activity_add_book);

        add_book_name_re = (RelativeLayout) findViewById(R.id.add_book_name_re);
        add_book_intro_re = (RelativeLayout) findViewById(R.id.add_book_intro_re);
        add_book_img_re = (RelativeLayout) findViewById(R.id.add_book_img_re);
        add_book_name = (EditText) findViewById(R.id.add_book_name);
        add_book_intro = (EditText) findViewById(R.id.add_book_intro);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return mDetector.onTouchEvent(event);
    }

    @Override
    protected void addAction() {
        mDetector = new GestureDetector(this, new GestureDetector.OnGestureListener() {

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                // TODO Auto-generated method stub
                return false;
            }

            @Override
            public void onShowPress(MotionEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                // TODO Auto-generated method stub
                return false;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                // TODO Auto-generated method stub

            }

            /**
             *
             * e1 The first down motion event that started the fling. e2 The
             * move motion event that triggered the current onFling.
             */
            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                if (e1.getX() - e2.getX() > FLIP_DISTANCE) {
                    Log.i("MYTAG", "向左滑...");
                    rightDo();

                    return true;
                }
                if (e2.getX() - e1.getX() > FLIP_DISTANCE) {
                    Log.i("MYTAG", "向右滑...");
                    leftDo();
                    return true;
                }
                if (e1.getY() - e2.getY() > FLIP_DISTANCE) {
                    Log.i("MYTAG", "向上滑...");
                    return true;
                }
                if (e2.getY() - e1.getY() > FLIP_DISTANCE) {
                    Log.i("MYTAG", "向下滑...");
                    return true;
                }

                Log.d("TAG", e2.getX() + " " + e2.getY());

                return false;
            }

            @Override
            public boolean onDown(MotionEvent e) {
                // TODO Auto-generated method stub
                return false;
            }
        });
    }

    @Override
    protected void initData() {

    }

    private void leftDo() {
        if (inputType == 0) {

        } else if (inputType == 4) {
            inputType = 3;
        } else {
            inputType = inputType - 1;
        }
        reflushView();
    }

    private void rightDo() {
        if (inputType >= 4) {
            return;
        } else if (inputType == 0) {
            inputType = 1;
        }
        inputType = inputType + 1;
        reflushView();
    }

    private void reflushView() {
        Log.i("MYTAG", "inputType==="+inputType);
        switch (inputType) {
            case 0:
                View.OnClickListener onClickListener = new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AddBookActivity.this.finish();
                    }
                };
                showChooseDialog("小说还未创建成功，确认退出？", onClickListener);
                break;
            case 1:
                add_book_name_re.setVisibility(View.VISIBLE);
                add_book_intro_re.setVisibility(View.GONE);
                add_book_img_re.setVisibility(View.GONE);
                break;
            case  2:
                add_book_name_re.setVisibility(View.GONE);
                add_book_intro_re.setVisibility(View.VISIBLE);
                add_book_img_re.setVisibility(View.GONE);
                break;
            case  3:
                add_book_name_re.setVisibility(View.GONE);
                add_book_intro_re.setVisibility(View.GONE);
                add_book_img_re.setVisibility(View.VISIBLE);
                break;
            case  4:
                doCreatBook();
                break;
            default:

                break;
        }
    }

    private void doCreatBook() {
        if (TextUtils.isEmpty(add_book_name.getText())) {
            showToastText("请输入书名");
            return;
        }
        if (TextUtils.isEmpty(add_book_intro.getText())) {
            showToastText("请输入简介");
            return;
        }
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doInsertBook();
            }
        };
        showChooseDialog("小说信息填写成功，确认是否创建", onClickListener);
    }

    private void doInsertBook() {
        Book book = new Book();
        long count = 0;
        book.setCount(count);
        book.setChapters(count);
        book.setCreatTime(""+System.currentTimeMillis());
        book.setUpdateTime(""+System.currentTimeMillis());
        book.setIntroduce(add_book_intro.getText().toString());
        book.setUid(MyApplication.STORE_BEAN.user.getId());
        book.setStatus(0);
        book.setName(add_book_name.getText().toString());
        book.setInputTime(count);
        BookDao.insertBook(book);
        gotoBookChapteractivity(book);
//        book.setImg("");
    }

    private void gotoBookChapteractivity(Book book) {
        Intent intent = new Intent(this, BookChapteractivity.class);
        MyApplication.STORE_BEAN.book = book;
//        intent.putExtra("book", book);
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
