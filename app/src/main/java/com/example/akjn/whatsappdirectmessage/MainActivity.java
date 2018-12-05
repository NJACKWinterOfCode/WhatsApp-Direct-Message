package com.example.akjn.whatsappdirectmessage;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.view.View;
import android.content.Intent;
import android.net.Uri;
import android.widget.Switch;

import com.hbb20.CountryCodePicker;

public class MainActivity extends AppCompatActivity {
Switch s;
boolean isEnabled1=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
       // AppCompatDelegate.setDefaultNightMode(
         //       AppCompatDelegate.MODE_NIGHT_YES);
            Intent intent = getIntent();
            boolean b1=intent.getBooleanExtra("state",false);
            if(b1)
            {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

            }
            else
            {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        s=(Switch)findViewById(R.id.switch1);
            
        s.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {

                    isEnabled1=true;
                    restartApp();
                }else
                {
                    isEnabled1=false;
                restartApp();}
            }
        });
    }

public void restartApp()
{
    Intent i=new Intent(getApplicationContext(),MainActivity.class);
    i.putExtra("state",isEnabled1);
    startActivity(i);
    finish();

}
    public void contactOnWhatsApp(View v)
    {
        EditText phoneNumberField = (EditText)findViewById(R.id.inputField);

//  CODE FOR COUNTRY CODE SPINNER

        CountryCodePicker cpp=(CountryCodePicker)findViewById(R.id.cpp);
        cpp.registerCarrierNumberEditText(phoneNumberField);
        String phoneNumber = cpp.getFullNumber();


        //Toast.makeText(MainActivity.this,phoneNumber,Toast.LENGTH_LONG).show();


        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://api.whatsapp.com/send?phone="+phoneNumber));
        startActivity(browserIntent);
    }
}
