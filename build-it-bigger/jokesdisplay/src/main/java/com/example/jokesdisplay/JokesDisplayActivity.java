package com.example.jokesdisplay;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

public class JokesDisplayActivity extends AppCompatActivity {

    private TextView joke;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jokes_display);
        joke = findViewById(R.id.tv_joke);
        displayJoke();
    }

    private void displayJoke() {
        Intent intent = getIntent();
        if (intent.hasExtra(Intent.EXTRA_TEXT)) {
            joke.setText(getIntent().getStringExtra(Intent.EXTRA_TEXT));
        } else {
            Toast.makeText(this, getString(R.string.emptyJokePlaceholder), Toast.LENGTH_SHORT).show();
        }
    }
}
