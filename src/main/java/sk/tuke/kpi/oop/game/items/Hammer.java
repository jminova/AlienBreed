package sk.tuke.kpi.oop.game.items;


import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.Repairable;




public class Hammer extends BreakableTool<Repairable> implements Collectible{

    public Hammer(){
        super(1);
        setAnimation(new Animation ("sprites/hammer.png"));
    }

    @Override
    public void useWith(Repairable Actor) {
        if(Actor == null) return;
        //if(Actor.getDamage() == 0) return;
        int uses = getRemainingUses();
        if (uses == 0) {
            return;
        }
        if(!Actor.repair()){
            return;
        }else {
            //Actor.repair();
            uses = uses - 1;
            setRemainingUses(uses);
            if (uses == 0) {
                getScene().removeActor(this);
                return;
            }
        }

    }

    @Override
    public Class<Repairable> getUsingActorClass() {
            return Repairable.class;
    }
}
