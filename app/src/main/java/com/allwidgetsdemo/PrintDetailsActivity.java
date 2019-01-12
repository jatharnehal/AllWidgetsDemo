package com.allwidgetsdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class PrintDetailsActivity extends AppCompatActivity {

    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_print_details);

        textView=findViewById(R.id.textView);

        Bundle extras=getIntent().getExtras();
        String value=extras.getString("value");
        textView.setText(value);

    }
}
