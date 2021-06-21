package sk.tuke.kpi.oop.game.items;



import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.framework.AbstractActor;


public abstract class BreakableTool <A extends Actor> extends AbstractActor implements Usable <A>{
    private int remainingUses;

    public BreakableTool(int uses){
        remainingUses = uses;
    }

    public int getRemainingUses(){
        return this.remainingUses;
    }
    public void setRemainingUses(int uses){
        this.remainingUses = uses;
    }

    public void useWith(A Actor) {
        if(this == null) return;
        int uses = getRemainingUses();
        if (uses == 0) {
            return;
        }
        uses = uses - 1;
        setRemainingUses(uses);
        if (uses == 0) {
            getScene().removeActor(this);
            return;
        }
    }


}
