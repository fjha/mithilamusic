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

public class BhajangeetActivity extends AppCompatActivity {
   public static Activity bhajan ;


    private MediaPlayer mMediaPlayer ;
    InterstitialAd mInterstitialAd;
    private    int start=0;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        releaseMediaPlayer();
        bhajan=this;
        setContentView(R.layout.word_list);
        // ArrayList<String> words = new ArrayList<String >();
        final ArrayList<Word> words =new ArrayList<Word>();
        words.add(new Word("ke duariya","bhola baba " ,R.drawable.bhajan ,R.raw.bhajangeet_bhola_baba_ke_duariya));
        words.add(new Word("bhajan" ,"bhola ho",R.drawable.bhajan,R.raw.bhajangeet_bhola_ho ));

        words.add(new Word("karanma" ,"shiv ke ",R.drawable.bhajan,R.raw.bhajangeet_shiv_ke_karanma_gauri ));

        words.add(new Word("bhajan" ,"shiv shankar",R.drawable.bhajan,R.raw.bhajangeet_shiv_shankar ));



        WordAdapter adapter = new WordAdapter(this,words ,R.color.category_bhajangeet);

        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);
        // Interstitial ad


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public  void onItemClick(AdapterView<?>parent, View view , int position , long id)
            {
                releaseMediaPlayer();

                Word word =words.get(position);


                mMediaPlayer = MediaPlayer.create(BhajangeetActivity.this ,word.getAudioResourceId());

                if(start==1)
                {
                    System.out.println("bhajangeet Media player is  playing .");
                    mMediaPlayer.stop();
                    releaseMediaPlayer();
                    start=0;
                }
                else if(!mMediaPlayer.isPlaying() && start==0)
                {
                    System.out.println("bhajangeet Media player not playing ");

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


            if (isFinishing() ) {

              releaseMediaPlayer();
            }
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
        super.onStop();

             if (isFinishing() ) {

        releaseMediaPlayer();
        }

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



