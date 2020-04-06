package com.example.rewardanimation.utilitypackage;

import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class ProgressAnimation extends Animation {
    private ProgressBar progressBar;
    private LottieAnimationView lottieAnimationView;
    private TextView textView;
    private int from;
    private int  to;

    public ProgressAnimation(ProgressBar progressBar, LottieAnimationView lottieAnimationView, TextView textView) {
        super();
        this.progressBar = progressBar;
        this.lottieAnimationView=lottieAnimationView;
        this.textView=textView;

    }
    public void setProgress(int from,int to)
    {
        this.from = from;
        this.to = to;
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        super.applyTransformation(interpolatedTime, t);
        float value = from + (to - from) * interpolatedTime;
        progressBar.setProgress((int) value);
        textView.setText((int) value+"/100");

    }
}
