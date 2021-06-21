package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.gamelib.Scene;
import org.jetbrains.annotations.NotNull;

public class Cooler extends AbstractActor implements Switchable{
    private Reactor reactor;
    private Animation normalAnimation;

    private Animation on = new Animation("sprites/fan.png", 32,32,0.2f, Animation.PlayMode.LOOP_PINGPONG);
    private Animation off = new Animation("sprites/fan.png",32,32,0.0f,Animation.PlayMode.LOOP);

    public Cooler(Reactor reactor){
        this.reactor = reactor;
        normalAnimation = off;
        setAnimation(normalAnimation);
    }

    @Override
    public void turnOn() {
        normalAnimation = on;
        setAnimation(normalAnimation);
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
        }else{return false;}
    }

    private void coolReactor(){
        if(reactor == null) return;
        //if(reactor.isOn()) {
            int t = reactor.getTemperature();
            if (isOn() && t >= 0) {
                reactor.decreaseTemperature(1);
            }
        //}

    }
    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        new Loop<>(new Invoke<>(this::coolReactor)).scheduleFor(this);
    }
}
