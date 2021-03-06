package com.fouq.wodapalooza;

/**
 * Author: Matthew Fouquier
 * Date: May 1, 2022
 * Project: Wodapalooza
 */

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

public class SoundEffects {

    private final SoundPool mSoundPool;
    private static int mShortBeep;
    private static int mLongBeep;
    private static int mFireworks;
    private static int mOneMinuteRemaining;
    private static int mThirtySecondsRemaining;
    private static int mTenSecondsRemaining;

    public SoundEffects(Context context) {

        mSoundPool = new SoundPool(2, AudioManager.STREAM_MUSIC, 0);
        mShortBeep = mSoundPool.load(context, R.raw.beep_short, 1);
        mLongBeep = mSoundPool.load(context, R.raw.beep_long, 1);
        mFireworks = mSoundPool.load(context, R.raw.fireworkssound, 1);
        mOneMinuteRemaining = mSoundPool.load(context, R.raw.one_minute_remaining, 1);
        mThirtySecondsRemaining = mSoundPool.load(context, R.raw.thirty_seconds_remaining, 1);
        mTenSecondsRemaining = mSoundPool.load(context, R.raw.ten_seconds_remaining, 1);
    }

    public void playShortBeep(){
        mSoundPool.play(mShortBeep, 1.0f, 1.0f, 1, 0, 1.0f);
    }

    public void playLongBeep(){
        mSoundPool.play(mLongBeep, 1.0f, 1.0f, 1, 0, 1.0f);
    }

    public void playFireworks(){mSoundPool.play(mFireworks,1.0f, 1.0f, 1, 0, 1.0f);}

    public void playOneMinute(){mSoundPool.play(mOneMinuteRemaining,1.0f, 1.0f, 1, 0, 1.0f);}

    public void playThirtySeconds(){mSoundPool.play(mThirtySecondsRemaining,1.0f, 1.0f, 1, 0, 1.0f);}

    public void playTenSeconds(){mSoundPool.play(mTenSecondsRemaining,1.0f, 1.0f, 1, 0, 1.0f);}
}
