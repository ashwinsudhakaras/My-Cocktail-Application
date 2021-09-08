package com.ashwinsudhakar.mycocktailapplication.ui.customViews;

import android.app.Activity;
import android.graphics.Typeface;

import com.ashwinsudhakar.mycocktailapplication.R;
import com.marcoscg.dialogsheet.DialogSheet;


public class MyDialogSheet extends DialogSheet {
    Activity activity;

    public MyDialogSheet(Activity activity) {
        super(activity);
        this.activity = activity;
        setTitleTypeface(Typeface.createFromAsset(activity.getAssets(), "fonts/roboto_bold.ttf"));
        setMessageTypeface(Typeface.createFromAsset(activity.getAssets(), "fonts/roboto_regular.ttf"));
        setButtonsColorRes(R.color.colorSecondary);
        setRoundedCorners(true);
        setColoredNavigationBar(true);
    }
}
