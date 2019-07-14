package com.slide.qq.com.fastindex.widgets;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * 快速索引栏
 *
 * @author poplar
 *         <p>
 *         onMeasure -> onLayout -> onDraw
 */
public class QuickIndexBar extends View {

    int currentIndex = -1; // 被按下字母的索引

    public interface OnLetterUpdateListener {
        // 当新的字母被选中时,调用
        void onLetterUpdate(String letter);
    }

    private OnLetterUpdateListener onLetterUpdateListener;

    public OnLetterUpdateListener getOnLetterUpdateListener() {
        return onLetterUpdateListener;
    }

    public void setOnLetterUpdateListener(
            OnLetterUpdateListener onLetterUpdateListener) {
        this.onLetterUpdateListener = onLetterUpdateListener;
    }

    private static final String[] LETTERS = new String[]{
            "A", "B", "C", "D",
            "E", "F", "G", "H",
            "I", "J", "K", "L",
            "M", "N", "O", "P",
            "Q", "R", "S", "T",
            "U", "V", "W", "X",
            "Y", "Z"};

    private Paint paint;

    private int mHeight;

    private int cellWidth; // 单元格宽度

    private float cellHeight; // 单元格的高度

    public QuickIndexBar(Context context) {
        this(context, null);
    }

    public QuickIndexBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public QuickIndexBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        paint = new Paint(Paint.ANTI_ALIAS_FLAG); // 添加抗锯齿的属性
        paint.setColor(Color.WHITE);
        paint.setTypeface(Typeface.DEFAULT_BOLD);// 文本加粗
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // 绘制字母A-Z
        for (int i = 0; i < LETTERS.length; i++) {
            String letter = LETTERS[i];

            float x = cellWidth / 2.0f - paint.measureText(letter) / 2.0f;

            Rect bounds = new Rect(); // 矩形
            paint.getTextBounds(letter, 0, letter.length(), bounds); // 获取文本所占用的矩形区域
            float y = cellHeight / 2.0f + bounds.height() / 2.0f + i * cellHeight;

            // 根据当前按下字母的索引, 设置画笔颜色
            paint.setColor(i == currentIndex ? Color.GRAY : Color.WHITE);

            // 绘制字母到画板上
            canvas.drawText(letter, x, y, paint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int index;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: // 按下
                // y / cellHeight = index
                index = (int) (event.getY() / cellHeight);
                // [0, 25]
                if (currentIndex != index) {
                    // 刚刚索引和最新的索引不同, 才更新
                    if (index >= 0 && index < LETTERS.length) {
                        // 获取字母
                        String letter = LETTERS[index];
                        System.out.println(letter);
                        if (onLetterUpdateListener != null) {
                            onLetterUpdateListener.onLetterUpdate(letter);
                        }

                        currentIndex = index;
                    }
                }

                break;
            case MotionEvent.ACTION_MOVE: // 移动
                index = (int) (event.getY() / cellHeight);
                // [0, 25]
                if (currentIndex != index) {
                    // 刚刚索引和最新的索引不同, 才更新
                    if (index >= 0 && index < LETTERS.length) {
                        // 获取字母
                        String letter = LETTERS[index];
                        System.out.println(letter);
                        if (onLetterUpdateListener != null) {
                            onLetterUpdateListener.onLetterUpdate(letter);
                        }
                        currentIndex = index;
                    }
                }

                break;
            case MotionEvent.ACTION_UP: // 抬起
                currentIndex = -1;
                break;

            default:
                break;
        }

        invalidate(); // -> onDraw();

        return true; //  可以消费事件
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        cellWidth = getMeasuredWidth();

        mHeight = getMeasuredHeight();
        // 10 / 3 = 3

        cellHeight = mHeight * 1.0f / LETTERS.length;
    }

}










