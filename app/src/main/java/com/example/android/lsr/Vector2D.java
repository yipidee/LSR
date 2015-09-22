package com.example.android.lsr;

/**
 * Representation of 2D vector.
 * Part of "2Touch:Leitrim School Rulez" for use in physics simulation
 *
 * This class maintains a representation of a 2D vector, as well as a static collection of
 * useful vector manipulation tools. (As the result of a cross product will be a vector
 * perpendicular to the original 2 2D vectors, the result is a 3D vector defined in Vector3D class.
 *
 * Member methods:
 *      void setVector(double, double)      sets i and j components
 *      double getMagnitude()               returns magnitude of vector
 *      void normalize()                    normalizes the vector
 *      String toString()                   returns String representation of vector
 *      boolean equals(Vector2D)            returns true for equal vector
 *
 * Static methods:
 *      double dotProduct(Vector2D, Vector2D)       returns v1 dot v2
 *      Vector3D crossProduct(Vector2D, Vector2D)   returns v1 cross v2
 *      double getAngle(Vector2D, Vector2D)         returns angle between v1 and v2
 *      Vector2D add(Vector2D, Vector2D)            returns v1+v2
 *      Vector2D sum(Vector2D...vectors)            returns sum(vn)
 *      Vector2D scale(Vetor2D, double)             returns d*v
 *      Vector2D subtract(Vector2D, Vector2D)       returns v1-v2
 *
 * Date        Rev     Author          Comment
 * ====        ===     ======          =======
 * 2015.9.20     0     A. Connolly     Initial version
 *
 */

public class Vector2D {

    //Constants
    public final static Vector2D NORTH = new Vector2D(0, -1);
    public final static Vector2D EAST = new Vector2D(1, 0);
    public final static Vector2D SOUTH = new Vector2D(0, 1);
    public final static Vector2D WEST = new Vector2D(-1, 0);
    public final static Vector2D ZERO = new Vector2D(0, 0);

    //member variables
    public double i, j;

    public Vector2D(double i, double j) {
        this.i = i;
        this.j = j;
    }

    public Vector2D(){
        this.i=ZERO.i;
        this.j=ZERO.j;
    }

    public void setVector(double i, double j){
        this.i = i;
        this.j = j;
    }

    public double getMagnitude() {
        return Math.sqrt(this.i * this.i + this.j * this.j);
    }

    public static double dotProduct(Vector2D v1, Vector2D v2) {
        if (v1 != null && v2 != null) {
            return (v1.i * v2.i + v1.j * v2.j);
        } else {
            return 0;
        }
    }

    public static Vector3D crossProduct(Vector2D v1, Vector2D v2) {
        return new Vector3D(0, 0, v1.i * v2.j - v1.j * v2.i);
    }

    public static double getAngle(Vector2D v1, Vector2D v2) {
        double alpha = Math.acos(dotProduct(v1, v2) / (v1.getMagnitude() * v2.getMagnitude()));
        return alpha;
    }

    public static Vector2D add(Vector2D v1, Vector2D v2) {
        return new Vector2D(v1.i + v2.i, v1.j + v2.j);
    }

    public static Vector2D sum(Vector2D ... vectors){
        Vector2D v = ZERO;

        for(int a=0;a<vectors.length;a++){
            v.i+=vectors[a].i;
            v.j+=vectors[a].j;
        }

        return v;
    }

    public static Vector2D scale(Vector2D v, double x) {
        return new Vector2D(v.i*x,v.j*x);
    }

    public static Vector2D subtract(Vector2D v1, Vector2D v2) {
        return new Vector2D(v1.i - v2.i, v1.j - v2.j);
    }

    public void normalize(){
        double mag = this.getMagnitude();
        this.i/=mag;
        this.j/=mag;
    }

    public String toString(){
        String s;
        if(this.j>=0) {
            s = String.format("%1.3fi+%2.3fj", this.i, this.j);
        }else{
            s = String.format("%1.3fi%2.3fj", this.i, this.j);
        }
        return s;
    }

    public boolean equals(Vector2D v){
        return (this.i==v.i && this.j==v.j);
    }
}
