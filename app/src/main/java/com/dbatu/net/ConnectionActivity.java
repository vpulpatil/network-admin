package com.dbatu.net;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.IntegerRes;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ConnectionActivity extends AppCompatActivity {

    public EditText textIpAddress;
    public Button connect, clear;
    private Socket client;
    private PrintWriter printwriter;
    public String messsage = "Server established.";
    public String ipAddress=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getApplicationContext(),LoginActivity.class);
                Toast.makeText(ConnectionActivity.this, "Logged out successfully", Toast.LENGTH_SHORT).show();
                startActivity(i);
            }
        });

        textIpAddress = (EditText) findViewById(R.id.ip_address);
        connect = (Button) findViewById(R.id.buttonConnect);
        clear = (Button) findViewById(R.id.buttonClear);

        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String ipAddress;
                ipAddress = textIpAddress.getText().toString();
                //getAddress(ipAddress);
                textIpAddress.setText(""); // Reset the text field to blank
                try {
                    SendMessage sendMessageTask = new SendMessage(ipAddress);
                    sendMessageTask.execute();
                    Toast.makeText(ConnectionActivity.this, "Connection Established", Toast.LENGTH_SHORT).show();
                    Intent i= new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(i);
                } catch (Exception ex) {
                    Toast.makeText(ConnectionActivity.this, "Connection Falied. Please try again..", Toast.LENGTH_SHORT).show();
                    ex.printStackTrace();
                }

                /*try {
                    Socket client= new Socket(ipAddress,4444);
                    client.connect();
                    Toast.makeText(ConnectionActivity.this, "Connection Established", Toast.LENGTH_SHORT).show();
                    Intent i= new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(i);
                } catch (IOException e) {
                    Toast.makeText(ConnectionActivity.this, "Connection Falied. Please try again..", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }*/
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textIpAddress.setText("");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.sign_out) {
            Toast.makeText(ConnectionActivity.this, "You have successfully logged out!", Toast.LENGTH_SHORT).show();
            Intent i=new Intent(ConnectionActivity.this,LoginActivity.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(i);
    }

    private class SendMessage extends AsyncTask<Void, Void, Void> {

        public String ip=ipAddress;

        public SendMessage(String ip) {
            this.ip = ip;
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                client = new Socket(ip , 4444); // connect to the server
                printwriter = new PrintWriter(client.getOutputStream(), true);
                printwriter.write(messsage); // write the message to output stream

                printwriter.flush();
                printwriter.close();
                client.close(); // closing the connection

            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

    }


}

