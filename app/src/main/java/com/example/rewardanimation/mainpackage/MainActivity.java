package com.example.rewardanimation.mainpackage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentTransaction;

import android.animation.Animator;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.rewardanimation.Dialogs.CongratsDialog;
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
        lottieAnimationView.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                DialogFragment congrats=new CongratsDialog();
                FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
                congrats.show(fragmentTransaction,null);


            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        progressAnimation=new ProgressAnimation(progressBar,lottieAnimationView,textView);
        progressAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if(progressBar.getProgress()==100)
                    lottieAnimationView.playAnimation();

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                progressAnimation.setProgress(progress,(progress+20));
                progressAnimation.setDuration(500);
                progressBar.startAnimation(progressAnimation);
                progress+=20;
                progress=progress%100;
            }
        });



    }
}
