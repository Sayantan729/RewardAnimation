package com.example.rewardanimation.mainpackage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.rewardanimation.R;
import com.example.rewardanimation.utilitypackage.ProgressAnimation;

public class MainActivity extends AppCompatActivity {
    private int progress;
    private ProgressBar progressBar;
    private Button button;
    private ProgressAnimation progressAnimation;
    private LottieAnimationView lottieAnimationView;
    private TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progress=0;
        progressBar=findViewById(R.id.rewardprogress);
        lottieAnimationView=findViewById(R.id.celebrate);
        textView=findViewById(R.id.progress);
        textView.setText(progress+"/100");
        button=findViewById(R.id.tap);
        lottieAnimationView.setAnimation(R.raw.celebration);
        progressAnimation=new ProgressAnimation(progressBar,lottieAnimationView,textView);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progressAnimation.setProgress(progress,(progress+20));
                progressAnimation.setDuration(500);
                progressBar.startAnimation(progressAnimation);
                progress+=20;
                if(progress==100)
                {
                    progress=0;
                    progressBar.setProgress(0);
                }

            }
        });



    }
}
