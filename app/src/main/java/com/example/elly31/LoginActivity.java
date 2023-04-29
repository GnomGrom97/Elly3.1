package com.example.elly31;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity {
    Button BtnGuest,BtnPsych,BtnAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }
    private   void init(){
        BtnGuest = findViewById(R.id.GuestBtn);
        BtnPsych= findViewById(R.id.GuestBtn);
        BtnAdmin=findViewById(R.id.BtnAdmin);
    }
    public  void OnClickBtnGuest(View view){
        Intent intent = new Intent(LoginActivity.this,GuestRegActivity.class);
        startActivity(intent);
    }
    public  void OnClickBtnPsych(View view){
        Intent intent = new Intent(LoginActivity.this,MainActivityPsych.class);
        startActivity(intent);
    }
    public  void OnClickBtnAdmin(View view){
        Intent intent = new Intent(LoginActivity.this,AdminActivity.class);
        startActivity(intent);
    }
    }
