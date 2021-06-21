package sk.tuke.kpi.oop.game.items;

import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.Reactor;

public class FireExtinguisher extends BreakableTool<Reactor> implements Collectible{


    public FireExtinguisher(){
        super(1);
        setAnimation(new Animation ("sprites/extinguisher.png"));
    }

    @Override
    public void useWith(Reactor Actor) {
        if(Actor == null) return;
        //if(Actor.getDamage() == 0) return;
        int uses = getRemainingUses();
        if (uses == 0) {
            return;
        }
        if(!Actor.extinguish()){
            return;
        }else {
            uses = uses - 1;
            setRemainingUses(uses);
            if (uses == 0) {
                getScene().removeActor(this);
                return;
            }
        }
    }

    @Override
    public Class<Reactor> getUsingActorClass() {
        return Reactor.class;
    }
}
