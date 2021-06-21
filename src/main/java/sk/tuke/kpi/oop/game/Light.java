package sk.tuke.kpi.oop.game;


import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Light extends AbstractActor implements Switchable, EnergyConsumer {
    private Animation normalAnimation;
    private int e;

    private Animation on = new Animation("sprites/light_on.png");
    private Animation off = new Animation("sprites/light_off.png");

    public Light(){
        normalAnimation = off;
        e=0;
        setAnimation(normalAnimation);
    }

    public void toggle(){
        if (normalAnimation == off) {
            normalAnimation = on;
        } else {
            normalAnimation = off;
        }
        if(e==1) {
            setAnimation(normalAnimation);
        }
    }

    public void setElectricityFlow(boolean power){
        if (power){
            e = 1;
        }else{
            e=0;
        }
    }

    @Override
    public void turnOn() {
        normalAnimation = on;
        if(e==1){
            //normalAnimation = on;
            setAnimation(normalAnimation);
        }
    }

    @Override
    public void turnOff() {
        normalAnimation = off;
        setAnimation(normalAnimation);
    }

    @Override
    public boolean isOn() {
        if(normalAnimation == on){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public void setPowered(boolean on) {
        if(on){
            e=1;
            if(this.isOn()){
                setAnimation(this.on);
            }
            //setAnimation(this.on);
        }else{
            e=0;
            setAnimation(this.off);

        }
    }
    /*@Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        if(e==1) {
            new Loop<>(new Invoke<>(this::turnOn)).scheduleFor(this);
        }else{
            new Loop<>(new Invoke<>(this::turnOff)).scheduleFor(this);

        }
    }*/
}
