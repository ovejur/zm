package zhujj.zm.activity;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.File;
import java.io.FileWriter;

import zhujj.baselibrary.activity.BaseActivity;
import zhujj.zm.MyApplication;
import zhujj.zm.R;
import zhujj.zm.db.bean.Chapter;
import zhujj.zm.db.dao.ChapterDao;
import zhujj.zm.view.ConfirmDialog;

/**
 * 作者：朱建晶 on 2018/2/8 16:22
 * 邮箱：344951059@qq.com
 */

public class WriteChapterActivity extends BaseActivity {

    public static final int WRITE_EXTERNAL_STORAGE_REQUEST_CODE = FIRST_VALUE++;

    private Chapter chapter;
    private TextView chapter_name, text_content_info, text_size_n, text_size_p;
    private RelativeLayout chapter_content_re, bg_color, text_color, chapter_txt_re;
    private EditText chapter_content;

    private int textSize = 15;
    private int bgType = 0;
    private int textColorType = 0;

    private long startTime = 0;
    private long endTime = 0;
    private long writeTime = 0;
    private long startCount = 0;
    private long startWriteTime = 0;

    private boolean isSave = false;

    @Override
    protected void findViews() {
        setContentView(R.layout.activity_write_chapter);
        chapter_name = (TextView) findViewById(R.id.chapter_name);
        text_content_info = (TextView) findViewById(R.id.text_content_info);
        text_size_n = (TextView) findViewById(R.id.text_size_n);
        text_size_p = (TextView) findViewById(R.id.text_size_p);
        chapter_content_re = (RelativeLayout) findViewById(R.id.chapter_content_re);
        chapter_txt_re = (RelativeLayout) findViewById(R.id.chapter_txt_re);
        bg_color = (RelativeLayout) findViewById(R.id.bg_color);
        text_color = (RelativeLayout) findViewById(R.id.text_color);
        chapter_content = (EditText) findViewById(R.id.chapter_content);
    }

    @Override
    protected void addAction() {
        text_size_n.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textSize = textSize + 1;
                if (textSize == 29) {
                    textSize = 28;
                }
                setContentTextSize();
            }
        });
        text_size_p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textSize = textSize - 1;
                if (textSize == 5) {
                    textSize = 6;
                }
                setContentTextSize();
            }
        });
        bg_color.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bgType = bgType + 1;
                if (bgType == 9) {
                    bgType = 0;
                }
                setContentBg();
            }
        });
        text_color.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textColorType = textColorType + 1;
                if (textColorType == 9) {
                    textColorType = 0;
                }
                setContentTextColor();
            }
        });

        chapter_txt_re.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View.OnClickListener onClickListener = new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (ContextCompat.checkSelfPermission(WriteChapterActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                                != PackageManager.PERMISSION_GRANTED) {
                            //申请WRITE_EXTERNAL_STORAGE权限
                            ActivityCompat.requestPermissions(WriteChapterActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                    WRITE_EXTERNAL_STORAGE_REQUEST_CODE);
                        } else {
                            stringTxt(chapter_content.getText().toString(), chapter.getName());
                        }

                    }
                };
                showChooseDialog("确认保存为TXT文件吗？", onClickListener);
            }
        });

        chapter_content.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (startTime == 0) {
                    startTime = System.currentTimeMillis();
                }
                endTime = System.currentTimeMillis();
                setCountView();
            }
        });
    }

    @Override
    protected void initData() {
        chapter = MyApplication.STORE_BEAN.chapter;
        bgType = MyApplication.STORE_BEAN.user.getBgColor();
        textColorType = MyApplication.STORE_BEAN.user.getTextColor();
        textSize = MyApplication.STORE_BEAN.user.getTextSize();
        writeTime = chapter.getCountTime();
        startCount = chapter.getCount();
        startWriteTime = chapter.getCountTime();
        initView();

    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        saveData();
        super.onDestroy();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) { // 监控/拦截/屏蔽返回键
            saveData();
        }
        return super.onKeyDown(keyCode, event);
    }

    private void saveData() {
        if (!isSave) {
            String content = chapter_content.getText().toString();
            String time = "" + System.currentTimeMillis();
            long count = content.length();
            chapter.setContent(content);
            chapter.setCount(count);
            chapter.setCountTime(writeTime);
            chapter.setUpdateTime(time);
            ChapterDao.updateChapter(chapter);

            MyApplication.STORE_BEAN.book.setUpdateTime(time);
            MyApplication.STORE_BEAN.book.setCount(MyApplication.STORE_BEAN.book.getCount() + (count - startCount));
            MyApplication.STORE_BEAN.book.setInputTime(MyApplication.STORE_BEAN.book.getInputTime() + (writeTime - startWriteTime));
            MyApplication.STORE_BEAN.book.update();
//
            MyApplication.STORE_BEAN.user.setCount(MyApplication.STORE_BEAN.user.getCount() + (count - startCount));
            MyApplication.STORE_BEAN.user.setUpdateTime(time);
            MyApplication.STORE_BEAN.user.setTextSize(textSize);
            MyApplication.STORE_BEAN.user.setTextColor(textColorType);
            MyApplication.STORE_BEAN.user.setBgColor(bgType);
            MyApplication.STORE_BEAN.user.setCountTime(MyApplication.STORE_BEAN.user.getCountTime() + (writeTime - startWriteTime));
            MyApplication.STORE_BEAN.user.update();

            isSave = true;
        }

    }

    private void initView() {
        chapter_name.setText(chapter.getName());

        if (chapter.getContent() != null) {
            chapter_content.setText(chapter.getContent());
            chapter_content.setSelection(chapter.getContent().length());
        }
        if (chapter.getCount() == 0) {
            text_content_info.setText("字数:0  |  0/分钟");
        } else if (writeTime == 0) {
            text_content_info.setText("字数:0  |  0/分钟");
        } else {
            if (writeTime > 60) {
                text_content_info.setText("字数:" + chapter.getCount() + "  |  " + (chapter.getCount() / (writeTime / 60)) + "/分钟");
            } else {
                text_content_info.setText("字数:" + chapter.getCount() + "  |  " + chapter.getCount() + "/分钟");
            }
        }
        setContentBg();
        setContentTextColor();
        setContentTextSize();
        InputMethodManager imm = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }

    private void setContentBg() {

        switch (bgType) {
            case 0:
                chapter_content_re.setBackgroundColor(getResources().getColor(R.color.white));
                break;
            case 1:
                chapter_content_re.setBackgroundColor(getResources().getColor(R.color.beige));
                break;
            case 2:
                chapter_content_re.setBackgroundColor(getResources().getColor(R.color.blanchedalmond));
                break;
            case 3:
                chapter_content_re.setBackgroundColor(getResources().getColor(R.color.burlywood));
                break;
            case 4:
                chapter_content_re.setBackgroundColor(getResources().getColor(R.color.aquamarine));
                break;
            case 5:
                chapter_content_re.setBackgroundColor(getResources().getColor(R.color.chartreuse));
                break;
            case 6:
                chapter_content_re.setBackgroundColor(getResources().getColor(R.color.aqua));
                break;
            case 7:
                chapter_content_re.setBackgroundColor(getResources().getColor(R.color.floralwhite));
                break;
            case 8:
                chapter_content_re.setBackgroundColor(getResources().getColor(R.color.darkgoldenrod));
                break;
            default:
                chapter_content_re.setBackgroundColor(getResources().getColor(R.color.white));
                break;
        }
    }

    private void setContentTextColor() {
        switch (textColorType) {
            case 0:
                chapter_content.setTextColor(getResources().getColor(R.color.black));
                break;
            case 1:
                chapter_content.setTextColor(getResources().getColor(R.color.darkblue));
                break;
            case 2:
                chapter_content.setTextColor(getResources().getColor(R.color.darkgreen));
                break;
            case 3:
                chapter_content.setTextColor(getResources().getColor(R.color.darkmagenta));
                break;
            case 4:
                chapter_content.setTextColor(getResources().getColor(R.color.firebrick));
                break;
            case 5:
                chapter_content.setTextColor(getResources().getColor(R.color.crimson));
                break;
            case 6:
                chapter_content.setTextColor(getResources().getColor(R.color.green));
                break;
            case 7:
                chapter_content.setTextColor(getResources().getColor(R.color.indigo));
                break;
            case 8:
                chapter_content.setTextColor(getResources().getColor(R.color.dodgerblue));
                break;
            default:
                chapter_content.setTextColor(getResources().getColor(R.color.black));
                break;
        }
    }

    private void setContentTextSize() {
        chapter_content.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
    }

    private void setCountView() {
        if ((endTime - startTime) > 1000) {
            writeTime = startWriteTime + (endTime - startTime) / 1000;
            if (writeTime > 60) {
                text_content_info.setText("字数:" + chapter_content.getText().toString().length()
                        + "  |  " + (chapter_content.getText().toString().length() / (writeTime / 60)) + "/分钟");
            } else {
                text_content_info.setText("字数:" + chapter_content.getText().toString().length()
                        + "  |  " + chapter_content.getText().toString().length() + "/分钟");
            }

        }
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

    public static void stringTxt(String str, String name){
        try {
            File file = new File("/mnt/sdcard/zm");
            if (!file.exists()) {
                file.mkdirs();
            }
            File file1 = new File("/mnt/sdcard/zm/"+MyApplication.STORE_BEAN.book.getName());
            if (!file1.exists()) {
                file1.mkdirs();
            }
            FileWriter fw = new FileWriter("/mnt/sdcard/zm/"+MyApplication.STORE_BEAN.book.getName() + "/"+name+".txt");//SD卡中的路径
            fw.flush();
            fw.write(str);
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == WRITE_EXTERNAL_STORAGE_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                stringTxt(chapter_content.getText().toString(), chapter.getName());
            } else {

            }
        }
    }
}
