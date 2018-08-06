package application.android.flora.mithilamusic;

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

public class AlbumgeetActivity extends AppCompatActivity {

    InterstitialAd mInterstitialAd;
    private   MediaPlayer mMediaPlayer ;
    private int start=0;
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
       // PowerManager mPowerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);

        if (mMediaPlayer != null ) {
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
        final   ArrayList<Word> words =new ArrayList<Word>();
        words.add(new Word("mon kehan " ,"ab kahu" ,R.drawable.album ,R.raw.movie_ab_kahu));

        words.add(new Word("dahi chatakan " ,"atkan matkan" ,R.drawable.album, R.raw.movie_atkan_matkan_dahi));
        words.add(new Word("lagabau" ,"barki bhauji" ,R.drawable.album, R.raw.movie_barki_bhauji_lagabu));
        words.add(new Word("katai " ,"kania heragel", R.drawable.album, R.raw.movie_kania_hegel));

        WordAdapter adapter = new WordAdapter(this,words,R.color.category_moviegeet);

        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);
        // music file
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public  void onItemClick(AdapterView<?>parent, View view , int position , long id)
            {

                releaseMediaPlayer();

                Word word =words.get(position);

                mMediaPlayer = MediaPlayer.create(AlbumgeetActivity.this ,word.getAudioResourceId());
                if(start==1)
                {
                    System.out.println("Moviegeet Media player is  playing .");
                    mMediaPlayer.stop();
                    releaseMediaPlayer();
                    start=0;
                }
                else if(!mMediaPlayer.isPlaying() && start==0)
                {
                    if(mMediaPlayer.isPlaying())
                        mMediaPlayer.release();

                    System.out.println("Moviegeet Media player not playing ");

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

        super.onPause();

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
