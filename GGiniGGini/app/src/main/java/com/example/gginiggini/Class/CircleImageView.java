package com.example.gginiggini.Class;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by 이용준 on 2016-01-13.
 * Class for Circle image View
 */
public class CircleImageView extends ImageView {

    public CircleImageView(Context context) {
        super(context);
    }

    public CircleImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CircleImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void Update(){
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {


        Path clipPath = new Path();

        final float halfWidth = canvas.getWidth() / 2;
        final float halfHeight = canvas.getHeight() / 2;
        float radius = Math.max(halfWidth, halfHeight);

        clipPath.addCircle(halfWidth, halfHeight, radius, Path.Direction.CW);

        canvas.clipPath(clipPath);

        //Log.w("onDraw", "call~~~~~~~~~~~~~~");

        super.onDraw(canvas);
    }
}

