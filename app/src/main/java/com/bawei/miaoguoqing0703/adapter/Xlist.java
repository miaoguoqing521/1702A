package com.bawei.miaoguoqing0703.adapter;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

public class Xlist extends ListView {
    public Xlist(Context context) {
        super(context);
    }

    public Xlist(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Xlist(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
