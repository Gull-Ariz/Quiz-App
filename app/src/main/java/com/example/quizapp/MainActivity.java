package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;

class Question{
    public String statement;
    public String A ;
    public String B ;
    public String C ;
    public String D ;
}
public class MainActivity extends AppCompatActivity {

    private String[] Key = new String[]{"James Gosling","Transfer Statements","JVM","then","()","BothA and B","Oak",
    "A class","new","Braces"};
    RadioGroup[] radioGroups;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button submit = findViewById(R.id.submitBtn);
        TextView[] textViews = new TextView[]{findViewById(R.id.question1), findViewById(R.id.question2), findViewById(R.id.question3),
                findViewById(R.id.question4), findViewById(R.id.question5), findViewById(R.id.question6), findViewById(R.id.question7),
                findViewById(R.id.question8), findViewById(R.id.question9), findViewById(R.id.question10)};

        radioGroups = new RadioGroup[]{findViewById(R.id.radioGroup1), findViewById(R.id.radioGroup2), findViewById(R.id.radioGroup3),
                findViewById(R.id.radioGroup4), findViewById(R.id.radioGroup5), findViewById(R.id.radioGroup6), findViewById(R.id.radioGroup7),
                findViewById(R.id.radioGroup8), findViewById(R.id.radioGroup9), findViewById(R.id.radioGroup10)};


        String[] data = getResources().getStringArray(R.array.questions);
        List<Question> list = Collections.synchronizedList(new ArrayList<Question>());
        for (int i = 0; i < data.length; i++) {
            String[] question = data[i].split("\\#");
            Question q = new Question();
            q.statement = question[0];
            q.A = question[1];
            q.B = question[2];
            q.C = question[3];
            q.D = question[4];
            list.add(i, q);
        }
        for (int i = 0; i < 10; i++)
        {
            setQuestion(textViews[i],radioGroups[i],list,i);
        }

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int score = 0;
                try{
                    for(int i = 0; i < 10; i++)
                    {
                        int checked = radioGroups[i].getCheckedRadioButtonId();
                        RadioButton radioButton = findViewById(checked);
                        String ans = radioButton.getText().toString();
                        if(Key[i].equals(ans))
                        {
                            score = score + 1;
                        }
                    }
                }
                catch (Exception e)
                {
                }
                finally {
                    String f_score = Integer.toString(score);
                    Intent intent = new Intent(getApplicationContext(),MainActivity2.class);
                    intent.putExtra("score",f_score);
                    startActivity(intent);
                }
            }
        });
    }
    @Override protected void onRestart()
    {
        super.onRestart();
        try {
            for(int i = 0; i < 10; i++)
            {
                radioGroups[i].clearCheck();
            }
        }
        catch (Exception e)
        {
        }
    }
    public void setQuestion(TextView textView,RadioGroup radioGroup, List<Question>list,int index)
    {
        String srNo = Integer.toString(index+1);
        textView.setText(srNo+ ". " + list.get(index).statement);
        for (int i = 0; i < radioGroup.getChildCount(); i++) {
            if(i == 0)
            {
                ((RadioButton) radioGroup.getChildAt(i)).setText(list.get(index).A);
            }
            if(i == 1)
            {
                ((RadioButton) radioGroup.getChildAt(i)).setText(list.get(index).B);
            }
            if(i == 2)
            {
                ((RadioButton) radioGroup.getChildAt(i)).setText(list.get(index).C);
            }
            if(i == 3)
            {
                ((RadioButton) radioGroup.getChildAt(i)).setText(list.get(index).D);
            }
        }
    }

}