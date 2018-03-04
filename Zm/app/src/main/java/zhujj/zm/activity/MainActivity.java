package zhujj.zm.activity;

import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;

import zhujj.baselibrary.activity.BaseActivity;
import zhujj.zm.R;

/**
 * 作者：朱建晶 on 2018/1/24 15:41
 * 邮箱：344951059@qq.com
 */

public class MainActivity extends BaseActivity {

    private RelativeLayout novel_re, book_re, diary_re, sys_re;

    @Override
    protected void findViews() {
        setContentView(R.layout.activity_main);

        novel_re = (RelativeLayout) findViewById(R.id.novel_re);
        book_re = (RelativeLayout) findViewById(R.id.book_re);
        diary_re = (RelativeLayout) findViewById(R.id.diary_re);
        sys_re = (RelativeLayout) findViewById(R.id.sys_re);

    }

    @Override
    protected void addAction() {
        novel_re.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoNovelMainActivity();
            }
        });
        book_re.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoBookMainActivity();
            }
        });
        diary_re.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoDiaryMainActivity();
            }
        });
        sys_re.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showToastText("敬请期待");
            }
        });
    }

    @Override
    protected void initData() {

    }

    private void gotoNovelMainActivity() {
        Intent intent = new Intent(this, NovelMainActivity.class);
        startActivity(intent);
    }

    private void gotoBookMainActivity() {
        Intent intent = new Intent(this, WriteBookMainActivity.class);
        startActivity(intent);
    }

    private void gotoDiaryMainActivity() {
        Intent intent = new Intent(this, DiaryMainActivity.class);
        startActivity(intent);
    }
}
