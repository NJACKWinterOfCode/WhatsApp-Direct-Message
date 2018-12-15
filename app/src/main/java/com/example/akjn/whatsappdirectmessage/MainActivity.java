package com.example.akjn.whatsappdirectmessage;

import android.content.Intent;
import android.content.res.Resources;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatDelegate;
import android.util.DisplayMetrics;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;

import android.widget.ArrayAdapter;
import android.view.Menu;
import android.view.MenuInflater;

import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import android.widget.EditText;
import android.view.View;
import android.content.Intent;
import android.net.Uri;
import android.widget.AdapterView;
import android.widget.Spinner;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ToggleButton;

import com.hbb20.CountryCodePicker;

public class MainActivity extends AppCompatActivity {
    Spinner spinner;
    private EditText mPhoneNumber = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

//        if(AppCompatDelegate.getDefaultNightMode()==AppCompatDelegate.MODE_NIGHT_YES)
//        {
//            setTheme(R.style.DarkTheme);
//        }
//        else
//            setTheme(R.style.AppTheme);

        ToggleTheme.setActivityTheme(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ToggleButton toggle = (ToggleButton) findViewById(R.id.toggleBtn);
        if(ToggleTheme.getmTheme()==0)
        {
            toggle.setText(R.string.off);
        }
        else
        {
            toggle.setText(R.string.on);
        }
        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(ToggleTheme.getmTheme()==0)
                {
                    ToggleTheme.setmTheme(1);
                }
                else
                {
                    ToggleTheme.setmTheme(0);
                }
                MainActivity.this.finish();
                MainActivity.this.startActivity(new Intent(MainActivity.this, MainActivity.this.getClass()));

            }
        });


    }

        @Override
        public boolean onCreateOptionsMenu(Menu menu)
        {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.action_menu, menu);
            return true;
        }
    public void contactOnWhatsApp (View v){

            EditText phoneNumberField = (EditText) findViewById(R.id.inputField);


            //  CODE FOR COUNTRY CODE SPINNER

            CountryCodePicker cpp = (CountryCodePicker) findViewById(R.id.cpp);
            cpp.registerCarrierNumberEditText(phoneNumberField);
            String phoneNumber = cpp.getFullNumber();


            //Toast.makeText(MainActivity.this,phoneNumber,Toast.LENGTH_LONG).show();


            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://api.whatsapp.com/send?phone=" + phoneNumber));
            startActivity(browserIntent);
        }

    //Creating the Actions for the menu items
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.help:
                Intent intent = new Intent(MainActivity.this, help.class);
                startActivity(intent);
                break;
            case R.id.dark_mode:
                //add code here
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                Intent i=new Intent(MainActivity.this,MainActivity.class);
                startActivity(i);
                finish();
                return true;

            case R.id.lightmode:
                //add code here
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                Intent ins=new Intent(MainActivity.this,MainActivity.class);
                startActivity(ins);
                finish();
                return true;


            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    //Function to save contact
    public void SaveContact (View v) {

        Intent intent = new Intent(ContactsContract.Intents.Insert.ACTION);
        intent.setType(ContactsContract.RawContacts.CONTENT_TYPE);
        mPhoneNumber= (EditText) findViewById(R.id.inputField);

        CountryCodePicker cpp = (CountryCodePicker) findViewById(R.id.cpp);
        String mNo= cpp.getFullNumberWithPlus() + mPhoneNumber.getText();
        intent.putExtra(ContactsContract.Intents.Insert.PHONE, mNo);

        startActivity(intent);

    }


    //Function to clear numbers
    public void clearNumber(View view) {
        EditText hello = (EditText)findViewById(R.id.inputField);
        hello.setText("");
    }
}

