package sk.tuke.kpi.oop.game.actions;

import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;
import sk.tuke.kpi.oop.game.Keeper;
import sk.tuke.kpi.oop.game.items.Collectible;


public class Take<A extends Keeper> extends AbstractAction<A> {

   /* private Keeper keeper;
    public Take(Keeper keeper){this.keeper = keeper;}*/

    @Override
    public void execute(float deltaTime) {
        /*setDone(false);
        try {
            if(getActor() instanceof Collectible){
                Collectible c = (Collectible) getActor();
                if (this.keeper.intersects(c)) {
                    (keeper.getBackpack()).add(c);
                    getActor().getScene().removeActor(c);
                    setDone(true);
                }
            }
        } catch(IllegalStateException ex){
            System.out.println(ex);

        }*/
        if(getActor() !=null){
            getActor().getScene().getActors().stream().filter(Collectible.class::isInstance).filter(this.getActor()::intersects)
                .map(Collectible.class::cast).findFirst().ifPresent(collectible -> {
                    try{
                        Keeper k = (Keeper) getActor();
                        k.getBackpack().add(collectible);
                        k.getScene().removeActor(collectible);
                    }catch (Exception e){
                        getActor().getScene().getOverlay().drawText(e.getMessage(),0,0).showFor(2f);
                        return;
                    }
            });
        }
        setDone(true);

    }
}
