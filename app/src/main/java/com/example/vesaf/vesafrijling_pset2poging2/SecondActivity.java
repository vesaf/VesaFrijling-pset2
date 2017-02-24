package com.example.vesaf.vesafrijling_pset2poging2;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity {

    Story story;
    ProgressBar progress;
    TextView type_text;
    EditText entry;
    String placeholder;
    ArrayList<String> placeholder_list = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle outState) {
        super.onCreate(outState);
        setContentView(R.layout.activity_second);


        Context context = getApplicationContext();
        InputStream is = null;
        try {
            is = context.getAssets().open("madlib0_simple.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        story = new Story(is);

        if (outState != null) {
            placeholder_list = outState.getStringArrayList("entered_placeholders");
            for (int i = 0; i < placeholder_list.size(); i++) {
                story.fillInPlaceholder(placeholder_list.get(i));
            }
        }

        progress = (ProgressBar) findViewById(R.id.progressBar);
        progress.setMax(story.getPlaceholderCount());

        type_text = (TextView) findViewById(R.id.textView2);
        entry = (EditText) findViewById(R.id.editText);

        Button button = (Button) findViewById(R.id.button2);
        button.performClick();
    }

    public void nextWord(View view) {
        if (!"TextView".equals(type_text.getText())) {
            story.fillInPlaceholder(entry.getText().toString());
            placeholder_list.add(entry.getText().toString());
        }

        progress.setProgress(story.getPlaceholderCount() - story.getPlaceholderRemainingCount());

        if (!story.isFilledIn()) {
            placeholder = story.getNextPlaceholder();
            type_text.setText(getString(R.string.instruction, placeholder));
            entry.setText(placeholder);
        }
        else {
            Intent intent = new Intent(this, Third_Activity.class);
            intent.putExtra("results", story.toString());
            startActivity(intent);
            finish();
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d("rotate", "rotate");
        outState.putStringArrayList("entered_placeholders", placeholder_list);
    }
}
