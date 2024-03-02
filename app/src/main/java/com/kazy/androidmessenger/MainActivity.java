package com.kazy.androidmessenger;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.Firebase;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_READ_CONTACTS = 1;
    private static boolean READ_CONTACTS_GRANTED = false;
    private String[] namesArr = new String[]{"Askar", "Askar", "Askar", "Askar", "Askar", "Askar", "Askar", "Askar", "Askar", "Askar", "Askar", "Askar", "Askar", "Askar", "Askar", "Askar", "Askar", "Askar"};
    private ListView listView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, namesArr);
        listView.setAdapter(adapter);


        if (FirebaseAuth.getInstance().getCurrentUser() == null) {
            startActivity(new Intent(MainActivity.this, login.class));
            finish();
        } else {
            Toast.makeText(getApplicationContext(), "Вы успешно вошли в программу", Toast.LENGTH_SHORT).show();
        }
    }
}