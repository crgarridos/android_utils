package com.cgarrido.android.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

public class OverlayOnTouchView extends FrameLayout {
    public OverlayOnTouchView(Context context) {
        super(context);
        init(context);
    }

    public OverlayOnTouchView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public OverlayOnTouchView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(final Context ctx) {
        this.setForeground(ResUtils.drw(R.drawable.overlay));
    }

}
