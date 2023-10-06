package com.example.android_quizapp_sqlite_v3;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.android_quizapp_sqlite_v3.QuizContract.*;

import java.util.ArrayList;
import java.util.List;

public class QuizDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "DinoAprende.db";
    private static final int DATABASE_VERSION = 1;

    private static  QuizDbHelper instance;
    private SQLiteDatabase db;

    public QuizDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static synchronized QuizDbHelper getInstance(Context context) {
        if (instance == null) {
            instance = new QuizDbHelper(context.getApplicationContext());
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        final String SQL_CREATE_CATEGORIES_TABLE = "CREATE TABLE " +
                CategoriesTable.TABLE_NAME + "( " +
                CategoriesTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                CategoriesTable.COLUMN_NAME + " TEXT " +
                ")";

        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuestionsTable.TABLE_NAME + " ( " +
                QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
                QuestionsTable.COLUMN_ANSWER_NR + " INTEGER, " +
                QuestionsTable.COLUMN_DIFFICULTY + " TEXT, " +
                QuestionsTable.COLUMN_CATEGORY_ID + " INTEGER, " +
                "FOREIGN KEY(" + QuestionsTable.COLUMN_CATEGORY_ID + ") REFERENCES " +
                CategoriesTable.TABLE_NAME + "(" + CategoriesTable._ID + ")" + "ON DELETE CASCADE" +
                ")";

        db.execSQL(SQL_CREATE_CATEGORIES_TABLE);
        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillCategoriesTable();
        fillQuestionsTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CategoriesTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + QuestionsTable.TABLE_NAME);
        onCreate(db);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

    private void fillCategoriesTable() {
        Category c1 = new Category("Sumas");
        addCategory(c1);
        Category c2 = new Category("Restas");
        addCategory(c2);
        Category c3 = new Category("Multiplicaciones");
        addCategory(c3);
        Category c4 = new Category("Divisiones");
        addCategory(c4);
        Category c5 = new Category("Mixtas");
        addCategory(c5);
    }

    private void addCategory(Category category) {
        ContentValues cv = new ContentValues();
        cv.put(CategoriesTable.COLUMN_NAME, category.getName());
        db.insert(CategoriesTable.TABLE_NAME, null, cv);
    }

    private void fillQuestionsTable() {
        // |====================| SUMAS |====================| \\
        // |--------------| DIFICULTAD: FÁCIL |--------------| \\
        Question q1 = new Question("¿Cuánto es 1+1?",
                "2", "5", "3", 1,
                Question.DIFFICULTY_EASY, Category.ADDITIONS);
        addQuestion(q1);
        Question q2 = new Question("¿Cuánto es 3+1?",
                "7", "1", "4", 3,
                Question.DIFFICULTY_EASY, Category.ADDITIONS);
        addQuestion(q2);
        Question q3 = new Question("¿Cuánto es 2+1?",
                "2", "5", "3", 3,
                Question.DIFFICULTY_EASY, Category.ADDITIONS);
        addQuestion(q3);
        Question q4 = new Question("¿Cuánto es 1+2?",
                "1", "3", "5", 2,
                Question.DIFFICULTY_EASY, Category.ADDITIONS);
        addQuestion(q4);
        Question q5 = new Question("¿Cuánto es 0+2?",
                "1", "0", "2", 3,
                Question.DIFFICULTY_EASY, Category.ADDITIONS);
        addQuestion(q5);

        // |--------------| DIFICULTAD: MEDIA |--------------| \\
        Question q6 = new Question("¿Cuánto es 5+0?",
                "0", "5", "4", 2,
                Question.DIFFICULTY_MEDIUM, Category.ADDITIONS);
        addQuestion(q6);
        Question q7 = new Question("¿Cuánto es 6+1?",
                "9", "7", "8", 2,
                Question.DIFFICULTY_MEDIUM, Category.ADDITIONS);
        addQuestion(q7);
        Question q8 = new Question("¿Cuánto es 8+0+1?",
                "11", "7", "9", 3,
                Question.DIFFICULTY_MEDIUM, Category.ADDITIONS);
        addQuestion(q8);
        Question q9 = new Question("¿Cuánto es 9+2?",
                "11", "7", "9", 1,
                Question.DIFFICULTY_MEDIUM, Category.ADDITIONS);
        addQuestion(q9);
        Question q10 = new Question("¿Cuánto es 7+3?",
                "11", "10", "15", 2,
                Question.DIFFICULTY_MEDIUM, Category.ADDITIONS);
        addQuestion(q10);

        // |--------------| DIFICULTAD: DIFICIL |--------------| \\
        Question q11 = new Question("¿Cuánto es 3+1+1?",
                "5", "7", "6", 1,
                Question.DIFFICULTY_HARD, Category.ADDITIONS);
        addQuestion(q11);
        Question q12 = new Question("¿Cuánto es 7+0+2?",
                "9", "7", "0", 1,
                Question.DIFFICULTY_HARD, Category.ADDITIONS);
        addQuestion(q12);
        Question q13 = new Question("¿Cuánto es 4+2+3?",
                "10", "4", "9", 3,
                Question.DIFFICULTY_HARD, Category.ADDITIONS);
        addQuestion(q13);
        Question q14 = new Question("¿Cuánto es 2+10?",
                "11", "12", "15", 2,
                Question.DIFFICULTY_HARD, Category.ADDITIONS);
        addQuestion(q14);
        Question q15 = new Question("¿Cuánto es 15+1?",
                "12", "15", "16", 3,
                Question.DIFFICULTY_HARD, Category.ADDITIONS);
        addQuestion(q15);


        // |====================| RESTAS |====================| \\
        // |--------------| DIFICULTAD: FÁCIL |--------------| \\
        Question q16 = new Question("¿Cuánto es 1-1?",
                "2", "0", "1", 2,
                Question.DIFFICULTY_EASY, Category.SUBTRACTIONS);
        addQuestion(q16);
        Question q17 = new Question("¿Cuánto es 2-1?",
                "4", "3", "1", 3,
                Question.DIFFICULTY_EASY, Category.SUBTRACTIONS);
        addQuestion(q17);
        Question q18 = new Question("¿Cuánto es 5-2?",
                "4", "3", "1", 2,
                Question.DIFFICULTY_EASY, Category.SUBTRACTIONS);
        addQuestion(q18);
        Question q19 = new Question("¿Cuánto es 3-2?",
                "1", "5", "3", 1,
                Question.DIFFICULTY_EASY, Category.SUBTRACTIONS);
        addQuestion(q19);
        Question q20 = new Question("¿Cuánto es 4-3?",
                "7", "2", "1", 3,
                Question.DIFFICULTY_EASY, Category.SUBTRACTIONS);
        addQuestion(q20);

        // |--------------| DIFICULTAD: MEDIA |--------------| \\
        Question q21 = new Question("¿Cuánto es 9-3?",
                "6", "9", "15", 1,
                Question.DIFFICULTY_MEDIUM, Category.SUBTRACTIONS);
        addQuestion(q21);
        Question q22 = new Question("¿Cuánto es 5-4?",
                "7", "9", "1", 3,
                Question.DIFFICULTY_MEDIUM, Category.SUBTRACTIONS);
        addQuestion(q22);
        Question q23 = new Question("¿Cuánto es 8-4?",
                "4", "5", "7", 1,
                Question.DIFFICULTY_MEDIUM, Category.SUBTRACTIONS);
        addQuestion(q23);
        Question q24 = new Question("¿Cuánto es 10-3-1?",
                "10", "9", "6", 3,
                Question.DIFFICULTY_MEDIUM, Category.SUBTRACTIONS);
        addQuestion(q24);
        Question q25 = new Question("¿Cuánto es 5-1?",
                "3", "4", "5", 2,
                Question.DIFFICULTY_MEDIUM, Category.SUBTRACTIONS);
        addQuestion(q25);

        // |--------------| DIFICULTAD: DIFICIL |--------------| \\
        Question q26 = new Question("¿Cuánto es 15-5-2?",
                "4", "8", "7", 2,
                Question.DIFFICULTY_HARD, Category.SUBTRACTIONS);
        addQuestion(q26);
        Question q27 = new Question("¿Cuánto es 12-10?",
                "5", "2", "9", 2,
                Question.DIFFICULTY_HARD, Category.SUBTRACTIONS);
        addQuestion(q27);
        Question q28 = new Question("¿Cuánto es 13-3?",
                "8", "12", "10", 3,
                Question.DIFFICULTY_HARD, Category.SUBTRACTIONS);
        addQuestion(q28);
        Question q29 = new Question("¿Cuánto es 10-9-1?",
                "7", "1", "0", 3,
                Question.DIFFICULTY_HARD, Category.SUBTRACTIONS);
        addQuestion(q29);
        Question q30 = new Question("¿Cuánto es 20-10-1?",
                "9", "10", "15", 1,
                Question.DIFFICULTY_HARD, Category.SUBTRACTIONS);
        addQuestion(q30);

        // |===============| MULTIPLICACIONES |===============| \\
        // |--------------| DIFICULTAD: FÁCIL |--------------| \\
        Question q31 = new Question("¿Cuánto es 1x1?",
                "1", "0", "2", 1,
                Question.DIFFICULTY_EASY, Category.MULTIPLICATIONS);
        addQuestion(q31);
        Question q32 = new Question("¿Cuánto es 2x1?",
                "3", "2", "5", 2,
                Question.DIFFICULTY_EASY, Category.MULTIPLICATIONS);
        addQuestion(q32);
        Question q33 = new Question("¿Cuánto es 1x7?",
                "7", "10", "9", 1,
                Question.DIFFICULTY_EASY, Category.MULTIPLICATIONS);
        addQuestion(q33);
        Question q34 = new Question("¿Cuánto es 4x2?",
                "9", "6", "8", 3,
                Question.DIFFICULTY_EASY, Category.MULTIPLICATIONS);
        addQuestion(q34);
        Question q35 = new Question("¿Cuánto es 2x2?",
                "7", "4", "3", 2,
                Question.DIFFICULTY_EASY, Category.MULTIPLICATIONS);
        addQuestion(q35);

        // |--------------| DIFICULTAD: MEDIA |--------------| \\
        Question q36 = new Question("¿Cuánto es 3x2?",
                "6", "4", "3", 1,
                Question.DIFFICULTY_MEDIUM, Category.MULTIPLICATIONS);
        addQuestion(q36);
        Question q37 = new Question("¿Cuánto es 4x6?",
                "20", "15", "24", 3,
                Question.DIFFICULTY_MEDIUM, Category.MULTIPLICATIONS);
        addQuestion(q37);
        Question q38 = new Question("¿Cuánto es 5x1?",
                "6", "7", "5", 3,
                Question.DIFFICULTY_MEDIUM, Category.MULTIPLICATIONS);
        addQuestion(q38);
        Question q39 = new Question("¿Cuánto es 6x2?",
                "16", "12", "14", 2,
                Question.DIFFICULTY_MEDIUM, Category.MULTIPLICATIONS);
        addQuestion(q39);
        Question q40 = new Question("¿Cuánto es 3x7?",
                "21", "17", "25", 1,
                Question.DIFFICULTY_MEDIUM, Category.MULTIPLICATIONS);
        addQuestion(q40);

        // |--------------| DIFICULTAD: DIFICIL |--------------| \\
        Question q41 = new Question("¿Cuánto es 8x2?",
                "14", "18", "16", 3,
                Question.DIFFICULTY_HARD, Category.MULTIPLICATIONS);
        addQuestion(q41);
        Question q42 = new Question("¿Cuánto es 7x9?",
                "64", "69", "63", 3,
                Question.DIFFICULTY_HARD, Category.MULTIPLICATIONS);
        addQuestion(q42);
        Question q43 = new Question("¿Cuánto es 9x3?",
                "29", "27", "25", 2,
                Question.DIFFICULTY_HARD, Category.MULTIPLICATIONS);
        addQuestion(q43);
        Question q44 = new Question("¿Cuánto es 8x9?",
                "72", "80", "75", 1,
                Question.DIFFICULTY_HARD, Category.MULTIPLICATIONS);
        addQuestion(q44);
        Question q45 = new Question("¿Cuánto es 7x7?",
                "50", "49", "45", 2,
                Question.DIFFICULTY_HARD, Category.MULTIPLICATIONS);
        addQuestion(q45);

        // |===============| DIVISIONES |===============| \\
        // |--------------| DIFICULTAD: FÁCIL |--------------| \\
        Question q46 = new Question("¿Cuánto es 6÷2?",
                "1", "2", "3", 3,
                Question.DIFFICULTY_EASY, Category.DIVISIONS);
        addQuestion(q46);
        Question q47 = new Question("¿Cuánto es 8÷4?",
                "4", "1", "2", 3,
                Question.DIFFICULTY_EASY, Category.DIVISIONS);
        addQuestion(q47);
        Question q48 = new Question("¿Cuánto es 10÷5?",
                "2", "6", "4", 1,
                Question.DIFFICULTY_EASY, Category.DIVISIONS);
        addQuestion(q48);
        Question q49 = new Question("¿Cuánto es 10÷2?",
                "4", "5", "6", 2,
                Question.DIFFICULTY_EASY, Category.DIVISIONS);
        addQuestion(q49);
        Question q50 = new Question("¿Cuánto es 4÷2?",
                "5", "2", "4", 2,
                Question.DIFFICULTY_EASY, Category.DIVISIONS);
        addQuestion(q50);

        // |--------------| DIFICULTAD: MEDIA |--------------| \\
        Question q51 = new Question("¿Cuánto es 16÷4?",
                "8", "5", "4", 3,
                Question.DIFFICULTY_MEDIUM, Category.DIVISIONS);
        addQuestion(q51);
        Question q52 = new Question("¿Cuánto es 18÷3?",
                "6", "9", "3", 1,
                Question.DIFFICULTY_MEDIUM, Category.DIVISIONS);
        addQuestion(q52);
        Question q53 = new Question("¿Cuánto es 9÷3?",
                "3", "4", "7", 1,
                Question.DIFFICULTY_MEDIUM, Category.DIVISIONS);
        addQuestion(q53);
        Question q54 = new Question("¿Cuánto es 15÷5?",
                "6", "3", "9", 2,
                Question.DIFFICULTY_MEDIUM, Category.DIVISIONS);
        addQuestion(q54);
        Question q55 = new Question("¿Cuánto es 12÷4?",
                "3", "4", "5", 1,
                Question.DIFFICULTY_MEDIUM, Category.DIVISIONS);
        addQuestion(q55);

        // |--------------| DIFICULTAD: DIFICIL |--------------| \\
        Question q56 = new Question("¿Cuánto es 20÷5?",
                "5", "6", "8", 1,
                Question.DIFFICULTY_HARD, Category.DIVISIONS);
        addQuestion(q56);
        Question q57 = new Question("¿Cuánto es 25÷5?",
                "7", "6", "5", 3,
                Question.DIFFICULTY_HARD, Category.DIVISIONS);
        addQuestion(q57);
        Question q58 = new Question("¿Cuánto es 14÷7?",
                "2", "4", "3", 1,
                Question.DIFFICULTY_HARD, Category.DIVISIONS);
        addQuestion(q58);
        Question q59 = new Question("¿Cuánto es 18÷6?",
                "5", "3", "4", 2,
                Question.DIFFICULTY_HARD, Category.DIVISIONS);
        addQuestion(q59);
        Question q60 = new Question("¿Cuánto es 21÷3?",
                "6", "8", "7", 3,
                Question.DIFFICULTY_HARD, Category.DIVISIONS);
        addQuestion(q60);

        // |===============| MIXTAS |===============| \\
        // |--------------| DIFICULTAD: FÁCIL |--------------| \\
        Question q61 = new Question("¿Cuánto es 3+4?",
                "8", "7", "3", 2,
                Question.DIFFICULTY_EASY, Category.MIXED);
        addQuestion(q61);
        Question q62 = new Question("¿Cuánto es 6-2?",
                "7", "5", "4", 3,
                Question.DIFFICULTY_EASY, Category.MIXED);
        addQuestion(q62);
        Question q63 = new Question("¿Cuánto es 2x3?",
                "4", "6", "5", 2,
                Question.DIFFICULTY_EASY, Category.MIXED);
        addQuestion(q63);
        Question q64 = new Question("¿Cuánto es 8÷4?",
                "2", "5", "3", 1,
                Question.DIFFICULTY_EASY, Category.MIXED);
        addQuestion(q64);
        Question q65 = new Question("¿Cuánto es 4+5-2?",
                "9", "10", "7", 3,
                Question.DIFFICULTY_EASY, Category.MIXED);
        addQuestion(q65);

        // |--------------| DIFICULTAD: MEDIA |--------------| \\
        Question q66 = new Question("¿Cuánto es 7+6-4?",
                "9", "10", "8", 1,
                Question.DIFFICULTY_MEDIUM, Category.MIXED);
        addQuestion(q66);
        Question q67 = new Question("¿Cuánto es 5x3+2?",
                "19", "15", "17", 3,
                Question.DIFFICULTY_MEDIUM, Category.MIXED);
        addQuestion(q67);
        Question q68 = new Question("¿Cuánto es 10-4x2?",
                "8", "2", "12", 2,
                Question.DIFFICULTY_MEDIUM, Category.MIXED);
        addQuestion(q68);
        Question q69 = new Question("¿Cuánto es 12÷4+3?",
                "7", "9", "6", 1,
                Question.DIFFICULTY_MEDIUM, Category.MIXED);
        addQuestion(q69);
        Question q70 = new Question("¿Cuánto es 8+4÷2?",
                "8", "6", "10", 3,
                Question.DIFFICULTY_MEDIUM, Category.MIXED);
        addQuestion(q70);

        // |--------------| DIFICULTAD: DIFICIL |--------------| \\
        Question q71 = new Question("¿Cuánto es 14+7x2-10?",
                "32", "18", "20", 2,
                Question.DIFFICULTY_HARD, Category.MIXED);
        addQuestion(q71);
        Question q72 = new Question("¿Cuánto es 20−8÷2+5×3?",
                "30", "40", "25", 2,
                Question.DIFFICULTY_HARD, Category.MIXED);
        addQuestion(q72);
        Question q73 = new Question("¿Cuánto es 21÷3+12−4×2?",
                "13", "9", "15", 1,
                Question.DIFFICULTY_HARD, Category.MIXED);
        addQuestion(q73);
        Question q74 = new Question("¿Cuánto es 10×2−4÷2+8?",
                "28", "24", "26", 3,
                Question.DIFFICULTY_HARD, Category.MIXED);
        addQuestion(q74);
        Question q75 = new Question("¿Cuánto es 15+10÷2−3×4?",
                "9", "13", "15", 1,
                Question.DIFFICULTY_HARD, Category.MIXED);
        addQuestion(q75);
    }

    private void addQuestion(Question question) {
        ContentValues cv = new ContentValues();
        cv.put(QuestionsTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuestionsTable.COLUMN_OPTION1, question.getOption1());
        cv.put(QuestionsTable.COLUMN_OPTION2, question.getOption2());
        cv.put(QuestionsTable.COLUMN_OPTION3, question.getOption3());
        cv.put(QuestionsTable.COLUMN_ANSWER_NR, question.getAnswerNr());
        cv.put(QuestionsTable.COLUMN_DIFFICULTY, question.getDifficulty());
        cv.put(QuestionsTable.COLUMN_CATEGORY_ID, question.getCategoryID());
        db.insert(QuestionsTable.TABLE_NAME, null, cv);
    }

    @SuppressLint("Range")
    public List<Category> getAllCategories() {
        List<Category> categoryList =  new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + CategoriesTable.TABLE_NAME, null);

        if (c.moveToFirst()) {
            do {
                Category category = new Category();
                category.setId(c.getInt(c.getColumnIndex(CategoriesTable._ID)));
                category.setName(c.getString(c.getColumnIndex(CategoriesTable.COLUMN_NAME)));
                categoryList.add(category);
            } while (c.moveToNext());
        }
        c.close();
        return categoryList;
    }

    @SuppressLint("Range")
    public ArrayList<Question> getAllQuestions() {
        ArrayList<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuestionsTable.TABLE_NAME, null);

        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setId(c.getInt(c.getColumnIndex(QuestionsTable._ID)));
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NR)));
                question.setDifficulty(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_DIFFICULTY)));
                question.setCategoryID(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_CATEGORY_ID)));
                questionList.add(question);
            } while (c.moveToNext());
        }

        c.close();
        return questionList;
    }

    @SuppressLint("Range")
    public ArrayList<Question> getQuestions(String difficulty, int categoryID) {
        ArrayList<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();

        String[] selectionArgs = new String[]{difficulty, String.valueOf(categoryID)};
        Cursor c = db.rawQuery("SELECT * FROM " + QuestionsTable.TABLE_NAME +
                " WHERE " + QuestionsTable.COLUMN_DIFFICULTY + " = ? AND " +
                QuestionsTable.COLUMN_CATEGORY_ID + " = ?", selectionArgs);

        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setId(c.getInt(c.getColumnIndex(QuestionsTable._ID)));
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NR)));
                question.setDifficulty(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_DIFFICULTY)));
                question.setCategoryID(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_CATEGORY_ID)));
                questionList.add(question);
            } while (c.moveToNext());
        }

        c.close();
        return questionList;
    }
}