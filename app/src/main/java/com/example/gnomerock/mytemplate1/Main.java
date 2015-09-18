package com.example.gnomerock.mytemplate1;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

/**
 * Created by User on 17/9/2558.
 */
public class Main extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);
    }
}