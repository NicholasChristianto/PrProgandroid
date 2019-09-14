package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private EditText uname;
    private EditText pw;

    //   private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.btnSubmit);
        uname = findViewById(R.id.txtEmailAddress);
        pw = findViewById(R.id.txtPassword);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cek(String.valueOf(uname.getText()), String.valueOf(pw.getText()))) {
                    Intent home = new Intent(MainActivity.this, homePage.class);
                    finish();
                    startActivity(home);
                }
            }

        });


    }


    private boolean cek(String uname, String pw) {
        if (uname.equals("nicholas.christianto@ti.ukdw.ac.id") && pw.equals("12345"))
            return true;
        else {
            Toast.makeText(this, "LOGIN GAGAL", Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}


