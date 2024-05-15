package com.example.finlandweather;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class QuizActivity extends AppCompatActivity {

    private List<Question> questionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quizlayout);


        initializeQuestions();


        displayQuestions();


        ImageButton homeButton = findViewById(R.id.homeButton);
        homeButton.setOnClickListener(view -> {

            Intent intent = new Intent(QuizActivity.this, MainActivity.class);
            startActivity(intent);
        });


        ImageButton settingsButton = findViewById(R.id.settingsbutton);


        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(QuizActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });


    }

    private void initializeQuestions() {
        questionList = new ArrayList<>();
        // This are all the 50 questions
        questionList.add(new Question("What is the capital of Finland?", "Helsinki", "Oslo", "Stockholm", "Copenhagen","Helsinki"));
        questionList.add(new Question("Which of the following is a traditional Finnish dish?", "Karjalanpiirakka", "Paella", "Sushi", "Spaghetti", "Karjalanpiirakka"));
        questionList.add(new Question("What was the unemployment rate in Finland in 2010?", "8.4%", "5.2%", "10.1%", "12.8%", "8.4%"));
        questionList.add(new Question("In which year did Finland have the highest GDP growth rate in the 2000s?", "2000", "2007", "2005", "2009", "2000"));
        questionList.add(new Question("What percentage of Finland's population lives in urban areas as of 2020?", "85%", "72%", "60%", "93%", "85%"));
        questionList.add(new Question("How many births were recorded in Finland in 2015?", "59,840", "42,510", "71,326", "64,912", "59,840"));
        questionList.add(new Question("What was the average life expectancy in Finland in 1995?", "76.5 years", "70.2 years", "81.3 years", "68.9 years", "76.5 years"));
        questionList.add(new Question("Which Finnish city had the highest population growth rate between 2000 and 2010?", "Espoo", "Tampere", "Turku", "Oulu", "Espoo"));
        questionList.add(new Question("What was the median income in Finland in 2018?", "€3,310 per month", "€2,150 per month", "€4,520 per month", "€1,890 per month", "€3,310 per month"));
        questionList.add(new Question("How many international tourists visited Finland in 2019?", "6.7 million", "3.5 million", "9.2 million", "5.1 million", "6.7 million"));
        questionList.add(new Question("What percentage of Finland's land area is covered by forests?", "72%", "60%", "85%", "50%", "72%"));
        questionList.add(new Question("Which sector contributes the most to Finland's GDP as of 2022?", "Services", "Industry", "Agriculture", "Construction", "Services"));
        questionList.add(new Question("What was the total population of Finland in 1990?", "4.98 million", "3.75 million", "6.21 million", "2.89 million", "4.98 million"));
        questionList.add(new Question("Which year saw the highest number of marriages in Finland during the 2000s?", "2006", "2002", "2009", "2004", "2006"));
        questionList.add(new Question("What percentage of Finland's energy consumption came from renewable sources in 2015?", "38%", "25%", "51%", "14%", "38%"));
        questionList.add(new Question("How many Nobel Prizes in Physiology or Medicine have been awarded to Finnish laureates as of 2020?", "4", "2", "6", "1", "4"));
        questionList.add(new Question("What was the average household size in Finland in 2010?", "2.2 people", "3.1 people", "1.8 people", "2.8 people", "2.2 people"));
        questionList.add(new Question("Which Finnish city has the highest proportion of foreign-born residents?", "Helsinki", "Tampere", "Turku", "Oulu", "Helsinki"));
        questionList.add(new Question("What was Finland's inflation rate in 2018?", "1.2%", "3.5%", "0.8%", "2.7%", "1.2%"));
        questionList.add(new Question("How many universities are there in Finland as of 2022?", "13", "8", "16", "10", "13"));
        questionList.add(new Question("What percentage of Finland's workforce is employed in the information and communication sector?", "6.8%", "9.3%", "4.2%", "12.1%", "6.8%"));
        questionList.add(new Question("What was the total value of Finland's exports in 2021?", "€67.4 billion", "€52.1 billion", "€81.6 billion", "€45.8 billion", "€67.4 billion"));
        questionList.add(new Question("What was the highest recorded temperature in Finland in 2019?", "33.7°C", "28.9°C", "36.4°C", "31.2°C", "33.7°C"));
        questionList.add(new Question("What percentage of Finnish households owned a car in 2005?", "81%", "69%", "92%", "75%", "81%"));
        questionList.add(new Question("How many hours of daylight does Helsinki receive on the shortest day of the year?", "6 hours", "4 hours", "8 hours", "3 hours", "6 hours"));
        questionList.add(new Question("What percentage of Finland's population is under 15 years old as of 2020?", "16%", "22%", "10%", "28%", "16%"));
        questionList.add(new Question("What was Finland's total fertility rate in 2017?", "1.49 children per woman", "1.82 children per woman", "1.27 children per woman", "2.05 children per woman", "1.49 children per woman"));
        questionList.add(new Question("Which Finnish city has the highest median income?", "Espoo", "Helsinki", "Vantaa", "Tampere", "Espoo"));
        questionList.add(new Question("What was the average annual temperature in Finland in 2016?", "6.7°C", "4.3°C", "8.9°C", "5.1°C", "6.7°C"));
        questionList.add(new Question("How many national parks are there in Finland?", "40", "25", "30", "35", "40"));
        questionList.add(new Question("What percentage of Finnish households have access to the internet?", "95%", "87%", "82%", "99%", "95%"));
        questionList.add(new Question("What was Finland's GDP per capita in 2022?", "€45,782", "€39,204", "€51,319", "€36,890", "€45,782"));
        questionList.add(new Question("What was the total number of registered vehicles in Finland in 2015?", "4.7 million", "3.2 million", "5.5 million", "2.9 million", "4.7 million"));
        questionList.add(new Question("How many homicides were recorded in Finland in 2020?", "77", "54", "92", "63", "77"));
        questionList.add(new Question("What percentage of Finland's electricity production comes from nuclear power as of 2021?", "34%", "42%", "28%", "51%", "34%"));
        questionList.add(new Question("What was the literacy rate in Finland in 1995?", "99%", "92%", "97%", "85%", "99%"));
        questionList.add(new Question("Which year marked the highest level of foreign direct investment (FDI) inflows into Finland during the 2010s?", "2017", "2013", "2019", "2015", "2017"));
        questionList.add(new Question("What percentage of Finland's population is over 65 years old as of 2020?", "21%", "15%", "27%", "32%", "21%"));
        questionList.add(new Question("How many official languages does Finland have?", "2", "1", "3", "4", "2"));
        questionList.add(new Question("What was the average household debt in Finland in 2018?", "€120,000", "€85,000", "€150,000", "€65,000", "€120,000"));
        questionList.add(new Question("Which Finnish company had the highest revenue in 2020?", "Nokia", "Fortum", "Kone", "Stora Enso", "Nokia"));
        questionList.add(new Question("What percentage of Finland's land area is covered by lakes?", "10%", "22%", "31%", "15%", "10%"));
        questionList.add(new Question("How many sauna sessions per week does the average Finn have?", "2-3", "1-2", "3-4", "4-5", "2-3"));
        questionList.add(new Question("What was Finland's rank in the World Happiness Report 2021?", "7th", "12th", "4th", "15th", "7th"));
        questionList.add(new Question("How many kilometers of coastline does Finland have?", "1,100 km", "650 km", "1,500 km", "850 km", "1,100 km"));
        questionList.add(new Question("What percentage of Finland's workforce is employed in the public sector?", "24%", "30%", "18%", "35%", "24%"));
        questionList.add(new Question("What was the total number of asylum applications received by Finland in 2018?", "6,582", "9,210", "4,135", "12,367", "6,582"));
        questionList.add(new Question("Which Finnish municipality has the highest population density?", "Helsinki", "Espoo", "Vantaa", "Tampere", "Helsinki"));
        questionList.add(new Question("What percentage of Finland's electricity comes from wind power as of 2022?", "7%", "12%", "4%", "9%", "7%"));
        questionList.add(new Question("What was Finland's rank in the Global Gender Gap Report 2020?", "3rd", "8th", "5th", "10th", "3rd"));
        questionList.add(new Question("How many hours of sunlight does Finland receive during the summer solstice in Lapland?", "24 hours", "22 hours", "20 hours", "18 hours", "24 hours"));
        questionList.add(new Question("What percentage of Finland's population belongs to the Swedish-speaking minority?", "5%", "10%", "15%", "20%", "5%"));


        Collections.shuffle(questionList);

        List<Question> selectedQuestions = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            int index = random.nextInt(questionList.size());
            selectedQuestions.add(questionList.get(index));
            questionList.remove(index);
        }


        questionList = selectedQuestions;
    }


    private HashMap<Integer, String> userAnswers = new HashMap<>();
    private void displayQuestions() {
        LinearLayout linearLayout = findViewById(R.id.quizLinearLayout);
        int questionNumber = 1;

        for (Question question : questionList) {

            TextView numberTextView = new TextView(this);
            numberTextView.setText(questionNumber + "/" + questionList.size());
            numberTextView.setTextColor(Color.BLACK);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(0, 20, 0, 0);
            numberTextView.setLayoutParams(params);
            linearLayout.addView(numberTextView);


            TextView textView = new TextView(this);
            textView.setText(question.getQuestion());
            textView.setTextSize(20);
            textView.setTextColor(Color.BLACK);
            params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(0, 20, 0, 0);
            textView.setLayoutParams(params);
            linearLayout.addView(textView);


            RadioGroup radioGroup = new RadioGroup(this);
            radioGroup.setOrientation(LinearLayout.VERTICAL);


            RadioButton radioButton1 = new RadioButton(this);
            radioButton1.setText(question.getShuffledOptions().get(0));
            radioGroup.addView(radioButton1);

            RadioButton radioButton2 = new RadioButton(this);
            radioButton2.setText(question.getShuffledOptions().get(1));
            radioGroup.addView(radioButton2);

            RadioButton radioButton3 = new RadioButton(this);
            radioButton3.setText(question.getShuffledOptions().get(2));
            radioGroup.addView(radioButton3);

            RadioButton radioButton4 = new RadioButton(this);
            radioButton4.setText(question.getShuffledOptions().get(3));
            radioGroup.addView(radioButton4);

            params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(0, 10, 0, 0);
            radioGroup.setLayoutParams(params);
            linearLayout.addView(radioGroup);


            questionNumber++;

            int questionIndex = questionNumber - 1;
            radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
                RadioButton checkedRadioButton = (RadioButton) group.findViewById(checkedId);
                String selectedOption = checkedRadioButton.getText().toString();
                userAnswers.put(questionIndex, selectedOption);
            });
        }


        Button submitButton = new Button(this);
        submitButton.setText("Submit");
        submitButton.setTextColor(Color.WHITE);
        submitButton.setBackgroundColor(Color.BLACK);
        submitButton.setPadding(40, 20, 40, 20);
        submitButton.setGravity(Gravity.CENTER);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(0, 40, 0, 0);
        submitButton.setLayoutParams(params);
        linearLayout.addView(submitButton);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int correctAnswers = 0;
                for (int i = 1; i <= questionList.size(); i++) {
                    String userAnswer = userAnswers.get(i);
                    if (userAnswer != null) {
                        String correctAnswer = questionList.get(i - 1).getCorrectAnswer();
                        Log.d("QuizActivity", "Question " + i + ": User answer = " + userAnswer + ", Correct answer = " + correctAnswer);
                        if (userAnswer.equals(correctAnswer)) {
                            correctAnswers++;
                        }
                    }
                }


                showScoreAlert(correctAnswers);
            }
        });




    }

    private void showScoreAlert(int score) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        String title;
        if (score <= 1) {
            title = "👎Poor";
        } else if (score < 4) {
            title = "🤞Better Luck Next Time";
        } else if (score <= 6) {
            title = "👍Very Good";
        } else if (score <= 9) {
            title = "👏You Did Amazing";
        } else {
            title = "👌Perfect Score";
        }
        builder.setTitle(title);

        String message;
        if (score <= 1) {
            message = ". I know you can better than that!";
        } else if (score < 4) {
            message = ", But there is no shame in trying.";
        } else if (score <= 6) {
            message = ". However, there is still room for improvement.";
        } else if (score <= 9) {
            message = ". That is really a fantastic score ";
        } else {
            message = ". I know your brain is on fire😂🥳.";
        }
        builder.setMessage("You got " + score + " out of 10" + message);
        builder.setPositiveButton("Restart", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                recreate();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

}


class Question {
    private List<String> options;
    private String question;
    private String correctAnswer;


    public Question(String question, String option1, String option2, String option3, String option4, String correctAnswer) {
        this.question = question;
        this.options = new ArrayList<>();
        options.add(option1);
        options.add(option2);
        options.add(option3);
        options.add(option4);
        Collections.shuffle(options);
        this.correctAnswer = correctAnswer;
    }

    public String getQuestion() {
        return question;
    }


    public List<String> getShuffledOptions() {
        return options;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }


    public List<String> getOriginalOptions() {

        return new ArrayList<>(options);
    }
}
