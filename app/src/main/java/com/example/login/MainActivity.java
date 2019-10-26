package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;



public class MainActivity extends AppCompatActivity {
    public static final String MyPREFERENCES = "MyPrefs" ;
    private Button button;
    private EditText uname;
    private EditText pw;
    public static final String Email = "nameKey";
    SharedPreferences sharedpreferences;

    //   private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.btnSubmit);
        uname = findViewById(R.id.txtEmailAddress);
        pw = findViewById(R.id.txtPassword);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        System.out.println("available Login Email: "+ sharedpreferences.getString(Email, new String()));

        if(sharedpreferences.getString(Email, new String()).equals("nicholas.christianto@ti.ukdw.ac.id") )
        {
            Intent home = new Intent(MainActivity.this, homePage.class);
            startActivity(home);
        }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cek(String.valueOf(uname.getText()), String.valueOf(pw.getText()))) {
                    String name = uname.getText().toString();
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString(Email, name);
                    editor.commit();
                    Intent home = new Intent(MainActivity.this, homePage.class);

//                    Bundle b = new Bundle();
//                    b.putString("Email: ", uname.getText().toString());
//                    home.putExtras(b);
//                    finish();
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


