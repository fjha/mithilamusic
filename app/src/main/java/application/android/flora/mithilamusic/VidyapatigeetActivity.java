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

public class VidyapatigeetActivity extends AppCompatActivity {
    private   MediaPlayer mMediaPlayer ;


    private int start=0;

    InterstitialAd mInterstitialAd;
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
        // ArrayList<String> words = new ArrayList<String >();
        final ArrayList<Word> words =new ArrayList<Word>();
        words.add(new Word("jagat ke ","vidyapati  " ,R.drawable.vidyapati,R.raw.vidyapati_jagat ));
        words.add(new Word("bhairav  ","jai jai " ,R.drawable.vidyapati,R.raw.vidyapati_jai_jai_bhairav ));
        words.add(new Word("anganma  ","mora re " ,R.drawable.vidyapati,R.raw.vidyapati_mora_re_anganma ));
        words.add(new Word("gauri ","rusi chalal " ,R.drawable.vidyapati,R.raw.vidyapati_rusi_chalal_gauri ));


        WordAdapter adapter = new WordAdapter(this,words ,R.color.category_vidyapati);

        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public  void onItemClick(AdapterView<?>parent, View view , int position , long id)
            {
                releaseMediaPlayer();


                Word word =words.get(position);


                mMediaPlayer = MediaPlayer.create(VidyapatigeetActivity.this ,word.getAudioResourceId());
                if(start==1 && mMediaPlayer.isPlaying())
                {
                    System.out.println("vidyapati Media player is  playing .");
                    mMediaPlayer.stop();
                    releaseMediaPlayer();
                    start=0;
                }
                else if(!mMediaPlayer.isPlaying() && start==0)
                {
                    System.out.println("Vidyapati Media player not playing ");
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
        System.out.println("Vidyapati onPause() is finishing 1");
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
                showInterstitial();
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
                mInterstitialAd.show();
            }
        });


    }
}
