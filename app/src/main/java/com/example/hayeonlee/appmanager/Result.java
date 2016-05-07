package com.example.hayeonlee.appmanager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by hayeonlee on 16. 5. 7.
 */
public class Result extends Activity {

    ArrayList<String> pkgName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pkgName = new ArrayList<String>();
        setContentView(R.layout.result);

        Intent intent = getIntent();
        pkgName = intent.getStringArrayListExtra("pkgName_list");
        TextView tv = (TextView) findViewById(R.id.textView);

        for (int i = 0; i < pkgName.size(); i++) {
            tv.append(pkgName.get(i) + "\n");
        }
    }
}