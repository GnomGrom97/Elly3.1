package com.example.elly31;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

public class QuizResults extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_results);

        final AppCompatButton startNewQuiz = findViewById(R.id.startNewQuizBtn);
        final TextView correctAnswears= findViewById(R.id.correctAnswears);
        final TextView incorrectAnswears=findViewById(R.id.incorrectAnswears);

        final int  getCorrectAnswears = getIntent().getIntExtra("correct",0);
        final int  getInCorrectAnswears = getIntent().getIntExtra("incorrect",0);
        correctAnswears.setText(String.valueOf("Верных ответов:" + getCorrectAnswears));//получение корректных ответов и перевод в текст
        incorrectAnswears.setText(String.valueOf("Неверных ответов:" + getInCorrectAnswears));//получение некорректных ответов

        //
        startNewQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(QuizResults.this,QuizMainActivity.class));
                finish();

            }
        });
    }
//действия при нажатии кнопки
    @Override
    public void onBackPressed() {
        startActivity(new Intent(QuizResults.this,QuizMainActivity.class));
        finish();

    }
}