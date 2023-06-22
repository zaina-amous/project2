package bzu.edu.project;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<Quiz.Question> model;

    public MyAdapter(Context context, ArrayList<Quiz.Question> model) {
        this.context = context;
        this.model = model;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Quiz.Question currentQuestion = model.get(position);
        holder.textView.setText(currentQuestion.getQuestionText());
        List<String> answers = currentQuestion.getAnswers();
        holder.option1Button.setText(answers.get(0));
        holder.option2Button.setText(answers.get(1));
        holder.option3Button.setText(answers.get(2));
        holder.option4Button.setText(answers.get(3));

        // Set onClick listeners for answer buttons
        holder.option1Button.setOnClickListener(view -> {
            checkAnswer(holder.option1Button, answers.get(0), currentQuestion.getCorrectAnswer());
        });
        holder.option2Button.setOnClickListener(view -> {
            checkAnswer(holder.option2Button, answers.get(1), currentQuestion.getCorrectAnswer());
        });
        holder.option3Button.setOnClickListener(view -> {
            checkAnswer(holder.option3Button, answers.get(2), currentQuestion.getCorrectAnswer());
        });
        holder.option4Button.setOnClickListener(view -> {
            checkAnswer(holder.option4Button, answers.get(3), currentQuestion.getCorrectAnswer());
        });
    }

    @Override
    public int getItemCount() {
        return model.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        Button option1Button, option2Button, option3Button, option4Button;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.questionTextView);
            option1Button = itemView.findViewById(R.id.option1Button);
            option2Button = itemView.findViewById(R.id.option2Button);
            option3Button = itemView.findViewById(R.id.option3Button);
            option4Button = itemView.findViewById(R.id.option4Button);
        }
    }

    private void checkAnswer(Button answerButton, String selectedAnswer, String correctAnswer) {
        answerButton.setEnabled(false);
        if (selectedAnswer.equals(correctAnswer)) {
            answerButton.setBackgroundColor(Color.GREEN);
        } else {
            answerButton.setBackgroundColor(Color.RED);
            Button correctButton;
            MyViewHolder holder = (MyViewHolder) answerButton.getTag();
            if (correctAnswer.equals(holder.option1Button.getText())) {
                correctButton = holder.option1Button;
            } else if (correctAnswer.equals(holder.option2Button.getText())) {
                correctButton = holder.option2Button;
            } else if (correctAnswer.equals(holder.option3Button.getText())) {
                correctButton = holder.option3Button;
            } else {
                correctButton = holder.option4Button;
            }
            correctButton.setBackgroundColor(Color.GREEN);
        }
    }
}
