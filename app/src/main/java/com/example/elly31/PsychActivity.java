package com.example.elly31;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class PsychActivity extends AppCompatActivity {
    /**Активити психолога**/
    private Button message, settings, exit, posters, quizList;
    TextView tvPsychName;
    ImageView PsychImage;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_psych);
        init();
    }
    private void init(){
        message = findViewById(R.id.message);
        settings = findViewById(R.id.settings);
        exit = findViewById(R.id.exit);
        posters = findViewById(R.id.posters);
        quizList = findViewById(R.id.quizList);
        mAuth = FirebaseAuth.getInstance();
    }
    public void onClickPsychMessages(View view){}
    public void onClickPosters(View view){}
    public void onClickQuizList(View view){}
    public void onClickSettings(View view){}
    public void onClickSignOut(View view){}
    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser cUser = mAuth.getCurrentUser();
        if (cUser != null) {//если пользователь вошел
            //здесь нужно сделать разделение на запуск активити в зависимости от вошедшего пользователя

            String userName = "Вы вошли как:" + cUser.getEmail();
            tvPsychName.setText(userName);
            //если пользователь не 0 то покажем окно приложения
            Toast.makeText(this, "Пользователи есть", Toast.LENGTH_SHORT).show();
        } else {//если не вошел

            Toast.makeText(this, "Пользователей нет ", Toast.LENGTH_SHORT).show();
        }
    }

}