package com.molivars.notes.views;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.molivars.notes.R;

public class BaseActivity extends AppCompatActivity {
    public static final String INDIGO = "indigo";
    public static final String PINK = "pink";
    public static final String GREEN_LIGHT = "green_light";
    private static final String TAG = BaseActivity.class.getCanonicalName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(getSavedTheme());
    }


    protected void saveTheme(String value) {
        SharedPreferences.Editor editor = getPreferences(Activity.MODE_PRIVATE).edit();
        editor.putString("theme", value);
        editor.commit();
        recreate();
    }

    private int getSavedTheme() {
        String theme = getPreferences(Activity.MODE_PRIVATE).getString("theme", GREEN_LIGHT);
        Log.d(TAG, "hola + " + theme);
        switch (theme) {
            case PINK:
                return R.style.AppTheme_Pink;
            case GREEN_LIGHT:
                return R.style.AppTheme_LightGreen;
            case INDIGO:
            default:
                return R.style.AppTheme_Indigo;
        }
    }

}
