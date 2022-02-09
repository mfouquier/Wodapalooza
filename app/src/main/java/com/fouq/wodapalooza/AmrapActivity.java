package com.fouq.wodapalooza;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.Locale;

public class AmrapActivity extends AppCompatActivity  implements delayFragment.delayTimeListener, SetTimeFragment.setTimeListener {

    private long mStartTimeInMillis = 600000;
    private boolean isRunning;
    private boolean isPaused;
    public TextView mTextViewCountDown;
    private CountDownTimer mCountDownTimer;
    private long mTimeLeftInMillis = mStartTimeInMillis;
    private CountDownTimer mDelay;
    long mDelayTimeMills = 5000;
    private TextView mTextViewDelay;
    private boolean delayComplete = false;
    private SoundEffects sound;
    private ImageView mRoundUp;
    private TextView mRoundCount;
    private String [] mRoundNumbers;
    private Button mSetTimeButton;
    private int roundIndex = 0;
    private ImageView mFireworksImageView;
    private long mEndTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amrap);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //Set the Up Arrow to return to MainActivity
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mTextViewCountDown = findViewById(R.id.countdownTimer);
        mTextViewDelay = findViewById(R.id.delay);
        sound = new SoundEffects(this);
        mRoundUp = findViewById(R.id.plusrounds);
        mRoundCount = findViewById(R.id.roundcount);
        mRoundNumbers = getResources().getStringArray(R.array.rounds);
        mSetTimeButton = findViewById(R.id.setTimeButton);
        mFireworksImageView = findViewById(R.id.fireworksImageView);

        if(savedInstanceState != null){
            mTimeLeftInMillis = savedInstanceState.getLong("millisLeft");
            isRunning = savedInstanceState.getBoolean("timerRunning");
            isPaused = savedInstanceState.getBoolean("isPaused");
            roundIndex = savedInstanceState.getInt("roundIndex");
            mRoundCount.setText(mRoundNumbers[roundIndex]);
            updateTimer();

            if(isRunning){
                mEndTime = savedInstanceState.getLong("endTime");
                mTimeLeftInMillis = mEndTime - System.currentTimeMillis();
                startTimer();
            }
        }

        //Round + sign onClick listener
        mRoundUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                roundCounter();
            }
        });
        //Set time onClick Listener
        mSetTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTime();
            }
        });
        //Set Countdown onClick Listener
        mTextViewCountDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isRunning) {
                    pauseTimer();
                } else {
                    if (!isPaused){
                        mTextViewDelay.setVisibility(View.VISIBLE);
                        timerDelayStart();
                    }
                    if(delayComplete) startTimer();
                }
            }
        });

        updateTimer();
    }
    //START/UPDATE/PAUSE THE MAIN TIMER
    private void startTimer() {
        mEndTime = System.currentTimeMillis() + mTimeLeftInMillis;

        mCountDownTimer = new CountDownTimer(mStartTimeInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateTimer();
            }
            @Override
            public void onFinish() {
                mFireworksImageView.setVisibility(View.VISIBLE);
                animateFireworks();
                mTimeLeftInMillis = mStartTimeInMillis;
                isRunning = false;
                updateTimer();
                mFireworksImageView.setVisibility(View.INVISIBLE);
            }
        }.start();
        isRunning = true;
    }
    //PAUSE THE TIMER ON TAP
    private void pauseTimer() {
        mCountDownTimer.cancel();
        isRunning = false;
        isPaused = true;
    }
    //UPDATE THE TIMER EVERY SECOND
    private void updateTimer() {
        int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;

        String timeLeft = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        mTextViewCountDown.setText(timeLeft);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong("millisLeft", mTimeLeftInMillis);
        outState.putBoolean("timerRunning", isRunning);
        outState.putBoolean("isPaused", isPaused);
        outState.putInt("roundIndex", roundIndex);
        outState.putLong("endTime", mEndTime);
    }

//    @Override
//    protected void onRestoreInstanceState(Bundle savedInstanceState) {
//        super.onRestoreInstanceState(savedInstanceState);
//        mTimeLeftInMillis = savedInstanceState.getLong("millisLeft");
//        isRunning = savedInstanceState.getBoolean("timerRunning");
//        isPaused = savedInstanceState.getBoolean("isPaused");
//        roundIndex = savedInstanceState.getInt("roundIndex");
//        mRoundCount.setText(mRoundNumbers[roundIndex]);
//        updateTimer();
//
//        if(isRunning){
//            mEndTime = savedInstanceState.getLong("endTime");
//            mTimeLeftInMillis = mEndTime - System.currentTimeMillis();
//            startTimer();
//        }
//    }

    //START THE DELAY TIMER
    private void timerDelayStart() {
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
                startTimer();
            }
        }.start();
    }
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
    public boolean onCreateOptionsMenu(Menu menu) {
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
                //Set the reset function
                mCountDownTimer.cancel();
                mTimeLeftInMillis = mStartTimeInMillis;
                updateTimer();
                delayComplete = false;
                isPaused = false;
                isRunning = false;
                mFireworksImageView.setVisibility(View.INVISIBLE);
                mRoundCount.setText(mRoundNumbers[0]);
                return true;
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
    }
    //Set Time Button Clicked --> SetTimeFragment
    private void setTime(){
        SetTimeFragment timeFragment = new SetTimeFragment();
        timeFragment.show(getSupportFragmentManager(), "set the workout time");
    }
    //Delay Menu Clicked --> delayFragment
    public void delayMenuClicked(){
        delayFragment delay = new delayFragment();
        delay.show(getSupportFragmentManager(),"set delay time" );
    }
    //Applies the delay change set by delayFragment
    @Override
    public void applyDelay(int delay) {
        mDelayTimeMills = delay;
    }
    //Increment the index and set the round counter +1
    public void roundCounter(){
        roundIndex++;
        mRoundCount.setText(mRoundNumbers[roundIndex]);
    }
    //Bring up Exit dialog when the Back Arrow in the Toolbar is pressed
    private void doExit() {
        AlertDialog.Builder builder = new AlertDialog.Builder(AmrapActivity.this);
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
    //Applies the time set by SetTimeFragment
    @Override
    public void applyTime(String time) {
        mStartTimeInMillis = (Long.parseLong(time) * 1000) * 60;
        int minutes = (int) (mStartTimeInMillis / 1000) / 60;
        int seconds = (int) (mStartTimeInMillis / 1000) % 60;
        String timeLeft = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        mTextViewCountDown.setText(timeLeft);
    }
    //Animate fireworks when clocked stopped
    private void animateFireworks(){
        Animation fireworksAnim = AnimationUtils.loadAnimation(this, R.anim.fireworks);
        mFireworksImageView.startAnimation(fireworksAnim);
        sound.playFireworks();
    }
}
