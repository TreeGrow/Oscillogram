package com.luntek.chiplink.test.oscillogramlib;


import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import java.util.List;

/**
 * @author lxc
 */
public class CustomLayout extends RelativeLayout {
    CoordinatesView coordinatesView;
    TimingView timingView;


    public CustomLayout(Context context) {
        super(context);
    }

    public CustomLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        View view = LayoutInflater.from(context).inflate(R.layout.product_item_layout, this);
        initView(view);
    }

    public CustomLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }


    public CustomLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    private void initView(View view) {
        timingView = view.findViewById(R.id.timing);
        coordinatesView = view.findViewById(R.id.Coordinates);


    }

    public void setTimingView(List<ItemBean> item) {
        timingView.setTimingList(item);
    }

    public void setTimingViews(List<ItemBean> item, List<ItemBean> item2) {
        timingView.setTimingList(item, item2);
    }

    public void setTxtSize(int txtSize) {
        timingView.setTxtSize(txtSize);
    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
