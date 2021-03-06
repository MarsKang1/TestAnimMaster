package com.testanimmaster.ui.activity.valueanim;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.testanimmaster.R;
import com.testanimmaster.common.RecycleViewCallback;
import com.testanimmaster.common.StringItemAdapter;
import com.testanimmaster.ui.activity.TransitActivity;

import java.util.ArrayList;
import java.util.List;

public class AnimSetActivity extends AppCompatActivity implements RecycleViewCallback {
    RecyclerView rvMain;
    StringItemAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anim_set);
        rvMain = (RecyclerView) findViewById(R.id.rv_anim_set);
        initViews();
    }

    private void initViews() {
        List<String> dates = new ArrayList<>();
        dates.add("AnimSet动画");
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
                intent.putExtra("animFlg", "AnimSet");
                break;
            case 1:
                intent.putExtra("animFlg", "Parabola2");
                break;
        }
        startActivity(intent);
    }
}
