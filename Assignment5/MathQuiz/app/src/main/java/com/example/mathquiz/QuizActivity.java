package com.example.mathquiz;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class QuizActivity extends AppCompatActivity {
    private String difficulty; // the selected difficulty
    private int diffState; // holds an int value associated to the selected difficulty value
    private int score = 0; // keeps track of how many questions in a row are the same
    private int errCount = 0; // keeps track of number of incorrect guesses per question
    private QuestionValues[][] qPairs = new QuestionValues[10][2]; // holds past 10 questions to ensure none are the same (dubious method at best tbh)
    private int qCount = 0; // keeps track of which "index" of the question history we are at

    /**
     * Determine if the sum of two integers would result in a carry over
     * @return true iff of x and y would result in a "carry over" +1
     */
    private boolean carryOver(int x, int y){
        return x + y >= 10;
    }

    /**
     * Parses the "answer" field of the quiz into just the non-whitespace values
     * @param answerTxt
     * @return ret
     */
    private String getAnswerNums(String answerTxt) {
        String[] answerSplit = answerTxt.split(" ");
        String ret = "";

        for (String i : answerSplit){
            if (!i.equals(" ")){
                ret += i;
            }
        }

        return ret;
    }

    /**
     * Generates an addition question based on the specified difficulty level
     * @return ret, a 2d String array with the generated values
     */
    private String[] questionPair(){
        // Log.v("questionPair","Started RNG");
        int[] p1 = new int[2];
        int[] p2 = new int[2];
        boolean fitDiff = false;
        String[] ret = new String[2];

        while(!fitDiff) {
            // generate random values for it
            p1[0] = (int) (Math.random() * 9 + 1);
            p1[1] = (int) (Math.random() * 10);
            p2[0] = (int) (Math.random() * 9 + 1);
            p2[1] = (int) (Math.random() * 10);

            // checks if the random question values fit the difficulty and are unique for
            // the set of questions.
            switch(diffState){
                case 0:
                    // easy difficulty - no carry over in tens and ones
                    if (!carryOver(p1[0], p2[0]) && !carryOver(p1[1], p2[1]) && !qContains(p1, p2)){
                        fitDiff = true;
                    }
                    break;
                case 1:
                    // medium difficulty - carry over in tens OR ones place only
                    if (!((!carryOver(p1[0], p2[0]) && !carryOver(p1[1], p2[1])) || (carryOver(p1[0], p2[0]) && carryOver(p1[1], p2[1])))
                            && !(carryOver(p1[1], p2[1]) && p1[0] + p2[0] == 9) && !qContains(p1, p2)){
                        fitDiff = true;
                    }
                    break;
                case 2:
                    // hard difficulty - carry over in tens AND ones
                    if (((carryOver(p1[0], p2[0]) && carryOver(p1[1], p2[1]))|| (carryOver(p1[1], p2[1]) && p1[0] + p2[0] == 9)) && !qContains(p1, p2)){
                        fitDiff = true;
                    }
                    break;
            }
        }

        // add the pair to question history
        QuestionValues qv1 = new QuestionValues(p1[0], p1[1]);
        QuestionValues qv2 = new QuestionValues(p2[0], p2[1]);

        // reset qCount if reaches the end of the array
        if(qCount >= 10) {
            qCount = 0;
        }

        Log.v("questionPair", "qCount: " + qCount);

        qPairs[qCount] = new QuestionValues[]{qv1, qv2};
        qCount++;


        // convert the question pair to something that can be displayed
        ret[0] = Integer.toString(p1[0]).concat(Integer.toString(p1[1]));
        ret[1] = Integer.toString(p2[0]).concat(Integer.toString(p2[1]));
        return ret;
    }

    /**
     * Given a pair of numbers, check if that pair in that order has appeared in the past 10 questions
     * @param p1
     * @param p2
     * @return iff (p1, p2) is not in qPairs
     */
    private boolean qContains(int[] p1, int[] p2){
        for(int i = 0; i < 10; i ++){
            // Log.v("qContains","Comparing " + i +"th pair");
            if(qPairs[i] == null || qPairs[i][0] == null || qPairs[i][1] == null) {
                continue;
            } else {
                int[] qp1 = qPairs[i][0].getPair();
                int[] qp2 = qPairs[i][1].getPair();

                if(qp1[0] == p1[0] && qp1[1] == p1[1] && qp2[0] == p2[0] && qp2[1] == p2[1]) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Checks to see if the entered user sum is correct
     * @param answerTxt, the value the user enetered
     * @return true iff answerTxt == qnum_1 + qnum_2
     */
    private boolean ansCorrect(String answerTxt) {
        // get addends
        QuestionValues[] addends = qPairs[qCount - 1];
        int qSum = addends[0].getVal() + addends[1].getVal();
        int answerInt;

        // catch the case where answerTxt = "?"
        try {
            answerInt = Integer.parseInt(answerTxt);
        } catch (NumberFormatException e) {
            return false;
        }
        return qSum == Integer.parseInt(answerTxt);
    }

    /**
     * Generates a new addition question fitting difficulty setting
     * and resets the answer area (if applicable)
     */
    private void setQuestion() {
        // generate random pair of numbers
        TextView q1V = findViewById(R.id.qnum_1);
        TextView q2V = findViewById(R.id.qnum_2);
        String[] qGen = questionPair();

        q1V.setText("   " + qGen[0]);
        q2V.setText("+ " + qGen[1]);

        // "reset" answer field
        TextView answerView = findViewById(R.id.answer_field);
        answerView.setTextColor(Color.BLUE);
        answerView.setText("    ?");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        // get difficulty
        Bundle b = getIntent().getExtras();
        difficulty = b.getString("Difficulty");
        if (difficulty.equals("Easy")) {
            diffState = 0;
        } else if(difficulty.equals("Medium")) {
            diffState = 1;
        } else {
            diffState = 2;
        }
        Log.v("start","Started Quiz with " + difficulty + " difficulty, diff state: " + diffState);

        // make a new question
        setQuestion();
    }

    public void onUpdateCounterButtonClick(View v){
        // get button name
        String buttonName = getResources().getResourceEntryName(v.getId());
        Log.v("Button Pressed", buttonName);

        TextView answerView = findViewById(R.id.answer_field);
        String answerTxt = answerView.getText().toString();
        answerTxt = getAnswerNums(answerTxt);

        if (buttonName.contains("num")) {
            // update number on screen
            String num = ((Button) v).getText().toString();

            // check if is "?" (replace) or is number already (concat)
            if (answerTxt.contains("?")){
                answerView.setText("    " + num);
            } else {
                // check if trying to input a fourth digit, otherwise update text.
                if (answerTxt.length() < 3){
                    // get the number of (white)spaces needed for formatting
                    String whitespaces = "";
                    for (int i = 0; i < 5 - (num + answerTxt).length(); i++){
                        whitespaces += " ";
                    }
                    answerView.setText(whitespaces + num + answerTxt);
                }
            }

        } else if (buttonName.contains("clear")) {
            // resets the answer field
            answerView.setText("    ?");
        } else {
            boolean reset = true;
            // Check if value is correct - if so, new question and increase counter
            if (ansCorrect(answerTxt)){
                // Log.v("ansCorrect", "Score: " + score);
                score += 1;
                errCount = 0;

                // chain message
                String chain = "";
                if (score > 1) {
                    chain = " " + Integer.toString(score) + " in a row!";
                }

                Toast.makeText(this, "Correct!" + chain, Toast.LENGTH_LONG).show();
            } else if (!ansCorrect(answerTxt) && errCount < 2){
                // incorrect answer + guesses remaining
                errCount++;
                reset = false;

                Toast.makeText(this, "Incorrect! Try Again!" , Toast.LENGTH_LONG).show();
            } else {
                // incorrect answer + no more guesses + show correct answer + L
                score = 0;
                errCount = 0;
                reset = true;

                // get correct answer
                QuestionValues[] addends = qPairs[qCount - 1];
                int qSum = addends[0].getVal() + addends[1].getVal();

                // set the number of whitespaces needed
                String whitespace = "";
                for (int i = 0; i < 5 - Integer.toString(qSum).length(); i++){
                    whitespace += " ";
                }

                answerView.setText(whitespace + qSum);
                answerView.setTextColor(Color.RED);
                Toast.makeText(this, "Incorrect! The correct answer is " + qSum, Toast.LENGTH_LONG).show();
            }

            // 3 second Timer
            Executor executor = Executors.newSingleThreadExecutor();
            if(reset) {
                executor.execute( () -> {
                    try {Thread.sleep(3000);} catch (Exception e) {}

                    setQuestion();
                });
            } else {
                executor.execute( () -> {
                    try {Thread.sleep(3000);} catch (Exception e) {}

                    // "reset" answer field
                    answerView.setTextColor(Color.BLUE);
                    answerView.setText("    ?");
                });
            }

            Log.v("submit", "3 second timer over!");

        }
    }
}
