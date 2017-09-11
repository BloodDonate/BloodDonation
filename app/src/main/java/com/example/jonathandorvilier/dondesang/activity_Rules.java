package com.example.jonathandorvilier.dondesang;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class activity_Rules extends ActionBarActivity implements View.OnClickListener {

    private TextView tvRulesText;
    private TextView tvRepons1;
    private TextView tvRepons2;
    private TextView tvquestion2;
    private TextView tvRepons3;
    private TextView tvquestion3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__rules);


        tvRulesText = (TextView) findViewById(R.id.tvRulesText);
        tvRepons1 = (TextView) findViewById(R.id.tvRepons1);
        tvRepons2 = (TextView) findViewById(R.id.tvRepons2);
        tvquestion2 = (TextView) findViewById(R.id.tvquestion2);

        tvRepons3 = (TextView) findViewById(R.id.tvRepons3);
        tvquestion3 = (TextView) findViewById(R.id.tvquestion3);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.menu.activity_main_drawer:

                startActivity(new Intent(this, activity_Rules.class));
                break;

                    }
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Aide");


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