package zhujj.zm.activity;

import android.content.DialogInterface;
import android.graphics.Color;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import net.steamcrafted.lineartimepicker.dialog.LinearDatePickerDialog;

import zhujj.baselibrary.activity.BaseActivity;
import zhujj.zm.R;
import zhujj.zm.util.TimeUtils;

/**
 * 作者：朱建晶 on 2018/3/4 21:14
 * 邮箱：344951059@qq.com
 */

public class DiaryMainActivity extends BaseActivity {

    private ListView diary_listview;
    private RelativeLayout add_diary_re;

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
                .setShowTutorial(true)
                .setButtonCallback(new LinearDatePickerDialog.ButtonCallback() {
                    @Override
                    public void onPositive(DialogInterface dialog, int year, int month, int day) {
                        Toast.makeText(DiaryMainActivity.this, TimeUtils.timedateYMD(TimeUtils.dateYMDToTime("" + year + "-" + month + "-" + day + "-12-12-12")), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNegative(DialogInterface dialog) {

                    }
                })
                .build()
                .show();
    }
}
