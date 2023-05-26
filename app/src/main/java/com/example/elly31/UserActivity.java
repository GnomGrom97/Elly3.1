package com.example.elly31;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**Активити пациента**/
public class UserActivity extends AppCompatActivity {
    private Button message, settings, exit, Events, consultations;
    TextView tvUserName;
    ImageView UserImage;
    private FirebaseAuth mAuth;


    @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        init();
    }

    private void init() {
        message = findViewById(R.id.message);
        settings = findViewById(R.id.settings);
        exit = findViewById(R.id.exit);
        Events = findViewById(R.id.Events);
        consultations = findViewById(R.id.consultations);
        mAuth = FirebaseAuth.getInstance();
        tvUserName=findViewById(R.id.tvUserName);
        UserImage =findViewById(R.id.UserImage);
    }
/** cообщения от психологов */
    public void onClickEvents(View view){
    }
    public void onClickMessages(View view){}
    public void onClickSettings(View view){}
    public void onClickSignOut(View view){}
    public  void onClickConsultation(View view){}

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser cUser = mAuth.getCurrentUser();
        if (cUser != null) {//если пользователь вошел
            //здесь нужно сделать разделение на запуск активити в зависимости от вошедшего пользователя

            String userName = "Вы вошли как:" + cUser.getEmail();
            tvUserName.setText(userName);
            //если пользователь не 0 то покажем окно приложения
            Toast.makeText(this, "Пользователи есть", Toast.LENGTH_SHORT).show();
        } else {//если не вошел

            Toast.makeText(this, "Пользователей нет ", Toast.LENGTH_SHORT).show();
        }
    }
}