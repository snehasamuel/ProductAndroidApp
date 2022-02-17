package com.example.productappmzc;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    static String DbName="Company.db";
    static String TableName="Product";
    static String Col1="Id";
    static String Col2="ProCode";
    static String Col3="ProName";
    static String Col4="ProPrice";

    public DatabaseHelper( Context context) {
        super(context,DbName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query="create table "+TableName+"("+
                Col1+" integer primary key autoincrement,"+
                Col2+" text,"+
                Col3+" text,"+
                Col4+" text)";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean insertData(String ProCode,String ProName,String Price)
    {
        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues c=new ContentValues();
        c.put(Col2,ProCode);
        c.put(Col3,ProName);
        c.put(Col4,Price);
        long status= db.insert(TableName,null,c);


        if (status==-1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

}
