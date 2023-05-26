package com.example.elly31;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
/**Активити с регистрацией пользователя*/
public class MainActivity extends AppCompatActivity {
    //используется myDataBase
    private DatabaseReference myDataBase;
    private EditText edName2, edSecName, edEmail2;
    private String ADMIN_KEY = "User";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDataBase = FirebaseDatabase.getInstance().getReference(ADMIN_KEY);
        init();
    }

    private void init() {
        edName2 = findViewById(R.id.edName1);
        edSecName = findViewById(R.id.edSecName1);
        edEmail2 = findViewById(R.id.edEmail1);
        myDataBase = FirebaseDatabase.getInstance().getReference(ADMIN_KEY);
    }

    public void OnClickSave(View view) {
        String id2 = myDataBase.getKey();//получение порядкового номера пользователя из
        String name2 = edName2.getText().toString();
        String sec_name2 = edSecName.getText().toString();
        //здесь нужно добавить регистрацию по номеру телефона для пользователя
        //с передачейй данных на сервер
        String email2 = edEmail2.getText().toString();
        User2 newUser = new User2(id2, name2, sec_name2, email2); //создание пользователя с атрибутами в ()
        if     (!TextUtils.isEmpty(name2) &&
                !TextUtils.isEmpty(sec_name2) &&
                !TextUtils.isEmpty(email2)){

        myDataBase.push().setValue(newUser);//отправить информацию о бд -создание пользователя
        Toast.makeText(this, "Ожидайте кода с для подтверждения регистрации", Toast.LENGTH_SHORT).show();
        } else {//выдает сообщение о пустом поле
            Toast.makeText(this, "Пустое поле", Toast.LENGTH_SHORT).show();
    }
    }
    public void OnClickRead(View view) {
        Intent i = new Intent(MainActivity.this,ReadActivity.class);
        startActivity(i);
    }

}
