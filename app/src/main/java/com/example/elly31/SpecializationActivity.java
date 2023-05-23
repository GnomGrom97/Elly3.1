package com.example.elly31;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.sql.Time;

public class SpecializationActivity extends AppCompatActivity {
    /** активити с выбором специализации**/
private Button button1,button2,button3,button4,button5,button6,button7,button8,button9,ReadyBtn;
private TextView textView1,textView2,textView3,textView4,textView5,textView6,textView7,textView8,textView9;
private  String spec;

    FirebaseDatabase myDataBase = FirebaseDatabase.getInstance();
    DatabaseReference myRef = myDataBase.getReference("message");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specialization);
        init();
    }
    private  void init(){
        //кнопки
        button1=findViewById(R.id.button1);
        button2=findViewById(R.id.button2);
        button3=findViewById(R.id.button3);
        button4=findViewById(R.id.button4);
        button5=findViewById(R.id.button5);
        button6=findViewById(R.id.button6);
        button7=findViewById(R.id.button7);
        button8=findViewById(R.id.button8);
        button9=findViewById(R.id.button9);
        ReadyBtn=findViewById(R.id.ReadyBtn);
        //текст
        textView1=findViewById(R.id.textView1);
        textView2=findViewById(R.id.textView2);
        textView3=findViewById(R.id.textView3);
        textView4=findViewById(R.id.textView4);
        textView5=findViewById(R.id.textView5);
        textView6=findViewById(R.id.textView6);
        textView7=findViewById(R.id.textView7);
        textView8=findViewById(R.id.textView8);
        textView9=findViewById(R.id.textView9);
    }

    public  void OnClick1(View view){
    button1.setBackgroundResource(R.drawable.ok);
    button2.setBackgroundResource(R.drawable.empty);
    button3.setBackgroundResource(R.drawable.empty);
    button4.setBackgroundResource(R.drawable.empty);
    button5.setBackgroundResource(R.drawable.empty);
    button6.setBackgroundResource(R.drawable.empty);
    button7.setBackgroundResource(R.drawable.empty);
    button8.setBackgroundResource(R.drawable.empty);
    button9.setBackgroundResource(R.drawable.empty);
        myRef.setValue(textView1.getText().toString());
    //создать текстовую переменную с названием

    //при выборе данного метода должна отправляться информация из переменой текст должна отправляться на сервер


    }
    public  void OnClick2(View view){
        button1.setBackgroundResource(R.drawable.empty);
        button2.setBackgroundResource(R.drawable.ok);
        button3.setBackgroundResource(R.drawable.empty);
        button4.setBackgroundResource(R.drawable.empty);
        button5.setBackgroundResource(R.drawable.empty);
        button6.setBackgroundResource(R.drawable.empty);
        button7.setBackgroundResource(R.drawable.empty);
        button8.setBackgroundResource(R.drawable.empty);
        button9.setBackgroundResource(R.drawable.empty);
        myRef.setValue(textView2.getText().toString());

    }
    public  void OnClick3(View view){
        button1.setBackgroundResource(R.drawable.empty);
        button2.setBackgroundResource(R.drawable.empty);
        button3.setBackgroundResource(R.drawable.ok);
        button4.setBackgroundResource(R.drawable.empty);
        button5.setBackgroundResource(R.drawable.empty);
        button6.setBackgroundResource(R.drawable.empty);
        button7.setBackgroundResource(R.drawable.empty);
        button8.setBackgroundResource(R.drawable.empty);
        button9.setBackgroundResource(R.drawable.empty);
        myRef.setValue(textView3.getText().toString());

    }
    public  void OnClick4(View view){
        button1.setBackgroundResource(R.drawable.empty);
        button2.setBackgroundResource(R.drawable.empty);
        button3.setBackgroundResource(R.drawable.empty);
        button4.setBackgroundResource(R.drawable.ok);
        button5.setBackgroundResource(R.drawable.empty);
        button6.setBackgroundResource(R.drawable.empty);
        button7.setBackgroundResource(R.drawable.empty);
        button8.setBackgroundResource(R.drawable.empty);
        button9.setBackgroundResource(R.drawable.empty);
        myRef.setValue(textView4.getText().toString());

    }
    public  void OnClick5(View view){
        button1.setBackgroundResource(R.drawable.empty);
        button2.setBackgroundResource(R.drawable.empty);
        button3.setBackgroundResource(R.drawable.empty);
        button4.setBackgroundResource(R.drawable.empty);
        button5.setBackgroundResource(R.drawable.ok);
        button6.setBackgroundResource(R.drawable.empty);
        button7.setBackgroundResource(R.drawable.empty);
        button8.setBackgroundResource(R.drawable.empty);
        button9.setBackgroundResource(R.drawable.empty);
        myRef.setValue(textView5.getText().toString());
    }
    public  void OnClick6(View view){
        button1.setBackgroundResource(R.drawable.empty);
        button2.setBackgroundResource(R.drawable.empty);
        button3.setBackgroundResource(R.drawable.empty);
        button4.setBackgroundResource(R.drawable.empty);
        button5.setBackgroundResource(R.drawable.empty);
        button6.setBackgroundResource(R.drawable.ok);
        button7.setBackgroundResource(R.drawable.empty);
        button8.setBackgroundResource(R.drawable.empty);
        button9.setBackgroundResource(R.drawable.empty);
        myRef.setValue(textView6.getText().toString());
    }
    public  void OnClick7(View view){
        button1.setBackgroundResource(R.drawable.empty);
        button2.setBackgroundResource(R.drawable.empty);
        button3.setBackgroundResource(R.drawable.empty);
        button4.setBackgroundResource(R.drawable.empty);
        button5.setBackgroundResource(R.drawable.empty);
        button6.setBackgroundResource(R.drawable.empty);
        button7.setBackgroundResource(R.drawable.ok);
        button8.setBackgroundResource(R.drawable.empty);
        button9.setBackgroundResource(R.drawable.empty);
        myRef.setValue(textView7.getText().toString());
    }
    public  void OnClick8(View view){
        button1.setBackgroundResource(R.drawable.empty);
        button2.setBackgroundResource(R.drawable.empty);
        button3.setBackgroundResource(R.drawable.empty);
        button4.setBackgroundResource(R.drawable.empty);
        button5.setBackgroundResource(R.drawable.empty);
        button6.setBackgroundResource(R.drawable.empty);
        button7.setBackgroundResource(R.drawable.empty);
        button8.setBackgroundResource(R.drawable.ok);
        button9.setBackgroundResource(R.drawable.empty);
        myRef.setValue(textView8.getText().toString());
    }
    public  void OnClick9(View view){
        button1.setBackgroundResource(R.drawable.empty);
        button2.setBackgroundResource(R.drawable.empty);
        button3.setBackgroundResource(R.drawable.empty);
        button4.setBackgroundResource(R.drawable.empty);
        button5.setBackgroundResource(R.drawable.empty);
        button6.setBackgroundResource(R.drawable.empty);
        button7.setBackgroundResource(R.drawable.empty);
        button8.setBackgroundResource(R.drawable.empty);
        button9.setBackgroundResource(R.drawable.ok);
        myRef.setValue(textView9.getText().toString());


    }
    public   void onClickReady(View view){
        Toast.makeText(this,"Регистрация прошла успешно.Ожидате подтвеждения на почте",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(SpecializationActivity.this,LoginActivity.class);
        startActivity(intent);
        //получение вида психологии мб из editText и отправка его в класс Psych после нажатия
       // FirebaseDatabase myDataBase = FirebaseDatabase.getInstance();
       // DatabaseReference myRef = myDataBase.getReference("message");
        //myRef.setValue(textView2.getText().toString());


    }
}