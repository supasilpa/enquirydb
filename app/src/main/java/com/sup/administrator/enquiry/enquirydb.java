package com.sup.administrator.enquiry;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 1/30/2019.
 */
public class enquirydb extends SQLiteOpenHelper {
    public static  final String dbname="enquiry.db";
    public static final String tablename="enquiry";
    public static final String col1="id";
    public static final String col2="name";
    public static final String col3="email";
    public static final String col4="mobile";
    public static final String col5="place";
    public static final String col6="msg";


    public enquirydb(Context context) {
        super(context, dbname,null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query="create table "+tablename+"("+col1+ " integer primary key autoincrement, "+col2+ " text, "+col3+ " text, "+col4+ " text, "+col5+ " text, "+col6+ " text )";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
     String query="drop table if exists "+tablename;
        sqLiteDatabase.execSQL(query);
        onCreate(sqLiteDatabase);
    }
    public boolean insert(String name,String email,String mobile,String place,String msg)
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col2,name);
        contentValues.put(col3,email);
        contentValues.put(col4,mobile);
        contentValues.put(col5,place);
        contentValues.put(col6,msg);
        Long status=sqLiteDatabase.insert(tablename,null,contentValues);
        if(status==-1)
        {
            return false;
        }
        else
        {
            return  true;
        }

    }
//    data retrival
    public Cursor Searchdata(String name)
    {
        SQLiteDatabase sq=this.getWritableDatabase();
        Cursor cur=sq.rawQuery("SELECT * FROM "+tablename+" WHERE "+col2+"='"+name+"'",null);
        return cur;
    }
//    update
    public boolean updatedata(String id,String email){
        SQLiteDatabase sq=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(col3,email);
        long status=sq.update(tablename,cv,col1 + "=" +id,null);
        if(status==-1)
        {
            return false;

        }
        else
        {
            return true;
        }



    }
    public boolean delete(String id){
        SQLiteDatabase sq=this.getWritableDatabase();
        long status=sq.delete(tablename,col1+"="+id,null);
        if(status==-1){
            return false;
        }
        else
        {
            return true;
        }
    }

}
