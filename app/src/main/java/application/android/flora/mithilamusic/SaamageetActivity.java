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

public class SaamageetActivity extends AppCompatActivity {


    InterstitialAd mInterstitialAd;
    private int start=0;
    private  MediaPlayer mMediaPlayer ;
    private void showInterstitial() {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
            System.out.print("Interstitial Ad is  showing");
        }
    }
    private MediaPlayer.OnCompletionListener mCompletionListener =new MediaPlayer.OnCompletionListener()
    {
        public void onCompletion(MediaPlayer mp)
        {
            releaseMediaPlayer();
        }
    };
    public void releaseMediaPlayer() {
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        releaseMediaPlayer();


        setContentView(R.layout.word_list);

        android.app.ActionBar actionBar =getActionBar();

        final ArrayList<Word> words =new ArrayList<Word>();
        words.add(new Word("jeta ganga " ,"kone bhaiya " ,R.drawable.sama,R.raw.sama_kone_bhaiya_jeta_ganga));
        words.add(new Word("chalali " ,"sama khele" ,R.drawable.sama,R.raw.sama_sama_khele_chalali));

        words.add(new Word("me " ,"kartik mase" ,R.drawable.sama,R.raw.saama_kartik_maase));
        words.add(new Word("vileen " ,"mitti me " ,R.drawable.sama,R.raw.saama_mitti_me_vileen));


        WordAdapter adapter = new WordAdapter(this,words  ,R.color.category_saamageet);

        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public  void onItemClick(AdapterView<?>parent, View view , int position , long id)
            {
                releaseMediaPlayer();

                Word word =words.get(position);


                mMediaPlayer = MediaPlayer.create(SaamageetActivity.this ,word.getAudioResourceId());
                if(start==1)
                {
                    System.out.println("samageet Media player is  playing .");
                    mMediaPlayer.stop();
                    releaseMediaPlayer();
                    start=0;
                }
                else if(!mMediaPlayer.isPlaying() && start==0)
                {
                    if(mMediaPlayer.isPlaying())
                        mMediaPlayer.release();

                    System.out.println("saamgeet Media player not playing ");



                    mMediaPlayer.start();
                    mMediaPlayer.setOnCompletionListener(mCompletionListener);
                    start =1;
                }

            }

        });

    }
    protected void onPause() {
        System.out.println("Chatgeet onPause() is finishing 1");
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
