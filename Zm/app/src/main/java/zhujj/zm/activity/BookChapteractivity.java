package zhujj.zm.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import zhujj.baselibrary.activity.BaseActivity;
import zhujj.zm.MyApplication;
import zhujj.zm.R;
import zhujj.zm.db.bean.Book;
import zhujj.zm.db.bean.Chapter;
import zhujj.zm.db.dao.ChapterDao;
import zhujj.zm.util.TimeUtils;
import zhujj.zm.view.adapter.ChapterListAdapter;

/**
 * 作者：朱建晶 on 2018/2/6 16:54
 * 邮箱：344951059@qq.com
 */

public class BookChapteractivity extends BaseActivity {

    private Book book;
    private TextView book_name, book_intro, book_creat_time, book_status, book_count, book_chapter_count, book_chapter_speed;
    private ListView chapter_listview;
    private RelativeLayout add_chapter_re;
    private ChapterListAdapter chapterListAdapter;
    private List<Chapter> chapters = new ArrayList<Chapter>();

    @Override
    protected void findViews() {
        setContentView(R.layout.activty_book_chapter);
        book_name = (TextView) findViewById(R.id.book_name);
        book_intro = (TextView) findViewById(R.id.book_intro);
        book_creat_time = (TextView) findViewById(R.id.book_creat_time);
        book_status = (TextView) findViewById(R.id.book_status);
        book_count = (TextView) findViewById(R.id.book_count);
        book_chapter_count = (TextView) findViewById(R.id.book_chapter_count);
        book_chapter_speed = (TextView) findViewById(R.id.book_chapter_speed);
        add_chapter_re = (RelativeLayout) findViewById(R.id.add_chapter_re);
        chapter_listview = (ListView) findViewById(R.id.chapter_listview);
    }

    @Override
    protected void addAction() {
        add_chapter_re.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddChapterPop();
            }
        });

        chapter_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                MyApplication.STORE_BEAN.chapter = chapters.get(i);
                gotoChapterWriteActivity();
            }
        });
    }

    @Override
    protected void initData() {
        book = MyApplication.STORE_BEAN.book;
        chapterListAdapter = new ChapterListAdapter(this);
        chapter_listview.setAdapter(chapterListAdapter);


    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onResume() {
        super.onResume();
        reflushView();
    }

    private void showAddChapterPop() {
        View contentView= LayoutInflater.from(this).inflate(R.layout.pop_add_chapter, null, false);
        final PopupWindow window=new PopupWindow(contentView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        final EditText chapter_name = (EditText) contentView.findViewById(R.id.chapter_name);
        TextView chapter_add = (TextView) contentView.findViewById(R.id.chapter_add);
        if (book.getChapterobjs() != null && book.getChapterobjs().size() == 0) {
            chapter_name.setText("第1章  ");
        } else {
            chapter_name.setText("第"+(book.getChapterobjs().size()+1)+"章  ");
        }
        chapter_add.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(chapter_name.getText())) {
                    String name = chapter_name.getText().toString();
                    addChapter(name);
                    window.dismiss();
                    reflushView();
                } else {
                    showToastText("请输入章节名");
                }
            }
        });
        chapter_name.setFocusableInTouchMode(true);
        chapter_name.requestFocus();
        chapter_name.setFocusable(true);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        window.setOutsideTouchable(true);
        window.setTouchable(true);
        window.showAtLocation(chapter_listview, Gravity.BOTTOM, 0, 0);
        InputMethodManager imm = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }
    private void addChapter(String name) {
        Chapter chapter = new Chapter();
        chapter.setName(name);
        long count = 0;
        chapter.setCount(count);
        chapter.setCountTime(count);
        chapter.setContent("    ");
        chapter.setUid(MyApplication.STORE_BEAN.user.getId());
        chapter.setBid(book.getId());
        chapter.setCreatTime(""+System.currentTimeMillis());
        chapter.setUpdateTime(""+System.currentTimeMillis());

        ChapterDao.insertBook(chapter);
        book.setChapters(book.getChapters() + 1);
        book.setUpdateTime(""+System.currentTimeMillis());
        book.update();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void reflushView() {
        book.refresh();
        book_name.setText(book.getName());
        book_intro.setText(book.getIntroduce());
        book_creat_time.setText("创建时间:"+ TimeUtils.timedateYMD(book.getCreatTime()));

        book_status.setText(getTimeDifference(book.getUpdateTime(), book_status));
        book_count.setText("总字数:"+book.getCount());
        book_chapter_count.setText("|    共"+book.getChapters() + "章");
        if (book.getInputTime() != 0
                && book.getCount() != 0) {
            if (book.getInputTime() > 60) {
                book_chapter_speed.setText("码字速度："+(book.getCount()/(book.getInputTime()/60))+"/分钟");
            } else {
                book_chapter_speed.setText("码字速度："+book.getCount()+"/分钟");
            }
        } else {
            book_chapter_speed.setText("码字速度：0/分钟");
        }
        book.resetChapterobjs();
        chapters = book.getChapterobjs();
        if (chapters != null && chapters.size() == 0) {
            showToastText("新建一个章节开始码字吧");
        } else {
            chapterListAdapter.reflushAdapter(chapters);
        }
        chapter_listview.setSelection(chapters.size());
        MyApplication.STORE_BEAN.book = book;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private String getTimeDifference(String starTime, TextView textView) {
        String timeString = "";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        try {
            Date parse = dateFormat.parse(zhujj.zm.util.TimeUtils.timedateYMD(starTime));
            Date parse1 = dateFormat.parse(zhujj.zm.util.TimeUtils.timedateYMD(""+System.currentTimeMillis()));

            long diff = parse1.getTime() - parse.getTime();

            long day = diff / (24 * 60 * 60 * 1000);
            long hour = (diff / (60 * 60 * 1000) - day * 24);
            long min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
            long s = (diff / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
            long ms = (diff - day * 24 * 60 * 60 * 1000 - hour * 60 * 60 * 1000
                    - min * 60 * 1000 - s * 1000);
            long hour1 = diff / (60 * 60 * 1000);
            String hourString = hour1 + "";
            long min1 = ((diff / (60 * 1000)) - hour1 * 60);
            if (min1 < 60 && hour1 == 0) {
                timeString = min1 + "分钟前更新";
            } else if (hour1 < 12) {
                timeString = hour1 + "小时前更新";
                textView.setTextColor(getColor(R.color.darkturquoise));
            } else if (hour1 >= 12 && day < 1) {
                timeString = "当天更新";
                textView.setTextColor(getColor(R.color.deepskyblue));
            } else if (day < 7) {
                timeString = day + "天前更新";
                textView.setTextColor(getColor(R.color.deepskyblue));
            } else if (day == 7) {
                timeString = "一周前更新";
                textView.setTextColor(getColor(R.color.dodgerblue));
            } else if (day > 7 && day < 30) {
                timeString = day + "天前更新";
                textView.setTextColor(getColor(R.color.dodgerblue));
            } else {
                timeString = "很久以前更新";
                textView.setTextColor(getColor(R.color.red));
            }

        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return timeString;
    }

    private void gotoChapterWriteActivity() {
        Intent intent = new Intent(this, WriteChapterActivity.class);
        startActivity(intent);
    }
}
