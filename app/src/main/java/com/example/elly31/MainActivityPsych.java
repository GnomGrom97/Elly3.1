package com.example.elly31;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivityPsych extends AppCompatActivity {
    /**Активити регистации психолога**/

    private EditText edName, edSecondName, edEmail, edThreeName, edEducation,edPassword;
    //используется myDataBase
    private DatabaseReference myDatabase;//ссылка на объект бд
    private String PSYCH_KEY = "Psych";
    private Button DownloadBtn, NextBt, GuestBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_psych);
        init();
    }

    private void init() { //void ничего не возвращает
        edName = findViewById(R.id.edName);
        edSecondName = findViewById(R.id.edSecondName);
        edThreeName = findViewById(R.id.edThreeName);
        edEmail = findViewById(R.id.edEmail);
        edEducation = findViewById(R.id.edEducation);
        edPassword = findViewById(R.id.edPassword);
        DownloadBtn = findViewById(R.id.DownloadBtn);
        NextBt = findViewById(R.id.nextBt);
        GuestBtn = findViewById(R.id.GuestBtn);
        myDatabase = FirebaseDatabase.getInstance().getReference(PSYCH_KEY);
    }

    public void onClickSave(View view) {//метод для создания пользователя
        String id = myDatabase.getKey();//получение значения из поля
        String name = edName.getText().toString();//получение текста и превращение в
        String sec_name = edSecondName.getText().toString();//получение текста и превращение в строку
        String third_name = edThreeName.getText().toString();
        String education = edEducation.getText().toString();
        String email = edEmail.getText().toString();//получение текста и превращение в строку
        String password = edPassword.getText().toString();
        Psych newPsych = new Psych(id, name, sec_name, third_name, education, email,password);
        if (!TextUtils.isEmpty(name) &&
                !TextUtils.isEmpty(sec_name) &&
                !TextUtils.isEmpty(third_name)&&
                !TextUtils.isEmpty(email) &&
                !TextUtils.isEmpty(education)&&
                !TextUtils.isEmpty(password)) {
        //проверка на пустое поле
        myDatabase.push().setValue(newPsych);//отправить информацию о бд -создание пользователя
          Toast.makeText(this, "Пользователь успешно добавлен", Toast.LENGTH_SHORT).show();
        } else {//выдает сообщение о пустом поле
           Toast.makeText(this, "Пустое поле", Toast.LENGTH_SHORT).show();
        }
    }
    //метод для загрузки образования в профиль
    public void OnClickDownloadEducation (View view){
        Intent intent =new Intent(MainActivityPsych.this,ReadActivity.class);
        startActivity(intent);
//запуск активити с инфой из бд
    }
    public void OnClickSpecializaion(View view){
        Intent intent = new Intent(MainActivityPsych.this,SpecializationActivity.class);
        startActivity(intent);
    }

}
