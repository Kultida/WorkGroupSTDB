package com.example.gnomerock.mytemplate1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;

public class MainActivity extends AppCompatActivity {

    CallbackManager callbackManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialization();


    }


    public void initialization(){

        //Assign Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);


        //Assign main fragment
        MainActivityFragment mainFragment = new MainActivityFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_fragment,mainFragment)
                .commit();


        //Assign Facebook
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();

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
        if (id == R.id.action_settings) {
            //Display Toast
            Toast.makeText(getApplicationContext(),"ตั้งค่า", Toast.LENGTH_LONG).show();
            //Create Intent for setting Activity
            //Intent in = new Intent(MainActivity.this,SettingAtivity.class);
           // startActivity(in);

            return true;
        }
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            //Display Toast
            Toast.makeText(getApplicationContext(),"ค้นหา", Toast.LENGTH_LONG).show();
            return true;
        }
        else if (id == R.id.action_edit) {
            Toast.makeText(this,"EDIT PRESSED",Toast.LENGTH_SHORT).show();
            return true;
        }
        else if (id == R.id.action_logout) {
            Toast.makeText(this,"LOG OUT PRESSED",Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
