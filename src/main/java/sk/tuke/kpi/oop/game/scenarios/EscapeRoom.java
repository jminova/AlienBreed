package sk.tuke.kpi.oop.game.scenarios;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import sk.tuke.kpi.gamelib.*;
import sk.tuke.kpi.oop.game.behaviours.Observing;
import sk.tuke.kpi.oop.game.behaviours.RandomlyMoving;
import sk.tuke.kpi.oop.game.characters.*;
import sk.tuke.kpi.oop.game.controllers.KeeperController;
import sk.tuke.kpi.oop.game.controllers.MovableController;
import sk.tuke.kpi.oop.game.controllers.ShooterController;
import sk.tuke.kpi.oop.game.items.Ammo;
import sk.tuke.kpi.oop.game.items.Energy;
import sk.tuke.kpi.oop.game.items.Hammer;
import sk.tuke.kpi.oop.game.openables.Door;


import java.util.List;

public class EscapeRoom implements SceneListener {

    public static class Factory implements ActorFactory{


        @Override
        public @Nullable Actor create(@Nullable String type, @Nullable String name) {
            if (name.equals("ellen")) {
                return new Ripley();
            } else if (name.equals("energy")) {
                return new Energy();
            }else if(name.equals("alien")) {
                if(type.equals("running")){
                    return new Alien(100,new RandomlyMoving());
                }else if (type.equals("waiting1")){
                    return new Alien(
                        100,new Observing<>(
                            Door.DOOR_OPENED,
                            door -> name.equals("front door"),
                            new RandomlyMoving()
                        ));
                }else if(type.equals("waiting2")){
                    return new Alien(100,
                        new Observing<>(
                            Door.DOOR_OPENED,
                            door -> name.equals("back door"),
                            new RandomlyMoving()
                        ));
                }
            }
            else if (name.equals("ammo")) {
                return new Ammo();
            }else if(name.equals("alien mother")){
                if(type.equals("running")) {
                    return new AlienMother(200,new RandomlyMoving());
                }else{
                    return new AlienMother(200,null);
                }
            }else if(name.equals("front door")){
                if(type.equals("vertical")){ return new Door("front door", Door.Orientation.VERTICAL);
                }else{return new Door("front door", Door.Orientation.HORIZONTAL);}

            }else if(name.equals("back door")){
                if(type.equals("vertical")){ return new Door("back door", Door.Orientation.VERTICAL);
                }else{return new Door("back door", Door.Orientation.HORIZONTAL);}
            }else if(name.equals("exit door")){
                if(type.equals("vertical")){ return new Door("exit door", Door.Orientation.VERTICAL);
                }else{return new Door("exit door", Door.Orientation.HORIZONTAL);}

            }

                return null;

        }


    }

    @Override
    public void sceneCreated(@NotNull Scene scene){


    }

    @Override
    public void sceneInitialized(@NotNull Scene scene){
        Ripley ellen = (Ripley) scene.getFirstActorByName("Ellen");
        scene.follow(ellen);
        List<Actor> actors = scene.getActors();

        MovableController movableController = new MovableController(ellen);
        KeeperController keeperController = new KeeperController(ellen);
        ShooterController shooterController = new ShooterController(ellen);
        Input in = scene.getInput();
        Disposable dM = in.registerListener(movableController);
        Disposable dK = in.registerListener(keeperController);
        Disposable dS = in.registerListener(shooterController);
        Alien alien  = new Alien(100,null);
        Hammer h = new Hammer();
        scene.addActor(h,125,65);
        for(int i=0; i<actors.size();i++){

            if(actors.get(i) instanceof Door){
                Door d = (Door) actors.get(i);
                d.close();
            }
           /* if (actors.get(i) instanceof Enemy && actors.get(i).intersects(ellen)){
                Alien enemy = (Alien) actors.get(i);
                enemy.drainLife(ellen);


            }*/
        }
        /*Bullet bullet = new Bullet();
        scene.addActor(bullet);
        new Move<Movable>(Direction.NORTHWEST).scheduleFor(bullet);*/
        //new Fire<>().scheduleFor(ellen);
      /*  Energy e = new Energy();
        scene.addActor(e,100,150);
        scene.scheduleAction(new Use<>(e),ellen);*/

    }

    @Override
    public void sceneUpdating(@NotNull Scene scene){
        Ripley ellen = (Ripley) scene.getFirstActorByName("Ellen");
        ellen.showRipleyState(scene);

        List<Actor> actors = scene.getActors();

    }
}
