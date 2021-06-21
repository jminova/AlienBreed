package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.*;
import sk.tuke.kpi.oop.game.scenarios.EscapeRoom;



public class Main {
    public static void main(String[] args) {
        // nastavenie okna hry: nazov okna a jeho rozmery
        WindowSetup windowSetup = new WindowSetup("Project Ellen", 800, 600);

        // vytvorenie instancie hernej aplikacie
        // pouzijeme implementaciu rozhrania `Game` triedou `GameApplication`
        Game game = new GameApplication(windowSetup);

        // vytvorenie sceny pre hru
        // pouzijeme implementaciu rozhrania `Scene` triedou `World`
        Scene scene = new World("escape-room","maps/escape-room.tmx",new EscapeRoom.Factory());
        //Scene scene = new World("project-ellen");
        // pridanie sceny do hry
        game.addScene(scene);

        //FirstSteps scenario = new FirstSteps();
        //MissionImpossible impossible = new MissionImpossible();
        EscapeRoom escape = new EscapeRoom();
        //scene.addListener(scenario);

        //scene.addListener(impossible);
        scene.addListener(escape);
        //impossible.sceneInitialized(scene);
        //scenario.sceneInitialized(scene);
        //escape.sceneInitialized(scene);

        // spustenie hry
        game.start();
        game.getInput().onKeyPressed(Input.Key.ESCAPE, game::stop);
    }
}
