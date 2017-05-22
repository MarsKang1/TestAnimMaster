package com.testanimmaster.ui.fragment.XMLValueAnim;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;

import com.testanimmaster.R;

public class BasicXMLPropertyAnimtFragment extends Fragment {

    public static BasicXMLPropertyAnimtFragment newInstance() {
        BasicXMLPropertyAnimtFragment fragment = new BasicXMLPropertyAnimtFragment();
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
        pao.setText("点击播放动画");
        pao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 加载动画
                Animator anim = AnimatorInflater.loadAnimator(getContext(), R.animator.basicanim);
                ball.setPivotX(0);
                ball.setPivotY(0);
                //显示的调用invalidate
                ball.invalidate();
                anim.setTarget(ball);
                anim.start();
            }
        });
    }
}
