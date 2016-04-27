package com.dbatu.net;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    public EditText editTextUsername, editTextPassword,editTextConfirmPassword;
    SQLiteDatabase db=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        editTextUsername= (EditText) findViewById(R.id.username);
        editTextPassword= (EditText) findViewById(R.id.password);
        editTextConfirmPassword= (EditText) findViewById(R.id.confirm_password);
        db=openOrCreateDatabase("mydb", MODE_PRIVATE, null);
        db.execSQL
                ("create table if not exists login(user_name varchar,password varchar)");
    }

    public void signUpClicked(View v)
    {
        String userName= editTextUsername.getText().toString();
        String passWord= editTextPassword.getText().toString();
        if(editTextConfirmPassword.getText().toString().equals(passWord)){
            db.execSQL("insert into login values('"+userName+"','"+passWord+"')");
            Intent i=new Intent(this,LoginActivity.class);
            Toast.makeText(RegisterActivity.this, "Registration Success", Toast.LENGTH_SHORT).show();
            startActivity(i);
            db.close();
            finish();
        }
        else
            Toast.makeText(RegisterActivity.this, "Wrong Credentials", Toast.LENGTH_SHORT).show();
        
    }
}
