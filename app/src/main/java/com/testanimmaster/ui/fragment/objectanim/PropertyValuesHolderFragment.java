package com.testanimmaster.ui.fragment.objectanim;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
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

public class PropertyValuesHolderFragment extends Fragment {

    public static PropertyValuesHolderFragment newInstance() {
        return new PropertyValuesHolderFragment();
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
        Button pao = (Button) getActivity().findViewById(R.id.pao);
        pao.setText("点击动画播放");
        pao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat("alpha", 1f, 0f, 1f);
                PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat("scaleX", 1f, 0, 1f);
                PropertyValuesHolder pvhZ = PropertyValuesHolder.ofFloat("scaleY", 1f, 0, 1f);
                ObjectAnimator.ofPropertyValuesHolder(ball, pvhX, pvhY, pvhZ).setDuration(3000).start();
            }
        });
    }
}
