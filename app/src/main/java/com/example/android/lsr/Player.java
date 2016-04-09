package com.example.android.lsr;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Player class extended from Sprite2D in the 2T:LSR game.
 * Part of "2Touch:Leitrim School Rulez".
 *
 * This class maintains the basic information of a Player, including position and velocity, as
 * well as giving access to drawing control.
 *
 * Member methods:
 *
 *
 * Static methods:
 *
 *
 * Date        Rev     Author          Comment
 * ====        ===     ======          =======
 * 2015.9.21     0     A. Connolly     Initial version
 *
 */

public class Player extends Sprite2D{

    //constants
    public final int PLAYER_HEIGHT = 100;
    private final double MAX_SPEED = 100;
    private final int MAX_TOUCHES = 2;
    private final int[] FRAME_PER_ROW = {1,2};

    //player states
    public final int PLAYER_STATE_STATIONARY = 0;
    public final int PLAYER_STATE_RUNNING = 1;
    public final int PLAYER_STATE_KICKING = 2;
    public final int PLAYER_STATE_HEADING = 3;
    public final int PLAYER_STATE_CONTROL = 4;
    public final int PLAYER_STATE_FALLING = 5;

    //member variables
    private SpriteMap spriteMap;
    private boolean frameUpdateNeeded;
    private int playerState, currFrame;
    private Controller playerController;
    private int MAX_X, MAX_Y;
    private int noTouches;

    //TESTING ONLY
    //TODO:delete these
    Paint testPaint = new Paint();

    public Player(int width, int height, Vector2D pos){
        super(width, height);
        this.setX(pos);
        this.setDX(Vector2D.ZERO);
        this.noTouches=this.MAX_TOUCHES;
        this.currFrame=0;
        this.playerState=PLAYER_STATE_STATIONARY;
        this.currFrame=0;

        //TODO:delete
        testPaint.setColor(Color.BLUE);
        testPaint.setAntiAlias(true);
        testPaint.setDither(true);
    }

    public void setPlayerController(Controller controller){
        this.playerController=controller;
    }

    public void setSpriteMap(Context c, int resID){
        spriteMap=SpriteMap.makeSpriteMap(c,resID,getWidth(),getHeight(),FRAME_PER_ROW);
    }

    @Override
    public void draw(Canvas c) {
        //spriteMap.drawFromIndex(c, new Rect(0,0,getWidth(),getHeight()),playerState,0);
        cc.drawCircle((float)this.getX().i,(float)this.getX().j,100,testPaint);
        c.drawLine((float)this.getX().i,(float)this.getX().j,(float)this.getX().i,(float)this.getX().j-50,null);
    }

    @Override
    public void updatePhysics(long deltaT) {
        setLastX(getX());       //save last position
        setDX(Vector2D.scale(
                playerController.getControllerOutput().getVelocityOutput(),
                MAX_SPEED)
        ); //get new velocity input
        setX(Vector2D.add(getLastX(),Vector2D.scale(getDX(),deltaT))); //calc new position

        //Check validity of position, adjusting for bounds
        int x = (int)getX().i;
        int y = (int)getX().j;
        getX().i=x<0?0:x;
        getX().j=y<0?0:y;
        getX().i=(x+this.getWidth())>MAX_X?MAX_X:x;
        getX().j=(y+this.getHeight())>MAX_Y?MAX_Y:y;
    }

    public void setBounds(int X, int Y){
        MAX_X=X;
        MAX_Y=Y;
    }

    public int getSpriteType(){
        return Sprite2D.PLAYER_SPRITE;
    }

    public void decrementTouches(){
        this.noTouches--;
    }

    public void resetTouchCount(){
        this.noTouches=this.MAX_TOUCHES;
    }

    public boolean hasTouches(){
        return (this.noTouches>0);
    }

    public void setState(int playerState){
        this.playerState=playerState;
        currFrame=0;
        spriteMap.setIndex(playerState,currFrame);
    }
}
