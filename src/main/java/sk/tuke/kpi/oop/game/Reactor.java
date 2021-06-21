package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.actions.PerpetualReactorHeating;


import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Reactor extends AbstractActor implements Switchable, Repairable {

    private Set<EnergyConsumer> devices;
    private int temperature;
    private int damage;
    private int dam, tem;
    private Animation normalAnimation;

    private Animation off = new Animation("sprites/reactor.png");
    private Animation good1 = new Animation("sprites/reactor_on.png",80,80,0.1f,Animation.PlayMode.LOOP_PINGPONG);
    //private Animation good3 = new Animation("sprites/reactor_on.png",80,80,0.07f,Animation.PlayMode.LOOP_PINGPONG);
    private Animation hot1 = new Animation("sprites/reactor_hot.png",80,80,0.05f, Animation.PlayMode.LOOP_PINGPONG);
    //private Animation hot3 = new Animation("sprites/reactor_hot.png",80,80,0.02f, Animation.PlayMode.LOOP_PINGPONG);
    private Animation broken = new Animation("sprites/reactor_broken.png",80,80,0.1f, Animation.PlayMode.LOOP_PINGPONG);
    private Animation extinguished = new Animation("sprites/reactor_extinguished.png");

    public Reactor(){
        temperature = 0;
        damage = 0;
        normalAnimation = off;
        dam = 0;
        tem = 0;
        devices = new HashSet<>();
        setAnimation(normalAnimation);
    }

    public int getTemperature() {
        return temperature;
    }

    public int getDamage() {
        return damage;
    }

    public void increaseTemperature(int increment) {
        if(!isOn()){
            return;
        }
        int t = getTemperature();
        int d = getDamage();
        int inc = increment;
        if(inc<0){
            return;
        }
        if (d == 100) {
            turnOff();
            inc = inc * 2;
            t = t + inc;
            this.temperature = t+1;
            return;
        }
        if ((33 <= d) && (d <= 66)) {
            inc = (inc *3)/2;
        } else if (d > 66) {
            inc = inc * 2;
        }
        t = t + inc;
        if (t >= 6000) {
            d = 100;
            dam = 1;
            tem = 1;
            t=t+1;
        } else if ((t >= 2000)) {
            d = (t - 2000) / 40;
            dam = 1;
        }

        this.temperature = t;
        this.damage = d;
        updateAnimation();
    }
    public void decreaseTemperature(int decrement){
        if(!isOn()){
            return;
        }
        int t = getTemperature();
        int d = getDamage();
        int dec = decrement;
        if(t==0){
            return;
        }
        if(dec<0){
            return;
        }
        if(d==100){

            return;
        }
        if((d >= 50) && (d<100)){
            dec = dec/2;

        }
        t=t-dec;

        this.temperature = t;
        updateAnimation();
    }


    private void updateAnimation() {
        int d = getDamage();
        int t = getTemperature();
        if(!isOn()){
            if(d==100){
                normalAnimation = broken;
            }else {
                normalAnimation = off;
            }
            //setAnimation(normalAnimation);
            //return;
        }else {
            if (t <= 4000) {
                normalAnimation = good1;
            /*if((d>=32) && (d<50)){
                normalAnimation = good3;
            }*/
            }
            if (t > 4000) {
                normalAnimation = hot1;
           /* if((d>=68) && (d<100)){
                normalAnimation = hot3;
            }*/
            }
            if (d == 100) {
                normalAnimation = broken;
            }
        }
        setAnimation(normalAnimation);
    }

    @Override
    public boolean repair(){
            //if(tool == null) return false;
            //int d = getDamage();
            int t;
            int help;
            if(dam==0){return false;}
            //if(/*(tool.getRemainingUses()== 0) ||*/ (d == 100)) return false;
            else{
                int d = getDamage();
                help = d-50;
                if(d >= 50){
                    d=d-50;
                }else{
                    d=0;
                }
                this.damage = d;
                t = 2000+(40*help);
                if(this.temperature>t){
                    this.temperature = t;
                }
                updateAnimation();
                return true;
            }

    }
    public void turnOn(){
        normalAnimation = good1;
        updateAnimation();
        EnergyConsumer consumer;
        Iterator<EnergyConsumer> i = devices.iterator();
        while(i.hasNext()){
            consumer = i.next();
            if (consumer != null) {
                consumer.setPowered(isOn());
            }

        }
    }

    public void turnOff() {
        normalAnimation = off;
        updateAnimation();
        EnergyConsumer consumer;
        Iterator<EnergyConsumer> i = devices.iterator();
        while(i.hasNext()){
            consumer = i.next();
            if (consumer != null) {
                consumer.setPowered(false);
            }

        }
    }

    public boolean isOn(){
        if(normalAnimation == off) return false;
        else if(normalAnimation == broken) {
            EnergyConsumer consumer;
            Iterator<EnergyConsumer> i = devices.iterator();
            while(i.hasNext()){
                consumer = i.next();
                if (consumer != null) {
                    consumer.setPowered(false);
                }

            }
            return false;
        }
        else{return true;}
    }

   public void addDevice(EnergyConsumer energyConsumer){
        energyConsumer.setPowered(isOn());
        devices.add(energyConsumer);


    }

    public void removeDevice(EnergyConsumer energyConsumer){
        energyConsumer.setPowered(false);
        devices.remove(energyConsumer);

    }
    public boolean extinguish() {
        //if(tool == null) return false;
        //int t  = getTemperature();
        if(tem == 0){
            return false;
        }
        else{
            int t = 4000;
            this.temperature = t;
            normalAnimation = extinguished;
            setAnimation(normalAnimation);
            return true;
        }

    }

    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        scene.scheduleAction(new PerpetualReactorHeating(1), this);
    }

}
