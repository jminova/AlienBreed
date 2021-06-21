package sk.tuke.kpi.oop.game.scenarios;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import sk.tuke.kpi.gamelib.*;


import sk.tuke.kpi.oop.game.Locker;
import sk.tuke.kpi.oop.game.Ventilator;


import sk.tuke.kpi.oop.game.actions.Use;
import sk.tuke.kpi.oop.game.characters.Ripley;
import sk.tuke.kpi.oop.game.controllers.KeeperController;
import sk.tuke.kpi.oop.game.controllers.MovableController;
import sk.tuke.kpi.oop.game.items.AccessCard;
import sk.tuke.kpi.oop.game.items.Energy;
import sk.tuke.kpi.oop.game.items.Hammer;
import sk.tuke.kpi.oop.game.items.Wrench;
import sk.tuke.kpi.oop.game.openables.Door;
import sk.tuke.kpi.oop.game.openables.LockedDoor;

import java.util.List;



public class MissionImpossible implements SceneListener {

    public static class Factory implements ActorFactory{
        @Override
        public @Nullable Actor create(@Nullable String type, @Nullable String name) {
            if (name.equals("ellen")) {
                return new Ripley();
            } else if (name.equals("energy")) {
                return new Energy();
            } else if (name.equals("door")) {
                if(type.equals("vertical")){
                    return new LockedDoor("locked door1", Door.Orientation.VERTICAL);
                }else if(type.equals("horizontal")){
                    return new LockedDoor("locked door2", Door.Orientation.HORIZONTAL);
                }

            } else if (name.equals("access card")) {
                return new AccessCard();
            } else if (name.equals("locker")) {
                return new Locker();
            }else if(name.equals("ventilator")){
                return new Ventilator();
            }
                return null;

        }
    }

    @Override
    public void sceneInitialized(@NotNull Scene scene){

        Ripley ellen = (Ripley) scene.getFirstActorByName("Ellen");

        MovableController movableController = new MovableController(ellen);
        KeeperController keeperController = new KeeperController(ellen);
        Input in = scene.getInput();
        Hammer h = new Hammer();
        Wrench w = new Wrench();
        //ellen.getBackpack().add(h);
        scene.addActor(h,52,90);
        scene.addActor(w,32,80);
        Energy health = new Energy();

        scene.addActor(health, 130,100);

        /*Locker l = new Locker();
        scene.addActor(l,52,90);*/

        Disposable dM = in.registerListener(movableController);
        Disposable dK = in.registerListener(keeperController);
        List<Actor> actors = scene.getActors();
        new Use<>(health).scheduleFor(ellen);

        for(int i=0; i<actors.size();i++){
            if(actors.get(i) instanceof LockedDoor) {
                LockedDoor lockedDoor = (LockedDoor) actors.get(i);
                lockedDoor.close();
            }

        }
        scene.getMessageBus().subscribe(Ripley.RIPLEY_DIED,ripley -> dM.dispose());
        scene.getMessageBus().subscribe(Ripley.RIPLEY_DIED,ripley -> dK.dispose());
       /* scene.getMessageBus().subscribe(Door.DOOR_OPENED, door -> {
            ellen.setLowerEnergy();
        });*/

       Disposable disposable = scene.getMessageBus().subscribe(Door.DOOR_OPENED, door ->  ellen.getHealth().drain(1));
       scene.getMessageBus().subscribe(Ventilator.VENTILATOR_REPAIRED,ventilator -> disposable.dispose());

    }

    @Override
    public void sceneUpdating(@NotNull Scene scene){
        Ripley ellen = (Ripley) scene.getFirstActorByName("Ellen");
        ellen.showRipleyState(scene);
    }



}
