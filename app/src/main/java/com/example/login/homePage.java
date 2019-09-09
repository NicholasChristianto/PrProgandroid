package com.example.login;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;

public class homePage extends AppCompatActivity {
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        keAbout();
        logOut();
        prepareFragmment();
    }

    protected void keAbout() {
        button = findViewById(R.id.about);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent about = new Intent(homePage.this, About.class);
                startActivity(about);
            }

        });
    }
    protected void logOut(){
        button = findViewById(R.id.logout);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent main = new Intent(homePage.this, MainActivity.class);
                startActivity(main);
            }
        });
    }
    private void prepareFragmment(){
        this.getSupportFragmentManager().beginTransaction().add(R.id.fragment_placeholder, new fragment_message()).commit();
    }
}