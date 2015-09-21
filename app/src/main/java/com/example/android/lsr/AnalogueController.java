package com.example.android.lsr;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Onscreen touch sensitive analogue like controller.
 * Part of "2Touch:Leitrim School Rulez" where 3 are combined into the control system.
 *
 * The controller receives touch event, redrawing itself so as to show current touch position.
 * It also holds a output Vector2D as representation of the output measured from the central
 * "free" position.
 *
 * Inputs:
 *   TouchEvent         passed from Activity via onTouch(MotionEvent) method
 *
 * Outputs:
 *   Vector2D           2D vector representative of current touch position.
 *                      Vector is scaled against View size so min is 0, max is 1
 *
 * Colour of inner and outer circles, and ratio of inner and outer circle radii can be changed
 * with setInnerColor(int), setOuterColor(int), setRRatio(double) methods.
 *
 * Date        Rev     Author          Comment
 * ====        ===     ======          =======
 * 2015.9.20     0     A. Connolly     Initial version
 *
 */
public class AnalogueController extends View{

    //member variable
    private int mR, mr;
    private double mRRatio;
    private Paint mPaintInner;
    private Paint mPaintOuter;
    private Vector2D mOffset, mOutput, mPoint;

    public AnalogueController(Context context) {
        super(context);
        init();
    }

    public AnalogueController(Context context, AttributeSet attrs){
        super(context, attrs);
        init();
    }

    public AnalogueController(Context context, AttributeSet attrs, int defaultStyle){
        super(context, attrs, defaultStyle);
        init();
    }

    // initialises member variable to default vales, everything here can be changed
    private void init(){
        mPaintInner=new Paint();
        mPaintOuter=new Paint();

        mPaintInner.setColor(Color.argb(255,0,0,200));
        mPaintInner.setAntiAlias(true);
        mPaintInner.setDither(true);

        mPaintOuter.setColor(Color.argb(100, 0, 200, 0));
        mPaintOuter.setAntiAlias(true);
        mPaintOuter.setDither(true);

        mRRatio=0.32;
    }

    /* View's onDraw(Canvas) override.
     * simply draws 2 circles to the canvas representing usuable area, and current position
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(mR, mR, (int)(mR*(1-mRRatio)), mPaintOuter);
        canvas.drawCircle((int)mPoint.i ,(int)mPoint.j ,mr, mPaintInner);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        int mD=Math.min(w, h);
        mR=mD/2;
        mr=(int)(mR*mRRatio);

        mOffset=new Vector2D(mR,mR);
        mPoint=mOffset;
        mOutput=Vector2D.ZERO;
    }

    /**
     * Method that receives and processes Touch Events from activity
     *
     * Input:
     *      MotionEvent         Received from containing Activity
     *
     * Output:
     *      boolean             true is event handled, false otherwise
     *
     * This method is also responsible for updating member variable used in onDraw(), and
     * the Output Vector2D.
     */
    public boolean onTouch(MotionEvent e){
        boolean res = false;
        Vector2D pos = new Vector2D();
        if(e.getAction()==MotionEvent.ACTION_DOWN||e.getAction()==MotionEvent.ACTION_MOVE) {
            pos.setVector(e.getX(), e.getY());
            mOutput = Vector2D.subtract(pos, mOffset);
            if (inBounds(mOutput)) {
                mOutput.i /= mR;
                mOutput.j /= mR;
                mPoint=pos;
                res=true;
            } else {
                double alpha = Vector2D.getAngle(mOutput, Vector2D.EAST);
                mOutput.i = Math.cos(alpha);
                mOutput.j = Math.sin(alpha);
                mPoint=Vector2D.add(mOffset,Vector2D.scale(mOutput,mR*(1-mRRatio)));
                res=true;
            }
        }else if(e.getAction()==MotionEvent.ACTION_UP){
            mOutput=Vector2D.ZERO;
            mPoint=mOffset;
            res=true;
        }
        this.invalidate();
        return res;
    }

    /* Private function that returns whether Output vector is wihtin the bounds of the View
     *
     * Input:
     *      Vector2D            vector to be checked (assumed measured from centre of outer circle
     *
     * Output:
     *      boolean             The result of te check
     */
    private boolean inBounds(Vector2D point){
        return (point.getMagnitude()<=(mR*(1-mRRatio)));
    }

    /* Method that returns the current Output Vector2D of the controller view
     *
     * Input:
     *      None
     *
     * Output:
     *      Vector2D            The output of the controller between 0.0 and 1.0
     */
    public Vector2D getOutput(){
        return mOutput;
    }

    public void setInnerColor(int color){
        mPaintInner.setColor(color);
    }

    public void setOuterColor(int color){
        mPaintOuter.setColor(color);
    }

    public void setRRatio(double r){
        mRRatio=r;
    }
}
