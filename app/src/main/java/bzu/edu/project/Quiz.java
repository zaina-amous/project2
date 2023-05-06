package bzu.edu.project;


    import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

    public class Quiz {

       //name of all the planets in our solar system
        private static final String[] PLANETS = {"Mercury", "Venus", "Earth", "Mars", "Jupiter", "Saturn", "Uranus", "Neptune"};
       //selects random planets for the button texts
        private static final Random random = new Random();
//list of questioons to ask
        private List<Question> questions;
    private String questionText , correctAnswer;

      //create an object of type quiz witha specific num of questions
        public Quiz(int numQuestions) {
            questions = new ArrayList<>();

            //displaying the question depending on the correct planet
            for (int i = 0; i < numQuestions; i++) {
                String planet = PLANETS[random.nextInt(PLANETS.length)];
                questionText = "What planet is known for its rings?";
                correctAnswer = "Saturn";
                if (planet.equals("Saturn")) {
                    correctAnswer = "Saturn";
                    questionText = "What planet is the second largest in our solar system?";
                } else if (planet.equals("Jupiter")) {
                    correctAnswer = "Jupiter";
                    questionText = "What planet is known for its Great Red Spot?";
                } else if (planet.equals("Neptune")) {
                    correctAnswer = "Neptune";
                    questionText = "What planet is farthest from the sun?";
                } else if (planet.equals("Uranus")) {
                    correctAnswer = "Uranus";
                    questionText = "What planet is tilted on its side?";
                } else if (planet.equals("Venus")) {
                    correctAnswer = "Venus";
                    questionText = "What planet is known as the Morning Star or Evening Star?";
                } else if (planet.equals("Mars")) {
                    correctAnswer = "Mars";
                    questionText = "What planet is often referred to as the Red Planet?";
                } else if (planet.equals("Mercury")) {
                    correctAnswer = "Mercury";
                    questionText = "What planet is closest to the sun?";
                } else if (planet.equals("Earth")) {
                    correctAnswer = "Earth";
                    questionText = "What planet is home to humans?";
                }
                //this is only to generate a random answer options
                List<String> answers = new ArrayList<>();
                answers.add(correctAnswer);
                //only 3 options since the 4th one is going to be our correct answer
                while (answers.size() < 4) {
                    String randomPlanet = PLANETS[random.nextInt(PLANETS.length)];
                    if (!answers.contains(randomPlanet)) {
                        answers.add(randomPlanet);
                    }
                }
                Collections.shuffle(answers);
                Question question = new Question(questionText, answers, correctAnswer);
                questions.add(question);
            }
        }

//to create question
        public List<Question> getQuestions() {
            return questions;
        }

        //inner class
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


