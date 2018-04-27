package ch.hevs.nautischool;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DrawLine extends View {

    private Paint paint = new Paint();
    /**
     * Create 8 points into view
     */
    private PointF  pointA, pointB,
                    pointC,pointD,
                    pointE, pointF,
                    pointG, pointH;

    public DrawLine(Context context) {
        super(context);
    }

    public DrawLine(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DrawLine(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    /**
     *
     * @param canvas
     * choose lign color and width
     * set the 8 points and draw 4 line between:
     * right1-sk1
     * right2-sk2
     * right3-sk3
     * right4-sk4
     * with her position (responsive)
     */
    @Override
    public void onDraw(Canvas canvas) {
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(3);
        canvas.drawLine(pointA.x, pointA.y, pointB.x, pointB.y, paint);
        canvas.drawLine(pointC.x, pointC.y, pointD.x, pointD.y, paint);
        canvas.drawLine(pointE.x, pointE.y, pointF.x, pointF.y, paint);
        canvas.drawLine(pointG.x, pointG.y, pointH.x, pointH.y, paint);

        super.onDraw(canvas);
    }
    public void setPointA(PointF point){
        pointA = point;
    }
    public void setPointB(PointF point){
        pointB = point;
    }
    public void setPointC(PointF pointC) {
        this.pointC = pointC;
    }

    public void setPointD(PointF pointD) {
        this.pointD = pointD;
    }

    public void setPointE(PointF pointE) {
        this.pointE = pointE;
    }

    public void setPointF(PointF pointF) {
        this.pointF = pointF;
    }

    public void setPointG(PointF pointG) {
        this.pointG = pointG;
    }

    public void setPointH(PointF pointH) {
        this.pointH = pointH;
    }

    public void draw(){
        invalidate();
        requestLayout();
    }

}