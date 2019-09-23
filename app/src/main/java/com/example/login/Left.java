package com.example.login;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.example.login.R;

import static android.content.Context.JOB_SCHEDULER_SERVICE;
import static android.content.Intent.getIntent;
import static androidx.core.content.ContextCompat.getSystemService;

public class Left extends Fragment {

    ExampleJobService ex = new ExampleJobService();
    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.left_activity,container,false);


    }




}