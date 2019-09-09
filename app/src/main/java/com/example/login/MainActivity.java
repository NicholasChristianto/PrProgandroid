package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login();
        prepareFragmment();
//        move();
    }

    protected void login() {
        button = findViewById(R.id.btnSubmit);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent home = new Intent(MainActivity.this, homePage.class);
                startActivity(home);
            }

        });
    }
    private void prepareFragmment(){
        this.getSupportFragmentManager().beginTransaction().add(R.id.fragment_placeholder, new fragment_message()).commit();
    }
//    protected void move() {
//        btn = findViewById(R.id.fragment_move);
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent gas = new Intent(MainActivity.this, About.class);
//                startActivity(gas);
//            }
//        });
//    }
}


