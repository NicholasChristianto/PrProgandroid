package com.example.login;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.viewpager.widget.ViewPager;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class homePage extends AppCompatActivity {
    Boolean boolReceiver=false;
    Boolean isReceiverRegistered=false;
    Boolean wifiConnected = false;
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





}
