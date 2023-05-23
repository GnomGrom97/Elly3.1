package com.example.elly31;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ChoiceActivity extends AppCompatActivity {
    private Button btnAdmin,  btnPsych,btnUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);
        init();
    }
    private void init() {
        btnAdmin=findViewById(R.id.bntAdmin);
        btnPsych=findViewById(R.id.btnPsych);
        btnUser=findViewById(R.id.btnUser);
    }
    //пока поставил новый юзер
    public void onClickAdmin(View view){
       Intent intent = new Intent(ChoiceActivity.this,AdminLoginActivity.class);
       startActivity(intent);
    }
    public void onClickPsych(View view){
        Intent intent = new Intent(ChoiceActivity.this,MainActivityPsych.class);
        startActivity(intent);

    }
    public void onClickUser(View view){
        Intent intent = new Intent(ChoiceActivity.this,MainActivity.class);
        startActivity(intent);

    }
}