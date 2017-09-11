package com.example.jonathandorvilier.dondesang;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class QuestionActivity extends AppCompatActivity {

    private RadioGroup q1,q2,q3,q4,q5,q6,q7;
    private RadioButton radioButton1,radioButton2,radioButton3,radioButton4,radioButton5,radioButton6,radioButton7;
    private Button btnVerifier;
    SharedPreferences sharedPreferences ;
    SharedPreferences.Editor editor ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        sharedPreferences = getSharedPreferences("PreferencesTAG", Context.MODE_PRIVATE);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Questionnaire :");

        btnVerifier = (Button) findViewById(R.id.btnVerifier);

        q1 = (RadioGroup) findViewById(R.id.q1);
        q2 = (RadioGroup) findViewById(R.id.q2);
        q3 = (RadioGroup) findViewById(R.id.q3);
        q4 = (RadioGroup) findViewById(R.id.q4);
        q5 = (RadioGroup) findViewById(R.id.q5);
        q6 = (RadioGroup) findViewById(R.id.q6);
        q7 = (RadioGroup) findViewById(R.id.q7);

        btnVerifier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int selected1 = q1.getCheckedRadioButtonId();
                int selected2 = q2.getCheckedRadioButtonId();
                int selected3 = q3.getCheckedRadioButtonId();
                int selected4 = q4.getCheckedRadioButtonId();
                int selected5 = q5.getCheckedRadioButtonId();
                int selected6 = q6.getCheckedRadioButtonId();
                int selected7 = q7.getCheckedRadioButtonId();

                radioButton1 = (RadioButton) findViewById(selected1);
                radioButton2 = (RadioButton) findViewById(selected2);
                radioButton3 = (RadioButton) findViewById(selected3);
                radioButton4 = (RadioButton) findViewById(selected4);
                radioButton5 = (RadioButton) findViewById(selected5);
                radioButton6 = (RadioButton) findViewById(selected6);
                radioButton7 = (RadioButton) findViewById(selected7);

                editor = sharedPreferences.edit();
                if(q1.getCheckedRadioButtonId() != -1 && q2.getCheckedRadioButtonId() != -1 && q3.getCheckedRadioButtonId() != -1 && q4.getCheckedRadioButtonId() != -1 && q5.getCheckedRadioButtonId() != -1 && q6.getCheckedRadioButtonId() != -1 && q7.getCheckedRadioButtonId() != -1){
                    if(radioButton1.getText().equals("Non") || radioButton2.getText().equals("Non") || radioButton3.getText().equals("Non") || radioButton4.getText().equals("Non") || radioButton5.getText().equals("Non") || radioButton6.getText().equals("Non") || radioButton7.getText().equals("Non")){
                        Toast.makeText(QuestionActivity.this, "Vous ne pouvez pas....", Toast.LENGTH_SHORT).show();
                        editor.putBoolean("eligibleUserDon", false);
                        editor.apply();
                    }else{
                        editor.putBoolean("eligibleUserDon", true);
                        editor.apply();

                        Toast.makeText(getApplicationContext(),
                                "1: "+radioButton1.getText()+
                                        "\n2: "+radioButton2.getText()+
                                        "\n3: "+ radioButton3.getText()+
                                        "\n4: "+radioButton4.getText()+
                                        "\n5: "+radioButton5.getText()+
                                        "\n6: "+radioButton6.getText()+
                                        "\n7: "+radioButton7.getText(), Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(QuestionActivity.this, "Question oublié", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                overridePendingTransition(android.R.anim.slide_out_right, android.R.anim.slide_in_left);
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
