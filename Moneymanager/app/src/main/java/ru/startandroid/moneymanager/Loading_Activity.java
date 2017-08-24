package ru.startandroid.moneymanager;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class Loading_Activity extends AppCompatActivity {

    AnimationDrawable animation;
    Animation anim = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        ImageView image = (ImageView)findViewById(R.id.loadAnim);
        ImageView imageFace = (ImageView)findViewById(R.id.imageFace);

        //анимация текста
        image.setBackgroundResource(R.drawable.animation);
        animation= (AnimationDrawable)image.getBackground();
        animation.start();

        anim = AnimationUtils.loadAnimation(this, R.anim.transform);
        imageFace.startAnimation(anim);


        Runnable r = new Runnable() {
            @Override
            public void run(){
                Intent intent = new Intent(getApplicationContext(), TestActivity.class);
                startActivity(intent);
            }
        };
        Handler h = new Handler();
        h.postDelayed(r, 3000);

    }
}
