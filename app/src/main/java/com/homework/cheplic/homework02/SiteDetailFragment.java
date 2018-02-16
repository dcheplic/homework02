package com.homework.cheplic.homework02;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;
import java.util.UUID;

public class SiteDetailFragment extends Fragment {

    private static final String BUN_SITE_ID = "list_item_id";
    private TextView mName;
    private TextView mUrl;
    private TextView mCount;
    private Button mBack;

    private static WebView mWebView;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_site_detail, container, false);

        FavoritePage page = Database.getInstance().getPageViaUUID((UUID) getArguments().getSerializable(BUN_SITE_ID));

        mUrl = (TextView) v.findViewById(R.id.url);
        mUrl.setText(page.getUrl());

        mCount = (TextView) v.findViewById(R.id.count);
        mCount.setText("" + page.getViewCount());

        mWebView = (WebView) v.findViewById(R.id.detail_web_view);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.loadUrl(page.getUrl());
        mWebView.setWebViewClient(new WebViewClient());
        mWebView.setInitialScale(1);
        mWebView.getSettings().setUseWideViewPort(true);

        mBack = (Button) v.findViewById(R.id.back_button);
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mWebView.canGoBack())
                    mWebView.goBack();
            }
        });

        return v;
    }

    public static SiteDetailFragment get(UUID id){
        Bundle buns = new Bundle();
        buns.putSerializable(BUN_SITE_ID, id);

        SiteDetailFragment frag = new SiteDetailFragment();
        frag.setArguments(buns);
        return frag;
    }
}
