package com.testanimmaster.ui.fragment.objectanim;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.testanimmaster.R;

public class BasicObjectAnimFragment extends Fragment {

    public static BasicObjectAnimFragment newInstance() {
        BasicObjectAnimFragment fragment = new BasicObjectAnimFragment();
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
                ObjectAnimator.ofFloat(iv_anim, "rotationX", 0.0F, 360.0F).setDuration(1000).start();  //x轴滑动    rotationX是getPropName反射获取
            }
        });

    }
}
