package application.android.flora.mithilamusic;

/**
 * Created by Flora on 3/31/2017.
 */

public class Word {private String mDefaultTranslation ;

    // Miwok  translation
    private String mEnglishTranslation ;
    private int    mImageResourceId =NO_IMAGE_PROVIDED ;
    private static final  int NO_IMAGE_PROVIDED =-1;


    private int mAudioResourceId ;

    public Word(String DefaultTranslation  , String EnglishTranslation , int ImageResourceId ,int AudioResourceId)
    {
        mDefaultTranslation =DefaultTranslation ;
        mEnglishTranslation   =EnglishTranslation ;
        mImageResourceId    =ImageResourceId ;

        mAudioResourceId =AudioResourceId;

    }

    public Word(String DefaultTranslation  , String EnglishTranslation , int AudioResourceId)
    {
        mDefaultTranslation =DefaultTranslation ;
        mEnglishTranslation   =EnglishTranslation ;

        mAudioResourceId =AudioResourceId ;
    }

    public String getDefaultTranslation()
    {
        return mDefaultTranslation;
    }

    public String getEnglishTranslation()
    {
        return  mEnglishTranslation;
    }
    public int getImageResourceId()
    {
        return mImageResourceId ;
    }
    public boolean hasImage()
    {
        return mImageResourceId !=NO_IMAGE_PROVIDED ;
    }


    public int getAudioResourceId()
    {
        return mAudioResourceId ;
    }


}
