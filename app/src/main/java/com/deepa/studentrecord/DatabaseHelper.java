package com.deepa.studentrecord;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public final static String DATABSE_NAME = "MyStudent.db";
    public final static String TABLE_NAME = "MyStudent_TABLE";
    public final static String COL_1 = "ID";
    public final static String COL_2 = "NAME";
    public final static String COL_3 = "EMAIL";
    public final static String COL_4 = "COURSE_COUNT";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS "
                + TABLE_NAME +
                " (ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                " NAME TEXT, " +
                " EMAIL TEXT, " +
                " COURSE_COUNT INTEGER)");

    }
    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABSE_NAME, null, 1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }

    SQLiteDatabase db = this.getWritableDatabase();
    public boolean insertData ( String name, String email, String courseCount){
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, name);
        contentValues.put(COL_3, email);
        contentValues.put(COL_4, courseCount);

        long result = db.insert(TABLE_NAME, null, contentValues);
        if(result == -1){
            return false;
        }else {
            return true;
        }

    }

    public boolean updateData (String id, String name, String email, String courseCount){

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, id);
        contentValues.put(COL_2, name);
        contentValues.put(COL_3, email);
        contentValues.put(COL_4, courseCount);

        db.update(TABLE_NAME, contentValues, "ID=?", new String[]{id});
        return true;

    }

    public Cursor getData (String id){
        String query = "SELECT * FROM " + TABLE_NAME+" WHERE ID ='"+id+"'";
        Cursor cursor = db.rawQuery(query, null);
        return cursor;
    }

    public Integer deleteData(String id){
        return db.delete(TABLE_NAME, "ID=?", new String[]{id});
    }
    public Cursor getAllData(){
        Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE_NAME, null);
        return cursor;
    }

}
