package com.testanimmaster.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.testanimmaster.R;
import com.testanimmaster.common.RecycleViewCallback;
import com.testanimmaster.common.StringItemAdapter;

import java.util.ArrayList;
import java.util.List;

public class PropertyAnimationActivity extends AppCompatActivity implements RecycleViewCallback {
    RecyclerView rvMain;
    StringItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_animation);
        rvMain = (RecyclerView) findViewById(R.id.rv_property_anim);
        initViews();
    }

    private void initViews() {
        List<String> dates = new ArrayList<>();
        dates.add("ObjectAnimator动画");
        dates.add("ValueAnimator动画");
        dates.add("AnimatorSet动画");
        dates.add("xml定义动画");
        adapter = new StringItemAdapter(this, dates);
        adapter.setItemClickCallback(this);
        rvMain.setLayoutManager(new LinearLayoutManager(this));
        rvMain.scheduleLayoutAnimation();
        rvMain.setAdapter(adapter);
    }

    @Override
    public void onItemClick(View view, int position) {
        Intent intent = new Intent();
        switch (position) {
            case 0:
                intent.setClass(this, PropertyAnimationSubActivity.class);
                startActivity(intent);
                break;
            case 1:
                break;
        }
    }
}
