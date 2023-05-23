package com.example.elly31;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;

public class MainActivityPsych extends AppCompatActivity {
    /**
     * Активити регистации психолога
     **/

    private EditText edName, edSecondName, edEmail, edThreeName, edEducation, edPassword;
    //используется myDataBase
    private DatabaseReference myDatabase;//ссылка на объект бд
    private String PSYCH_KEY = "Psych";
    private Button DownloadEduBtn, saveBt, SpecializaionBtn;
    private  StorageReference storageRef;
    private ImageView ImImage;
    private  Uri uploadUri;


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
        DownloadEduBtn = findViewById(R.id.DownloadEduBtn);
        saveBt = findViewById(R.id.saveBt);
        SpecializaionBtn = findViewById(R.id.SpecializaionBtn);
        myDatabase = FirebaseDatabase.getInstance().getReference(PSYCH_KEY);
        //загрузка файлов в папку бд
        ImImage = findViewById(R.id.ImImage);
        storageRef = FirebaseStorage.getInstance().getReference("ImageDB");
    }

    public void onClickSave(View view) {//метод для создания пользователя
        String id = myDatabase.getKey();//получение значения из поля
        String name = edName.getText().toString();//получение текста и превращение в
        String sec_name = edSecondName.getText().toString();//получение текста и превращение в строку
        String third_name = edThreeName.getText().toString();
        String education = edEducation.getText().toString();
        String email = edEmail.getText().toString();//получение текста и превращение в строку
        String password = edPassword.getText().toString();
        //добавил появление кнопок на экране прри нужном нажатии
       // String specialization = + проверка на выбор специализации
        //добавить переменную константу специализация, которую получаешь из специализации
        Psych newPsych = new Psych(id, name,sec_name, third_name,email,education,password);
        if      (!TextUtils.isEmpty(name) &&
                !TextUtils.isEmpty(sec_name) &&
                !TextUtils.isEmpty(third_name) &&
                !TextUtils.isEmpty(email) &&
                !TextUtils.isEmpty(education) &&
                !TextUtils.isEmpty(password)) {
            //проверка на пустое поле
            myDatabase.push().setValue(newPsych);//отправить информацию о бд -создание пользователя
            Toast.makeText(this, "Пользователь успешно добавлен", Toast.LENGTH_SHORT).show();
        } else {//выдает сообщение о пустом поле
            Toast.makeText(this, "Пустое поле", Toast.LENGTH_SHORT).show();
        }
    }

    //метод для загрузки фото образования в профиль
    public void OnClickDownloadEducation(View view) {
        getImage();
//запуск активити с инфой из бд
    }

    public void OnClickSpecializaion(View view) {
        Intent intent = new Intent(MainActivityPsych.this, SpecializationActivity.class);
        startActivity(intent);
    }

    /**
     * окно для загрузки фото
     */
    private void getImage() {
        Intent intentChooser = new Intent();
        intentChooser.setType("image/*");
        intentChooser.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intentChooser, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //если полученная инфа не равна 0 и то что она содержит не равна 0
        if (requestCode == 1 && data != null && data.getData() != null) {
            if (resultCode == RESULT_OK) {
                Log.d("MyLog", "ImageURL:" + data.getData());
                ImImage.setImageURI(data.getData());
                uploadImage();
            }
        }
    }
/**Метод для загрузки фото на сервер */
    private void uploadImage(){
        Bitmap bitmap=((BitmapDrawable) ImImage.getDrawable()).getBitmap();//превращение картинки в битмап
        ByteArrayOutputStream baos= new ByteArrayOutputStream();//поток байтов
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100,baos);//сжимаем битмап до формата jpeg с качеством 100
//превращение потока в массив для передачи
        byte[] byteArray = baos.toByteArray();
        //создание названия для нашей картинки
        StorageReference mRef=storageRef.child(System.currentTimeMillis()+ "my_image");
        UploadTask up= mRef.putBytes(byteArray);
        Task<Uri>task=up.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
            @Override
            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                return mRef.getDownloadUrl();
            }
        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                //когда загрузится картинка и мы получим на нее ссылку она соханится в uplodaUri
            uploadUri=task.getResult();
            }
        });
    }

    }

