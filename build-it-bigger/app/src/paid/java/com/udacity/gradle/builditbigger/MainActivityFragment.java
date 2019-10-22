package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.example.jokesdisplay.JokesDisplayActivity;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements EndPointAsyncTask.Callback, View.OnClickListener {

    Button jokeTeller;
    ProgressBar pgBar;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        jokeTeller = root.findViewById(R.id.btn_joke);
        pgBar = root.findViewById(R.id.pgbar_joke);
        jokeTeller.setOnClickListener(this);
        return root;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_joke) {
            pgBar.setVisibility(View.VISIBLE);
            jokeTeller.setEnabled(false);
            new EndPointAsyncTask(this).execute();
        }
    }

    @Override
    public void onFinished(String result) {
        pgBar.setVisibility(View.GONE);
        jokeTeller.setEnabled(true);
        Intent intent = new Intent(getContext(), JokesDisplayActivity.class);
        intent.putExtra(Intent.EXTRA_TEXT, result);
        startActivity(intent);
    }
}
