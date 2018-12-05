package com.example.akjn.whatsappdirectmessage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.EditText;
import android.view.View;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;
import com.hbb20.CountryCodePicker;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_menu, menu);
        return true;
    }

    EditText phoneNumberField;

    public void contactOnWhatsApp(View v) {
        phoneNumberField = (EditText)findViewById(R.id.inputField);
        String phNumber = phoneNumberField.getText().toString();
        if (phNumber.length() == 10 || phNumber.length() == 12) {
            numberIsCorrect();
        } else {
            Toast.makeText(MainActivity.this, "Please enter a correct number", Toast.LENGTH_SHORT).show();
        }
    }

    public void numberIsCorrect(){

//  CODE FOR COUNTRY CODE SPINNER

        CountryCodePicker cpp=(CountryCodePicker)findViewById(R.id.cpp);
        cpp.registerCarrierNumberEditText(phoneNumberField);
        String phoneNumber = cpp.getFullNumber();


        //Toast.makeText(MainActivity.this,phoneNumber,Toast.LENGTH_LONG).show();


        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://api.whatsapp.com/send?phone="+phoneNumber));
        startActivity(browserIntent);

        phoneNumberField.setText("");
    }
}