package com.example.login;

import android.app.NotificationManager;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.nfc.Tag;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class homePage extends AppCompatActivity {
    Boolean boolReceiver=false;
    Boolean isReceiverRegistered=false;
    Boolean wifiConnected = false;
    Button button;
    private static final String TAG = "HomePage";
    public static final long INTERVAL=3000;//variable to execute services every 10 second
    private Handler mHandler=new Handler(); // run on another Thread to avoid crash
    private Timer mTimer=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        ViewPager vp = findViewById(R.id.view_pager);
        vp.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(),2));
        TabLayout tl = findViewById(R.id.tab_layout);
        tl.setupWithViewPager(vp);
        Intent intent = getIntent();
        Bundle bun = getIntent().getExtras();
        String txtEmail = bun.getString("Email: ","");
        Toast.makeText(getApplicationContext(), "Welcome "+txtEmail, Toast.LENGTH_SHORT).show();
        keList();
    }

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            WifiManager wm =(WifiManager) context.getSystemService(Context.WIFI_SERVICE);
            if (!isNetworkAvailable(context)) {
                Notification(context,"Wifi Turned OFF");

            } else {
                Notification(context,"Wifi Turned ON");
            }
        }
            public void Notification(Context context, String stt) {

                NotificationCompat.Builder notif = new NotificationCompat.Builder(context)
                        .setSmallIcon(R.drawable.ic_launcher_foreground)
                        .setTicker(stt)
                        .setContentTitle("Test Wifi")
                        .setContentText(stt)
                        .setAutoCancel(true);
                NotificationManager notificationmanager = (NotificationManager) context
                        .getSystemService(Context.NOTIFICATION_SERVICE);
                notificationmanager.notify(0, notif.build());
            }
            private boolean isNetworkAvailable(Context context){
                ConnectivityManager cm = (ConnectivityManager) context
                        .getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo ni = cm.getActiveNetworkInfo();
                return ni !=null;
            }
        };


    protected void onResume(){
        super.onResume();
        if(!isReceiverRegistered){
            isReceiverRegistered = true;
            registerReceiver(receiver,new IntentFilter("android.net.wifi.STATE_CHANGE"));
        }
        System.out.println("Wifi Status: "+wifiConnected);
    }
    public void scheduleJob(View v){
        ComponentName cn = new ComponentName(this, ExampleJobService.class);
        JobInfo info = new JobInfo.Builder(123, cn)
                .setPersisted(true)
                .setPeriodic(15*60*1000)
                .build();
        JobScheduler scheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        int resultCode = scheduler.schedule(info);
        if(resultCode == JobScheduler.RESULT_SUCCESS){
            Log.d(TAG, "Job Scheduled");

        }else {
            Log.d(TAG, "Job Scheduling failed");

        }
        if(mTimer!=null)
            mTimer.cancel();
        else
            mTimer=new Timer(); // recreate new timer
        mTimer.scheduleAtFixedRate(new TimeDisplayTimerTask(),0,INTERVAL);

    }
    public void cancelJob(View v){
        JobScheduler scheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        scheduler.cancel(123);
        Log.d(TAG, "Job Cancelled");
        mTimer.cancel();
    }

    private class TimeDisplayTimerTask extends TimerTask {
        @Override
        public void run() {
            // run on another thread
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    // display toast at every 10 second
                    Toast.makeText(getApplicationContext(), "3 Seconds", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
    protected void keList() {
        button = findViewById(R.id.list);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent list = new Intent(homePage.this, listFilm.class);
                startActivity(list);
            }

        });
    }


}
