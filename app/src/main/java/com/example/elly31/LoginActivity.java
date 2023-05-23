package com.example.elly31;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.jetbrains.annotations.Nullable;
/**Активити авторизации**/

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private Button bStart, btnSignUp, btnSignIn, bSignOut,btnChoice;
    private TextView tvUserName;
    private ImageView ImageBrain;
    private EditText edLogin, edPassword1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // убмрает вехнюю строку полностью, но оставляет черную полосу
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        init();
    }

    private void init() {
        edLogin = findViewById(R.id.edLogin);
        edPassword1 = findViewById(R.id.edPassword1);
        btnSignUp =findViewById(R.id.btnSignUp);
        btnSignIn =findViewById(R.id.btnSignIn);
        bStart=findViewById(R.id.bStart);
        tvUserName =findViewById(R.id.twUserEmail);
        mAuth = FirebaseAuth.getInstance();
        btnSignIn=findViewById(R.id.btnSignIn);
        btnSignUp=findViewById(R.id.btnSignUp);
        bSignOut=findViewById(R.id.bSignOut);
        btnChoice=findViewById(R.id.btnChoice);
        ImageBrain=findViewById(R.id.ImageBrain);


    }

    @Override
    public void  onStart() {
        super.onStart();
        FirebaseUser cUser = mAuth.getCurrentUser();
        if (cUser != null) {//если пользователь вошел
            ShowSignet();
              String userName = "Вы вошли как:" + cUser.getEmail();
             tvUserName.setText(userName);
            //если пользователь не 0 то покажем окно приложения
            Toast.makeText(this, "Пользователи есть", Toast.LENGTH_SHORT).show();
        } else {//если не вошел
             notSignet();
            Toast.makeText(this, "Пользователей нет ", Toast.LENGTH_SHORT).show();
        }

    }
    /**регистрация**/
    public void OnClickSignUp(View view) {
        if (!TextUtils.isEmpty(edLogin.getText().toString()) && !TextUtils.isEmpty(edPassword1.getText().toString())) {
            mAuth.createUserWithEmailAndPassword(edLogin.getText().toString(), edPassword1.getText().toString())
                    //слушатель должен быть серым

                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                ShowSignet();
                                sendEmailVer();//сообщение на почту с подтверждением
                                Toast.makeText(getApplicationContext(), "Пользователь успешно зарегистрирован", Toast.LENGTH_SHORT).show();
                            } else {
                                notSignet();
                                Toast.makeText(getApplicationContext(), "Пользователь не зарегистрирован или уже есть в системе", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        } else {
            Toast.makeText(getApplicationContext(), "Пожалуйста введите логин или пароль", Toast.LENGTH_SHORT).show();
        }

    }

    /**авторизауия**/
    public  void  OnClickSignIn(View view)
    {
        if(!TextUtils.isEmpty(edLogin.getText().toString()) && !TextUtils.isEmpty(edPassword1.getText().toString()))
        {
            mAuth.signInWithEmailAndPassword(edLogin.getText().toString(), edPassword1.getText().toString())
        .addOnCompleteListener(this,new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    ShowSignet();
                    Toast.makeText(getApplicationContext(), "Вы успешно вошли", Toast.LENGTH_SHORT).show();
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
    public void OnClickStart(View view) {   //запуск активити
        Intent  i =new Intent(LoginActivity.this, ReadActivity.class);//запуск гглавного класса
        startActivity(i);
    }
    private void ShowSignet(){//если зарегистрировался
        FirebaseUser user =mAuth.getCurrentUser();

        assert  user != null;
        if(user.isEmailVerified()) { //запускается при подтверждении почты
            String userName = "Вы вошли как:" + user.getEmail();
            tvUserName.setText(userName);
            ImageBrain.setVisibility(View.GONE);

            btnChoice.setVisibility(View.VISIBLE);
            bSignOut.setVisibility(View.VISIBLE);
            bStart.setVisibility(View.VISIBLE);
            tvUserName.setVisibility(View.VISIBLE);
            edLogin.setVisibility(View.GONE); //если пользователь вошел, то логин невидимый
            edPassword1.setVisibility(View.GONE);
            btnSignUp.setVisibility(View.GONE);
            btnSignIn.setVisibility(View.GONE);
        }
        else {
            Toast.makeText( getApplicationContext(), "Проверьте вашу почту для подтвердения Email-адреса", Toast.LENGTH_SHORT).show();
        }
    }

    private  void notSignet(){//не зарегистрировался
        edLogin.setVisibility(View.VISIBLE); //если пользователь вошел, то логин невидимый
        edPassword1.setVisibility(View.VISIBLE);
        btnSignIn.setVisibility(View.VISIBLE);
        btnSignUp.setVisibility(View.VISIBLE);
        bStart.setVisibility(View.GONE);   //GONE- невидимость
        tvUserName.setVisibility(View.GONE);
        bSignOut.setVisibility(View.GONE);
        btnChoice.setVisibility(View.GONE);

        ImageBrain.setVisibility(View.VISIBLE);

    }
    public  void onClickSignOut(View view) {
        FirebaseAuth.getInstance().signOut();
        bStart.setVisibility(View.GONE);
        tvUserName.setVisibility(View.GONE);
        edLogin.setVisibility(View.VISIBLE);
        edPassword1.setVisibility(View.VISIBLE);
        btnSignIn.setVisibility(View.VISIBLE);
        btnSignUp.setVisibility(View.VISIBLE);
        bSignOut.setVisibility(View.GONE);
        btnChoice.setVisibility(View.GONE);

        ImageBrain.setVisibility(View.VISIBLE);
    }
    public void onClickChoice(View view){
        Intent intent = new Intent(LoginActivity.this, ChoiceActivity.class);
        startActivity(intent);
    }
    /**Подтверждение почты*/
    private void sendEmailVer(){//подтверждение электронной почты
        FirebaseUser  user = mAuth.getCurrentUser();
        assert  user != null;   //проверка полььзователя на 0
        user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful())
                {
                    Toast.makeText(getApplicationContext(), "Проверьте вашу почту, для подтверждения Email-адреса", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Пользователь успешно добавлен", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}

