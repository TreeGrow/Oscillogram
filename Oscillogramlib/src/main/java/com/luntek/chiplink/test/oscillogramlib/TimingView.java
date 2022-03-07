package com.luntek.chiplink.test.oscillogramlib;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Typeface;
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
    public List<ItemBean> item2 = new ArrayList<>();
    //单位长度
    private float length = 10f;
    //默认行距
    private float rowledge = 50f;
    //获取画布大小
    private float width = 0;
    private float height = 0;
    //设置txt字体大小
    private int txtSize = 30;
    //txt起始偏移量
    private int XOffset = 100;
    //波形和txt的偏移量
    private int Offset = 150;
    //判断数据是否重复发送
    private byte change = 99;
    //判断是否有故障
    private boolean flag = false;
    //缓存上一个paint的颜色值
    private int lastColor = Color.BLACK;
    //是否启用缓存颜色
    private boolean flagColor = false;
    private String TAG = "TimingView";
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Canvas canvas;


    /**
     * 只生成时序图
     *
     * @param item List数据
     */
    public void setTimingList(List<ItemBean> item) {
        this.item = item;
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(txtSize);
        paint.setStrokeWidth(3);
        paint.setFakeBoldText(true); //true为粗体，false为非粗体
        paint.setAntiAlias(true);
        invalidate();
    }

    /**
     * 设置故障对照组
     *
     * @param item  原始样本数据
     * @param item2 故障的样本数据
     */
    public void setTimingList(List<ItemBean> item, List<ItemBean> item2) {
        this.item = item;
        this.item2 = item2;
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(txtSize);
        paint.setStrokeWidth(3);
        paint.setFakeBoldText(true);
        paint.setAntiAlias(true);
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
        rowledge = (height / (item.size() + 1));
        length = ((width - XOffset - Offset) / 35);

        canvas.drawColor(Color.WHITE);
        for (int a = 0; a < item2.size(); a++) {
            canvas.drawText(item2.get(a).getName(), XOffset, (a + 1) * rowledge, paint);
            float y = (a + 1) * rowledge - txtSize / 2;
            byte[] items = item.get(a).array;
            byte[] items2 = item2.get(a).array;
            int c = items.length;
            for (int i = 0; i < c; i++) {
                if (items2[i] != items[i]) {
                    paint.setColor(Color.RED);
                } else {
                    if (lastColor == Color.RED) {
                        flagColor = true;
                    }
                    paint.setColor(Color.BLACK);
                }
                lastColor = paint.getColor();
                //x坐标
                float x = XOffset + Offset + i * length;
                switch (items2[i]) {
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
                        if (items2[i] != items[i]) {
                            paint.setColor(Color.RED);
                        } else {
                            paint.setColor(Color.GRAY);
                        }
                        drawHRES(x, y);
                        break;
                }
                if (flagColor) {
                    paint.setColor(Color.RED);
                    flagColor = false;
                }
                //判断是否循环
                if (change != items2[i] && i != 0) {
                    if (change == 3) {
                        drawLine(x, y, false);
                    } else {
                        if (change == 4) {
                            paint.setColor(Color.BLACK);
                        }
                        drawLine(x, y, true);
                    }
                }
                change = items2[i];
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
    public void drawH(float x, float y) {
        canvas.drawLine(x, y, x + length, y, paint);
    }

    /**
     * 绘制低电平  0
     */
    public void drawL(float x, float y) {
        canvas.drawLine(x, y, x + length, y, paint);
    }

    /**
     * 绘制高低电平中间连线
     *
     * @param f 判断是虚线还是直线 false 为虚线
     */
    public void drawLine(float x, float y, boolean f) {
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
    public void drawDummyLine(float x, float y) {
        canvas.drawLine(x, y, x + length / 4 * 3 - 7.5f, y, paint);
        canvas.drawLine(x + length / 4 * 3 + 7.5f, y, x + length, y, paint);
    }

    /**
     * 绘制高阻 4
     */
    public void drawHRES(float x, float y) {
        float F2 = length / 2;
        canvas.drawRect(x, y - F2, x + length, y + F2, paint);
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
    public void setLength(float length) {
        this.length = length;
    }

    public float getLength() {
        return length;
    }


    public float getXOffset() {
        return XOffset;
    }

    public int getOffset() {
        return Offset;
    }

    public void setOffset(int offset) {
        Offset = offset;
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
