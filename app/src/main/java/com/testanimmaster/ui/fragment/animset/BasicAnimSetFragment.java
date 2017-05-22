package com.testanimmaster.ui.fragment.animset;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;

import com.testanimmaster.R;

public class BasicAnimSetFragment extends Fragment {

    public static BasicAnimSetFragment newInstance() {
        BasicAnimSetFragment fragment = new BasicAnimSetFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_bull_fall, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initDate();
    }

    private void initDate() {
        Button luoti = (Button) getActivity().findViewById(R.id.luoti);
        final ImageView ball = (ImageView) getActivity().findViewById(R.id.id_ball);
        final Button pao = (Button) getActivity().findViewById(R.id.pao);
        pao.setText("方案1");
        pao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //方案1
                ObjectAnimator anim1 = ObjectAnimator.ofFloat(ball, "scaleX", 1.0f, 2f);
                ObjectAnimator anim2 = ObjectAnimator.ofFloat(ball, "scaleY", 1.0f, 2f);
                AnimatorSet animSet = new AnimatorSet();
                animSet.setDuration(2000);
                animSet.setInterpolator(new LinearInterpolator());
                //两个动画同时执行
                animSet.playTogether(anim1, anim2);
                animSet.start();
            }
        });


        luoti.setText("方案2");
        luoti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //方案2
                ObjectAnimator anim1 = ObjectAnimator.ofFloat(ball, "scaleX", 1.0f, 2f);
                ObjectAnimator anim2 = ObjectAnimator.ofFloat(ball, "scaleY", 1.0f, 2f);
                ObjectAnimator anim3 = ObjectAnimator.ofFloat(ball, "x", 100, 0f);
                ObjectAnimator anim4 = ObjectAnimator.ofFloat(ball, "x", 100);
                //anim1，anim2, anim3同时执行  anim4接着执
                AnimatorSet animSet = new AnimatorSet();
                animSet.play(anim1).with(anim2).with(anim3);
//                animSet.play(anim2).with(anim3);
                animSet.play(anim4).after(anim3);
                animSet.setDuration(1000);
                animSet.start();
            }
        });
    }
}
