package com.example.elly31;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import de.hdodenhof.circleimageview.CircleImageView;

/**аккаунт админа**/
public class AdminActivity extends AppCompatActivity {
    private TextView textView;
    CircleImageView circleImageView;
    private Button message,clients,exit,psyho,settings,Imessage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        init();
    }
    private  void init (){
    textView=findViewById(R.id.textView);
    message=findViewById(R.id.message);
    clients=findViewById(R.id.clients);
    exit=findViewById(R.id.exit);
    psyho=findViewById(R.id.psyho);
    settings=findViewById(R.id.settings);
    circleImageView=findViewById(R.id.circleImageView);
    Imessage=findViewById(R.id.Imessage);
    }
    public void onClickMessage (View view){
        Intent intent =new Intent(AdminActivity.this,ReadActivity.class);
        startActivity(intent);
    }

}
