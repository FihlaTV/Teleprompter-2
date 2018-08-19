package com.and.ibrahim.teleprompter.modules.splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.and.ibrahim.teleprompter.R;
import com.and.ibrahim.teleprompter.modules.display.DisplayActivity;
import com.and.ibrahim.teleprompter.modules.listContents.ListContentsActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent mIntent;
        boolean isTablet = getResources().getBoolean(R.bool.isTablet);


        if (isTablet) {
            mIntent = new Intent(this, DisplayActivity.class);
        } else {
            mIntent = new Intent(this, ListContentsActivity.class);

        }
        startActivity(mIntent);
        finish();
    }

}