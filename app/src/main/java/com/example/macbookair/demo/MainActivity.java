package com.example.macbookair.demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static EditText addedContactName;
    private static EditText addedContactPhone;
    private ListView listView;
    private ListAdapter listAdapter;
    private Button btnSave;
    private List<Integer> checkedListPositions = new ArrayList<>();
    private int count = 0;

    public static EditText getAddedContactName() {
        return addedContactName;
    }

    public static EditText getAddedContactPhone() {
        return addedContactPhone;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView);
        addedContactName = findViewById(R.id.addedContactName);
        addedContactPhone = findViewById(R.id.addedContactPhone);
        btnSave = findViewById(R.id.btnSave);
        listView.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE_MODAL);

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


        listView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
            @Override
            public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {
                listView.setSelected(true);
                Animation animation = new AlphaAnimation(0.3f, 1.0f);
                animation.setDuration(800);
                listView.startAnimation(animation);
                count++;
                checkedListPositions.add(position);
                mode.setTitle(count + " Items Selected");
            }

            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {

                MenuInflater menuInflater = mode.getMenuInflater();
                menuInflater.inflate(R.menu.my_menu, menu);
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                if (item.getItemId() == R.id.removeContact) {
                    for (int i : checkedListPositions
                            ) {
                        listAdapter.delete(i);
                    }
                }

                Toast.makeText(MainActivity.this, count + " Items Removed", Toast.LENGTH_SHORT).show();
                checkedListPositions.clear();
                count = 0;
                mode.finish();

                return true;
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {
                count = 0;
                checkedListPositions.clear();
            }
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

        if (item.getItemId() == R.id.removeAll) {

            for (int i = 0; i < listAdapter.getCount(); i++) {

                listView.setItemChecked(i, false);
            }

            SparseBooleanArray sparseBooleanArray = listView.getCheckedItemPositions();
            for (int i = 0; i < sparseBooleanArray.size(); i++) {

                listAdapter.delete(i);
            }

            Toast.makeText(MainActivity.this, "All items have been removed", Toast.LENGTH_SHORT).show();
        }

        return true;

    }

    public void save(View v) {

        listAdapter.add(getAddedContactName().getText().toString(), getAddedContactPhone().getText().toString());
        btnSave.setVisibility(View.INVISIBLE);
        addedContactPhone.setVisibility(View.INVISIBLE);
        addedContactName.setVisibility(View.INVISIBLE);
    }


}
