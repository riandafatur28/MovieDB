package com.example.jsonmoviedb;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

public class DiagonalView extends View {

    private Paint paint;

    public DiagonalView(Context context) {
        super(context);
        init();
    }

    public DiagonalView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DiagonalView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(Color.RED);  // Warna background
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Path path = new Path();
        path.moveTo(0, 0);
        path.lineTo(getWidth(), 0);
        path.lineTo(0, getHeight());
        path.close();
        canvas.clipPath(path);
        canvas.drawColor(paint.getColor());
    }
}
