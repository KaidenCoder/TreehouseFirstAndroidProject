package treehousefirstandroid.kurosaki.quizapptreehouse;

import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private UserQuestion mUserQuestion = new UserQuestion();
    private QuestionBank mQuestionBank = new QuestionBank();

    private TextView mQuestion;
    private TextView mProgress;
    private RadioGroup mRadioGroup;
    RadioButton mOptionButtonOne;
    RadioButton mOptionButtonTwo;
    RadioButton mOptionButtonThree;
    RadioButton mOptionButtonFour;
    RadioButton mOptionButtonFive;
    Button mNextButton;
    private int count = 0;
    private int totalCount = 0;
    private int mAnswer;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {

            Intent intent = getIntent();
            count = intent.getIntExtra("count", 0);
            totalCount = intent.getIntExtra("totalScore", 0);

        } else {
            count = savedInstanceState.getInt("count");
            totalCount = savedInstanceState.getInt("totalScore");
        }

        mQuestion = findViewById(R.id.question);
        mProgress = findViewById(R.id.progress);
        mRadioGroup = findViewById(R.id.options);
        mOptionButtonOne = findViewById(R.id.firstoption);
        mOptionButtonTwo = findViewById(R.id.secondoption);
        mOptionButtonThree = findViewById(R.id.thirdoption);
        mOptionButtonFour = findViewById(R.id.fourthoption);
        mOptionButtonFive = findViewById(R.id.fifthoption);
        mNextButton = findViewById(R.id.button);
        final RadioButton[] mButtons = {
                mOptionButtonOne,
                mOptionButtonTwo,
                mOptionButtonThree,
                mOptionButtonFour,
                mOptionButtonFive
        };

        Quiz();

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                totalCount++;

                for(RadioButton button : mButtons){
                    if (button.isChecked() && button.getText().toString().equals(mAnswer+"")) {
                        count++;
                        Toast.makeText(MainActivity.this, "Correct Answer!", Toast.LENGTH_SHORT).show();
                    }
                    if (button.isChecked() && !button.getText().toString().equals(mAnswer+"")) {
                        Toast.makeText(MainActivity.this, "Incorrect Answer!", Toast.LENGTH_SHORT).show();
                    }
                }
                mRadioGroup.clearCheck();
                Quiz();
            }
        };
        mNextButton.setOnClickListener(listener);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("count",count);
        outState.putInt("totalScore",totalCount);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        count = savedInstanceState.getInt("count");
        totalCount = savedInstanceState.getInt("totalScore");

    }

    private void Quiz() {
        final RadioButton[] mButtons = {
                mOptionButtonOne,
                mOptionButtonTwo,
                mOptionButtonThree,
                mOptionButtonFour,
                mOptionButtonFive
        };
        int firstNumber = mQuestionBank.getLeft();
        int secondNumber = mQuestionBank.getRight();
        mAnswer = firstNumber + secondNumber;;
        Random random = new Random();
        int randomButtonNum = random.nextInt(mButtons.length);
        RadioButton radioButton = mButtons[randomButtonNum];
        for (RadioButton button : mButtons) {
            button.setText(random.nextInt(190) + "");
        }
        radioButton.setText(mAnswer + "");

        String question = mUserQuestion.formQuestion(firstNumber, secondNumber);
        mQuestion.setText(question);

        mProgress.setText(mUserQuestion.formProgress(count, totalCount));
    }
}
