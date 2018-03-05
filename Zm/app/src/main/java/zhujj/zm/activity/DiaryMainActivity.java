package zhujj.zm.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import net.steamcrafted.lineartimepicker.dialog.LinearDatePickerDialog;

import java.util.ArrayList;
import java.util.List;

import zhujj.baselibrary.activity.BaseActivity;
import zhujj.zm.MyApplication;
import zhujj.zm.R;
import zhujj.zm.db.bean.Diary;
import zhujj.zm.db.bean.User;
import zhujj.zm.db.dao.DiaryDao;
import zhujj.zm.util.TimeUtils;
import zhujj.zm.view.adapter.DiaryListAdapter;

/**
 * 作者：朱建晶 on 2018/3/4 21:14
 * 邮箱：344951059@qq.com
 */

public class DiaryMainActivity extends BaseActivity {

    private ListView diary_listview;
    private RelativeLayout add_diary_re;

    private List<Diary> diaries = new ArrayList<>();
    private DiaryListAdapter diaryListAdapter;

//    final int backgroundDark = ResourcesCompat.getColor(getResources(), R.color.darkslateblue, getTheme());
//    final int foregroundDark = ResourcesCompat.getColor(getResources(), R.color.indigo, getTheme());

    @Override
    protected void findViews() {
        setContentView(R.layout.activity_diary_main);

        diary_listview = (ListView) findViewById(R.id.diary_listview);
        add_diary_re = (RelativeLayout) findViewById(R.id.add_diary_re);

    }

    @Override
    protected void addAction() {
        add_diary_re.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog();
            }
        });
    }

    @Override
    protected void initData() {
        diaryListAdapter = new DiaryListAdapter(this);
        diary_listview.setAdapter(diaryListAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        refushDiaryListView();
    }

    private void refushDiaryListView() {
        User user = MyApplication.STORE_BEAN.user;
        user.resetDiarys();
        diaries = user.getDiarys();
        diaryListAdapter.reflushAdapter(diaries);

    }

    private void showDateDialog() {
        LinearDatePickerDialog.Builder.with(DiaryMainActivity.this)
                .setDialogBackgroundColor(R.color.darkslateblue)
                .setPickerBackgroundColor(R.color.darkslateblue)
//                        .setLineColor(Color.argb(64, 255, 255, 255))
                .setTextColor(Color.WHITE)
//                        .setTextBackgroundColor(Color.argb(16, 255, 255, 255))
                .setYear(2018)
                .setMinYear(2000)
                .setMaxYear(2040)
                .setShowTutorial(false)
                .setButtonCallback(new LinearDatePickerDialog.ButtonCallback() {
                    @Override
                    public void onPositive(DialogInterface dialog, int year, int month, int day) {
                        Toast.makeText(DiaryMainActivity.this, TimeUtils.dateYMDToTime("" + year + "-" + month + "-" + day + "-12-12-12"), Toast.LENGTH_SHORT).show();
                        goToWriteDiary(TimeUtils.dateYMDToTime("" + year + "-" + month + "-" + day + "-12-12-12"));
                    }

                    @Override
                    public void onNegative(DialogInterface dialog) {

                    }
                })
                .build()
                .show();
    }

    private void goToWriteDiary(String time) {
        Diary diary = new Diary();
        diary.setUid(MyApplication.STORE_BEAN.user.getId());
        diary.setContent("");
        diary.setTime(time);
        DiaryDao.insertDiary(diary);
        Intent intent = new Intent(this, WriteDiaryActivity.class);
        startActivity(intent);
    }
}
