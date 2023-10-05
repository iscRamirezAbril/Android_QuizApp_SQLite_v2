package com.example.android_quizapp_sqlite_v3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;

import java.lang.reflect.Array;
import java.util.List;

public class QuizMenu_Activity extends AppCompatActivity {
    private static final int REQUEST_CODE_QUIZ = 1;
    public static final String EXTRA_CATEGORY_ID = "extraCategoryID";
    public static final String EXTRA_CATEGORY_NAME = "extraCategoryName";
    public static final String EXTRA_DIFFICULTY = "extraDifficulty";

    public static final String SHARED_PREFS = "sharedPrefs";
    private Spinner spinnerDifficulty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_quizmenu);

        spinnerDifficulty = findViewById(R.id.spinner_difficulty);
        ImageButton btnStartAddQuiz = findViewById(R.id.btnAddQuiz);
        ImageButton btnStartSubsQuiz = findViewById(R.id.btnSubsQuiz);
        ImageButton btnStartMultQuiz = findViewById(R.id.btnMultiQuiz);
        ImageButton btnStartDivQuiz = findViewById(R.id.btnDivQuiz);
        ImageButton btnStartMixed = findViewById(R.id.btnMixedQuiz);


        // Aquí es donde vamos a poblar el Spinner con las dificultades
        ArrayAdapter<String> difficultyAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, Question.getAllDifficultyLevels());
        difficultyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDifficulty.setAdapter(difficultyAdapter);

        btnStartAddQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startQuiz(Category.ADDITIONS); // Usamos la constante Category.ADDITIONS para representar la categoría "Sumas"
            }
        });

        btnStartSubsQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startQuiz(Category.SUBTRACTIONS); // Usamos la constante Category.SUBTRACTIONS para representar la categoría "Restas"
            }
        });

        btnStartMultQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startQuiz(Category.MULTIPLICATIONS); // Usamos la constante Category.SUBTRACTIONS para representar la categoría "Restas"
            }
        });

        btnStartDivQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startQuiz(Category.DIVISIONS); // Usamos la constante Category.SUBTRACTIONS para representar la categoría "Restas"
            }
        });

        btnStartMixed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startQuiz(Category.MIXED); // Usamos la constante Category.SUBTRACTIONS para representar la categoría "Restas"
            }
        });
    }

    private void startQuiz(int categoryID){
        String difficulty = spinnerDifficulty.getSelectedItem().toString();

        Intent intent = new Intent(QuizMenu_Activity.this, Quiz_Activity.class);
        intent.putExtra(EXTRA_DIFFICULTY, difficulty);
        intent.putExtra(EXTRA_CATEGORY_ID, categoryID); // Aquí pasas la categoría seleccionada
        startActivity(intent);
    }
}