package com.example.android.lsr;

/**
 * Created by adrian on 22/09/15.
 */
public class Controller {

    //member variables
    AnalogueController vel, touch, shot;

    public Controller(AnalogueController vel, AnalogueController touch, AnalogueController shot){
        this.vel=vel;
        this.touch=touch;
        this.shot=shot;
    }

    public Output getControllerOutput(){
        if(!shot.getOutput().equals(Vector2D.ZERO)){
            return new Output(Output.OUTPUT_TYPE_SHOT, vel.getOutput(), shot.getOutput());
        }else if(!touch.getOutput().equals(Vector2D.ZERO)){
            return new Output(Output.OUTPUT_TYPE_CONTROL, vel.getOutput(), touch.getOutput());
        }else{
            return new Output(Output.OUTPUT_TYPE_NONE, vel.getOutput(), Vector2D.ZERO);
        }
    }

    public class Output{

        // types of controller output
        public static final int OUTPUT_TYPE_NONE = 0;
        public static final int OUTPUT_TYPE_CONTROL = 1;
        public static final int OUTPUT_TYPE_SHOT = 2;

        private int type;
        private Vector2D touchOutput;
        private Vector2D velOutput;

        public Output(int type, Vector2D vel, Vector2D touch){
            this.type=type;
            this.velOutput=vel;
            this.touchOutput=touch;
        }

        public int getType(){
            return type;
        }

        public Vector2D getTouchOutput(){
            switch(type){
                case OUTPUT_TYPE_NONE:
                    return Vector2D.ZERO;
                default:
                    return touchOutput;
            }
        }

        public Vector2D getVelocityOutput(){
            return velOutput;
        }
    }
}
