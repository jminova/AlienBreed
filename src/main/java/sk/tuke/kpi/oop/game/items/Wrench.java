package sk.tuke.kpi.oop.game.items;


import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.DefectiveLight;
import sk.tuke.kpi.oop.game.Reactor;

public class Wrench extends BreakableTool<DefectiveLight> implements Collectible {


    public Wrench(){
        super(2);

        setAnimation(new Animation ("sprites/wrench.png"));
    }

    @Override
    public void useWith(DefectiveLight Actor) {
        if(Actor == null) return;
        //if(Actor.getR() == 0) return;
        int uses = getRemainingUses();
        if (uses == 0) {
            return;
        }
        if(!Actor.repair()){
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
    public Class<DefectiveLight> getUsingActorClass() {
            return DefectiveLight.class;
    }
}
