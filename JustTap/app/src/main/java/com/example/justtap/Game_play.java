package com.example.justtap;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class Game_play extends AppCompatActivity {
    private TextView scoreTextView;
    private TextView timerTextView;
    private GridLayout gridLayout;
    private Button[][] buttons;
    private int score;
    private int timeRemaining;
    private Button replayButton;
    private Button easyBButton;
    private Button normalButton;
    private Button hardButton;
    private static final int GRID_SIZE = 3;
    private static final int GAME_DURATION = 10000;
    public static int totalMiceShown ;
    private int mouseTTL = 255;
    public static int countTime;
    public static int countClick;
    public int countMice;
    private static final String SHARED_PREFS_KEY = "my_prefs";
    private static final String TIME_KEY = "time_key";
    private static final String CLICK_KEY = "click_key";
    private static final String MISS_KEY = "miss_key";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_play);

        timerTextView = findViewById(R.id.Timer);
        scoreTextView = findViewById(R.id.Score);
        gridLayout = findViewById(R.id.gridLayout);
        replayButton = findViewById(R.id.replay_button);
        easyBButton = findViewById(R.id.easy);
        normalButton = findViewById(R.id.normal);
        hardButton = findViewById(R.id.hard);

        replayButton.setVisibility(View.GONE);

        replayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGame();
                replayButton.setVisibility(View.GONE);
            }
        });

        easyBButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setMouseTTL(1500);
                replayButton.setVisibility(View.GONE);
                startGame();
            }
        });

        normalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setMouseTTL(700);
                replayButton.setVisibility(View.GONE);
                startGame();
            }
        });

        hardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setMouseTTL(250);
                replayButton.setVisibility(View.GONE);
                startGame();
            }
        });

        buttons = new Button[GRID_SIZE][GRID_SIZE];
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                int buttonId = getResources().getIdentifier("click_" + i + "_" + j, "id", getPackageName());
                buttons[i][j] = findViewById(buttonId);
                buttons[i][j].setOnClickListener(moleClickListener);
            }
        }
        }

    private void setMouseTTL(int ttl) {
        mouseTTL = ttl;
    }
    private void startGame() {
        totalMiceShown = 0;
        score = 0;
        timeRemaining = GAME_DURATION;
        countTime = countTime + 10;
        updateScore();
        updateTimerText();

        new CountDownTimer(GAME_DURATION, 1000) {

            public void onTick(long millisUntilFinished) {
                timeRemaining = (int) millisUntilFinished;
                updateTimerText();
                updateTotalShow();
                showRandomMole();
            }

            public void onFinish() {
                timeRemaining = 0;
                updateTimerText();
                endGame();
            }
        }.start();
    }

    private void updateTimerText() {
        int seconds = timeRemaining / 1000;
        timerTextView.setText("Time Remaining: " + seconds + " seconds");
    }

    private  void updateTotalShow(){
        scoreTextView.setText("Score: " + score + "       Total:" + getTotalMiceShown());
    }
    private void updateScore() {
        scoreTextView.setText("Score: " + score + "       Total:" + getTotalMiceShown());
    }

    private void showRandomMole() {
        int randomRow = new Random().nextInt(GRID_SIZE);
        int randomCol = new Random().nextInt(GRID_SIZE);

        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                buttons[i][j].setVisibility(View.INVISIBLE);
            }
        }

        buttons[randomRow][randomCol].setVisibility(View.VISIBLE);

        totalMiceShown++;
        countMice ++;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                buttons[randomRow][randomCol].setVisibility(View.INVISIBLE);
            }
        }, mouseTTL);
    }


    public int getTotalMiceShown() {
        return totalMiceShown;
    }


    private View.OnClickListener moleClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Button clickedButton = (Button) v;
            if (clickedButton.getVisibility() == View.VISIBLE) {
                score++;
                countClick = countClick + 1;
                updateScore();
                clickedButton.setVisibility(View.INVISIBLE);
            }
        }
    };
    private void endGame() {
        replayButton.setVisibility(View.VISIBLE);
    }

    protected void onPause() {
        super.onPause();
        // 在活動暫停時保存 countTime 的值
        saveDataToPreferences();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        saveDataToPreferences();
    }

    private void saveDataToPreferences() {

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS_KEY, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(TIME_KEY, countTime);
        editor.putInt(CLICK_KEY, countClick);
        editor.putInt(MISS_KEY, countMice);
        editor.apply();
    }

    public static String getSharedPrefsKey() {
        return SHARED_PREFS_KEY;
    }

    public static String getTimeKey() {
        return TIME_KEY;
    }

    public static String getClickKey() {
        return CLICK_KEY;
    }

    public static String getMissKey() {
        return MISS_KEY;
    }
}