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
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements EndPointAsyncTask.Callback, View.OnClickListener {

    ProgressBar pgBar;
    Intent intent;
    InterstitialAd interstitialAd;
    Button jokeTeller;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        AdView mAdView = root.findViewById(R.id.adView);
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);

        interstitialAd = new InterstitialAd(getContext());
        interstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712"); // android dedicated test ad unit ID
        requestNewInterstitial();

        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                requestNewInterstitial();
                if (intent != null) {
                    startActivity(intent);
                }
            }
        });

        pgBar = root.findViewById(R.id.pgbar_joke);
        jokeTeller = root.findViewById(R.id.btn_tellJoke);
        jokeTeller.setOnClickListener(this);

        return root;
    }

    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        interstitialAd.loadAd(adRequest);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_tellJoke) {
            pgBar.setVisibility(View.VISIBLE);
            jokeTeller.setEnabled(false);
            new EndPointAsyncTask(this).execute();
        }
    }

    @Override
    public void onFinished(String result) {
        intent = new Intent(getContext(), JokesDisplayActivity.class);
        intent.putExtra(Intent.EXTRA_TEXT, result);
        pgBar.setVisibility(View.GONE);
        jokeTeller.setEnabled(true);
        if (interstitialAd.isLoaded()) {
            interstitialAd.show();
        } else startActivity(intent);
    }
}
