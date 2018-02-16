package com.homework.cheplic.homework02;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Devin on 2/7/2018.
 */

public abstract class SingleFragmentActivity extends AppCompatActivity {

    protected  abstract Fragment createFragment();

    @LayoutRes
    protected int getLayoutResId(){
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
            return R.layout.activity_two_pane;
        else
            return R.layout.activity_fragment_host;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());

        FragmentManager fm = getSupportFragmentManager();
        Fragment f = fm.findFragmentById(R.id.fragment_container);

        if(f == null){
            f = createFragment();
            fm.beginTransaction()
                    .add(R.id.fragment_container, f)
                    .commit();
        }


    }
}
