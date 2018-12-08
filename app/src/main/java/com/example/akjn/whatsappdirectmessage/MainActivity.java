package com.example.akjn.whatsappdirectmessage;

import android.content.Intent;
import android.content.res.Resources;
import android.util.DisplayMetrics;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.widget.ArrayAdapter;
import android.view.Menu;
import android.view.MenuInflater;



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

import com.hbb20.CountryCodePicker;

public class MainActivity extends AppCompatActivity {
    Spinner spinner;
    Locale myLocale;
    String currentLanguage = "en", currentLang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        currentLanguage = getIntent().getStringExtra(currentLang);
        spinner = (Spinner) findViewById(R.id.spinner);
        List<String> list = new ArrayList<String>();
        list.add("Select Language");
        list.add("English");
        list.add("Hindi");
        list.add("Bengali");
        list.add("French");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        break;
                    case 1:
                        setLocale("en");
                        break;
                    case 2:
                        setLocale("hi");
                        break;
                    case 3:
                        setLocale("bng");
                        break;
                    case 4:
                        setLocale("fr");
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }


    public void setLocale(String localeName) {

        myLocale = new Locale(localeName);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
        Intent refresh = new Intent(this, MainActivity.class);
        refresh.putExtra(currentLang, localeName);
        startActivity(refresh);

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

    public void clearNumber(View view) {
        EditText hello = (EditText)findViewById(R.id.inputField);
        hello.setText("");
    }
}
