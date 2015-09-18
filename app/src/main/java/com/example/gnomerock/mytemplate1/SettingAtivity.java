package com.example.gnomerock.mytemplate1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

/**
 * Created by User on 17/9/2558.
 */
public class SettingAtivity extends AppCompatActivity {
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.home) {
            Intent userIntent = new Intent(this,MainActivity.class);
            startActivity(userIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
