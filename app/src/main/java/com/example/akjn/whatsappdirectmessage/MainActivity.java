package com.example.akjn.whatsappdirectmessage;

import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.view.View;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void contactOnWhatsApp(View v) {
        EditText phoneNumberField = (EditText) findViewById(R.id.inputField);
        String phoneNumber = phoneNumberField.getText().toString();
        int length = phoneNumber.length();
        if (length == 12) {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://api.whatsapp.com/send?phone=" + phoneNumber));
            startActivity(browserIntent);
        }
        else{
            Toast.makeText(this, "Enter mobile number with proper country code",
                    Toast.LENGTH_LONG).show();
        }
    }
}