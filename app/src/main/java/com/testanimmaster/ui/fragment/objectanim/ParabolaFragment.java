package com.testanimmaster.ui.fragment.objectanim;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.testanimmaster.R;

public class ParabolaFragment extends Fragment {

    public static ParabolaFragment newInstance() {
        return new ParabolaFragment();
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
        Button pao = (Button) getActivity().findViewById(R.id.pao);
        pao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paowuxian();
            }
        });

        Button luoti = (Button) getActivity().findViewById(R.id.luoti);
        luoti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ziyouLuoTi();
            }
        });


    }

    public void paowuxian() {
        final ImageView ball = (ImageView) getActivity().findViewById(R.id.id_ball);
        ObjectAnimator anim = ObjectAnimator.ofFloat(ball, "zhy", 1.0F, 0.0F).setDuration(2000);//设置属性的那个字符串，随便写一个该对象没有的属性，就是不管~~咱们只需要它按照时间插值和持续时间计算的那个值，我们自己手动调用~
        anim.start();
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float x = 50;
                float cVal = (Float) animation.getAnimatedValue();
                ball.setTranslationX((1 - cVal) * x * 25);
                ball.setTranslationY((1 - cVal) * x * x * (1 - cVal));
            }
        });
    }

    public void ziyouLuoTi() {
        final ImageView ball = (ImageView) getActivity().findViewById(R.id.id_ball);
        ObjectAnimator anim = ObjectAnimator.ofFloat(ball, "zhy", 1.0F, 0.0F).setDuration(2000);//设置属性的那个字符串，随便写一个该对象没有的属性，就是不管~~咱们只需要它按照时间插值和持续时间计算的那个值，我们自己手动调用~
        anim.start();
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float g = 10;
                float h = 20;
                float cVal = (Float) animation.getAnimatedValue();
                ball.setTranslationY((1 - cVal) * h * (1 - cVal) * h * g * 0.5f);
            }
        });
    }
}
