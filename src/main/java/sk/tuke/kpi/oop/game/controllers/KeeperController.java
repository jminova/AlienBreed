package sk.tuke.kpi.oop.game.controllers;
import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Input;
import sk.tuke.kpi.gamelib.KeyboardListener;
import sk.tuke.kpi.oop.game.Keeper;
import sk.tuke.kpi.oop.game.actions.Drop;
import sk.tuke.kpi.oop.game.actions.Shift;
import sk.tuke.kpi.oop.game.actions.Take;
import sk.tuke.kpi.oop.game.actions.Use;
import sk.tuke.kpi.oop.game.items.Usable;


public class KeeperController implements KeyboardListener {

    private Keeper actor;

    public KeeperController(Keeper actor) {
        this.actor = actor;

    }
    @Override
    public void keyPressed(@NotNull Input.Key key) {


            if(key == Input.Key.ENTER){
                /*List <Actor> actors = actor.getScene().getActors();
                if(actors !=null) {
                    for (int i = 0; i < actors.size(); i++) {
                        if (actors.get(i) instanceof Collectible && actors.get(i).intersects(actor)) {
                            //take = new Take<>(actor);*/
                            new Take<>().scheduleFor(actor);
                            //scene.scheduleAction(take,actors.get(i));
                            //break;
                       // }
                   // }
                //}
            }
            else if(key == Input.Key.BACKSPACE){
                new Drop<>().scheduleFor(actor);
                //scene.scheduleAction(drop,actor);

            }
            else if(key == Input.Key.S){
                new Shift<>().scheduleFor(actor);

            }
            else if(key == Input.Key.U){


              actor.getScene().getActors().stream().filter(actor::intersects)
                  .filter(Usable.class::isInstance).findFirst().ifPresent(specActor->{
                      new Use<>((Usable<?>)specActor).scheduleForIntersectingWith(actor);
              });


            }else if(key == Input.Key.B){

                if(actor.getBackpack().getSize() == 0 || !(actor.getBackpack().peek() instanceof Usable)){
                    return;
                }
                Usable<?> usable = (Usable<?>) actor.getBackpack().peek();

                this.actor.getScene().getActors().stream().filter(this.actor::intersects)
                    .filter(usable.getUsingActorClass()::isInstance)
                    .findFirst().ifPresent(specActor->{
                        new Use<>(usable).scheduleForIntersectingWith(specActor);
                });
            }

    }

    @Override
    public void keyReleased(@NotNull Input.Key key){

    }

}
