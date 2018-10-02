package com.thenextbiggeek.iphonexsxrwallpapers;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import java.lang.reflect.Field;

public class SelectorActivity extends AppCompatActivity {

    ListView mListView;
    int deviceFlag;
    int resId[];
    String namelist[];
    Context context;
    int a1 = 3, a2 = 3,  a3=12;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selector);
        context = getApplicationContext();


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            deviceFlag = extras.getInt("FLAG_DEVICE", 0);
        }

        initializeResId();



        mListView = (ListView) findViewById(R.id.listView);
        ListAdapter mListAdapter = new ListAdapter(resId, SelectorActivity.this, context);
        mListView.setAdapter(mListAdapter);


        // Click event for single list row
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //pass to finalActivity with the resId and then kaboom
                Intent mIntent = new Intent(SelectorActivity.this, FinalActivity.class);
                mIntent.putExtra("RES_ID", resId[position]);
                startActivity(mIntent);
            }
        });


    }


    //resId will be into the array which can be passed onto the adapter
    private void initializeResId() {
        switch(deviceFlag){
            case 0:
                //XS
                resId = new int[a1];
                namelist = new String[a1];
                for(int i =0; i <a1; i++){
                    namelist[i] = "xs"+(i+1);
                    resId[i] = getResId( namelist[i], R.drawable.class);
                }
                break;
            case 1:
                //XSMax
                resId = new int[a2];
                namelist = new String[a2];
                for(int i =0; i <a2; i++){
                    namelist[i] = "x"+(i+1);
                    resId[i] = getResId( namelist[i], R.drawable.class);
                }
                break;
            case 2:
                //Xr
                resId = new int[a3];
                namelist = new String[a3];
                for(int i =0; i <a3; i++){
                    namelist[i] = "xr"+(i+1);
                    resId[i] = getResId( namelist[i], R.drawable.class);
                }
                break;
            case 3:
                //TODO GO DIRECT TO THE FINALACTIVITY WITH THE WALLPAPER RESOURCE
                resId = new int[1];
                namelist = new String[1];
                for(int i =0; i <1; i++){
                    namelist[i] = "x"+(0);
                    resId[i] = getResId( namelist[i], R.drawable.class);
                }
                break;

        }

    }

    public static int getResId(String resName, Class<?> c) {

        try {
            Field idField = c.getDeclaredField(resName);
            return idField.getInt(idField);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public void cancel(View view) {
        Intent mIntent = new Intent(SelectorActivity.this, MainActivity.class);
        startActivity(mIntent);
    }
}
