package com.and.ibrahim.teleprompter.util;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.and.ibrahim.teleprompter.R;

public class FabAnimations {

    private static final String TAG="MainActivity";
    private Context mContext;
    private FloatingActionButton mFab,mFabAdd,mFabStorag,mFabCloud;
    private Animation fab_open,fab_close,rotate_forward,rotate_backward;
    private boolean isFab=false;

    public FabAnimations(Context mContext, FloatingActionButton mFab, FloatingActionButton mFabAdd, FloatingActionButton mFabStorag, FloatingActionButton mFabCloud) {
        this.mContext = mContext;
        this.mFab = mFab;
        this.mFabAdd = mFabAdd;
        this.mFabStorag = mFabStorag;
        this.mFabCloud = mFabCloud;
    }

    public void addFabAnimationRes() {
        fab_open = AnimationUtils.loadAnimation(mContext.getApplicationContext(), R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(mContext.getApplicationContext(),R.anim.fab_close);
        rotate_forward = AnimationUtils.loadAnimation(mContext.getApplicationContext(),R.anim.rotate_forward);
        rotate_backward = AnimationUtils.loadAnimation(mContext.getApplicationContext(),R.anim.rotate_backward);

    }

    public void animateFAB(){

        if(isFab){

            mFab.startAnimation(rotate_backward);
            mFabAdd.startAnimation(fab_close);
            mFabStorag.startAnimation(fab_close);
            mFabCloud.startAnimation(fab_close);

            mFabAdd.setClickable(false);
            mFabStorag.setClickable(false);
            mFabCloud.setClickable(false);

            isFab = false;
            Log.d(TAG, "FabAction is "+"close");

        } else {

            mFab.startAnimation(rotate_forward);
            mFabAdd.startAnimation(fab_open);
            mFabStorag.startAnimation(fab_open);
            mFabCloud.startAnimation(fab_open);

            mFabAdd.setClickable(true);
            mFabStorag.setClickable(true);
            mFabCloud.setClickable(true);

            isFab = true;
            Log.d(TAG,"FabAction is "+"open");

        }
    }
}
