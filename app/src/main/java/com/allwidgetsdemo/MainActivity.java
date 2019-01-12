
package com.allwidgetsdemo;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.time.DayOfWeek;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    // Declaration Widgets
    EditText bDate, fname, lname, emailid, phoneno, pass;
    DatePickerDialog birthdate;
    ImageView pickDate;
    RadioGroup radioGroup;
    Button signUp;
    RadioButton genderRadio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        find_All_IDs();

        //DatePicker with Edittext
        bDate.setInputType(InputType.TYPE_NULL);
        pickDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);

                //Date PickerDiaL̥log
                birthdate = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                        bDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                    }
                }, year, month, day);
                birthdate.show();
            }
        });
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                validation();

            }
        });


    }

    @SuppressLint("ShowToast")
    private void validation() {
        int selectedId = radioGroup.getCheckedRadioButtonId();
        genderRadio = findViewById(selectedId);

        String fname1 = fname.getText().toString().trim();
        String lname1 = lname.getText().toString().trim();
        String emailid1 = emailid.getText().toString().trim();
        String pass1 = pass.getText().toString().trim();
        String phone1 = phoneno.getText().toString().trim();
        String dob = bDate.getText().toString().trim();

        if (fname1.equalsIgnoreCase("")) {
            fname.setError("Please fill Filed");
            fname.requestFocus();
        } else if (lname1.isEmpty()) {
            lname.setError("Please fill Filed");
            lname.requestFocus();
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(emailid1).matches()) {
            emailid.setError("Enter valid Email id");
            emailid.requestFocus();
        } else if (pass1.length()<4) {
            pass.setError("Enter password (5-10)digit");
            pass.requestFocus();
        } else if (phone1.length() < 10) {
            phoneno.setError("Enter phone Too short digit");
            phoneno.requestFocus();
        } else if (dob.isEmpty()) {
            bDate.setError("Please fill Filed");
            bDate.requestFocus();
        } else if (selectedId == -1) {
            Toast.makeText(getApplicationContext(),"Select Gender",Toast.LENGTH_LONG);

        } else {
            String str = fname1 + "\n" + lname1 + "\n" + emailid1 + "\n" + phone1 + "\n" + dob + "\n" + genderRadio.getText();
            Intent intent = new Intent(getApplicationContext(), PrintDetailsActivity.class);
            intent.putExtra("value", str);
            startActivity(intent);
        }

    }

    private void find_All_IDs() {
        pickDate = findViewById(R.id.image);
        bDate = findViewById(R.id.bdate);
        fname = findViewById(R.id.fname);
        lname = findViewById(R.id.lname);
        emailid = findViewById(R.id.email);
        phoneno = findViewById(R.id.phoneno);
        radioGroup = findViewById(R.id.radioGroup);
        signUp = findViewById(R.id.signup);
        pass = findViewById(R.id.pass);
    }
}
