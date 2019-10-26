package com.example.login;

import android.content.ContentValues;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class fragmentDisplay extends Fragment {
    int pos = 0;
    private SQLiteDatabase mydb ;
    private RecyclerView rv;
    private EditText isinim;
    private RecycleViewDatabase la;
    private EditText isinama;
    private EditText isinohp;
    private TextView nim;
    private TextView nama;
    private TextView nohp;
    private Button tambah;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v= inflater.inflate(R.layout.fragment_display,container,false);
        rv = v.findViewById(R.id.listdb);
        la = new RecycleViewDatabase(getContext(),getAllItem());
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv.setAdapter(la);
        isinim = v.findViewById(R.id.isinim);
        isinama = v.findViewById(R.id.isinama);
        isinohp = v.findViewById(R.id.isinohp);
        tambah = v.findViewById(R.id.btntambah);
        tambah.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                ContentValues contentValues = new ContentValues();
                contentValues.put("nim", isinim.getText().toString());
                contentValues.put("nama", isinama.getText().toString());
                contentValues.put("noHp", isinohp.getText().toString());
                mydb.insert("mahasiswa",null,contentValues);
                la.swapCursor(getAllItem(),);

                //insertMahasiswa(isinama.getText().toString(),isinohp.getText().toString(),isinim.getText().toString());
            }
        });
        return v;

    }
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        DBHelper dbhelper = new DBHelper(getContext());
        mydb = dbhelper.getReadableDatabase();
    }


    private Cursor getAllItem(){
        return mydb.query(
                mahasiswaContract.mahasiswaEntry.TABLE_NAME,
                null,null,null,null,null,null
        );
    }
}