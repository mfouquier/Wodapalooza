package com.fouq.wodapalooza;

/**
 * Author: Matthew Fouquier
 * Date: May 1, 2022
 * Project: Wodapalooza
 */

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Chronometer;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class StopwatchActivity extends AppCompatActivity implements DelayFragment.delayTimeListener{
    private Chronometer stopwatch;
    private long pauseOffset;
    public long mDelayTimeMills = 5000;
    private TextView mTextViewDelay;
    private Switch mFinishSlide;
    private ImageView mFireworksImageView;
    private boolean delayComplete = false;
    private boolean isPaused;
    private boolean isRunning;
    private SoundEffects sound;
    private CountDownTimer mDelay;
    private ImageView mRoundUp;
    private TextView mRoundCount;
    private String [] mRoundNumbers;
    private int roundIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stopwatch);
        //Creates the toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //Set the Up Arrow to return to MainActivity
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        stopwatch = findViewById(R.id.countdownTimer);
        mTextViewDelay = findViewById(R.id.delay);
        mFinishSlide = findViewById(R.id.finish_switch);
        sound = new SoundEffects(this);
        mRoundUp = findViewById(R.id.plusrounds);
        mRoundCount = findViewById(R.id.roundcount);
        mRoundNumbers = getResources().getStringArray(R.array.rounds);
        mFireworksImageView = findViewById(R.id.fireworksImageView);

        if(savedInstanceState != null){
            stopwatch.setBase(savedInstanceState.getLong("timer"));
            isRunning = savedInstanceState.getBoolean("timerRunning");
            isPaused = savedInstanceState.getBoolean("isPaused");
            roundIndex = savedInstanceState.getInt("roundIndex");
            mRoundCount.setText(mRoundNumbers[roundIndex]);

            if(isRunning) {
                startStopwatch();
            }
        }

        //Increments round on click
        mRoundUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                roundCounter();
            }
        });

        //When switch slides stops time and plays animation
        mFinishSlide.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mFireworksImageView.setVisibility(View.VISIBLE);
                    animateFireworks();
                    stopwatch.stop();
                }else{
                    mFireworksImageView.setVisibility(View.INVISIBLE);
                    stopTimer();
                }
            }
        });
        //Start/Pause stopwatch - starts delay timer
        stopwatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isRunning) {
                    pauseTimer();
                } else {
                    if (!isPaused){
                        mTextViewDelay.setVisibility(View.VISIBLE);
                        timerDelayStart();
                    }
                    if(delayComplete) startStopwatch();
                }
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong("timer", stopwatch.getBase());
        outState.putBoolean("timerRunning", isRunning);
        outState.putBoolean("isPaused", isPaused);
        outState.putInt("roundIndex", roundIndex);
    }

    //Stopwatch Methods - Start/Stop/Pause
    private void startStopwatch(){
        stopwatch.setBase(SystemClock.elapsedRealtime() - pauseOffset);
        stopwatch.start();
        isRunning = true;
        isPaused = false;
    }
    private void pauseTimer() {
        stopwatch.stop();
        pauseOffset = SystemClock.elapsedRealtime() - stopwatch.getBase();
        isRunning = false;
        isPaused = true;
    }
    private void stopTimer(){
        stopwatch.setBase(SystemClock.elapsedRealtime());
        pauseOffset = 0;
        stopwatch.stop();
        isPaused = false;
        isRunning = false;
        delayComplete = false;
    }

    //START THE DELAY TIMER
    private void timerDelayStart() {
        if(!isPaused) {
            mDelay = new CountDownTimer(mDelayTimeMills, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                   mDelayTimeMills = millisUntilFinished;
                    updateTimerDelay();
                }
                @Override
                public void onFinish() {
                    mTextViewDelay.setVisibility(View.INVISIBLE);
                    delayComplete = true;
                    startStopwatch();
                }
            }.start();
        }
    }
    //Plays beep sound on Delay timer update
    private void updateTimerDelay() {
        int seconds = (int) (mDelayTimeMills / 1000);
        switch (seconds){
            case 10:
                mTextViewDelay.setText(R.string.ten);
                sound.playShortBeep();
                break;
            case 9:
                mTextViewDelay.setText(R.string.nine);
                sound.playShortBeep();
                break;
            case 8:
                mTextViewDelay.setText(R.string.eight);
                sound.playShortBeep();
                break;
            case 7:
                mTextViewDelay.setText(R.string.seven);
                sound.playShortBeep();
                break;
            case 6:
                mTextViewDelay.setText(R.string.six);
                sound.playShortBeep();
                break;
            case 5:
                mTextViewDelay.setText(R.string.five);
                sound.playShortBeep();
                return;
            case 4:
                mTextViewDelay.setText(R.string.four);
                sound.playShortBeep();
                return;
            case 3:
                mTextViewDelay.setText(R.string.three);
                sound.playShortBeep();
                return;
            case 2:
                mTextViewDelay.setText(R.string.two);
                sound.playShortBeep();
                return;
            case 1:
                mTextViewDelay.setText(R.string.one);
                sound.playLongBeep();
                return;
        }
    }

    //The Toolbar Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.timermenu, menu);
        return true;
    }
    //Toolbar menu Item clicked
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                doExit();
                return true;
            case R.id.delay_timer:
                // User chose the "Settings" item, show the app settings UI...
                delayMenuClicked();
                return true;
            case R.id.reset_icon:
                stopTimer();
                delayComplete = false;
                isPaused = false;
                mFinishSlide.setChecked(false);
                mFireworksImageView.setVisibility(View.INVISIBLE);
                mRoundCount.setText(mRoundNumbers[0]);
                return true;
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
    }

    //Delay Menu Clicked --> delayFragment
    public void delayMenuClicked(){
        DelayFragment delay = new DelayFragment();
        delay.show(getSupportFragmentManager(),"set delay time" );
    }
    @Override
    public void applyDelay(int delay) {
        mDelayTimeMills = delay;
    }

    //Increment the Round number when the + is clicked
    public void roundCounter(){
        roundIndex++;
        mRoundCount.setText(mRoundNumbers[roundIndex]);
    }
    //Bring up Exit dialog when the Back Arrow in the Toolbar is pressed
    private void doExit() {
        AlertDialog.Builder builder = new AlertDialog.Builder(StopwatchActivity.this);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.setNegativeButton("No", null);
        builder.setMessage("Do you want to exit?");
        builder.setTitle("Quit");
        builder.show();
    }
    //Animate fireworks when clocked stopped
    private void animateFireworks(){
        Animation fireworksAnim = AnimationUtils.loadAnimation(this, R.anim.fireworks);
        mFireworksImageView.startAnimation(fireworksAnim);
        sound.playFireworks();
    }
}