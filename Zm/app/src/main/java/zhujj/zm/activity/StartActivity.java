package zhujj.zm.activity;

import android.content.Intent;

import zhujj.baselibrary.activity.BaseActivity;
import zhujj.zm.R;

/**
 * 作者：朱建晶 on 2018/1/21 16:51
 * 邮箱：344951059@qq.com
 */
public class StartActivity extends BaseActivity {

    @Override
    protected void findViews() {
        setContentView(R.layout.activity_start);
        handleGoMain();
    }

    @Override
    protected void addAction() {

    }

    @Override
    protected void initData() {

    }

    private void handleGoMain() {
        handler.postDelayed(new Runnable() {

            @Override
            public void run() {
                gotoMainActivity();
            }
        }, 1000);
    }

    private void gotoMainActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
