package com.testanimmaster.ui.fragment.valueanim;

import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;

import com.testanimmaster.R;

import static android.content.ContentValues.TAG;

public class ParabolaValueAnimFragment extends Fragment {

    public static ParabolaValueAnimFragment newInstance() {
        return new ParabolaValueAnimFragment();
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
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setDuration(3000);
        valueAnimator.setObjectValues(new PointF(0, 0));
        valueAnimator.setInterpolator(new LinearInterpolator());//控制动画执行的速度
        //TypeEvaluator完成的工作为动画从起始值到结束值的平滑过渡 fraction代表动画的完成度  startValue动画的起始值  endValue动画的结束值
        valueAnimator.setEvaluator(new TypeEvaluator<PointF>() {
            @Override   // fraction = t / duration
            public PointF evaluate(float fraction, PointF startValue, PointF endValue) {
                PointF point = new PointF(); // x方向200px/s ，则y方向0.5 * 10 * t
                point.x = 200 * fraction * 3;
                point.y = 0.5f * 200 * (fraction * 3) * (fraction * 3);
                return point;
            }
        });
        valueAnimator.start();
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                PointF point = (PointF) animation.getAnimatedValue();//默认是0-1的完成比例 设置TypeEvaluator后为返回内容的返回值
                ball.setX(point.x);
                ball.setY(point.y);
            }
        });
//        PointF和Point的区别就是x,y的单位一个是float,一个是int;RectF,Rect也是）PointF中包含了x,y的当前位置～然后我们在监听器中获取
    }

    public void ziyouLuoTi() {
        /*final ImageView ball = (ImageView) getActivity().findViewById(R.id.id_ball);
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
        });*/
    }
}
