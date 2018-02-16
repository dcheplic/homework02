package com.homework.cheplic.homework02;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import java.io.File;
import java.io.IOException;

/**
 * Created by Devin on 2/7/2018.
 */

public class SiteListActivity extends SingleFragmentActivity implements SiteListFragment.Callbacks{

    @Override
    protected Fragment createFragment() {
        return new SiteListFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        if(Database.getInstance() == null)
            Database.createDefaultDatabase();
        else
            loadDatabase();
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onSiteSelected(FavoritePage page) {
        Fragment newDetail = SiteDetailFragment.get(page.getUUID());
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.detail_fragment_container, newDetail).commit();
    }

    private void loadDatabase() {
        try {
            Database.load(new File(getFilesDir(), "whatever.ser"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
