package application.android.flora.mithilamusic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;


public class MainActivity extends AppCompatActivity {

    InterstitialAd mInterstitialAd;
    private AdView mAdView;

    private void showInterstitial() {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }
    }


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MobileAds.initialize(getApplicationContext(), "ca-app-pub-3459004490451586~5525857952");
         mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);





        // InterstitialAd ;

      /*  mInterstitialAd = new InterstitialAd(this);

        // set the ad unit ID
        mInterstitialAd.setAdUnitId(getString(R.string.ad_unit_id));
        AdRequest adRequestInterstitial  = new AdRequest.Builder()
                .build();
     */


// Load ads into Interstitial Ads
   //     mInterstitialAd.loadAd(adRequest);

    //    mInterstitialAd.setAdListener(new AdListener() {
      //      public void onAdLoaded() {
        //        showInterstitial();
     //       }
     //   });




        final TextView numbers = (TextView)findViewById(R.id.saamageet);
        numbers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Create a new Intent to open  the @ linkNumbers
                Intent numbersIntent = new Intent(MainActivity.this , SaamageetActivity.class);
                // start new activity
                startActivity(numbersIntent);
            }
        });


        final TextView colors = (TextView)findViewById(R.id.holigeet);
        colors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Create a new Intent to open  the @ linkNumbers
                Intent colorsIntent = new Intent(MainActivity.this , HoligeetActivity.class);
                // start new activity
                startActivity(colorsIntent);
            }
        });


        final TextView family = (TextView)findViewById(R.id.chaatgeet);
        family.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Create a new Intent to open  the @ linkNumbers
                Intent familyIntent = new Intent(MainActivity.this , ChaathgeetActivity.class);
                // start new activity
                startActivity(familyIntent);
            }
        });


        final TextView clothes = (TextView)findViewById(R.id.lookgeet);
        clothes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Create a new Intent to open  the @ linkNumbers
                Intent phrasesIntent = new Intent(MainActivity.this , LokgeetActivity.class);
                // start new activity
                startActivity(phrasesIntent);
            }
        });
        final TextView phrases = (TextView)findViewById(R.id.moviegeet);
        phrases.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Create a new Intent to open  the @ linkNumbers
                Intent phrasesIntent = new Intent(MainActivity.this , AlbumgeetActivity.class);
                // start new activity
                startActivity(phrasesIntent);
            }
        });
        final TextView foods = (TextView)findViewById(R.id.bhajangeet);
        foods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Create a new Intent to open  the @ linkNumbers
                Intent foodsIntent = new Intent(MainActivity.this , BhajangeetActivity.class);
                // start new activity
                startActivity(foodsIntent);
            }
        });
        final TextView days = (TextView)findViewById(R.id.vivahgeet);
        days.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Create a new Intent to open  the @ linkNumbers
                Intent daysIntent = new Intent(MainActivity.this , VivahgeetActivity.class);
                // start new activity
                startActivity(daysIntent);
            }
        });
        final TextView moods = (TextView)findViewById(R.id.vidyapatigeet);
        moods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Create a new Intent to open  the @ linkNumbers
                Intent moodsIntent = new Intent(MainActivity.this , VidyapatigeetActivity.class);
                // start new activity
                startActivity(moodsIntent);
            }
        });
    }



}
