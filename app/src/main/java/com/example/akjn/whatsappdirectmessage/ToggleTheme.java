package com.example.akjn.whatsappdirectmessage;

import android.app.Activity;
import android.widget.ToggleButton;

public class ToggleTheme {

    private static int mTheme;

    public static void setmTheme(int theme)
    {
        mTheme = theme;
    }
    public static void setActivityTheme(Activity activity)
    {
        ToggleButton toggleButton = (ToggleButton) activity.findViewById(R.id.toggleBtn);
        if(mTheme==0)
        {
            activity.setTheme(R.style.AppTheme);
        }
        else
        {
            activity.setTheme(R.style.DarkTheme);
        }
    }

    public static int getmTheme() {
        return mTheme;
    }
}
