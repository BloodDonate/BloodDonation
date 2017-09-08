package com.example.jonathandorvilier.dondesang;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ProfilActivity extends AppCompatActivity {

    EditText etName,etUserName,etPassword,etTelephone;
    static EditText etBirthday;
    Spinner spSexe, spinnerBloodGroup;
    Button btnModifier;
    SharedPreferences sharedPreferences ;
    String id_user, nom_user, telephone_user, birthday_user, sexe_user, gsanguin_user, username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Profil :");

        sharedPreferences = getSharedPreferences("PreferencesTAG", Context.MODE_PRIVATE);

        id_user=sharedPreferences.getString("id_user", null);
        nom_user=sharedPreferences.getString("nom_user", null);
        telephone_user=sharedPreferences.getString("telephone_user", null);
        birthday_user=sharedPreferences.getString("birthday_user", null);
        sexe_user=sharedPreferences.getString("sexe_user", null);
        gsanguin_user=sharedPreferences.getString("gsanguin_user", null);
        username=sharedPreferences.getString("username", null);

        etName=(EditText)findViewById(R.id.etName);
        etUserName=(EditText)findViewById(R.id.etUserName);
        etPassword=(EditText)findViewById(R.id.etPassword);
        etTelephone=(EditText)findViewById(R.id.etTelephone);
        etBirthday=(EditText)findViewById(R.id.etBirthday);

        spSexe=(Spinner)findViewById(R.id.spSexe);
        spinnerBloodGroup=(Spinner)findViewById(R.id.spinnerBloodGroup);
        btnModifier=(Button) findViewById(R.id.btnModifier);

        etName.setText(nom_user);
        etTelephone.setText(telephone_user);
        etUserName.setText(username);
        etBirthday.setText(birthday_user);
        /*String[] gSanguinUser = getResources().getStringArray(R.array.gSanguin);
        String[] sexeUser = getResources().getStringArray(R.array.sexeUser);*/

        if(sexe_user.contains("M")){
            spSexe.setSelection(0);
        }else if(sexe_user.contains("F")){
            spSexe.setSelection(1);
        }else{

        }

        if(spinnerBloodGroup.equals("O+")){
            spinnerBloodGroup.setSelection(1);
        }else if(spinnerBloodGroup.equals("O-")){
            spinnerBloodGroup.setSelection(2);
        }else if(spinnerBloodGroup.equals("AB+")){
            spinnerBloodGroup.setSelection(3);
        }else if(spinnerBloodGroup.equals("AB-")){
            spinnerBloodGroup.setSelection(4);
        }else if(spinnerBloodGroup.equals("A+")){
            spinnerBloodGroup.setSelection(5);
        }else if(spinnerBloodGroup.equals("A-")){
            spinnerBloodGroup.setSelection(6);
        }else if(spinnerBloodGroup.equals("B+")){
            spinnerBloodGroup.setSelection(7);
        }else if(spinnerBloodGroup.equals("B-")){
            spinnerBloodGroup.setSelection(8);
        }else{
            spinnerBloodGroup.setSelection(0);
        }

        etBirthday.setKeyListener(null);
        etBirthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(getSupportFragmentManager(), "datePicker");
            }
        });
        btnModifier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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


    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

       /* @Override
        public void onDismiss(DialogInterface dialog) {
            edtStartDate.getText().toString();
            edtEndDate.getText().toString();
            spSortBy.getSelectedItem().toString();
            super.onDismiss(dialog);
        }*/

        public void onDateSet(DatePicker view, int year, int month, int day) {
            // Do something with the date chosen by the user
            final Calendar c = Calendar.getInstance();
            c.set(Calendar.YEAR, year);
            c.set(Calendar.MONTH, month);
            c.set(Calendar.DAY_OF_MONTH, day);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            etBirthday.setText(simpleDateFormat.format(c.getTime()));
        }
    }
}
