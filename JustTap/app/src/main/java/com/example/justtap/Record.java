package com.example.justtap;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;

import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;
import pl.droidsonroids.gif.GifImageView;


public class Record extends AppCompatActivity implements GestureDetector.OnGestureListener{
    private TextView numTime;
    private TextView numClick;
    private GestureDetectorCompat gestureDetector;
    private Animation translateAnimation;
    private GifImageView gifImageView;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (gestureDetector != null) {
            return gestureDetector.onTouchEvent(event);
        }
        return super.onTouchEvent(event);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        gestureDetector = new GestureDetectorCompat(this, this);
        numTime = findViewById(R.id.numTime);
        numClick = findViewById(R.id.numClick);

        SharedPreferences sharedPreferences = getSharedPreferences(Game_play.getSharedPrefsKey(), MODE_PRIVATE);
        int savedTime = sharedPreferences.getInt(Game_play.getTimeKey(), 0);
        numTime.setText(String.valueOf(savedTime));

        int savedClicks = sharedPreferences.getInt(Game_play.getClickKey(), 0);
        numClick.setText(String.valueOf(savedClicks));


    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(@NonNull MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(@NonNull MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(@Nullable MotionEvent e1, @NonNull MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(@NonNull MotionEvent e) {
        GifImageView gifImageView = findViewById(R.id.cat);
        gifImageView.setImageResource(R.drawable.cat);
        translateAnimation = new TranslateAnimation(-gifImageView.getWidth(), getWindowManager().getDefaultDisplay().getWidth(), 0f, 0f);
        translateAnimation.setDuration(4000);
        translateAnimation.setRepeatCount(Animation.INFINITE);
        translateAnimation.setRepeatMode(Animation.RESTART);
        translateAnimation.setInterpolator(new LinearInterpolator());
        gifImageView.startAnimation(translateAnimation);
    }

    @Override
    public boolean onFling(@Nullable MotionEvent e1, @NonNull MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (gifImageView != null) {
            gifImageView.clearAnimation();
        }
    }
}
