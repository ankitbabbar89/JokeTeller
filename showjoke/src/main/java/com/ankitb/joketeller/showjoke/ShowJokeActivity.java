package com.ankitb.joketeller.showjoke;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;


public class ShowJokeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_joke);
        Intent intent = getIntent();
        String joke = "Test Joke";
        if(intent != null){
            Bundle extras = intent.getExtras();
            if(extras != null){
                joke = extras.getString("KEY_JOKE");
            }
        }

        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText(joke);
    }
}
