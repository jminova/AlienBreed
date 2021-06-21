package sk.tuke.kpi.oop.game.items;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.Keeper;
import sk.tuke.kpi.oop.game.openables.LockedDoor;

import java.util.List;
import java.util.concurrent.locks.Lock;

public class AccessCard extends AbstractActor implements Collectible, Usable<LockedDoor> {

    public AccessCard(){
        setAnimation(new Animation("sprites/key.png",16,16));
    }

    @Override
    public void useWith(LockedDoor Actor) {
        if(Actor == null){
            return;
        }
        List<Actor> a = getScene().getActors();
        for(int i=0; i<a.size();i++) {
            if(a.get(i) instanceof Keeper && a.get(i).intersects(Actor)) {
                if (Actor.isLocked()) {
                    Actor.unlock();
                    Actor.setLocked(false);
                    break;
                }

            }else if(a.get(i) instanceof Keeper && !(a.get(i).intersects(Actor))) {
                    Actor.lock();
                    Actor.setLocked(true);
                    break;
                }

        }

    }


    @Override
    public Class<LockedDoor> getUsingActorClass() {
        return LockedDoor.class;
    }
}
