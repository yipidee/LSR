package com.example.android.lsr;

import android.graphics.Canvas;

/**
 * Abstract Sprite2D class extended for Sprites in the 2T:LSR game.
 * Part of "2Touch:Leitrim School Rulez".
 *
 * This class maintains a the basic information of a sprite, including position, velocity and
 * acceleration as well as giving access to drawing control.
 *
 * Member methods:
 *
 *
 * Static methods:
 *
 *
 * Date        Rev     Author          Comment
 * ====        ===     ======          =======
 * 2015.9.20     0     A. Connolly     Initial version
 *
 */

public abstract class Sprite2D {

    private Vector2D mX, mLastX, mDX, mLastDX, mDDX;     //Vector for position and velocity
    private int mWidth, mHeight;                 //width and height of drawable

    public Sprite2D(int width, int height){
        mWidth=width;
        mHeight=height;
    }

    public int getWidth() {
        return mWidth;
    }

    public void setWidth(int Width) {
        this.mWidth = mWidth;
    }

    public int getHeight() {
        return mHeight;
    }

    public void setHeight(int Height) {
        this.mHeight = mHeight;
    }

    public abstract void draw(Canvas c);

    public abstract void updatePhysics(long deltaT);

    public void setX(Vector2D pos){
        mX=pos;
    }

    public Vector2D getX(){
        return mX;
    }

    public void setDX(Vector2D vel){
        mDX=vel;
    }

    public Vector2D getDX(){
        return mDX;
    }
    public void setLastX(Vector2D pos){
        mLastX=pos;
    }

    public Vector2D getLastX(){
        return mLastX;
    }

    public void setLastDX(Vector2D vel){
        mLastDX=vel;
    }

    public Vector2D getLastDX(){
        return mLastDX;
    }
    public double getDirection(){
        double alpha;
        alpha = Vector2D.getAngle(mDX,Vector2D.NORTH);
        if(mDX.i<0)alpha*=-1;
        return alpha;
    }
}
