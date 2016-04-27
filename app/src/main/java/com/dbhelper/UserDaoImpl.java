package com.dbhelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.dto.User;

/**
 * Created by VIPUL on 17-Apr-16.
 */
public class UserDaoImpl
{
    private UserDbHelper dbHelper;
    private SQLiteDatabase database;

    public UserDaoImpl(Context context)
    {
        dbHelper = new UserDbHelper(context);
    }

    public void open()
    {
        database = dbHelper.getWritableDatabase();
    }

    public void close()
    {
        database.close();
    }

    public long registerUser(User user)
    {
        ContentValues values = new ContentValues();
        values.put(UserDbHelper.user_name,user.getUserName());
        values.put(UserDbHelper.user_password, user.getPassword());
        return database.insert(UserDbHelper.USER_TABLE, null, values);
    }

    public void loginUser(User user)
    {
        Cursor cursor = database.query(UserDbHelper.TABLE_NAME, new String[]{UserDbHelper.user_name,UserDbHelper.user_password}, null, null, null, null, null);
        while(cursor.moveToNext())
        {
            cursor.getString(cursor.getColumnIndex(UserDbHelper.user_name));
            cursor.getString(cursor.getColumnIndex(UserDbHelper.user_password));
        }
    }

}

