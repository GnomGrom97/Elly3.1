package com.example.elly31;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import org.jetbrains.annotations.Nullable;

public class AdminPsychShowActivity extends AppCompatActivity {

    private TextView tvName, tvSec_Name, tvEmail, tvThird_Name, tvEducation, tvPassword;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_psych_show);
        init();//инициализация
        getIntentMain();
    }

    private void init() {
        tvName = findViewById(R.id.tvName);
        tvSec_Name = findViewById(R.id.tvSec_Name);
        tvThird_Name = findViewById(R.id.tvThird_Name);
        tvEmail = findViewById(R.id.tvEmail);
        tvEducation = findViewById(R.id.tvEducation);
        tvPassword = findViewById(R.id.tvPassword);
    }

    private void getIntentMain() {//передает текст полученный из бд
        Intent i = getIntent();
        if (i != null) {
            tvName.setText(i.getStringExtra(ConstantPsych.PSYCH_NAME));
            tvSec_Name.setText(i.getStringExtra(ConstantPsych.PSYCH_SEC_NAME));
            tvThird_Name.setText(i.getStringExtra(ConstantPsych.PSYCH_THIRD_NAME));
            tvEmail.setText(i.getStringExtra(ConstantPsych.PSYCH_EMAIL));
            tvEducation.setText(i.getStringExtra(ConstantPsych.PSYCH_EDUCATION));
            tvPassword.setText(i.getStringExtra(ConstantPsych.PSYCH_PASSWORD));

        }
    }
}