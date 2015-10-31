package ru.luli.controls;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;
import ru.luli.decompiler.utils.UiUtils;

public class SimpleChartView extends View {
    private int allParts;
    private int validParts;
    private RectF rect;
    Paint p;

    public SimpleChartView(Context context) {
        super(context);
        float width = UiUtils.getWidth(50);
        rect = new RectF(width,width,width+width/2, width+width/2);
        p = new Paint();
    }

    public void setParams(int allParts, int validParts) {
        this.allParts = allParts;
        this.validParts = validParts;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        p.setColor(Color.WHITE);
        p.setStyle(Paint.Style.FILL_AND_STROKE);
        float angle = validParts * 360/ allParts;
        canvas.drawArc(rect, 0, angle, true, p);

        p.setColor(Color.argb(0, 230, 40, 0));
        //canvas.drawArc(rect, angle, 0, true, p);
        super.onDraw(canvas);
    }
}
