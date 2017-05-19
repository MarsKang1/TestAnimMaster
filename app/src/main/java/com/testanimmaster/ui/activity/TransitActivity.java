package com.testanimmaster.ui.activity;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.testanimmaster.R;
import com.testanimmaster.ui.fragment.objectanim.BasicObjectAnimFragment;
import com.testanimmaster.ui.fragment.objectanim.MultyObjectAnimFragment;
import com.testanimmaster.ui.fragment.objectanim.ParabolaFragment;

public class TransitActivity extends AppCompatActivity {
    FrameLayout flContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transit);
        flContainer = (FrameLayout) findViewById(R.id.fl_container);
        Intent intent = getIntent();
        String animFlg = intent.getStringExtra("animFlg");
        if (animFlg == null) return;
        setFragmentByAnimFlg(animFlg);
    }

    private void setFragmentByAnimFlg(String animFlg) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        switch (animFlg) {
            case "BasicObjectAnim":
                transaction.replace(R.id.fl_container, BasicObjectAnimFragment.newInstance());
                break;
            case "MultyObjectAnim":
                transaction.replace(R.id.fl_container, MultyObjectAnimFragment.newInstance());
                break;
            case "Parabola":
                transaction.replace(R.id.fl_container, ParabolaFragment.newInstance());
                break;
        }
        transaction.commit();
    }


}
