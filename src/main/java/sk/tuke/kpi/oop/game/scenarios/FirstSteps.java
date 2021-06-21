package sk.tuke.kpi.oop.game.scenarios;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.*;

import sk.tuke.kpi.gamelib.graphics.Overlay;

import sk.tuke.kpi.oop.game.Keeper;
import sk.tuke.kpi.oop.game.actions.Drop;
import sk.tuke.kpi.oop.game.actions.Shift;
import sk.tuke.kpi.oop.game.actions.Take;
import sk.tuke.kpi.oop.game.actions.Use;
import sk.tuke.kpi.oop.game.characters.Ripley;
import sk.tuke.kpi.oop.game.controllers.KeeperController;
import sk.tuke.kpi.oop.game.controllers.MovableController;
import sk.tuke.kpi.oop.game.items.*;

import java.util.List;

public class FirstSteps implements SceneListener {

    @Override
    public void sceneInitialized(@NotNull Scene scene) {
        Ripley Ellen = new Ripley();
        Energy health = new Energy();
        Ammo ammo = new Ammo();
        scene.addActor(Ellen,0,0 );
        scene.addActor(health, 100,100);
        scene.addActor(ammo,10,89);

        FireExtinguisher fireExtinguisher = new FireExtinguisher();
        Hammer hammer = new Hammer();

        Wrench wrench = new Wrench();
        Backpack backpack = Ellen.getBackpack();
        backpack.add(fireExtinguisher);
        backpack.add(hammer);
        backpack.add(wrench);

        Wrench w = new Wrench();
        scene.addActor(w,-50,-50);
        MovableController movableController = new MovableController(Ellen);
        KeeperController keeperController = new KeeperController(Ellen);
        Input in = scene.getInput();
        in.registerListener(movableController);
        in.registerListener(keeperController);
        new Use<>(health).scheduleFor(Ellen);
        new Use<>(ammo).scheduleFor(Ellen);
        //new Take<>().scheduleFor(Ellen);
        //new Drop<>(Ellen).scheduleFor(Ellen);
        //new Shift<>(Ellen).scheduleFor(Ellen);
    }

    @Override
    public void sceneUpdating(@NotNull Scene scene) {
        int windowHeight = scene.getGame().getWindowSetup().getHeight();
        int yTextPos = windowHeight - GameApplication.STATUS_LINE_OFFSET;
        Overlay overlay = scene.getGame().getOverlay();
        Ripley ellen = scene.getFirstActorByType(Ripley.class);
        List<Actor> actors = scene.getActors();
        for(int i=0; i<actors.size();i++) {
            if (actors.get(i) instanceof Ammo) {
                Ammo health = (Ammo) actors.get(i);
                /*overlay.drawText("Energy: "+ ellen.getHealth(), 100, yTextPos);
                overlay.drawText("Ammo: "+ellen.getAmmo(), 230,yTextPos);
                Backpack<Collectible> b = ellen.getBackpack();
                scene.getGame().pushActorContainer(b);*/

                ellen.showRipleyState(scene);

            }

        }


    }
}
