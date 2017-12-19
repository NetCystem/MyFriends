package com.example.macbookair.demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private ListAdapter listAdapter;
    private static EditText addedContactName;
    private static EditText addedContactPhone;
    private Button btnSave;

    private int itemPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView);
        addedContactName = findViewById(R.id.addedContactName);
        addedContactPhone = findViewById(R.id.addedContactPhone);
        btnSave = findViewById(R.id.btnSave);

        btnSave.setVisibility(View.INVISIBLE);
        addedContactPhone.setVisibility(View.INVISIBLE);
        addedContactName.setVisibility(View.INVISIBLE);

        final Intent intent = new Intent(MainActivity.this, contacts_information.class);
        listAdapter = new ListAdapter(this);
        listView.setAdapter(listAdapter);
        
        listView.setOnItemClickListener((parent, view, position, id) -> {

            intent.putExtra("name", Contacts.getNames().get(position));
            intent.putExtra("phone", Contacts.getPhone_numbers().get(position));
            intent.putExtra("image", Contacts.getImages().get(position));
            intent.putExtra("email", Contacts.getE_mails().get(position));
            startActivity(intent);
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.addContact) {
            addedContactName.setVisibility(View.VISIBLE);
            addedContactPhone.setVisibility(View.VISIBLE);
            btnSave.setVisibility(View.VISIBLE);

        }

        if (item.getItemId() == R.id.removeContact) {

            listAdapter.delete(itemPosition);
        }

        if (item.getItemId() == R.id.selectContact) {

            listView.setOnItemLongClickListener((parent, view, position, id) -> {
                view.setSelected(true);
                itemPosition = position;
                return true;
            });
        }

        return true;

    }

    public static EditText getAddedContactName() {
        return addedContactName;
    }

    public static EditText getAddedContactPhone() {
        return addedContactPhone;
    }

    public void save(View v) {

        listAdapter.add(getAddedContactName().getText().toString(), getAddedContactPhone().getText().toString());
        btnSave.setVisibility(View.INVISIBLE);
        addedContactPhone.setVisibility(View.INVISIBLE);
        addedContactName.setVisibility(View.INVISIBLE);
    }


}
