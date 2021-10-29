package com.fei.damovie.view.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.fei.damovie.R;
import com.fei.damovie.view.main.MainActivity;

public class SplashScreenActivity extends AppCompatActivity {

    private ImageView splash_shadow, splash_astronout, splash_popcorn;
    private TextView splash_textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        initialize();
        setAnimation();

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        }, 3000);

    }

    private void setAnimation() {
        splash_astronout.animate().alpha(1).translationY(0).setDuration(2000).setStartDelay(400).start();
        splash_popcorn.animate().alpha(1).translationY(0).setDuration(2000).setStartDelay(400).start();
        splash_shadow.animate().alpha(1).translationY(0).setDuration(2000).setStartDelay(400).start();
        splash_textView.animate().alpha(1).translationY(0).setDuration(2000).setStartDelay(800).start();
    }

    private void initialize() {
        splash_shadow = findViewById(R.id.splash_shadow);
        splash_astronout = findViewById(R.id.splash_astronout);
        splash_popcorn = findViewById(R.id.splash_popcorn);
        splash_textView = findViewById(R.id.splash_textView);

        splash_astronout.setTranslationY(-100);
        splash_popcorn.setTranslationY(-80);
        splash_shadow.setTranslationY(40);
        splash_textView.setTranslationY(80);

        splash_astronout.setAlpha(0.1F);
        splash_popcorn.setAlpha(0.1F);
        splash_shadow.setAlpha(0.1F);
        splash_textView.setAlpha(0.1F);
    }
}