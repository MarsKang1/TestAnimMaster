package com.testanimmaster.ui.fragment.valueanim;

import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.testanimmaster.R;

public class BasicValueAnimFragment extends Fragment {

    public static BasicValueAnimFragment newInstance() {
        BasicValueAnimFragment fragment = new BasicValueAnimFragment();
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
        luoti.setVisibility(View.GONE);
        final ImageView ball = (ImageView) getActivity().findViewById(R.id.id_ball);
        final Button pao = (Button) getActivity().findViewById(R.id.pao);
        pao.setText("点击动画播放");
        pao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
                int mScreenHeight = wm.getDefaultDisplay().getHeight();
                ValueAnimator animator = ValueAnimator.ofFloat(0, mScreenHeight - pao.getHeight());
                animator.setTarget(ball);
                animator.setDuration(3000).start();
                animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        ball.setTranslationY((Float) animation.getAnimatedValue());
                    }
                });
            }
        });
    }
}
