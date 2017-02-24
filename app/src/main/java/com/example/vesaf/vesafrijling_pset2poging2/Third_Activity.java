package com.example.vesaf.vesafrijling_pset2poging2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Third_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_);

        TextView result = (TextView) findViewById(R.id.textView3);
        result.setText(getIntent().getStringExtra("results"));
    }
}
