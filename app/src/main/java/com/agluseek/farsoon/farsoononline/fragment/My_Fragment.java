package com.agluseek.farsoon.farsoononline.fragment;

import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.agluseek.farsoon.farsoononline.R;
import com.agluseek.farsoon.farsoononline.activity.MainActivity;
import com.agluseek.farsoon.farsoononline.model.LoginInfo;
import com.agluseek.farsoon.farsoononline.utils.Config;
import com.agluseek.farsoon.farsoononline.utils.Globals;
import com.agluseek.farsoon.farsoononline.utils.HttpUtils;
import com.alibaba.fastjson.JSON;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Response;

/**
 * Created by Wu'Ang on 2017/4/17.
 */

public class My_Fragment extends Fragment implements View.OnClickListener {

    private static View rootView;
    private Button loginButton;
    private boolean isLogin = false;
    private AutoCompleteTextView mUsernameView;
    private EditText mPasswordView;
    private OkHttpClient mOkHttpClient = null;
    private String name = null;
    private String pw = null;
    private View mProgressView;
    private static int LOGIN_SUCCESS = 1;
    private static int LOGIN_FAILED = 0;

    //    private String username = "AppTest";
    //    private String password = "123";

    //    private String url = "http://192.168.4.110/Default.aspx?username=" + name + "&password=" + pw;
    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle data = msg.getData();
            int status = data.getInt("LoginStatus");
            String loginInfo = data.getString("LoginMachines");
            switch (status) {
                case 0:
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setMessage("您的账号或者密码不正确，请重新输入")
                            .setTitle("提示")
                            .setPositiveButton("知道了", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            }).create().show();
                    break;

                case 1:
                    Globals.nowUsername = name;
                    Globals.nowPassword = pw;
                    System.out.println("loginInfo--------->>"+loginInfo);
                    Toast.makeText(getActivity(), "登录成功", Toast.LENGTH_SHORT).show();

                    MainActivity.actionStart(getActivity(), loginInfo);

                    // TODO: 2017/7/24 将登录后伴随着的机器设备数量、详情传递过去
                    break;

            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        rootView = inflater.inflate(R.layout.activity_login, container, false);
        loginButton = (Button) rootView.findViewById(R.id.login_button);
        mUsernameView = (AutoCompleteTextView) rootView.findViewById(R.id.username);
        mPasswordView = (EditText) rootView.findViewById(R.id.password);
        mProgressView = rootView.findViewById(R.id.login_progress);
        loginButton.setOnClickListener(this);

        return rootView;

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.login_button:

                attemptLogin();
                System.out.println();

                break;

        }

    }

    // TODO: 2017/12/7 输入新密码为空格，修改成功后，退出程序 , 重新登录时，输入用户名密码为空点击登录也能登录

    private void attemptLogin() {

        if (mOkHttpClient != null) {
            return;
        }

        mUsernameView.setError(null);
        mPasswordView.setError(null);

        boolean cancel = false;
        View focusView = null;

        name = mUsernameView.getText().toString().trim();
        pw = mPasswordView.getText().toString().trim();

        if (TextUtils.isEmpty(name)) {
            mUsernameView.setError("用户名不能为空");
            focusView = mUsernameView;
            cancel = true;
        }

        if (TextUtils.isEmpty(pw)) {
            mPasswordView.setError("密码不能为空");
            focusView = mPasswordView;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        }

        // showProgress(true);

        String param = String.format("username=%s&password=%s", name, pw);
        String api = "http://" + Config.address + Config.loginAPI + "username=" + name + "&password=" + pw;
        Map<String, String> map = new HashMap<>();
        map.put("username", name);
        map.put("password", pw);

        HttpUtils.doPost(api, map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
//                if (response != null) {
//                    Globals.nowUsername = name;
//                }
                String result = response.body().string();
                System.out.println("登录result---------->>"+result);
//                JSONObject jsonObject = JSON.parseObject(result);
//                Toast.makeText(getActivity(), "登录成功！", Toast.LENGTH_SHORT).show();
//                    System.out.println("这是登录回传的数据-------->>>"+result);

                LoginInfo info = JSON.parseObject(result, LoginInfo.class);
                String machines = info.getMachines();
                System.out.println("machines-------->>"+machines);
                int resultStatus = info.getLogin();
                Message s = Message.obtain();
                Bundle b = new Bundle();
                b.putInt("LoginStatus", resultStatus);
                b.putString("LoginMachines", machines);
                s.setData(b);
                handler.sendMessage(s);

            }

        });

//        mOkHttpClient = new OkHttpClient();

//        FormBody.Builder body = new FormBody.Builder();
//        body.add("username", name);
//        body.add("password", pw);
//        FormBody formBody = body.build();
//        final Request request = new Request.Builder()
//                .post(formBody)
//                .url(url)
//                .build();
//
//        Call call = mOkHttpClient.newCall(request);
//        call.enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//
//                System.out.println("登录失败！");
//
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
////                Log.d("onResponse------------>", response.body().string());
//                String Jsonstr = response.body().string();
////              Log.d("data" , "--------------->>>" +Jsonstr);
//                LoginInfo loginInfo = JSON.parseObject(Jsonstr, LoginInfo.class);
//                if (response != null && loginInfo.getLogin() == 1) {
//
//                    Message message = Message.obtain();
//                    message.what = 0;
//                    Bundle b = new Bundle();
//                    b.putString("LoginInfo", Jsonstr);
//                    message.setData(b);
//                    handler.sendMessage(message);
//
//                    System.out.println("登录成功");
//
//                } else {
//
//                    handler.sendEmptyMessage(1);
//                    System.out.println("登录失败");
//
//                }
//
//            }
//
//        });
    }

    private void showProgress(boolean show) {
        mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
        mUsernameView.setEnabled(!show);
        mPasswordView.setEnabled(!show);
        loginButton.setEnabled(!show);
    }


}
