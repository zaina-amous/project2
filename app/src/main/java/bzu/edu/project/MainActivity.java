package bzu.edu.project;

import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;



public class MainActivity extends AppCompatActivity {

 //time to wait before moving to the next question
    private static final long DELAY_MS =2000 ;
    //quiz instance
    private Quiz quiz;
    private int currentQuestionIndex;
    private int score;

    private TextView questionTextView , scoreView;
    private Button option1Button, option2Button, option3Button, option4Button, answer;

    private PlanetData[] planets = new PlanetData[PlanetData.PLANETS.length];
    private Random random = new Random();
   // private int currentQuestionIndex = -1;
    //private int score = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        // intializing the design elements that are used
        questionTextView = findViewById(R.id.questionTextView);
        option1Button = findViewById(R.id.option1Button);
        option2Button = findViewById(R.id.option2Button);
        option3Button = findViewById(R.id.option3Button);
        option4Button = findViewById(R.id.option4Button);
        scoreView = findViewById(R.id.scoreTextView);
        answer = findViewById(R.id.answer);
      //  next1 = findViewById(R.id.next1);



        // Initialize quiz
         quiz = new Quiz(10);
        currentQuestionIndex = 0;
        score = 0;

        //fisrt question to appear
        showQuestion();
    }

    private void showQuestion() {
        // current ques
        Quiz.Question currentQuestion = quiz.getQuestions().get(currentQuestionIndex);

        // change the question
        questionTextView.setText(currentQuestion.getQuestionText());

        // Set button texts
        List<String> answers = currentQuestion.getAnswers();
        option1Button.setText(answers.get(0));
        option2Button.setText(answers.get(1));
        option3Button.setText(answers.get(2));
        option4Button.setText(answers.get(3));

        // onClick actions depending on answer
        option1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(option1Button, currentQuestion.getCorrectAnswer());
            }
        });
        option2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(option2Button, currentQuestion.getCorrectAnswer());
            }
        });
        option3Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(option3Button, currentQuestion.getCorrectAnswer());
            }
        });
        option4Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(option4Button, currentQuestion.getCorrectAnswer());
            }
        });
    }

  //method to check what button was pressed and if its correcty or not, if not turn the pressed button to red
    //and turn the button with the correct answer to green
    //if the answer is correct increment score by one and just turn the correct answer to green
    private void checkAnswer(Button answerButton, String correctAnswer) {
        // Disable answer buttons
        option1Button.setEnabled(false);
        option2Button.setEnabled(false);
        option3Button.setEnabled(false);
        option4Button.setEnabled(false);

        // Check answer
        if (answerButton.getText().toString().equals(correctAnswer)) {
            // Correct answer
            answerButton.setBackgroundColor(Color.GREEN);
            score++;
        } else {
            // to make sure that the correct answer is within the options
            answerButton.setBackgroundColor(Color.RED);
            Button correctButton = null;
            switch (correctAnswer) {
                case "Mercury":
                    correctButton = option1Button;
                    break;
                case "Venus":
                    correctButton = option2Button;
                    break;
                case "Earth":
                    correctButton = option3Button;
                    break;
                case "Mars":
                    correctButton = option4Button;
                    break;
                case "Jupiter":
                    correctButton = option1Button;
                    break;
            }
            correctButton.setBackgroundColor(Color.GREEN);
        }

        // displaying score
        scoreView.setText("Score: " + score);

        // go to the next question after 2 sec (2000 MS)
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //to reset the colors of the buttons (change the red and green colors that were generated to indicate the right from the wronmg answers )
                option1Button.setBackgroundColor(getResources().getColor(R.color.purple_200));
                option2Button.setBackgroundColor(getResources().getColor(R.color.purple_200));
                option3Button.setBackgroundColor(getResources().getColor(R.color.purple_200));
                option4Button.setBackgroundColor(getResources().getColor(R.color.purple_200));

                // Move to next question or end quiz
                currentQuestionIndex++;
                if (currentQuestionIndex < quiz.getQuestions().size()) {

                    showQuestion();

                    // Enable answer buttons
                    option1Button.setEnabled(true);
                    option2Button.setEnabled(true);
                    option3Button.setEnabled(true);
                    option4Button.setEnabled(true);
                } else {
                   //end quiz which is when questions are over then show score
                    endQuiz();
                }
            }
        }, DELAY_MS);
    }

    private void endQuiz() {
        //change the layout by hiding the button answers to show the resulted score
        answer.setVisibility(View.GONE);

        // show result
        String scoreText = "You got " + score + " out of " + quiz.getQuestions().size() + " questions correct.";
        questionTextView.setText(scoreText);
    }
}





