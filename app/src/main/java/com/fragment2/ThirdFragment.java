package com.fragment2;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.fragment2.childfragment.FiveFragment;
import com.fragment2.childfragment.FourFragment;
import com.fragment2.childfragment.SevenFragment;
import com.fragment2.childfragment.SixFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiong on 2017/12/12.
 */

public class ThirdFragment extends BaseFragment implements Myinterface.changerThirdFragment,View.OnClickListener{


    private Button bt;
    private Button bt1;
    private Button bt2;
    private Button bt3;
    private LinearLayout ly;
    private FrameLayout fl;

    public static Myinterface.changerFourfragment changerFourfragment;

    ArrayList<BaseFragment> childfragment=new ArrayList<>();
    private FragmentManager childFragmentManager;
    private FragmentTransaction beginTransaction;

    @Override
    public int getlayout() {
        return R.layout.fragment_third;
    }

    public static void getFourFragment(Myinterface.changerFourfragment changerFourfragment){
        ThirdFragment.changerFourfragment=changerFourfragment;


    }

    @Override
    public void initview() {

        initchild();

        //这里的这个不能注释掉，因为这个的实例是用来调用ThirdFragment的方法的12.14
        FourFragment.getThirdFragment(this);
        FiveFragment.getThirdFragment(this);
        SixFragment.getThirdFragment(this);
        SevenFragment.getThirdFragment(this);
        MainActivity.getThridFragment(this);


//      fragmenttoFragment.Callback();
        bt = (Button) view.findViewById(R.id.bt);
        bt1 = (Button) view.findViewById(R.id.bt1);
        bt2 = (Button) view.findViewById(R.id.bt2);
        bt3 = (Button) view.findViewById(R.id.bt3);

        bt.setOnClickListener(this);
        bt1.setOnClickListener(this);
        bt2.setOnClickListener(this);
        bt3.setOnClickListener(this);


        ly = (LinearLayout) view.findViewById(R.id.ly);
        fl = (FrameLayout) view.findViewById(R.id.fl);
//        bt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                transation();
//
//            }
//        });

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.bt:
                //transation();
                switchchildfragment(0);
                break;
            case R.id.bt1:
                switchchildfragment(1);
                break;
            case R.id.bt2:
                switchchildfragment(2);
                break;
            case R.id.bt3:
                switchchildfragment(3);
                break;
        }

    }



    public void transation(){
        //这里的布局是在linearlayout外面嵌套了framelayout
       //ly.setVisibility(View.GONE);


        ly.setVisibility(View.GONE);
        getChildFragmentManager()
                .beginTransaction()
                .replace(R.id.fl,new FourFragment())
                .commit();
    }


//    public void switchchildfragment(int i){
//        ly.setVisibility(View.GONE);
//        FragmentManager childFragmentManager = getChildFragmentManager();
//        FragmentTransaction childbeginTransaction = childFragmentManager.beginTransaction();
//        for (int j=0;j<childfragment.size();j++){
//            BaseFragment childf = childfragment.get(j);
//            if (i==j){
//                if (childf.isAdded()){
//                    childbeginTransaction.show(childf);
//                }else {
//                    childbeginTransaction.add(R.id.fl,childf).addToBackStack(null);
//                }
//            }else {
//                if (childf.isAdded()){
//                    childbeginTransaction.hide(childf);
//                }
//            }
//
//        }
//        childbeginTransaction.commit();
//    }




        public void switchchildfragment(int j){
        ly.setVisibility(View.GONE);
        //这个是把集合放到外面来12.14
        BaseFragment baseFragment = childfragment.get(j);
            childFragmentManager = getChildFragmentManager();
            beginTransaction = childFragmentManager.beginTransaction();

            for (int i=0;i<childfragment.size();i++){
            if (i==j){
                if (baseFragment.isAdded()){
                    beginTransaction.show(baseFragment);
                }else {
                    beginTransaction.add(R.id.fl,baseFragment)
                            .addToBackStack(null);
                }

            }else {
                if (baseFragment.isAdded()){
                    beginTransaction.hide(baseFragment);
                }
            }
        }
        beginTransaction.commit();

    }


    public void initchild(){
        childfragment.add(new FourFragment());
        childfragment.add(new FiveFragment());
        childfragment.add(new SixFragment());
        childfragment.add(new SevenFragment());

    }







    @Override
    public void ThirdFragmentCallback() {
        /***同一个fragment的事务才能对其进行操作  1.10***/
        /***如果不是使用同一个事务返都返不会来***/
        childFragmentManager.popBackStack();
        ly.setVisibility(View.VISIBLE);

    }


}
