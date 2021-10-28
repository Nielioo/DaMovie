package com.fei.damovie.helper;

import android.app.Activity;
import android.app.Dialog;
import android.view.Window;

import com.fei.damovie.R;

public class ProgressBarGoogle {

    private Activity activity;
    private Dialog dialog;

    public ProgressBarGoogle(Activity activity){this.activity=activity;}

    public void startProgress(){
        dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.google_progress_bar);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    public void stopProgress(){
        dialog.dismiss();
    }



}
