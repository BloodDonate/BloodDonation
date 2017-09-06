package com.example.jonathandorvilier.dondesang;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.TextView;

public class activity_Rules extends ActionBarActivity implements View.OnClickListener {

    private TextView tvRulesText;
    private TextView tvRepons1;
    private TextView tvRepons2;
    private TextView tvquestion2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__rules);

        tvRulesText = (TextView) findViewById(R.id.tvRulesText);
        tvRepons1 = (TextView) findViewById(R.id.tvRepons1);
        tvRepons2 = (TextView) findViewById(R.id.tvRepons2);
        tvquestion2 = (TextView) findViewById(R.id.tvquestion2);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.menu.activity_main_drawer:

                startActivity(new Intent(this, activity_Rules.class));
                break;
        }
    }
}
