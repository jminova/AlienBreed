package sk.tuke.kpi.oop.game.actions;


import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;
import sk.tuke.kpi.oop.game.Keeper;


public class Shift <A extends Keeper> extends AbstractAction<A> {

   /* private Keeper keeper;

    public Shift(Keeper keeper){this.keeper = keeper;}*/

    @Override
    public void execute(float deltaTime) {
        Keeper keeper = getActor();
        if(keeper == null){
            setDone(true);
            return;
        }
        setDone(false);
        try {
            (keeper).getBackpack().shift();
            setDone(true);
        } catch (IndexOutOfBoundsException empty){
            System.out.println(empty);
        } catch (IllegalStateException full){
            System.out.println(full);
        }

    }
}
