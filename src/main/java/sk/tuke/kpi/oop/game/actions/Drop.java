package sk.tuke.kpi.oop.game.actions;


import sk.tuke.kpi.gamelib.GameApplication;
import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;
import sk.tuke.kpi.oop.game.Keeper;

import sk.tuke.kpi.oop.game.items.Collectible;



public class Drop<A extends Keeper> extends AbstractAction<A> {

    /*private Keeper keeper;
    public Drop(Keeper keeper){this.keeper = keeper;}*/

    @Override
    public void execute(float deltaTime) {


        Keeper keeper = getActor();
        if(keeper == null){
            setDone(true);
            return;
        }
        setDone(false);
            try {

                Collectible c = (keeper).getBackpack().peek();
                if(c==null){
                    return;
                }
                (keeper).getBackpack().remove(c);
                keeper.getScene().addActor(c,
                    keeper.getPosX()+keeper.getWidth()/2-c.getWidth()/2,
                    keeper.getPosY()+keeper.getHeight()/2-c.getHeight()/2);

            } catch (IndexOutOfBoundsException e){
                getActor().getScene().getGame().getOverlay().drawText(
                    "Your backpack is empty" ,
                    45,
                    getActor().getScene().getGame().getWindowSetup().getHeight() - GameApplication.STATUS_LINE_OFFSET * 2
                ).showFor(2);


            }
            setDone(true);

    }
}
