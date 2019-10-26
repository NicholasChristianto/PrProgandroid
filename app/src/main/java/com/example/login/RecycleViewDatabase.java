package com.example.login;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecycleViewDatabase extends RecyclerView.Adapter<RecycleViewDatabase.ViewHolder> {
    private Context context;
   private Cursor cursor;

    public RecycleViewDatabase(Context context, Cursor cursor) {
        this.context = context;
        this.cursor = cursor;

    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup vg , int viewType){
        View v=LayoutInflater.from(context).inflate(R.layout.list_db,vg,false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder vh, int pos){
        if (!cursor.moveToPosition(pos)) {
            return;
        }

        String nim = cursor.getString(cursor.getColumnIndex(mahasiswaContract.mahasiswaEntry.COLUMN_NIM));
        String nama = cursor.getString(cursor.getColumnIndex(mahasiswaContract.mahasiswaEntry.COLUMN_NAMA));
        String noHP = cursor.getString(cursor.getColumnIndex(mahasiswaContract.mahasiswaEntry.COLUMN_NOHP));
        vh.nim.setText(nim);
        vh.nama.setText(nama);
        vh.noHP.setText(noHP);
    }
    @Override
    public int getItemCount(){
        return cursor.getCount();
    }
    public void swapCursor(Cursor newCur){
        if(cursor !=null){
            cursor.close();
        }
        cursor = newCur;
        if(newCur!=null){
            notifyDataSetChanged();
        }
    }
    public class ViewHolder extends RecyclerView.ViewHolder {


        public TextView nim;
        public TextView nama;
        public TextView noHP;

        public ViewHolder(View v) {
            super(v);
            nim = (TextView) v.findViewById(R.id.col1);
            nama = (TextView) v.findViewById(R.id.col2);
            noHP = (TextView) v.findViewById(R.id.col3);
        }
    }


}