package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Computer extends AbstractActor implements EnergyConsumer{
    private Animation normalAnimation;
    private int e;
    private Animation on = new Animation("sprites/computer.png",80,48, 0.2f, Animation.PlayMode.LOOP_PINGPONG);;
    private Animation off = new Animation("sprites/computer.png",80,48, 0.0f, Animation.PlayMode.ONCE);

    public Computer(){
        e=0;
        normalAnimation = off;
        setAnimation(normalAnimation);
    }

    public int add (int a, int b){
        if(e==0){
            return 0;
        }else {
            return a + b;
        }
    }
    public float add (float a, float b){
        if(e==0) {
            return 0;
        }else{
            return a + b;
        }
    }
    public int sub(int a, int b){
        if(e==0){
            return 0;
        }
        else{
            return a - b;
        }
    }
    public float sub(float a, float b){
        if(e==0){
            return 0;
        }
        else {
            return a - b;
        }
    }

    @Override
    public void setPowered(boolean energy) {
            if(energy){
                e=1;
                normalAnimation = on;
            }else{
                e=0;
                normalAnimation = off;
            }
            setAnimation(normalAnimation);
    }
}
