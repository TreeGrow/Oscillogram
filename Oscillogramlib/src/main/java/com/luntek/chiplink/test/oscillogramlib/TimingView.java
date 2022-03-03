package com.luntek.chiplink.test.oscillogramlib;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lxc
 */
public class TimingView extends View {
    //线路的名称
    public List<ItemBean> item = new ArrayList<>();
    //单位长度
    private int length = 10;
    //默认行距
    private int rowledge = 50;
    //获取画布大小
    private float width = 0;
    private float height = 0;
    //设置txt字体大小
    private int txtSize = 20;
    //txt起始偏移量
    private int XOffset = 100;
    //判断数据是否重复发送
    private byte change = 99;
    private String TAG = "TimingView";
    private Path path = new Path();
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Canvas canvas;


    public void timingView(List<ItemBean> item) {
        this.item = item;
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(txtSize);
        invalidate();
    }


    public TimingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TimingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public TimingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.canvas = canvas;
        this.width = getWidth();
        this.height = getHeight();
        rowledge = (int) (height / (item.size() + 1));
        length = (int) ((width - XOffset - 150) / 35);
        canvas.drawColor(Color.WHITE);
        for (int a = 0; a < item.size(); a++) {
            int y = (a + 1) * rowledge;
            canvas.drawText(item.get(a).getName(), XOffset, y, paint);
            byte[] items = item.get(a).array;
            int c = items.length;
            for (int i = 0; i < c; i++) {
                //x坐标
                int x = XOffset + 150 + i * length;
                switch (items[i]) {
                    case 0:
                        drawL(x, y + length / 2);
                        break;
                    case 1:
                        drawH(x, y - length / 2);
                        break;
                    case 3:
                        drawDummyLine(x, y - length / 2);
                        drawDummyLine(x, y + length / 2);
                        break;
                    default:
                        drawHRES(x, y);
                        break;
                }
                //判断是否循环
                if (change != items[i] && i != 0) {
                    if (change == 3) {
                        drawLine(x, y, false);
                    } else {
                        drawLine(x, y, true);
                    }

                }
                change = items[i];
            }
        }

    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    /**
     * 绘制高电平  1
     */
    public void drawH(int x, int y) {
        canvas.drawLine(x, y, x + length, y, paint);
    }

    /**
     * 绘制低电平  0
     */
    public void drawL(int x, int y) {
        canvas.drawLine(x, y, x + length, y, paint);
    }

    /**
     * 绘制高低电平中间连线
     *
     * @param f 判断是虚线还是直线 false 为虚线
     */
    public void drawLine(int x, int y, boolean f) {
        if (f) {
            canvas.drawLine(x, y - length / 2, x, y + length / 2, paint);
        } else {
            canvas.drawLine(x, y + 2, x, y + length / 2, paint);
            canvas.drawLine(x, y - 2, x, y - length / 2, paint);
        }
    }

    /**
     * 绘制虚线 3
     */
    public void drawDummyLine(int x, int y) {
        canvas.drawLine(x, y, x + length / 4 * 3 - 7.5f, y, paint);
        canvas.drawLine(x + length / 4 * 3 + 7.5f, y, x + length, y, paint);
    }

    /**
     * 绘制高阻 4
     */
    public void drawHRES(int x, int y) {
        int F2 = length / 2;
        //加个阴影
        paint.setColor(Color.GRAY);
        canvas.drawRect(x,y - F2,x + length,y + F2,paint);
        paint.setColor(Color.BLACK);
        //上线两行
        canvas.drawLine(x, y - F2, x + length, y - F2, paint);
        canvas.drawLine(x, y + F2, x + length, y + F2, paint);
        //中间三角形
        canvas.drawLine(x, y - F2, x + F2, y + F2, paint);
        canvas.drawLine(x + F2, y - F2, x + length, y + F2, paint);

        canvas.drawLine(x, y + F2, x + F2, y - F2, paint);
        canvas.drawLine(x + F2, y + F2, x + length, y - F2, paint);


    }


    /**
     * 设置TXT大小
     *
     * @param txtSize 大小
     */
    public void setTxtSize(int txtSize) {
        this.txtSize = txtSize;
    }

    public int getTxtSize() {
        return txtSize;
    }


    /**
     * 设置线的单位长度
     *
     * @param length 单位长度
     */
    public void setLength(int length) {
        this.length = length;
    }

    public int getLength() {
        return length;
    }


    public int getXOffset() {
        return XOffset;
    }

    /**
     * txt文本X轴偏移
     *
     * @param XOffset X轴偏移量
     */
    public void setXOffset(int XOffset) {
        this.XOffset = XOffset;
    }
}
