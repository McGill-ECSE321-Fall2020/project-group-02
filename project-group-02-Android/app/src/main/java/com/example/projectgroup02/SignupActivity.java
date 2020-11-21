package com.example.projectgroup02;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class SignupActivity extends AppCompatActivity {
    private Toolbar toolbar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_page);


        toolbar=findViewById(R.id.GalleryHeader);
        setSupportActionBar(toolbar);
    }

}
