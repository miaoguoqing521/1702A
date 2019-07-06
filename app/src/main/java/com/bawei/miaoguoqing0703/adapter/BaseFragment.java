package com.bawei.miaoguoqing0703.adapter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

public abstract class BaseFragment extends FragmentActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(bindLayout());
        bindview();
        bindData();
    }

    protected abstract Bundle bindLayout();

    protected abstract void bindview();

    protected abstract void bindData();
}
