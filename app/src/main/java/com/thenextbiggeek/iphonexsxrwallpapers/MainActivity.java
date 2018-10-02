package com.thenextbiggeek.iphonexsxrwallpapers;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class MainActivity extends AppCompatActivity {

    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkForPermissions();
        // Sample AdMob app ID: ca-app-pub-3940256099942544~3347511713
        MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713");
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }

    int requestCode = 30;
    private void checkForPermissions() {
        PackageManager packageManager = this.getPackageManager();
        int hasPerm = packageManager.checkPermission(
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                this.getPackageName());
        if (hasPerm != PackageManager.PERMISSION_GRANTED) {
            // do stuff request permissions
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, requestCode);
        }else{
        }

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults){
        if(this.requestCode == requestCode){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                //yay. permission granted
                //Toast.makeText(this, "Permission Granted", Toast.LENGTH_LONG).show();
            }else{
              //TODO TELL THE USER OF THE CONSEQUENCe
                Toast.makeText(this, "External storage access is necessary to set wallpaper", Toast.LENGTH_LONG).show();

            }
        }
    }

    public void selectDevice(View v){
        int view = v.getId();
        Intent mIntent;

        //flag 0 = xs 1 = xs max 2= xr 3 = x
        switch (view){
            case R.id.iphonexs:
                mIntent = new Intent(MainActivity.this, SelectorActivity.class);
                mIntent.putExtra("FLAG_DEVICE", 0);
                startActivity(mIntent);
                break;
            case R.id.iphonexsmax:
                 mIntent = new Intent(MainActivity.this, SelectorActivity.class);
                mIntent.putExtra("FLAG_DEVICE", 1);
                startActivity(mIntent);
                break;
            case R.id.iphonexr:
                 mIntent = new Intent(MainActivity.this, SelectorActivity.class);
                mIntent.putExtra("FLAG_DEVICE", 2);
                startActivity(mIntent);
                break;
            case R.id.iphonex:
                 mIntent = new Intent(MainActivity.this, SelectorActivity.class);
                mIntent.putExtra("FLAG_DEVICE", 3);
                startActivity(mIntent);
                break;
        }

    }


}
