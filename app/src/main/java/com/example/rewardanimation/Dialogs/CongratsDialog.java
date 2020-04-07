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
    private LottieAnimationView levelup,achievement;
    private TextView level,nachievement;
    private Animation zoomout1,zoomout2,bounce;
    private SharedPreferences sharedPreferences;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.congratsdialog,container,false);
        levelup=view.findViewById(R.id.congrats);
        achievement=view.findViewById(R.id.achieve);
        level=view.findViewById(R.id.level);
        nachievement=view.findViewById(R.id.nachieve);
        sharedPreferences=getContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        level.setText("Level "+sharedPreferences.getInt("Level",1));
        levelup.setAnimation(R.raw.levelup);
        achievement.setAnimation(R.raw.correct);
        achievement.setRepeatCount(Animation.INFINITE);
        achievement.playAnimation();
        zoomout1= AnimationUtils.loadAnimation(getContext(),R.anim.zoomout);
        zoomout2= AnimationUtils.loadAnimation(getContext(),R.anim.zoomout);
        bounce=AnimationUtils.loadAnimation(getContext(),R.anim.bounce);

        nachievement.setText("New Achievement");
        nachievement.setVisibility(View.VISIBLE);
        nachievement.startAnimation(zoomout2);
        zoomout2.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                levelup.setVisibility(View.VISIBLE);
                levelup.playAnimation();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        levelup.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {


            }

            @Override
            public void onAnimationEnd(Animator animator) {
                levelup.startAnimation(bounce);
                level.startAnimation(zoomout1);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putInt("Level",sharedPreferences.getInt("Level",1)+1);
                editor.apply();
                level.setText("Level "+sharedPreferences.getInt("Level",1));




                //dismiss();

            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });


        return view;
    }
}
