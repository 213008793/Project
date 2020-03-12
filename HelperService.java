package com.example.pc.myapplication;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;

import java.util.List;

/**
 * Created by PC on 7/30/2018.
 */
public class HelperService extends Application
{
    public static final String APP_ID ="68676391-5E8C-1EE2-FF40-308B5C30C300";
    public static final String API_KEY ="6AB23C42-910E-A8FC-FF14-F3B9B4AC4300";
    public static final String SERVER_URL ="https://api.backendless.com";

    public static BackendlessUser user;
    public static List<BackendlessUser> drivers;

    @Override
    public void onCreate() {
        super.onCreate();

        Backendless.setUrl(SERVER_URL);
        Backendless.initApp(getApplicationContext(),APP_ID,API_KEY);


    }

    public static void setEditorAdapter(Context context, AutoCompleteTextView view, String[] dataList)
    {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(context,
                android.R.layout.simple_dropdown_item_1line, dataList);

        view.setAdapter(adapter);
    }

    public static boolean resolveIntent(Intent intent, PackageManager packageManager)
    {
        return intent.resolveActivity(packageManager) != null ? true : false;
    }

    public static void showToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static void setErrorTag(String[] errorTag, View... views)
    {
        for (int i = 0; i < errorTag.length; i++)
        {
            views[i].setTag(errorTag[i]);
        }
    }

    public static void clearViews(View... views) {
        for (View item : views) {
            if (item instanceof EditText) {
                ((EditText) item).setText("");
            } else if (item instanceof RadioButton) {
                ((RadioButton) item).setChecked(false);
            } else if (item instanceof CheckBox) {
                ((CheckBox) item).setChecked(false);
            } else if (item instanceof Spinner) {
                ((Spinner) item).setSelection(0);
            } else if (item instanceof ImageView) {
                ((ImageView) item).setImageResource(android.R.drawable.ic_dialog_info);
            }
        }
    }

    public static boolean hasText(EditText... editTexts)
    {
        int errorCount = 0;

        try
        {
            for (EditText item : editTexts)
            {
                item.setError(null);

                // length 0 means there is no text
                if (getText(item).length() == 0)
                {
                    item.setError(item.getTag().toString());

                    ++errorCount;
                }
            }
        }
        catch (Exception e)
        {
            Log.e(HelperService.class.getName(), e.getMessage());
        }

        return errorCount == 0;
    }

    public static void enableOrDisableViews(boolean flag, View... views)
    {
        for (View view : views)
        {
            view.setEnabled(flag);
        }
    }

    @NonNull
    public static String getText(EditText editText)
    {
        return editText.getText().toString().trim();
    }

    public static void setText(String[] values, EditText... editTexts)
    {
        for (int i = 0; i < values.length; i++)
        {
            editTexts[i].setText(values[i]);
        }
    }



}

