package application.android.flora.mithilamusic;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import java.util.ArrayList;

public class HoligeetActivity extends AppCompatActivity {



    InterstitialAd mInterstitialAd;
    private  int start=0;
    private   MediaPlayer mMediaPlayer ;

    private MediaPlayer.OnCompletionListener mCompletionListener =new MediaPlayer.OnCompletionListener()
    {
        public void onCompletion(MediaPlayer mp)
        {
            releaseMediaPlayer();
        }
    };
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mMediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mMediaPlayer = null;
        }
    }

    private void showInterstitial() {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
            System.out.print("Interstitial Ad is  showing");
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        releaseMediaPlayer();
        setContentView(R.layout.word_list);


        final ArrayList<Word> words =new ArrayList<Word>();
        words.add(new Word("aaj brij" ,"me hori",R.drawable.holi,R.raw.holi_aaj_brij_me_hori ));
        words.add(new Word("aave he"  ,"bhauji",R.drawable.holi,R.raw.holi_aave_he_bhauji ));
       words.add(new Word("jamuna tat"  ,"shyam ",R.drawable.holi,R.raw.holi_jamuna_tat_shyam_kehle ));
        words.add(new Word("jogira sa ","ra ra " ,R.drawable.holi,R.raw.holi_jogira_sa_ra_ra_ra ));




        WordAdapter adapter = new WordAdapter(this,words,R.color.category_holigeet);

        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);

        // music file
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public  void onItemClick(AdapterView<?>parent, View view , int position , long id)
            {



                Word word =words.get(position);

                mMediaPlayer = MediaPlayer.create(HoligeetActivity.this ,word.getAudioResourceId());
                if(start==1)
                {
                    System.out.println("holigeet Media player is  playing .");
                    mMediaPlayer.stop();
                    releaseMediaPlayer();
                    start=0;
                }
                else if(!mMediaPlayer.isPlaying() && start==0)
                {
                    System.out.println("holigeet Media player not playing ");

                    if(mMediaPlayer.isPlaying())
                        mMediaPlayer.release();

                    mMediaPlayer.start();
                    mMediaPlayer.setOnCompletionListener(mCompletionListener);
                    start =1;
                }

            }

        });




    }
    protected void onPause() {
        System.out.println("lokgeet onPause() is finishing 1");
        //  mMediaPlayer.stop();
        if (isFinishing() ) {
            releaseMediaPlayer();
        }


// Interstitial ad

        mInterstitialAd = new InterstitialAd(this);

        // set the ad unit ID
        mInterstitialAd.setAdUnitId(getString(R.string.ad_unit_id));
        AdRequest adRequestInterstital = new AdRequest.Builder()
                 .build();

        // Load ads into Interstitial Ads
        mInterstitialAd.loadAd(adRequestInterstital);

        mInterstitialAd.setAdListener(new AdListener() {
            public void onAdLoaded() {
                mInterstitialAd.show();
            }
        });

        super.onPause();


    }
    protected void onResume() {
        super.onResume();

        if (isFinishing() ) {
            releaseMediaPlayer();
        }

    }
    protected void onStop()
    {


        if (isFinishing() ) {
            releaseMediaPlayer();
        }
        super.onStop();
    }

    protected void onReStart()
    {
        super.onRestart();
        // Interstitial ad

        mInterstitialAd = new InterstitialAd(this);

        // set the ad unit ID
        mInterstitialAd.setAdUnitId(getString(R.string.ad_unit_id));
        AdRequest adRequestInterstital = new AdRequest.Builder()
                .build();

        // Load ads into Interstitial Ads
        mInterstitialAd.loadAd(adRequestInterstital);

        mInterstitialAd.setAdListener(new AdListener() {
            public void onAdLoaded() {
                showInterstitial();
            }
        });


    }
}
