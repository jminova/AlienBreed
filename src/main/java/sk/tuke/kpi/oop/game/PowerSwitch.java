package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.gamelib.graphics.Color;


public class PowerSwitch extends AbstractActor{
    private Switchable switchable;

    public PowerSwitch(Switchable switchable){
        this.switchable = switchable;
        if(switchable == null){
            return;
        }
        setAnimation(new Animation("sprites/switch.png"));
        if(!switchable.isOn()) this.getAnimation().setTint(Color.GRAY);
        else this.getAnimation().setTint(Color.WHITE);

    }


    public Switchable getDevice(){
        return this.switchable;
    }
    public void switchOn(){
        if(switchable == null){return;}
        if(!switchable.isOn()){
            switchable.turnOn();
            this.getAnimation().setTint(Color.WHITE);
        }
    }
    public void switchOff(){
            if(switchable == null){return;}
            switchable.turnOff();
            this.getAnimation().setTint(Color.GRAY);

    }
}
