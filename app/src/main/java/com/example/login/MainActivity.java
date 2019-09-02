package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
   // TextView txt = (TextView) findViewById(R.id.signUp);
    Button btn = (Button) findViewById(R.id.btnSubmit);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    btn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            homepage(v);
        }
    });

// some more code

    public void homepage(View v) {
        // does something very interesting
    }
//    txt.OnClickListener(new View.OnClickListener(){
//        @Override
//                public void onClick(View v){
//                    WindowBaru(v);
//        }
//    });
//    public void WindowBaru(View v){
//        MainActivity a = new MainActivity();
//    }

}

