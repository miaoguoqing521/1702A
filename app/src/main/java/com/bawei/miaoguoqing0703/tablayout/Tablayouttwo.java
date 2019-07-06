package com.bawei.miaoguoqing0703.tablayout;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.bawei.miaoguoqing0703.R;

public class Tablayouttwo extends Fragment {

    private WebView web;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.tablayouttwo, container, false);
        web = inflate.findViewById(R.id.web);
        web.loadUrl("http://blog.zhaoliang5156.cn/zixunnew/categories");
        web.setWebViewClient(new WebViewClient());
        return inflate;
    }
}
