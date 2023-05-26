package com.example.elly31;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import de.hdodenhof.circleimageview.CircleImageView;

/**аккаунт админа**/
public class AdminActivity extends AppCompatActivity {
    private TextView tvAdminName;
    CircleImageView circleImageView;
    private FirebaseAuth mAuth;
    private Button message,clients,exit,psyho,settings,Imessage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        init();
    }
    private  void init (){
    tvAdminName=findViewById(R.id.tvAdminName);
    message=findViewById(R.id.message);
    clients=findViewById(R.id.clients);
    exit=findViewById(R.id.exit);
    psyho=findViewById(R.id.psyho);
    settings=findViewById(R.id.settings);
    circleImageView=findViewById(R.id.circleImageView);
    Imessage=findViewById(R.id.Imessage);
        mAuth = FirebaseAuth.getInstance();
    }
    public void onClickClients (View view){
        Intent intent =new Intent(AdminActivity.this,ReadActivity.class);
        startActivity(intent);
    }
    public void onClickMessage (View view){
        Intent intent =new Intent(AdminActivity.this,ShowActivity.class);
        startActivity(intent);
    }
    public void onClickAdminPsych (View view){
        Intent intent =new Intent(AdminActivity.this,AdminPsychActivity.class);
        startActivity(intent);
    }
    public void onClickSignOut (View view){}
    public  void onClickSettings(View view){}
    @Override
    public void  onStart() {
        super.onStart();
        FirebaseUser cUser = mAuth.getCurrentUser();
        if (cUser != null) {//если пользователь вошел
            //здесь нужно сделать разделение на запуск активити в зависимости от вошедшего пользователя

            String userName = "Вы вошли как:" + cUser.getEmail();
            tvAdminName.setText(userName);
            //если пользователь не 0 то покажем окно приложения
            Toast.makeText(this, "Пользователи есть", Toast.LENGTH_SHORT).show();
        } else {//если не вошел

            Toast.makeText(this, "Пользователей нет ", Toast.LENGTH_SHORT).show();
        }
    }
}
