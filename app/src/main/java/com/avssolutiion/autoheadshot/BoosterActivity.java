package com.avssolutiion.autoheadshot;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxAdListener;
import com.applovin.mediation.MaxError;
import com.applovin.mediation.ads.MaxInterstitialAd;
import com.applovin.mediation.nativeAds.MaxNativeAdListener;
import com.applovin.mediation.nativeAds.MaxNativeAdLoader;
import com.applovin.mediation.nativeAds.MaxNativeAdView;
import com.avssolutiion.autoheadshot.databinding.ActivityBoosterBinding;

public class BoosterActivity extends AppCompatActivity implements MaxAdListener {

    ActivityBoosterBinding binding;
    public ProgressDialog dialog;

    //applovin ads
    private MaxInterstitialAd interstitialAd;
    private MaxNativeAdLoader nativeAdLoader;
    private MaxAd nativeAd;

    ProgressDialog progressDialog;
    //applovin ads

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booster);
        binding = ActivityBoosterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading...");
        dialog.setCancelable(false);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
       // progressDialog.show();


        interstitialAd = new MaxInterstitialAd(getString(R.string.inter),this);
        interstitialAd.setListener(this);
        interstitialAd.loadAd();
         loadnetiveAd();
        //applovin



        binding.ram1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                binding.ram2.setChecked(false);
                binding.ram3.setChecked(false);
                binding.ram4.setChecked(false);
                binding.ram5.setChecked(false);
                binding.ram6.setChecked(false);
            }
        });

        binding.ram2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                binding.ram1.setChecked(false);
              //  binding.ram2.setChecked(false);
                binding.ram3.setChecked(false);
                binding.ram4.setChecked(false);
                binding.ram5.setChecked(false);
                binding.ram6.setChecked(false);
            }
        });

        binding.ram3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                binding.ram1.setChecked(false);
                  binding.ram2.setChecked(false);
               // binding.ram3.setChecked(false);
                binding.ram4.setChecked(false);
                binding.ram5.setChecked(false);
                binding.ram6.setChecked(false);
            }
        });

        binding.ram4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                binding.ram1.setChecked(false);
                binding.ram2.setChecked(false);
                binding.ram3.setChecked(false);
             //   binding.ram4.setChecked(false);
                binding.ram5.setChecked(false);
                binding.ram6.setChecked(false);
            }
        });


        binding.ram5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                binding.ram1.setChecked(false);
                binding.ram2.setChecked(false);
                binding.ram3.setChecked(false);
                binding.ram4.setChecked(false);
               // binding.ram5.setChecked(false);
                binding.ram6.setChecked(false);
            }
        });


        binding.ram6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                binding.ram1.setChecked(false);
                binding.ram2.setChecked(false);
                binding.ram3.setChecked(false);
                   binding.ram4.setChecked(false);
                binding.ram5.setChecked(false);
             //   binding.ram6.setChecked(false);
            }
        });

        binding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        binding.cardView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (interstitialAd.isReady()){
                    interstitialAd.showAd();
                    dialog.show();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            //todo: check if user is already is login
                            dialog.dismiss();
                            Toast.makeText(BoosterActivity.this, "Applied", Toast.LENGTH_SHORT).show();

                        }
                    },4000);
                }else {
                    dialog.show();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            //todo: check if user is already is login
                            dialog.dismiss();
                            Toast.makeText(BoosterActivity.this, "Applied", Toast.LENGTH_SHORT).show();

                        }
                    },4000);
                }

            }
        });

        binding.linearLayout4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }
    void loadnetiveAd(){

        FrameLayout nativeAdContainer = findViewById( R.id.native_ad_layout );

        nativeAdLoader = new MaxNativeAdLoader( getString(R.string.netive), this );
        nativeAdLoader.setNativeAdListener( new MaxNativeAdListener()
        {
            @Override
            public void onNativeAdLoaded(final MaxNativeAdView nativeAdView, final MaxAd ad)
            {
                nativeAdContainer.setVisibility(View.VISIBLE);
                progressDialog.dismiss();
                // Clean up any pre-existing native ad to prevent memory leaks.
                if ( nativeAd != null )
                {
                    nativeAdLoader.destroy( nativeAd );
                }

                // Save ad for cleanup.
                nativeAd = ad;

                // Add ad view to view.
                nativeAdContainer.removeAllViews();
                nativeAdContainer.addView( nativeAdView );
            }

            @Override
            public void onNativeAdLoadFailed(final String adUnitId, final MaxError error)
            {
                nativeAdContainer.setVisibility(View.GONE);
                // Toast.makeText(MainActivity.this, "NetiveFailed", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
                // We recommend retrying with exponentially higher delays up to a maximum delay
            }

            @Override
            public void onNativeAdClicked(final MaxAd ad)
            {
                // Optional click callback
                progressDialog.dismiss();
            }
        } );

        nativeAdLoader.loadAd();

    }


    @Override
    public void onAdLoaded(MaxAd ad) {

    }

    @Override
    public void onAdDisplayed(MaxAd ad) {

    }

    @Override
    public void onAdHidden(MaxAd ad) {

    }

    @Override
    public void onAdClicked(MaxAd ad) {

    }

    @Override
    public void onAdLoadFailed(String adUnitId, MaxError error) {

    }

    @Override
    public void onAdDisplayFailed(MaxAd ad, MaxError error) {

    }
}