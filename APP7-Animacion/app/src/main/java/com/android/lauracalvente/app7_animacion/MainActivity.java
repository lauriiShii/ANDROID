package com.android.lauracalvente.app7_animacion;

import android.graphics.drawable.AnimationDrawable;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    static AnimationDrawable animacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imgPikachu = (ImageView) findViewById(R.id.imgPikachu);
        imgPikachu.setBackgroundResource(R.drawable.pikachu);
        animacion = (AnimationDrawable) imgPikachu.getBackground();
        animacion.start();
    }
}
