package com.example.elly31;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import org.jetbrains.annotations.Nullable;

public class ShowActivity extends AppCompatActivity {
    private TextView tvName, tvSec_Name, tvEmail;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        init();//инициализация
        getIntentMain();
    }
    private void init() {
        tvName = findViewById(R.id.tvName);
        tvSec_Name = findViewById(R.id.tvSecName);
        tvEmail = findViewById(R.id.tvEmail);
    }
    private void getIntentMain() {//передает текст полученный из бд
        Intent i = getIntent();
        if (i != null) {
            tvName.setText(i.getStringExtra(Constant.USER_NAME));
            tvSec_Name.setText(i.getStringExtra(Constant.USER_SEC_NAME));
            tvEmail.setText(i.getStringExtra(Constant.USER_EMAIL));

        }
    }
}