package zhujj.zm.activity;

import android.content.Intent;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

import java.util.List;

import zhujj.baselibrary.activity.BaseActivity;
import zhujj.zm.MyApplication;
import zhujj.zm.R;
import zhujj.zm.db.bean.User;
import zhujj.zm.db.dao.UserDao;
import zhujj.zm.util.SharedPreferencesUtil;
import zhujj.zm.view.ConfirmDialog;
import zhujj.zm.view.adapter.LoginGridviewAdapter;

/**
 * 作者：朱建晶 on 2018/1/24 16:23
 * 邮箱：344951059@qq.com
 */

public class LoginActivity extends BaseActivity {

    private GridView login_gridview;
    private EditText login_user, login_pwd;
    private String pwd = "";
    private TextView change_user_img;

    @Override
    protected void findViews() {
        setContentView(R.layout.activity_login);

        login_gridview = (GridView) findViewById(R.id.login_gridview);
        login_user = (EditText) findViewById(R.id.login_user);
        login_pwd = (EditText) findViewById(R.id.login_pwd);
        change_user_img = (TextView) findViewById(R.id.change_user_img);
    }

    @Override
    protected void addAction() {
        login_pwd.setInputType(InputType.TYPE_NULL);
        login_gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0) {
                    login_user.setText("");
                    pwd = "";
                    login_user.setEnabled(true);
                } else if (i == 1) {
                    pwd = pwd + "0";
                } else if (i == 2) {
                    if (pwd.length() != 0) {
                        pwd = pwd.substring(0, pwd.length()-1);
                    }
                } else {
                    pwd = pwd + (i - 2);
                }
                refushPwd();

            }
        });

        login_pwd.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE))
                            .hideSoftInputFromWindow(getCurrentFocus()
                                            .getWindowToken(),
                                    InputMethodManager.HIDE_NOT_ALWAYS);
                }
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
                            showChooseDialog("用户名不存在，是否进行注册");
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
        if (usersAll.size() >= 2) {
            change_user_img.setVisibility(View.VISIBLE);
        } else {
            change_user_img.setVisibility(View.GONE);
        }
        if (usersAll.size() > 0) {
            if (SharedPreferencesUtil.getUser(this) != null) {
                login_user.setText(SharedPreferencesUtil.getUser(this));
                login_user.setEnabled(false);
            } else {
                login_user.setText(usersAll.get(0).getName());
                login_user.setEnabled(false);
            }
        } else {
            showToastText("您还没有注册用户，输入用户名密码注册");
            login_user.setEnabled(true);
        }
    }

    private void refushPwd() {
        if (pwd.length() > 0) {
            String pwdString = "";
            for (int i=0;i<pwd.length();i++) {
                pwdString = pwdString + "*";
            }
            login_pwd.setText(pwdString);
        } else {
            login_pwd.setText("");
        }

    }

    private void showChooseDialog(String errorMsg) {
        ConfirmDialog confirmDialog = new ConfirmDialog(this, "提示", errorMsg,
                "确认", new View.OnClickListener() {
            public void onClick(View v) {

                User user = new User();
                user.setName(login_user.getText().toString());
                user.setPwd(pwd);
                insertUser(user);

                gotoMainActivity();
            }
        }, "其他用户名", new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                login_user.setText("");
                login_user.setEnabled(true);
                pwd = "";
                refushPwd();
            }
        });
        confirmDialog.show();
    }


    private long insertUser(User user) {
        return UserDao.insertUser(user);
    }

    private void gotoMainActivity() {
        List<User> users = UserDao.queryUser(login_user.getText().toString());
        if (users.size() > 0) {
            MyApplication.STORE_BEAN.user = users.get(0);
        }
        Intent intent = new Intent(this, WriteBookMainActivity.class);
        startActivity(intent);
        finish();
    }
}
