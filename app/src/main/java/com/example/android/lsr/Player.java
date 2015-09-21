package com.example.android.lsr;

import android.graphics.Bitmap;
import android.graphics.Canvas;

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
    private final int PLAYER_HEIGHT = 100;
    private final double MAX_SPEED = 100;

    //player states
    public final int PLAYER_STATE_STATIONARY = 0;
    public final int PLAYER_STATE_RUNNING = 1;
    public final int PLAYER_STATE_KICKING = 2;
    public final int PLAYER_STATE_HEADING = 3;
    public final int PLAYER_STATE_CONTROL = 4;
    public final int PLAYER_STATE_FALLING = 5;

    //member variables
    private Bitmap spriteMap;
    private AnalogueController playerVel;
    private int MAX_X, MAX_Y;

    public Player(int width, int height, Vector2D pos){
        super(width, height);
        this.setX(pos);
        this.setDX(Vector2D.ZERO);
    }

    @Override
    public void draw(Canvas c) {

    }

    @Override
    public void updatePhysics(long deltaT) {
        setLastX(getX());       //save last position
        setDX(Vector2D.scale(playerVel.getOutput(),MAX_SPEED)); //get new velocity input
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
}
