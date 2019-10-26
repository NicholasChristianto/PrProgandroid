package com.example.login;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "progandro.db";
    public static final String MAHASISWA_TABLE_NAME = "mahasiswa";
    public static final String MAHASISWA_COLUMN_NIM= "nim";
    public static final String MAHASISWA_COLUMN_NAMA = "nama";
    public static final String MAHASISWA_COLUMN_NOHP = "noHp";
    private HashMap hp;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
            final String SQL_CREATE_MAHASISWA_TABLE = "CREATE TABLE "+ MAHASISWA_TABLE_NAME+"("+
                    MAHASISWA_COLUMN_NIM+" TEXT NOT NULL PRIMARY KEY, "+MAHASISWA_COLUMN_NAMA+" TEXT NOT NULL, "+MAHASISWA_COLUMN_NOHP+" TEXT NOT NULL)";
            db.execSQL(SQL_CREATE_MAHASISWA_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS mahasiswa");
        onCreate(db);
    }



    public Cursor getData(int nim) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from mahasiswa where nim="+nim+"", null );
        return res;
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, MAHASISWA_TABLE_NAME);
        return numRows;
    }

    public boolean updateMahasiswa (String name, String phone, String nim) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("nama", name);
        contentValues.put("noHp", phone);
        db.update("mahasiswa", contentValues, "nim = ? ", new String[] { nim } );
        return true;
    }

    public Integer deleteMahasiswa (String nim) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("mahasiswa",
                "nim = ? ",
                new String[] { nim });
    }

    public ArrayList<String> getAllMahasiswa() {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from mahasiswa", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(MAHASISWA_COLUMN_NAMA)));
            res.moveToNext();
        }
        return array_list;
    }
}