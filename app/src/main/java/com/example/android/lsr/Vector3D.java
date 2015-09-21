package com.example.android.lsr;

/**
 * Created by adrian on 16/09/15.
 */
public class Vector3D {

    //Constants
    public final static Vector3D NORTH = new Vector3D(0, -1, 0);
    public final static Vector3D EAST = new Vector3D(1, 0, 0);
    public final static Vector3D SOUTH = new Vector3D(0, 1, 0);
    public final static Vector3D WEST = new Vector3D(-1, 0, 0);
    public final static Vector3D UP = new Vector3D(0, 0, 1);
    public final static Vector3D DOWN = new Vector3D(0, 0, -1);
    public final static Vector3D ZERO = new Vector3D(0, 0, 0);

    //members
    double i, j ,k;

    public Vector3D(double i, double j, double k){
        this.i=i;
        this.j=j;
        this.k=k;
    }

    public static Vector3D from2D(Vector2D v){
        return new Vector3D(v.i, v.j, 0);
    }

    public Vector2D to2D(){
        return new Vector2D(this.i, this.j);
    }
}
