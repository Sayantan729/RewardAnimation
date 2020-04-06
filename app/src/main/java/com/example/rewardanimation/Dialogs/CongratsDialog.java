package com.example.rewardanimation.Dialogs;

import android.animation.Animator;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.airbnb.lottie.LottieAnimationView;
import com.example.rewardanimation.R;

public class CongratsDialog extends DialogFragment {
    private LottieAnimationView lottieAnimationView;
    private TextView textView;
    private Animation zoomout;
    private SharedPreferences sharedPreferences;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.congratsdialog,container,false);

        lottieAnimationView=view.findViewById(R.id.congrats);
        textView=view.findViewById(R.id.level);
        sharedPreferences=getContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        textView.setText("Level "+sharedPreferences.getInt("Level",1));

        lottieAnimationView.setAnimation(R.raw.levelup);
        zoomout= AnimationUtils.loadAnimation(getContext(),R.anim.zoomout);
        zoomout.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {


            }

            @Override
            public void onAnimationEnd(Animation animation) {



            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        lottieAnimationView.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {


            }

            @Override
            public void onAnimationEnd(Animator animator) {
                textView.startAnimation(zoomout);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putInt("Level",sharedPreferences.getInt("Level",1)+1);
                editor.apply();
                textView.setText("Level "+sharedPreferences.getInt("Level",1));



                //dismiss();

            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        lottieAnimationView.playAnimation();

        return view;
    }
}
