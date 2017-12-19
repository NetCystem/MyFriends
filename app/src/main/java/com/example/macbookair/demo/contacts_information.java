package com.example.macbookair.demo;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class contacts_information extends AppCompatActivity {

    private static final int REQUEST_PHONE_CALL = 1;
    Intent intent;
    private TextView contactName;
    private TextView contactPhone;
    private TextView contactEmail;
    private ImageView contactImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts_information);
        contactName = findViewById(R.id.contactName);
        contactPhone = findViewById(R.id.contactPhone);
        contactImage = findViewById(R.id.contact_img);
        contactEmail = findViewById(R.id.contactEmail);

        contactImage.setImageResource(getIntent().getIntExtra("image", 0));
        contactName.setText(getIntent().getStringExtra("name"));
        contactPhone.setText(getIntent().getStringExtra("phone"));
        contactEmail.setText(getIntent().getStringExtra("email"));
    }

    public void makeCall(View v) {

        intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + contactPhone.getText().toString()));

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_PHONE_CALL);
        } else {
            startActivity(intent);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_PHONE_CALL: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    startActivity(intent);
                }

                return;
            }
        }
    }


}
