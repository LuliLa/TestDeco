package ru.luli.controls;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;
import ru.luli.decompiler.utils.UiUtils;

import java.util.Random;

public class MovableView extends View {
    private static final Random COLOR_LOTTERY = new Random(0);
    private static final Random POSITION_LOTTERY = new Random(UiUtils.getHight(30));

    private String text;
    private String pathToImage;
    private int initHeight;
    private int initWidth;

    private RectF rect;
    public MovableView(Context context) {
        super(context);
    }

    public MovableView(Context context, String text, String pathToImage) {
        super(context);
        this.text = text;
        this.pathToImage = pathToImage;
        this.initWidth = UiUtils.getWidth(20);
        this.initHeight = initWidth;
        rect = new RectF(UiUtils.getWidth(40), POSITION_LOTTERY.nextInt(UiUtils.getHight(100)), initHeight, initWidth);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint p = new Paint();
        p.setStyle(Paint.Style.FILL_AND_STROKE);
        p.setColor(Color.argb(1, COLOR_LOTTERY.nextInt(250), COLOR_LOTTERY.nextInt(250), COLOR_LOTTERY.nextInt(250)));

        canvas.drawRoundRect(rect, 7 , 7, p);

        p.setTextSize(15);
        p.setColor(Color.BLACK);
        canvas.drawText(text, rect.left+5, rect.bottom+5, p);
        super.onDraw(canvas);
    }

    @Override
    public void setOnTouchListener(OnTouchListener l) {

    }


    @Override
    public void setOnClickListener(OnClickListener l) {
        super.setOnClickListener(l);
    }
}
