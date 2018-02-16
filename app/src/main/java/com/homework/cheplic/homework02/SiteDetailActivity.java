package com.homework.cheplic.homework02;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import java.util.UUID;

/**
 * Created by Devin on 2/7/2018.
 */

public class SiteDetailActivity extends SingleFragmentActivity {
    private static final String EXTRA_PAGE_INDEX = "com.homework.cheplic.homework02.page_index";

    @Override
    protected Fragment createFragment() {
        UUID id = (UUID) getIntent().getSerializableExtra(EXTRA_PAGE_INDEX);
        SiteDetailFragment newFrag = SiteDetailFragment.get(id);
        return newFrag;
    }

    public static Intent newIntent(Context context, UUID uuid){
        Intent i = new Intent(context, SiteDetailActivity.class);
        i.putExtra(EXTRA_PAGE_INDEX, uuid);
        return i;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }
}
