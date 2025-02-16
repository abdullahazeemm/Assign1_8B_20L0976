package com.example.assign1;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.assign1.databinding.ActivityQuizScreenBinding;

public class Quiz_screen extends AppCompatActivity {
    RadioGroup rgAnswer;
    Button btnNext;
    Button btnPrev;
    TextView tvQuestion;
    TextView tvCounter;
    RadioButton rbAns1;
    RadioButton rbAns2;
    RadioButton rbAns3;
    RadioButton rbAns4;
    int[] result = new int[10];
    int counter=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_quiz_screen);
        init();
        Intent i = getIntent();
        String name = i.getStringExtra("name");
        Question[] questions = {
                new Question(
                        "What is the primary IDE used for Java-based Android development?",
                        new String[]{"A. Eclipse", "B. NetBeans", "C. Visual Studio", "D. Android Studio"},
                        4
                ),
                new Question(
                        "Which component is responsible for managing the UI in an Android app?",
                        new String[]{"A. Service", "B. Content Provider", "C. Activity", "D. Broadcast Receiver"},
                        3
                ),
                new Question(
                        "Which layout in Android arranges elements in a single horizontal or vertical row?",
                        new String[]{"A. FrameLayout", "B. RelativeLayout", "C. ConstraintLayout", "D. LinearLayout"},
                        4
                ),
                new Question(
                        "Which method is called when an Activity becomes visible to the user?",
                        new String[]{"A. onCreate()", "B. onStart()", "C. onResume()", "D. onPause()"},
                        2
                ),
                new Question(
                        "Which of the following is used for background tasks in Android?",
                        new String[]{"A. IntentService", "B. RecyclerView", "C. ViewPager", "D. CardView"},
                        1
                ),
                new Question(
                        "What is the purpose of the AndroidManifest.xml file?",
                        new String[]{"A. To define the UI layout", "B. To declare app permissions and activities",
                                "C. To store string resources", "D. To manage database operations"},
                        2
                ),
                new Question(
                        "Which data storage option is used for storing key-value pairs in Android?",
                        new String[]{"A. SQLite", "B. SharedPreferences", "C. ContentProvider", "D. Internal Storage"},
                        2
                ),
                new Question(
                        "Which component is used to display a scrollable list of items in Android?",
                        new String[]{"A. RecyclerView", "B. CardView", "C. ViewPager", "D. GridLayout"},
                        1
                ),
                new Question(
                        "Which method is used to start a new Activity in Android?",
                        new String[]{"A. startActivity()", "B. onCreate()", "C. onResume()", "D. onDestroy()"},
                        1
                ),
                new Question(
                        "Which library is commonly used for image loading in Android?",
                        new String[]{"A. Retrofit", "B. Picasso", "C. Room", "D. ButterKnife"},
                        2
                )
        };

        tvQuestion.setText(questions[counter].question);
        rbAns1.setText(questions[counter].options[0]);
        rbAns2.setText(questions[counter].options[1]);
        rbAns3.setText(questions[counter].options[2]);
        rbAns4.setText(questions[counter].options[3]);
        tvCounter.setText((counter+1)+"/10");



        btnNext.setOnClickListener((v)->{
            int idAnswer = rgAnswer.getCheckedRadioButtonId();
            if (idAnswer == R.id.rbAns1){
                idAnswer = 1;
            } else if (idAnswer == R.id.rbAns2) {
                idAnswer = 2;
            } else if (idAnswer == R.id.rbAns3) {
                idAnswer = 3;
            } else if(idAnswer == R.id.rbAns4){
                idAnswer = 4;
            } else{
                idAnswer = -1;
            }

            if(idAnswer!=-1)
            {
                if(idAnswer == questions[counter].correctAnswer) {
                    result[counter]=1;
                }
                counter += 1;
                rgAnswer.clearCheck();
                if(counter<10) {
                    tvCounter.setText((counter + 1) + "/10");
                    tvQuestion.setText(questions[counter].question);
                    rbAns1.setText(questions[counter].options[0]);
                    rbAns2.setText(questions[counter].options[1]);
                    rbAns3.setText(questions[counter].options[2]);
                    rbAns4.setText(questions[counter].options[3]);
                    if (counter == 9) {
                        btnNext.setText("Finish");
                    }
                } else{
                    int sum = 0;
                    for (int n = 0; n < result.length; n++) {
                        if (result[n] == 1) {
                            sum += 1;
                        }
                    }
                    Intent intent = new Intent(Quiz_screen.this, com.example.assign1.Final_result.class);
                    intent.putExtra("name", name);
                    intent.putExtra("sum", sum);
                    startActivity(intent);
                    finish();}
            }else
            {
                Toast.makeText(this, "Select Answer", Toast.LENGTH_SHORT).show();
            }
        });

        btnPrev.setOnClickListener((v)->{
            int idAnswer = rgAnswer.getCheckedRadioButtonId();
            if(idAnswer!=-1)
            {
                if(idAnswer == questions[counter].correctAnswer) {
                    result[counter]=1;
                } else {
                    result[counter]=0;
                }
            }
            if(counter != 0) {
                counter -= 1;
                rgAnswer.clearCheck();
                tvQuestion.setText(questions[counter].question);
                rbAns1.setText(questions[counter].options[0]);
                rbAns2.setText(questions[counter].options[1]);
                rbAns3.setText(questions[counter].options[2]);
                rbAns4.setText(questions[counter].options[3]);
                tvCounter.setText((counter+1)+"/10");
            }
        });

    }

    private void init(){
        rgAnswer = findViewById(R.id.rgAnswer);
        btnNext = findViewById(R.id.btnStart);
        btnPrev = findViewById(R.id.btnPrev);
        tvCounter = findViewById(R.id.tvCounter);
        tvQuestion = findViewById(R.id.tvQuestion);
        rbAns1 = findViewById(R.id.rbAns1);
        rbAns2 = findViewById(R.id.rbAns2);
        rbAns3 = findViewById(R.id.rbAns3);
        rbAns4 = findViewById(R.id.rbAns4);
    }

    static class Question {
        String question;
        String[] options;
        int correctAnswer;

        // Constructor
        public Question(String question, String[] options, int correctAnswer) {
            this.question = question;
            this.options = options;
            this.correctAnswer = correctAnswer;
        }
    }
}
