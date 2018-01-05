package com.lt.integrate.frame.view;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by iclick on 2017/10/27.
 */

public class ProgressBar extends View {
    private int progress = 1;
    private final static int HEIGHT = 5;
    private Paint paint;
    private final static int colors[] = new int[]{Color.RED, Color.YELLOW, Color.BLUE};
    LinearGradient shader =null;
    public ProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ProgressBar(Context context) {
        super(context);
        shader = new LinearGradient(
                0, 0,
                100, HEIGHT,
                colors,
                null,
                Shader.TileMode.MIRROR);
        paint=new Paint(Paint.DITHER_FLAG);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(HEIGHT);
        paint.setAntiAlias(true);
        paint.setShader(shader);
    }

    public void setProgressColors(int... colors ){
        if(colors.length >1) {
            shader = new LinearGradient(
                    0, 0,
                    100, HEIGHT,
                    colors,
                    null,
                    Shader.TileMode.MIRROR);

        }
        paint.setShader(shader);
    }

    public void setProgress(int progress){
        this.progress = progress;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawRect(0, 0, getWidth() * progress / 100, HEIGHT, paint);
    }
}