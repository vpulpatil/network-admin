package com.dbatu.net;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {

    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private SQLiteDatabase db= null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
      //  populateAutoComplete();

        mPasswordView = (EditText) findViewById(R.id.password);

        db=openOrCreateDatabase("mydb", MODE_PRIVATE, null);
    }

    public void registerClicked(View v)
    {
        Intent i= new Intent(getApplicationContext(),RegisterActivity.class);
        startActivity(i);
    }

    public void loginClicked(View v){
        String username= mEmailView.getText().toString();
        String password= mPasswordView.getText().toString();
        if(username.isEmpty() || password.isEmpty()){
            show("Login or Password should not be empty.");
        }
        else {
            Cursor c = db.rawQuery("select * from login where user_name='" + username + "' and password='" + password + "'", null);
            c.moveToFirst();
            if(c.getCount()>0)
            {
                Intent i= new Intent(getApplicationContext(), ConnectionActivity.class);
                show("Login Success");
                startActivity(i);
                db.close();
            }
            else
                show("Invalid Credentials");
        }
    }

    public void show(String str){
        Toast.makeText(LoginActivity.this, str, Toast.LENGTH_SHORT).show();
    }

}