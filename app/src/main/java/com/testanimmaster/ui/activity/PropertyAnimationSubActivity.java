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

public class PropertyAnimationSubActivity extends AppCompatActivity implements RecycleViewCallback {
    RecyclerView rvMain;
    StringItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_animation_sub);
        rvMain = (RecyclerView) findViewById(R.id.rv_property_anim_sub);
        initViews();
    }

    private void initViews() {
        List<String> dates = new ArrayList<>();
        dates.add("ObjectAnimator基本使用");
        dates.add("不用animset多种动画一起作用");
        dates.add("抛物线");
        adapter = new StringItemAdapter(this, dates);
        adapter.setItemClickCallback(this);
        rvMain.setLayoutManager(new LinearLayoutManager(this));
        rvMain.scheduleLayoutAnimation();
        rvMain.setAdapter(adapter);
    }

    @Override
    public void onItemClick(View view, int position) {
        Intent intent = new Intent(this, TransitActivity.class);
        switch (position) {
            case 0:
                intent.putExtra("animFlg", "BasicObjectAnim");
                break;
            case 1:
                intent.putExtra("animFlg", "MultyObjectAnim");
                break;
            case 2:
                intent.putExtra("animFlg", "Parabola");
                break;
        }
        startActivity(intent);
    }
}
