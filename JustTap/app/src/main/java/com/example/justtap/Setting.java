package com.example.justtap;

import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Setting extends AppCompatActivity {
    private static final String SWITCH_STATE = "switch_state";
    private SeekBar volumeSeekBar;
    private Switch musicSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        volumeSeekBar = findViewById(R.id.volume_seekbar);
        musicSwitch = findViewById(R.id.music_switch);

        boolean switchState = getSharedPreferences("my_prefs", MODE_PRIVATE).getBoolean(SWITCH_STATE, false);
        musicSwitch.setChecked(switchState);


        MediaPlayerManager.getInstance().setVolume(MediaPlayerManager.getInstance().getVolume());


        volumeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                float volume = (float) progress / 100;
                MediaPlayerManager.getInstance().setVolume(volume);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        musicSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            getSharedPreferences("my_prefs", MODE_PRIVATE)
                    .edit()
                    .putBoolean(SWITCH_STATE, isChecked)
                    .apply();
            if (isChecked) {
                MediaPlayerManager.getInstance().start();
                Toast.makeText(Setting.this, "music is playing", Toast.LENGTH_SHORT).show();
            } else {
                MediaPlayerManager.getInstance().pause();
                Toast.makeText(Setting.this, "music is pausing", Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
    @Override
    protected void onStop() {
        super.onStop();
    }
}