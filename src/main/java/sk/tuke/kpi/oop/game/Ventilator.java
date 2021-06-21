package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.gamelib.messages.Topic;

public class Ventilator extends AbstractActor implements Repairable {

    public Ventilator(){
        setAnimation(new Animation("sprites/ventilator.png",32,32,0.1f, Animation.PlayMode.LOOP_PINGPONG));
        getAnimation().stop();
    }
    @Override
    public boolean repair() {
        getAnimation().play();
        return true;
    }
    public static Topic<Ventilator> VENTILATOR_REPAIRED =Topic.create("Ventilator repaired", Ventilator.class);

}
