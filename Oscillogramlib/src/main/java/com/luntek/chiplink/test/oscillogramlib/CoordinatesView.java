package com.luntek.chiplink.test.oscillogramlib;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;


/**
 * 可拖动坐标轴
 *
 * @author lxc
 */
public class CoordinatesView extends View {
    private long lastClickTime;
    //默认圆心
    public Point point = new Point(0, 0);
    //xy坐标轴长度
    public int lengthX = 0;
    public int lengthY = 0;
    //移动的单位距离
    private float move = 0;
    //获取view长宽
    private int width = 0;
    private int height = 0;
    //获取光标
    private int x = 0;
    private int y = 0;
    private Paint paint = new Paint();


    /**
     * 设置初始属性
     */
    public CoordinatesView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(3);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        point.set(width / 2, height / 2);
    }

    public CoordinatesView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    public CoordinatesView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        width = getWidth();
        height = getHeight();
        lengthX = width;
        lengthY = height;
        canvas.drawLine(point.x - lengthX, point.y, point.x + lengthX, point.y, paint);
        canvas.drawLine(point.x, point.y - lengthY, point.x, point.y + lengthY, paint);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        x = (int) event.getX();
        y = (int) event.getY();
        if (x < 0 || y < 0 || x > width || y > height) {
            return true;
        }

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                point.set(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                //计算移动的距离
                point.set(x, y);
                invalidate();
                break;
        }
        return true;
    }

//    @Override
//    public void setOnClickListener(@Nullable OnClickListener l) {
//        super.setOnClickListener(l);
//        if (isFastDoubleClick()) {
//            Toast.makeText(getContext(), "双击", Toast.LENGTH_SHORT).show();
//        } else {
//            Toast.makeText(getContext(), "点击", Toast.LENGTH_SHORT).show();
//        }
//
//
//    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }


    public void setLength(int lengthX, int lengthY) {
        this.lengthX = lengthX;
        this.lengthY = lengthY;

    }


    /**
     * 双击判断
     *
     * @return
     */
    public boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if (0 < timeD && timeD < 800) {
            return true;
        }
        lastClickTime = time;
        return false;
    }

    //设置可滑动的位置;

}
