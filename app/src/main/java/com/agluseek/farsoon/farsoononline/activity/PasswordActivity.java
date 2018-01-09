package com.agluseek.farsoon.farsoononline.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.agluseek.farsoon.farsoononline.R;
import com.agluseek.farsoon.farsoononline.model.ModifyPassWord;
import com.agluseek.farsoon.farsoononline.utils.Config;
import com.agluseek.farsoon.farsoononline.utils.Globals;
import com.agluseek.farsoon.farsoononline.utils.HttpUtils;
import com.alibaba.fastjson.JSON;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * @author Farsoon Wu'Ang
 *         个性设置_修改密码
 */

public class PasswordActivity extends AppCompatActivity {
    private Button commit_password;
    private TextInputEditText original_user_password;
    private TextInputEditText for_original_user_password;
    private TextView username;
    private String useroriginal_password;
    private String again_password;
    public static final int MODIFY_CODE = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);
        Toolbar toolbar = (Toolbar) findViewById(R.id.password_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("修改密码");

        username = (TextView) findViewById(R.id.passwordactivity_username);
        original_user_password = (TextInputEditText) findViewById(R.id.original_user_password);
        for_original_user_password = (TextInputEditText) findViewById(R.id.for_original_user_password);

        username.setText(Globals.nowUsername);

//        useroriginal_password = original_user_password.getText().toString();
//        again_password = for_original_user_password.getText().toString();
        //修改密码


        commit_password = (Button) findViewById(R.id.update_button);
        commit_password.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //用户密码
                useroriginal_password = original_user_password.getText().toString();
                //确认密码
                again_password = for_original_user_password.getText().toString();

                boolean cancel = false;
                View focusView = null;
                if (TextUtils.isEmpty(useroriginal_password)) {

                    original_user_password.setError("新密码不能为空");
                    focusView = original_user_password;
                    cancel = true;
                }
                if (useroriginal_password.contains(" ")) {
                    Toast.makeText(PasswordActivity.this, "修改失败，密码不能包含空格，请重新输入", Toast.LENGTH_SHORT).show();
                    focusView = original_user_password;
                    cancel = true;
                }
                if (TextUtils.isEmpty(again_password)) {
                    for_original_user_password.setError("确认密码不能为空");
                    focusView = for_original_user_password;
                    cancel = true;
                }
                if (again_password.contains(" ")) {
                    Toast.makeText(PasswordActivity.this, "修改失败，密码不能包含空格，请重新输入", Toast.LENGTH_SHORT).show();
                    focusView = for_original_user_password;
                    cancel = true;
                }

                if (cancel) {
                    focusView.requestFocus();

                } else {
                    // TODO: 2017/12/7 修改密码，发现不用输入原始密码就可以修改密码,修改密码应该要输入原始密码才能修改
                    AlertDialog.Builder builder = new AlertDialog.Builder(PasswordActivity.this);
                    builder.setMessage("确定要修改密码")
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    System.out.println("提示框");
                                    if (useroriginal_password.equals(again_password) && useroriginal_password != null && again_password != null) {

                                        String api = "http://" + Config.address + Config.loginAPI + Config.modifyAPI + Globals.nowUsername + Config.newPasswordAPI + again_password;
                                        HttpUtils.doGet(api, new Callback() {
                                            @Override
                                            public void onFailure(Call call, IOException e) {
                                                System.out.println("提示框中失败的------>>" + e);

                                            }

                                            @Override
                                            public void onResponse(Call call, Response response) throws IOException {
                                                String result = response.body().string();
                                                System.out.println("提示框中成功的-------->>>" + result);
                                                ModifyPassWord modifyPassWord = JSON.parseObject(result, ModifyPassWord.class);
                                                int endding = modifyPassWord.getEndding();
                                                System.out.println(endding);

                                                Message s = Message.obtain();
                                                s.obj = endding;
                                                s.what = MODIFY_CODE;

                                                handler.sendMessage(s);

                                            }
                                        });
                                    } else {
                                        original_user_password.getText().clear();
                                        for_original_user_password.getText().clear();
                                        Toast.makeText(PasswordActivity.this, "您输入的两次密码不一致，请重新输入", Toast.LENGTH_SHORT).show();

                                    }

                                }

                            })
                            .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            })
                            .create().show();
                }


            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public static void actionStart(Activity activity) {
        Intent i = new Intent(activity, PasswordActivity.class);
        activity.startActivity(i);
    }

    private int Modify_result;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == MODIFY_CODE) {
                Modify_result = (int) msg.obj;
                System.out.println("这是传过来后的密码修改流程 -------->>" + Modify_result);
                switch (Modify_result) {
                    case 1:
                        original_user_password.getText().clear();
                        for_original_user_password.getText().clear();
                        Toast.makeText(PasswordActivity.this, "修改成功", Toast.LENGTH_SHORT).show();

                        break;
                    case 0:
                        Toast.makeText(PasswordActivity.this, "修改失败", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        }

    };

}
