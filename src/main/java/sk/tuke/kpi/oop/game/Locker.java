package sk.tuke.kpi.oop.game;


import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.characters.Ripley;
import sk.tuke.kpi.oop.game.items.Hammer;
import sk.tuke.kpi.oop.game.items.Usable;

public class Locker extends AbstractActor implements Usable<Ripley> {

    public Locker(){
        setAnimation(new Animation("sprites/locker.png",16,16));
    }
    @Override
    public void useWith(Ripley Actor) {
        if(Actor == null){
            return;
        }

        if(Actor.intersects(this)){
            Hammer h = new Hammer();
            Actor.getScene().addActor(h,Actor.getPosX(),Actor.getPosY());
        }
    }

    @Override
    public Class<Ripley> getUsingActorClass() {
        return Ripley.class;
    }
}
