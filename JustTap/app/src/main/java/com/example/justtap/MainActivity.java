package com.example.justtap;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.net.Uri;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    // 声明 About_me_button、Play_button、Sitting_button 和 Mission_button 四个 ImageButton
    private ImageButton About_me_button;
    private ImageButton Play_button;
    private ImageButton Sitting_button;
    private ImageButton Record_button;

    @Override
    // 重写 onCreate() 方法,该方法在 Activity 创建时被调用
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 设置 Activity 的布局为 activity_main.xml
        setContentView(R.layout.activity_main);

        // 通过 findViewById() 方法获取 activity_main.xml 中的四个 ImageButton
        ImageButton circleImageButton = findViewById(R.id.icon);
        About_me_button = (ImageButton) findViewById(R.id.icon);
        Play_button = (ImageButton) findViewById(R.id.Play);
        Sitting_button = (ImageButton) findViewById(R.id.setting);
        Record_button = (ImageButton) findViewById(R.id.record);

        // 设置 circleImageButton 的图标
        circleImageButton.setImageResource(R.drawable.me_icon);

        // 为 About_me_button 添加点击事件监听器
        About_me_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 点击 About_me_button 时,跳转到 About_me Activity
                Intent I_about_me = new Intent(MainActivity.this, About_me.class);
                startActivity(I_about_me);
            }
        });

        // 为 Play_button 添加点击事件监听器
        Play_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 点击 Play_button 时,跳转到 Game_play Activity
                Intent I_play = new Intent(MainActivity.this, Game_play.class);
                startActivity(I_play);
            }
        });

        Sitting_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent I_sitting = new Intent(MainActivity.this, Setting.class);
                startActivity(I_sitting);
            }
        });

        Record_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent I_record = new Intent(MainActivity.this, Record.class);
                startActivity(I_record);
            }
        });

        try {
            MediaPlayerManager.getInstance().getMediaPlayer().setDataSource(this, Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.bgm));
            MediaPlayerManager.getInstance().getMediaPlayer().prepare();
            float volume = MediaPlayerManager.getInstance().getVolume();
            boolean isMusicPlaying = MediaPlayerManager.getInstance().isMusicPlaying();
            MediaPlayerManager.getInstance().setVolume(volume);
            MediaPlayerManager.getInstance().setMusicPlaying(isMusicPlaying);
        } catch (IOException e) {
            e.printStackTrace();
        }
        float volume = MediaPlayerManager.getInstance().getVolume();
        boolean isMusicPlaying = MediaPlayerManager.getInstance().isMusicPlaying();

        MediaPlayerManager.getInstance().setVolume(volume);
        MediaPlayerManager.getInstance().setMusicPlaying(isMusicPlaying);
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