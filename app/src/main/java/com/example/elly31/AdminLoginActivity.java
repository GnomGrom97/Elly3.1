package com.example.elly31;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class AdminLoginActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText edLogin, edPassword;
    private ImageView ImageBrain;
    private TextView textView;
    private Button btnSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        init();
    }
    private void init() {
        edLogin=findViewById(R.id.edLogin);
        edPassword=findViewById(R.id.edPassword);
        btnSignIn =findViewById(R.id.btnSignIn);
        ImageBrain=findViewById(R.id.imageBrain);
        mAuth = FirebaseAuth.getInstance();
        textView=findViewById(R.id.textView);
    }
    /**авторизауия**/
    public  void  OnClickSignIn(View view)
    {
        if(!TextUtils.isEmpty(edLogin.getText().toString()) && !TextUtils.isEmpty(edPassword.getText().toString()))
        {
            mAuth.signInWithEmailAndPassword(edLogin.getText().toString(), edPassword.getText().toString())
                    .addOnCompleteListener(this,new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                //ShowSignet();
                                Toast.makeText(getApplicationContext(), "Вы успешно вошли", Toast.LENGTH_SHORT).show();
                                Intent intent =new Intent(AdminLoginActivity.this,AdminActivity.class);
                                startActivity(intent);
                            } else {
                                // notSignet();
                                Toast.makeText(getApplicationContext(), "User SigIn not Successful", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
        else {
            Toast.makeText( getApplicationContext(), "Пожалуйста введите логин или пароль", Toast.LENGTH_SHORT).show();
        }
    }
}