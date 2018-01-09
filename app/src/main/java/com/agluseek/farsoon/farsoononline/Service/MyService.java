package com.agluseek.farsoon.farsoononline.Service;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.Color;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.v7.app.NotificationCompat;

import com.agluseek.farsoon.farsoononline.R;
import com.agluseek.farsoon.farsoononline.activity.DevicesInfoActivity;
import com.agluseek.farsoon.farsoononline.fragment.DevicesItem_Fragment;
import com.agluseek.farsoon.farsoononline.model.PushInfo;
import com.agluseek.farsoon.farsoononline.model.PushStatus;
import com.agluseek.farsoon.farsoononline.utils.Config;
import com.agluseek.farsoon.farsoononline.utils.Globals;
import com.agluseek.farsoon.farsoononline.utils.HttpUtils;
import com.alibaba.fastjson.JSON;
import com.wenming.library.NotifyUtil;

import java.io.IOException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MyService extends Service {
    public static final int UPDATE_PUSHALERM = 0;
    private NotifyUtil currentNotify;
    private String messages;
    private Timer timer = null;
    private TimerTask task = null;
    private EchoServiceBinder echoServiceBinder = new EchoServiceBinder();

    public MyService() {

    }

    @Override
    public void onCreate() {
        System.out.println("Service_onCreate");
        startTimer();
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        System.out.println("Service_onDestroy");
        stopTimer();
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return echoServiceBinder;

    }

    public class EchoServiceBinder extends Binder {
        public MyService getService() {
            return MyService.this;
        }

    }

    public void startTimer() {
        if (timer == null) {
            timer = new Timer();
            task = new TimerTask() {
                @Override
                public void run() {
                    attempGetPushInfo();
                }

            };
            timer.schedule(task, 1000, 10000);
        }
    }

    public void stopTimer() {
        if (timer != null) {
            timer.cancel();
            task.cancel();
        }
    }
//  普通消息及警报推送
    public List<PushInfo> GetPushInfoList() {
        List<PushInfo> list = JSON.parseArray(messages, PushInfo.class);
        return list;
    }

    private void attempGetPushInfo() {
        String api = "http://" + Config.address + Config.getPushInfo + Globals.nowUsername;
        HttpUtils.doGet(api, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("推送获取失败");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                System.out.println(System.currentTimeMillis()+"推送的报警"+result);

                PushStatus pushResult = JSON.parseObject(result, PushStatus.class);
                String messages = pushResult.getMessages();
                Message s = Message.obtain();
                s.what = UPDATE_PUSHALERM;
                s.obj = messages;
                handler.sendMessage(s);

            }
        });
    }

    private Handler handler = new Handler() {

//        获取到推送警报的消息
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == UPDATE_PUSHALERM) {
                messages = (String) msg.obj;
                List<PushInfo> pushInfos = JSON.parseArray(messages, PushInfo.class);
                for (PushInfo infos : pushInfos) {
                    notify_normal_singLine(Integer.parseInt(infos.getMsgID()),infos.getID(),infos.getContent());
//                    sendNotifation(Integer.parseInt(infos.getMsgID()),infos.getID(),infos.getContent());
                    System.out.println(infos.getID());
                    System.out.println(infos.getContent());
                    System.out.println(infos.getMsgID());
                }
            }
        }
    };

    private void notify_normal_singLine(int id, String title, String content) {
        //设置想要展示的数据内容
        Intent intent = new Intent(this, DevicesItem_Fragment.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pIntent = PendingIntent.getActivity(this,
                1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        int smallIcon = R.mipmap.ic_launcher_round;

        //实例化工具类，并且调用接口
        NotifyUtil notify1 = new NotifyUtil(this, id);
        notify1.notify_normal_singline(pIntent, smallIcon, title + content, title, content, true, true, false);
        currentNotify = notify1;
    }

    public void sendNotifation(int id, String title, String text) {

        NotificationCompat.Builder nBuilder =
                new NotificationCompat.Builder(this);
        nBuilder.setSmallIcon(R.mipmap.ic_launcher_round);
        nBuilder.setContentTitle(title);
        nBuilder.setContentText(text);
        nBuilder.setVibrate(new long[]{100, 100, 100});
        nBuilder.setLights(Color.RED, 1000, 1000);

        NotificationManager notifyMgr = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notifyMgr.notify(id, nBuilder.build());

    }
}
