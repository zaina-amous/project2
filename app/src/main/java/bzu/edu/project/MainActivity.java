package bzu.edu.project;

import android.graphics.Color;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    // Time to wait before moving to the next question
    private static final long DELAY_MS = 2000;

    private Quiz quiz;
    private int currentQuestionIndex;
    private int score;

    private RecyclerView recyclerView;
    private TextView scoreView;

    private ArrayList<Quiz.Question> questionList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the RecyclerView
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        MyAdapter adapter = new MyAdapter(this, questionList);
        recyclerView.setAdapter(adapter);

        // Initialize other UI elements
        scoreView = findViewById(R.id.score);

        // Load the question list from SharedPreferences
        questionList = QuizSharedPreferences.loadQuestionList(this);

        // If the question list is empty, create and save a new quiz
        if (questionList.isEmpty()) {
            setupQuiz();
            QuizSharedPreferences.saveQuestionList(this, questionList);
        }

        // Initialize quiz state
        currentQuestionIndex = 0;
        score = 0;

        // Show the first question
        showQuestion();
    }

    private void setupQuiz() {
        String[] planetNames = getResources().getStringArray(R.array.planets_array);
        String[] planetQuestions = getResources().getStringArray(R.array.question_array);

        for (int i = 0; i < planetQuestions.length; i++) {
            List<String> answers = Collections.singletonList(planetNames[i]);
            Quiz.Question question = new Quiz.Question(planetQuestions[i], answers, planetNames[i]);
            questionList.add(question);
        }
    }

    private void showQuestion() {
        Quiz.Question currentQuestion = questionList.get(currentQuestionIndex);

        MyAdapter adapter = (MyAdapter) recyclerView.getAdapter();
        adapter.notifyDataSetChanged();

        // Update the score view
        scoreView.setText("Score: " + score);

        // Go to the next question after a delay
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                currentQuestionIndex++;

                if (currentQuestionIndex < questionList.size()) {
                    showQuestion();
                } else {
                    endQuiz();
                }
            }
        }, DELAY_MS);
    }

    private void endQuiz() {
        // Hide the RecyclerView and show the final score
        recyclerView.setVisibility(View.GONE);
        scoreView.setVisibility(View.VISIBLE);

        String scoreText = "You got " + score + " out of " + questionList.size() + " questions correct.";
        if (score <= 5) {
            scoreText += " Try harder next time :(";
        } else if (score > 5 && score < 10) {
            scoreText += " Bravo!!!";
        } else if (score == 10) {
            scoreText = score + " You aced it!";
        }

        scoreView.setText(scoreText);
    }

    public void checkAnswer(String selectedAnswer, String correctAnswer) {
        if (selectedAnswer.equals(correctAnswer)) {
            score++;
        }
    }
}
