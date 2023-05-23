package com.example.elly31;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class AdminPsychActivity extends AppCompatActivity {
    private ListView listView;//подключение чтения из бд
    private ArrayAdapter<String> adapter;//создание адаптера в него можно передать только лист
    private List<String> listData;//создание списочного массива
    private List<Psych> listTemp;

    private DatabaseReference myDataBase;//создание базы данных
    private String PSYCH_KEY = "Psych";// создание текстового ключа везде одного размера

    //создать еще 1 текстовый ключ admin- для сохранения админа

    @Override
    //Чтобы показать, что поле или параметр может быть null или метод
    // может вернуть null, используется аннотация @Nullable.
    protected void onCreate(@Nullable Bundle savedInstanceState) {//@Nullable
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_psych);
        init();
        getDataFromDB();
       // setOnClickItem();
    }

    private void init() {//инициализация чтения бд

        listView = findViewById(R.id.listView);//подключение и поиск по id листа
        listData = new ArrayList<>();//создание списочного массива
        listTemp = new ArrayList<>();//создание списочного массива
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);//создание нового адаптера
        listView.setAdapter(adapter);//указываем что в listView будем указывать этот адаптер
        myDataBase = FirebaseDatabase.getInstance().getReference(PSYCH_KEY);

    }

    private void getDataFromDB() {//функция считывает с бд и загружает в адаптер

        ValueEventListener vListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot)//snapshot
            {//с помощью цила получаем детей из бд
                if (listData.size() > 0) listData.clear();
                if (listTemp.size() > 0) listTemp.clear();
                for (DataSnapshot ds : datasnapshot.getChildren())//snapshot
                {
                    // Psych psych = ds.getValue(Psych.class);//получает каждого пользователя и помещает в массив
                    Psych psych = ds.getValue(com.example.elly31.Psych.class);//передать сначение
                    assert psych != null;//проверка на то что User не пустой/
                    // assert psych != null;
                    // listData.add(psych.name);//добавляет имя польззователя в список типа string
                    // listTemp2.add(psych);
                    listData.add(psych.name);//сохраняет пользователя в списке
                    listTemp.add(psych);//сохраняет пользователя в списке
                }/**для передачи данных через putExtre на новое вктивити нужно создать активити админа + активити АКпсих
             */
                // оповещение arrayadapter об изменении данных для его обновления
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        };
        myDataBase.addValueEventListener(vListener);//запуск добавление слушателя
    }
/**Метод для передачи инфы о психологах*/
    private void setOnClickItem() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Psych psych = listTemp.get(position);//получаем по нажатой позиции полностью пользователя
                Intent i = new Intent(AdminPsychActivity.this, ShowActivity.class);
                i.putExtra(Constant.USER_NAME, user2.name2);
                i.putExtra(Constant.USER_SEC_NAME, user2.sec_name2);
                i.putExtra(Constant.USER_EMAIL, user2.email2);
                startActivity(i);
            }
        });
    }
}