package com.example.elly31;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
/*

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
*/
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class QuizActivity extends AppCompatActivity {
    private TextView questions;
    private TextView question;
    private AppCompatButton option1, option2, option3, option4;
    private AppCompatButton nextBtn;
    private Timer quizTimer;
    private int seconds = 0;
    private int totalTimeInMins = 1;
    private List<QuestionsLIst> questionsList = new ArrayList<>();

    private int currentQuestionPosition = 0; //вроде корректная позиция вопроса
    //отвечает за правильный ответ пользователя
    private String selectedOptionByUser = "";

    //private InterstitialAd mInterstitialAd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        //подключение межстраничного баннера
      /* MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });


        LoadAds ();

*/
        //получение данных с предыдущего активити
        final ImageView backBtn = findViewById(R.id.backBtn);
        final TextView timer = findViewById(R.id.timer);
        final TextView selectedTopicName = findViewById(R.id.SelectedtopicName);
        questions = findViewById(R.id.questions);
        question = findViewById(R.id.question);
        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        option4 = findViewById(R.id.option4);
        nextBtn = findViewById(R.id.nextBtn);


        final String getSelectedTopic = getIntent().getStringExtra("selectedTopic");
        selectedTopicName.setText(getSelectedTopic);//получение названия викторины сверху из имени
        questionsList = QuestionsBank.getQuestions(getSelectedTopic);//получение вопросов  из банка
        StartTimer(timer);//запуск таймера
        //получение текста из переменных
        questions.setText((currentQuestionPosition + 1) + "/" + questionsList.size());//получение текста
        question.setText(questionsList.get(0).getQuestion());
        option1.setText(questionsList.get(0).getOption1());
        option2.setText(questionsList.get(0).getOption2());
        option3.setText(questionsList.get(0).getOption3());
        option4.setText(questionsList.get(0).getOption4());


        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//возврат по кнопке назад
                backBtn.setBackgroundResource(R.drawable.round_back_green_30);//изменение цвета кнопки по нажатию
                quizTimer.purge();
                quizTimer.cancel();
                startActivity(new Intent(QuizActivity.this, QuizMainActivity.class));
                finish();
            }
        });
        option1.setOnClickListener(new View.OnClickListener() {//кнопка 1
            @Override
            public void onClick(View v) {
                if (selectedOptionByUser.isEmpty()) {//окрвшивание в красный
                    selectedOptionByUser = option1.getText().toString();
                    option1.setBackgroundResource(R.drawable.round_back_red10);
                    option1.setTextColor(Color.WHITE);
                    revealAnswer();
                    questionsList.get(currentQuestionPosition).setUserSelectedAnswer(selectedOptionByUser);
                }
            }

        });
        option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedOptionByUser.isEmpty()) {//окрвшивание в красный
                    selectedOptionByUser = option2.getText().toString();
                    option2.setBackgroundResource(R.drawable.round_back_red10);
                    option2.setTextColor(Color.WHITE);
                    revealAnswer();
                    questionsList.get(currentQuestionPosition).setUserSelectedAnswer(selectedOptionByUser);
                }
            }
        });
        option3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedOptionByUser.isEmpty()) {//окрвшивание в красный
                    selectedOptionByUser = option3.getText().toString();
                    option3.setBackgroundResource(R.drawable.round_back_red10);
                    option3.setTextColor(Color.WHITE);
                    revealAnswer();
                    questionsList.get(currentQuestionPosition).setUserSelectedAnswer(selectedOptionByUser);
                }
            }

        });
        option4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedOptionByUser.isEmpty()) {//окрвшивание в красный
                    selectedOptionByUser = option4.getText().toString();
                    option4.setBackgroundResource(R.drawable.round_back_red10);
                    option4.setTextColor(Color.WHITE);
                    revealAnswer();
                    questionsList.get(currentQuestionPosition).setUserSelectedAnswer(selectedOptionByUser);
                }
            }

        });
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedOptionByUser.isEmpty()) {
                    Toast.makeText(QuizActivity.this, "Пожалуйстай,сделайте выбор", Toast.LENGTH_SHORT).show();
                } else {
                    ChangeNestQuestion();

                }

            }

        });
    }

    private void StartTimer(TextView timerTextView) {//метод для таймера в углу
        quizTimer = new Timer();
        quizTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (seconds == 0) {
                    totalTimeInMins--;// уменьшение минут на 1 сек
                    seconds = 59;
                } else if (seconds == 0 && totalTimeInMins == 0) {
                    quizTimer.purge();
                    quizTimer.cancel();//окончание времени работы таймера
                    //сообщение об окончании времени
                    Toast.makeText(QuizActivity.this, "Время вышло", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(QuizActivity.this, QuizResults.class);
                    intent.putExtra("correct", getCorrentAnswer());
                    //вызываем новое намерение, вкладываем в него количество корректных ответов
                    intent.putExtra("incorrect", getInCorrentAnswer());//получение некорректных ответов
                    startActivity(intent);
                    finish();
                } else {
                    seconds--;//уменьшение секунд
                }
                //метод для корректного отображения таймера
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {//перевод в строковые метод для двухразрядноо отображения 09 08 07 и тд
                        String finalMinutes = String.valueOf(totalTimeInMins);
                        String finalSeconds = String.valueOf(seconds);
                        if (finalMinutes.length() == 1) {
                            finalMinutes = "0" + finalMinutes;
                        }
                        if (finalSeconds.length() == 1) {
                            finalSeconds = "0" + finalSeconds;
                        }
                        timerTextView.setText(finalMinutes + ":" + finalSeconds);
                    }
                });//запуск нового потока
            }//внимательно БЛЯТЬ на эти скобки
        }, 1000, 1000);//отвечает за скорость таймера
    }

    private int getCorrentAnswer() {//получение корректного ответа
        int correctAnswer = 0;
        for (int i = 0; i < questionsList.size(); i++) {//повтор до тех пор пока не равен размеру листа
            final String getUserSelectedAnswer = questionsList.get(i).getUserSelectedAnswer();
            final String getAnswer = questionsList.get(i).getAnswer();
            if (getUserSelectedAnswer.equals(getAnswer)) {
                correctAnswer++;//если ответ правильный увеличивает кол-во правильных ответов на 1
            }
        }
        return correctAnswer;
    }

    private int getInCorrentAnswer() {//получение некорректного ответа
        int correctAnswer = 0;
        for (int i = 0; i < questionsList.size(); i++) {//повтор до тех пор пока не равен размеру листа
            final String getUserSelectedAnswer = questionsList.get(i).getUserSelectedAnswer();
            final String getAnswer = questionsList.get(i).getAnswer();
            if (!getUserSelectedAnswer.equals(getAnswer)) {//если ответ неправильный
                correctAnswer++;//если ответ не правильный увеличивает кол-во правильных ответов на 1
            }
        }
        return correctAnswer;
    }

    @Override
    public void onBackPressed() {//возврат назад
        quizTimer.purge();
        quizTimer.cancel();
        startActivity(new Intent(QuizActivity.this, QuizMainActivity.class));
        finish();
    }

    private void revealAnswer() {//метод на правильный ответ
        final String getAnswer = questionsList.get(currentQuestionPosition).getAnswer();//получение прав ответа

        if (option1.getText().toString().equals(getAnswer)) {//если выбранный ответ соответствует правильному
            option1.setBackgroundResource(R.drawable.round_back_green10);
            option1.setTextColor(Color.WHITE);
        } else if (option2.getText().toString().equals(getAnswer)) {//если выбранный ответ соответствует правильному
            option2.setBackgroundResource(R.drawable.round_back_green10);
            option2.setTextColor(Color.WHITE);
        } else if (option3.getText().toString().equals(getAnswer)) {//если выбранный ответ соответствует правильному
            option3.setBackgroundResource(R.drawable.round_back_green10);
            option3.setTextColor(Color.WHITE);
        } else if (option4.getText().toString().equals(getAnswer)) {//если выбранный ответ соответствует правильному
            option4.setBackgroundResource(R.drawable.round_back_green10);
            option4.setTextColor(Color.WHITE);
        }
    }

    private void ChangeNestQuestion() {
        currentQuestionPosition++;
        if ((currentQuestionPosition + 1) == questionsList.size()) {
            nextBtn.setText("Завершить");
        }
        if (currentQuestionPosition < questionsList.size()) {//сброс поля ответа в пустую позицию
            selectedOptionByUser = "";
            //возврат вариантов ответов к изначальным цветам
            option1.setBackgroundResource(R.drawable.round_back_white_stroke2_10);
            option1.setTextColor(Color.parseColor("#1f6bb8"));
            option2.setBackgroundResource(R.drawable.round_back_white_stroke2_10);
            option2.setTextColor(Color.parseColor("#1f6bb8"));
            option3.setBackgroundResource(R.drawable.round_back_white_stroke2_10);
            option3.setTextColor(Color.parseColor("#1f6bb8"));
            option4.setBackgroundResource(R.drawable.round_back_white_stroke2_10);
            option4.setTextColor(Color.parseColor("#1f6bb8"));

            questions.setText((currentQuestionPosition + 1) + "/" + questionsList.size());//получение текста
            question.setText(questionsList.get(currentQuestionPosition).getQuestion());
            option1.setText(questionsList.get(currentQuestionPosition).getOption1());
            option2.setText(questionsList.get(currentQuestionPosition).getOption2());
            option3.setText(questionsList.get(currentQuestionPosition).getOption3());
            option4.setText(questionsList.get(currentQuestionPosition).getOption4());
        } else {
//после ответа на последний вопрос открывается реклама
        /*  if (mInterstitialAd != null) {
                mInterstitialAd.show(QuizActivity.this);
                mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback(){

                    @Override
                    public void onAdDismissedFullScreenContent() {
                        // Called when ad is dismissed.
                        // Set the ad reference to null so you don't show the ad a second time.
                        Log.d("TAG", "Ad dismissed fullscreen content.");
                        mInterstitialAd = null;*/

            //передаем  данные в новое активити,правильные и неправильные ответы
            Intent intent = new Intent(QuizActivity.this, QuizResults.class);
            intent.putExtra("correct", getCorrentAnswer());
            intent.putExtra("incorrect", getInCorrentAnswer());
            startActivity(intent);
            finish();
        }
    }
}
                  /*  });
            } else {
              //  Log.d("TAG", "The interstitial ad wasn't ready yet.");

                //передаем  данные в новое активити,правильные и неправильные ответы
                Intent intent = new Intent(QuizActivity.this,QuizResults.class);
                intent.putExtra("correct",getCorrentAnswer());
                intent.putExtra("incorrect",getInCorrentAnswer());
                startActivity(intent);
                finish();
            }
        }
    }
   private void LoadAds () {
        AdRequest adRequest = new AdRequest.Builder().build();
        InterstitialAd.load(this,"ca-app-pub-3940256099942544/1033173712", adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.
                        mInterstitialAd = interstitialAd;
                        Log.i("TAG", "onAdLoaded");
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error
                        Log.d("TAG", loadAdError.toString());
                        mInterstitialAd = null;
                    }
                });
    }*/
