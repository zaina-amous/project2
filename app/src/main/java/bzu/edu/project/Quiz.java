package bzu.edu.project;

import android.content.Context;
import android.content.res.Resources;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Quiz {
    private static  String[] PLANETS;
    private static  String[] QUESTIONS;

    private List<Question> questions;

    public Quiz(Context context, int numQuestions) {
        Resources resources = context.getResources();
        PLANETS = resources.getStringArray(R.array.planets_array);
        QUESTIONS = resources.getStringArray(R.array.question_array);

        questions = new ArrayList<>();

        Random random = new Random();
        for (int i = 0; i < numQuestions; i++) {
            String planet = PLANETS[random.nextInt(PLANETS.length)];
            String questionText = getQuestionText(planet);
            String correctAnswer = getCorrectAnswer(planet);
            List<String> answers = generateRandomAnswers(correctAnswer);

            Question question = new Question(questionText, answers, correctAnswer);
            questions.add(question);
        }
    }

    private String getQuestionText(String planet) {
        if (planet.equals("Saturn")) {
            return QUESTIONS[1];
        } else if (planet.equals("Jupiter")) {
            return QUESTIONS[2];
        } else if (planet.equals("Neptune")) {
            return QUESTIONS[8];
        } else if (planet.equals("Uranus")) {
            return QUESTIONS[3];
        } else if (planet.equals("Venus")) {
            return QUESTIONS[4];
        } else if (planet.equals("Mars")) {
            return QUESTIONS[5];
        } else if (planet.equals("Mercury")) {
            return QUESTIONS[6];
        } else if (planet.equals("Earth")) {
            return QUESTIONS[7];
        } else {
            // Default question text
            return QUESTIONS[0];
        }
    }

    private String getCorrectAnswer(String planet) {
        switch (planet) {
            case "Saturn":
                return PLANETS[5];
            case "Jupiter":
                return PLANETS[4];
            case "Neptune":
                return PLANETS[7];
            case "Uranus":
                return PLANETS[6];
            case "Venus":
                return PLANETS[1];
            case "Mars":
                return PLANETS[3];
            case "Mercury":
                return PLANETS[0];
            case "Earth":
                return PLANETS[2];
            default:
                // Default correct answer
                return PLANETS[5];
        }
    }

    private List<String> generateRandomAnswers(String correctAnswer) {
        List<String> answers = new ArrayList<>();
        answers.add(correctAnswer);

        Random random = new Random();
        while (answers.size() < 4) {
            String randomPlanet = PLANETS[random.nextInt(PLANETS.length)];
            if (!answers.contains(randomPlanet)) {
                answers.add(randomPlanet);
            }
        }

        Collections.shuffle(answers);
        return answers;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public static class Question {
        private final String questionText;
        private final List<String> answers;
        private final String correctAnswer;

        public Question(String questionText, List<String> answers, String correctAnswer) {
            this.questionText = questionText;
            this.answers = answers;
            this.correctAnswer = correctAnswer;
        }

        public String getQuestionText() {
            return questionText;
        }

        public List<String> getAnswers() {
            return answers;
        }

        public String getCorrectAnswer() {
            return correctAnswer;
        }
    }
}
