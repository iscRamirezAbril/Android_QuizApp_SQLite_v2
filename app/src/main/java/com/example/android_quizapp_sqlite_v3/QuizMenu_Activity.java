package com.example.android_quizapp_sqlite_v3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;

public class QuizMenu_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_quizmenu);

        ImageButton btnStartAddQuiz = findViewById(R.id.btnAddQuiz);
        btnStartAddQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startAddQuiz();
            }
        });
    }

    private void startAddQuiz(){
        Intent intent = new Intent(QuizMenu_Activity.this, Quiz_Activity.class);
        startActivity(intent);
    }
}