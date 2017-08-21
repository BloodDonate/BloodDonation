package com.example.jonathandorvilier.dondesang;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.jonathandorvilier.dondesang.adapter.CustomSpinnerAdapter;

public class Register extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    String[] BloodGroup = {"O+", "O-", "AB+", "AB-", "A+", "A-", "B+", "B-"};
    int flags[] = {R.drawable.dondesang, R.drawable.dondesang, R.drawable.dondesang, R.drawable.dondesang, R.drawable.dondesang, R.drawable.dondesang, R.drawable.dondesang, R.drawable.dondesang};


    private EditText etName;
    private EditText etPassword;
    private EditText etEmail;
    private CheckBox chBoxMale;
    private CheckBox chBoxFemale;
    private EditText etBirthday;
    private  EditText etTelephone;
    private Spinner spinner;
    private Button btCancel;
    private Button btRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Getting the instance of Spinner and applying OnItemSelectedListener on it
        Spinner spin = (Spinner) findViewById(R.id.spinnerBloodGroup);
        spin.setOnItemSelectedListener(this);

        CustomSpinnerAdapter customSpinnerAdapter=new CustomSpinnerAdapter(getApplicationContext(),flags,BloodGroup);
        spin.setAdapter(customSpinnerAdapter);
    }

    //Performing action onItemSelected and onNothing selected
    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
        Toast.makeText(getApplicationContext(), BloodGroup[position], Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



}
