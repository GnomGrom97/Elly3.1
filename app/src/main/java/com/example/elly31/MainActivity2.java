package com.example.elly31;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity2 extends AppCompatActivity {
    private EditText edName, edSecName, edEmail;
    private DatabaseReference myDataBase;
    //регистрация для админа -фактически не нужна
    private String PSY_KEY = "ADMIN";//значение в скобках отвечает за наименование доступа

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        init();
    }
    private  void init(){
        edName =findViewById(R.id.edName);
        edSecName =findViewById(R.id.edSecName);
        edEmail =findViewById(R.id.edEmail);
        myDataBase = FirebaseDatabase.getInstance().getReference(PSY_KEY);
    }
    public  void onClickSave1(View view){
        String id=myDataBase.getKey();
        String name=edName.getText().toString();
        String sec_name=edSecName.getText().toString();
        String email=edEmail.getText().toString();
        User newUser =new User(id,name,sec_name,email);//создание пользователя с атрибутами в ()
        //
        if     (!TextUtils.isEmpty(name) &&
                !TextUtils.isEmpty(sec_name) &&
                !TextUtils.isEmpty(email)){

            myDataBase.push().setValue(newUser);//отправить информацию о бд -создание пользователя
            Toast.makeText(this, "Пользователь успешно добавлен", Toast.LENGTH_SHORT).show();
        } else {//выдает сообщение о пустом поле
            Toast.makeText(this, "Пустое поле", Toast.LENGTH_SHORT).show();
        }
        //
        //myDataBase.push().setValue(newUser);

    }
    public  void onClickRead1(View view){

    }
}