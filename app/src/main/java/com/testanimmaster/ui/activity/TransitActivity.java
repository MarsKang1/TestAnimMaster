package com.testanimmaster.ui.activity;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.testanimmaster.R;
import com.testanimmaster.ui.fragment.XMLValueAnim.BasicXMLPropertyAnimtFragment;
import com.testanimmaster.ui.fragment.animset.BasicAnimSetFragment;
import com.testanimmaster.ui.fragment.objectanim.BasicObjectAnimFragment;
import com.testanimmaster.ui.fragment.objectanim.MultyObjectAnimFragment;
import com.testanimmaster.ui.fragment.objectanim.ParabolaObjectAnimFragment;
import com.testanimmaster.ui.fragment.objectanim.PropertyValuesHolderFragment;
import com.testanimmaster.ui.fragment.valueanim.BasicValueAnimFragment;
import com.testanimmaster.ui.fragment.valueanim.ParabolaValueAnimFragment;

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
            case "BasicObjectAnim"://ObjectAnim基本使用
                transaction.replace(R.id.fl_container, BasicObjectAnimFragment.newInstance());
                break;
            case "MultyObjectAnim"://多种动画组合
                transaction.replace(R.id.fl_container, MultyObjectAnimFragment.newInstance());
                break;
            case "Parabola"://自由落体+抛物线objectAnim
                transaction.replace(R.id.fl_container, ParabolaObjectAnimFragment.newInstance());
                break;
            case "PropertyValuesHolder"://使用PropertyValuesHolder控制动画
                transaction.replace(R.id.fl_container, PropertyValuesHolderFragment.newInstance());
                break;
            case "BasicValueAnim"://ValueAnim基本使用
                transaction.replace(R.id.fl_container, BasicValueAnimFragment.newInstance());
                break;
            case "Parabola2"://自由落体+抛物线valueAnim
                transaction.replace(R.id.fl_container, ParabolaValueAnimFragment.newInstance());
                break;
            case "AnimSet"://
                transaction.replace(R.id.fl_container, BasicAnimSetFragment.newInstance());
                break;
            case "XMLBasicValueAnim"://
                transaction.replace(R.id.fl_container, BasicXMLPropertyAnimtFragment.newInstance());
                break;
        }
        transaction.commit();
    }


}
