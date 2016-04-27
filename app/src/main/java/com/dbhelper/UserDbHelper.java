package com.dbhelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by VIPUL on 17-Apr-16.
 */
public class UserDbHelper extends SQLiteOpenHelper
{
    public static final String DB_NAME="userdb";
    public static final int DB_VERSION=1;
    public static final String TABLE_NAME="userlist";
    public static final String user_name="user_name";
    public static final String user_password="user_password";
    public static final String USER_TABLE=
            "CREATE TABLE "
                    + TABLE_NAME + " (" + user_name + "TEXT" + user_password + " TEXT)";


    public UserDbHelper(Context context)
    {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

    }

}

