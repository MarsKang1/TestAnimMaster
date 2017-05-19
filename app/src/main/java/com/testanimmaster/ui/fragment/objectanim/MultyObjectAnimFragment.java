package com.testanimmaster.ui.fragment.objectanim;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.testanimmaster.R;

public class MultyObjectAnimFragment extends Fragment {

    public static MultyObjectAnimFragment newInstance() {
        return new MultyObjectAnimFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_basic_object_anim, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initDate();
    }

    private void initDate() {
        final ImageView iv_anim = (ImageView) getActivity().findViewById(R.id.iv_anim);
        iv_anim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator anim = ObjectAnimator.ofFloat(iv_anim, "zhy", 1.0F, 0.0F).setDuration(500);//设置属性的那个字符串，随便写一个该对象没有的属性，就是不管~~咱们只需要它按照时间插值和持续时间计算的那个值，我们自己手动调用~
                anim.start();
                anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        float cVal = (Float) animation.getAnimatedValue();
                        iv_anim.setAlpha(cVal);
                        iv_anim.setScaleX(cVal);
                        iv_anim.setScaleY(cVal);
                    }
                });
            }
        });

    }
}
