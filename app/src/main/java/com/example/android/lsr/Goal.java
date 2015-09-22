package com.example.android.lsr;

import android.graphics.Canvas;
import android.graphics.Rect;

/**
 * Created by adrian on 22/09/15.
 */
public class Goal extends Sprite2D{

    // Goal state
    public static final int GOAL_STATE_NORMAL=0;
    public static final int GOAL_STATE_STRUCK=1;
    public static final int GOAL_STATE_BASKET=2;

    //member variables
    private final int[] framesPerState = {1,3,4};
    private SpriteMap spriteMap;
    private int currentFrame;
    private Vector2D dir;
    private int state;

    public Goal(int width, int height, Vector2D pos){
        super(width, height);
        this.setX(pos);
        if(this.getX().j<150){
            this.dir=Vector2D.SOUTH;
        }else{
            this.dir=Vector2D.NORTH;
        }
        this.state=GOAL_STATE_NORMAL;
        this.currentFrame=0;
    }

    @Override
    public void draw(Canvas c) {
        spriteMap.drawFromIndex(c,new Rect(0,0,this.getWidth(),this.getHeight()),state,currentFrame);
    }

    @Override
    public void updatePhysics(long deltaT) {
        //goals don't move. No physics
    }
    
    public void setGoalState(int goalState){
        this.state=goalState;
        currentFrame=0;
        spriteMap.setIndex(this.state,currentFrame);
    }
}
