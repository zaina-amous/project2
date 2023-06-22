package bzu.edu.project;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class QuizSharedPreferences {

    private static final String SHARED_PREF_NAME = "quiz_preferences";
    private static final String KEY_QUESTION_LIST = "question_list";

    public static void saveQuestionList(Context context, ArrayList<Quiz.Question> questionList) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        Gson gson = new Gson();
        String questionListJson = gson.toJson(questionList);

        editor.putString(KEY_QUESTION_LIST, questionListJson);
        editor.apply();
    }

    public static ArrayList<Quiz.Question> loadQuestionList(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        String questionListJson = sharedPreferences.getString(KEY_QUESTION_LIST, null);

        if (questionListJson != null) {
            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<Quiz.Question>>() {}.getType();
            return gson.fromJson(questionListJson, listType);
        }

        return new ArrayList<>(); // Return an empty list if no data is found
    }
}
