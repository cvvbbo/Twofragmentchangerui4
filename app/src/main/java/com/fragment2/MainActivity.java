package com.fragment2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    /**
     *
     * 本demo的目的是为了测试setUserVisibleHint这个方法。
     *
     *
     */

    @BindView(R.id.fl)
    FrameLayout fl;
    @BindView(R.id.bt1)
    Button bt1;
    @BindView(R.id.bt2)
    Button bt2;
    @BindView(R.id.bt3)
    Button bt3;
    @BindView(R.id.btlayout)
    LinearLayout btlayout;
    ArrayList<BaseFragment> baseFragments=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initfragment();
        bt1.setOnClickListener(this);
        bt2.setOnClickListener(this);
        bt3.setOnClickListener(this);
    }

    public void switchfragment(int j){
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        for (int i = 0; i < baseFragments.size(); i++) {
            Fragment f = baseFragments.get(i);
            if (i == j) {
                if (f.isAdded()) {
                    //已经添加过，直接展示
                    beginTransaction.show(f);
                } else {
                    //并没有添加，则把fragment添加进来
                        beginTransaction.add(R.id.fl, f);
                        //beginTransaction.addToBackStack(null);
                        //下面这个类似于commitAllowingStateLoss()
                        //getSupportFragmentManager().executePendingTransactions();
                }
            } else {
                if (f.isAdded()) {
                    //当遍历的和当前的不相等的时候，把这些全部隐藏起来。
                    beginTransaction.hide(f);
                }
            }
        }
        //最后一定要记得提交
        beginTransaction.commit();
    }

    public void initfragment(){
        baseFragments.add(new FristFragment());
        baseFragments.add(new SecondFragment());
        baseFragments.add(new ThirdFragment());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bt1:
                switchfragment(0);
                break;
            case R.id.bt2:
                switchfragment(1);
                break;
            case R.id.bt3:
                switchfragment(2);
                break;
        }
    }

    public static Myinterface.changerThirdFragment changerThirdFragment;

    public static void getThridFragment(Myinterface.changerThirdFragment changerThirdFragment){
        MainActivity.changerThirdFragment=changerThirdFragment;

    }

    @Override
    public void onBackPressed() {
        //但是这个方法是弹栈的方法，每次弹栈之后都会造成数据的丢失1.10 ，虽然不会造成视图的重叠。燃石之前看到有个帖子说，在消失之前把数据先保存一遍
        //然后再进行弹栈的操作
        if (changerThirdFragment!=null){
            changerThirdFragment.ThirdFragmentCallback();
        }

    }
}
