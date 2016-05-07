package com.example.hayeonlee.appmanager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends Activity {

    Activity act = this;
    GridView gridView;
    private List<ResolveInfo> apps;
    private PackageManager pm;
    ArrayList<String> pkgName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent mainIntent = new Intent(Intent.ACTION_MAIN,null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);

        pm = getPackageManager();
        apps = pm.queryIntentActivities(mainIntent,0);
        pkgName = new ArrayList<String>();

        setContentView(R.layout.activity_main);

        gridView = (GridView) findViewById(R.id.gridView1);
        gridView.setAdapter(new gridAdapter());

        Button button = (Button)findViewById(R.id.Button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(act.getApplicationContext(),Result.class);
                intent.putStringArrayListExtra("pkgName_list",pkgName);
                act.startActivity(intent);
            }
        });
    }

    public class gridAdapter extends BaseAdapter {
        LayoutInflater inflater;

        public gridAdapter(){
            inflater = (LayoutInflater) act.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        public final int getCount(){
            return apps.size();
        }

        public final Object getItem(int position){
            return apps.get(position);
        }

        public final long getItemId(int position){
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.item, parent, false);
            }

            final ResolveInfo info = apps.get(position);

            ImageView imageView = (ImageView) convertView.findViewById(R.id.imageView1);
            TextView textView = (TextView) convertView.findViewById(R.id.textView1);
            imageView.setImageDrawable(info.activityInfo.loadIcon(getPackageManager()));
            textView.setText(info.activityInfo.loadLabel(pm).toString());
            CheckBox checkBox = (CheckBox) convertView.findViewById(R.id.checkbox1);

            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                String msg = info.activityInfo.packageName.toString();
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                    if (compoundButton.getId() == R.id.checkbox1) {
                        if (isChecked) {
                            Toast.makeText(act,msg,Toast.LENGTH_SHORT).show();
                            pkgName.add(msg);
                        }
                    }
                }
            });

            return convertView;
        }
    }
}
