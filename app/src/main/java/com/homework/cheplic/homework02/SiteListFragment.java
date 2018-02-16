package com.homework.cheplic.homework02;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.media.MediaBrowserCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class SiteListFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private SiteAdapter mAdapter;
    private Callbacks mCallbacks;

    public interface Callbacks{
        void onSiteSelected(FavoritePage favoritePage);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mCallbacks = (Callbacks) activity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_site_list, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }

    private void updateUI() {
        Database database = Database.getInstance();
        List<FavoritePage> pages = database.getPages();

        mAdapter = new SiteAdapter(pages);
        mRecyclerView.setAdapter(mAdapter);

    }

    private void saveDatabase() {
        try {
            Database.getInstance().save(new File(getActivity().getFilesDir(), "whatever.ser"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallbacks = null;
    }

    private class SiteHolder extends RecyclerView.ViewHolder{
        private FavoritePage mFavoritePage;
        public Button mButton;

        public SiteHolder(LayoutInflater inflater, ViewGroup parent){
            super(inflater.inflate(R.layout.list_item, parent, false));
            mButton = (Button) itemView.findViewById(R.id.list_item);
        }


        public void bind(FavoritePage page) {
            mFavoritePage = page;
            mButton.setText(page.getName());
            mButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mFavoritePage.incrementViewCount();
                    saveDatabase();
                    if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
                        mCallbacks.onSiteSelected(mFavoritePage);
                    else {
                        Intent intent = SiteDetailActivity.newIntent(getActivity(), mFavoritePage.getUUID());
                        startActivity(intent);
                    }
                }
            });
        }
    }

    private class SiteAdapter extends RecyclerView.Adapter<SiteHolder>{
        private List<FavoritePage> mFavoritePages;

        public SiteAdapter(List<FavoritePage> pages){
            mFavoritePages = pages;
        }

        @Override
        public SiteHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new SiteHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(SiteHolder holder, int position) {
            FavoritePage page = mFavoritePages.get(position);
            holder.bind(page);
        }

        @Override
        public int getItemCount() {
            return mFavoritePages.size();
        }
    }
}
