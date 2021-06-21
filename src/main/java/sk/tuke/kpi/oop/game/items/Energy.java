package sk.tuke.kpi.oop.game.items;


import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.characters.Alive;


public class Energy extends AbstractActor implements Usable<Alive> {

    public Energy(){
        setAnimation(new Animation ("sprites/energy.png"));
    }

    @Override
    public void useWith(Alive Actor) {
        if(Actor == null) return;
        int en = Actor.getHealth().getValue();
        int max = Actor.getHealth().getMaxValue();
        if(en == max){
            return;
        }
            Actor.getHealth().restore();
            getScene().removeActor(this);


    }

    @Override
    public Class<Alive> getUsingActorClass() {
        return Alive.class;
    }
}
