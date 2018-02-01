package zhujj.zm.activity;

import android.content.Intent;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import zhujj.baselibrary.activity.BaseActivity;
import zhujj.zm.*;
import zhujj.zm.db.bean.User;
import zhujj.zm.db.dao.UserDao;
import zhujj.zm.util.SharedPreferencesUtil;
import zhujj.zm.view.adapter.LoginGridviewAdapter;

/**
 * 作者：朱建晶 on 2018/1/24 16:23
 * 邮箱：344951059@qq.com
 */

public class LoginActivity extends BaseActivity {

    private GridView login_gridview;
    private EditText login_user, login_pwd;
    private String pwd = "";
    private TextView username_text, change_user_img;
    private LinearLayout user_li;

    @Override
    protected void findViews() {
        setContentView(R.layout.activity_login);
        login_gridview = (GridView) findViewById(R.id.login_gridview);
        login_user = (EditText) findViewById(R.id.login_user);
        login_pwd = (EditText) findViewById(R.id.login_pwd);
        user_li = (LinearLayout) findViewById(R.id.user_li);
        username_text = (TextView) findViewById(R.id.username_text);
        change_user_img = (TextView) findViewById(R.id.change_user_img);
    }

    @Override
    protected void addAction() {
        login_gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0) {
                    login_user.setText("");
                    pwd = "";
                } else if (i == 1) {
                    pwd = pwd + "0";
                } else if (i == 2) {
                    pwd = pwd.substring(0, pwd.length()-1);
                } else {
                    pwd = pwd + (i - 2);
                }
                refushPwd();

            }
        });

        login_pwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                pwd = editable.toString();
                if (pwd.length() == 6) {
                    if (TextUtils.isEmpty(login_user.getText())) {
                        showToastText("请输入用户名");
                    } else {
                        List<User> users = UserDao.queryUser(login_user.getText().toString());
                        if (users.size() > 0) {
                            if (users.get(0).getPwd().equals(pwd)) {
                                gotoMainActivity();
                            } else {
                                login_user.setText("");
                                pwd = "";
                                showToastText("密码错误，请重新输入");
                            }
                        } else {
                            showToastText("用户名不存在，是否进行注册");
                        }

                    }
                }
            }
        });

        change_user_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    protected void initData() {
        LoginGridviewAdapter loginGridviewAdapter = new LoginGridviewAdapter(this);
        login_gridview.setAdapter(loginGridviewAdapter);

        List<User> usersAll = UserDao.queryAll();
        if (usersAll.size() > 0) {
            user_li.setVisibility(View.VISIBLE);
            login_user.setVisibility(View.GONE);
            if (SharedPreferencesUtil.getUser(this) != null) {
                username_text.setText(SharedPreferencesUtil.getUser(this));
            } else {
                username_text.setText(usersAll.get(0).getName());
            }
        } else {
            login_user.setVisibility(View.VISIBLE);
        }
    }

    private void refushPwd() {
        login_pwd.setText(pwd);
    }

    private void gotoMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
