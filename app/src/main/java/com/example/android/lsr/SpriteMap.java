package com.example.android.lsr;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by adrian on 22/09/15.
 */
public class SpriteMap {

    //member variables
    private Bitmap bm;
    private int nRow;
    private int[] nFramePerRow;
    private int frameW, frameH;

    private int rowIndex;
    private int colIndex;
    private Rect src;
    private Paint p;

    private SpriteMap(Bitmap bm, int fW, int fH, int[] fperR){
        this.bm=bm;
        this.nFramePerRow=fperR;
        this.frameW=fW;
        this.frameH=fH;
        this.nRow=this.bm.getHeight()/this.frameH;

        this.rowIndex=0;
        this.colIndex=0;
        this.src = new Rect(0,0,this.frameW,this.frameH);
        p=new Paint();
        p.setAntiAlias(true);
        p.setDither(true);
    }

    public static SpriteMap makeSpriteMap(Context c, int resID, int fW, int fH, int[] FperR){
        Bitmap bm = BitmapFactory.decodeResource(c.getResources(), resID);
        SpriteMap sm = new SpriteMap(bm, fW, fH, FperR);
        bm.recycle();
        return sm;
    }

    public void drawFromIndex(Canvas c, Rect dest, int row, int col){
        src.left=frameW*(col%nFramePerRow[row]);
        src.right=src.left+frameW;
        src.top=(row%nRow)*frameH;
        src.bottom=src.top+frameH;

        c.drawBitmap(bm,src,dest,p);
    }

    public void drawNextFrame(Canvas c, Rect dest){
        drawFromIndex(c, dest,rowIndex,++colIndex%nFramePerRow[rowIndex]);
    }

    public void setIndex(int r, int c){
        rowIndex=r%nRow;
        colIndex=c%nFramePerRow[rowIndex];
    }

    public void changeState(int r){
        colIndex=0;
        rowIndex=r%nRow;
    }
}
