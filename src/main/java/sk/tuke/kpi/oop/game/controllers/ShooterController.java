package sk.tuke.kpi.oop.game.controllers;


import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Input;
import sk.tuke.kpi.gamelib.KeyboardListener;
import sk.tuke.kpi.oop.game.actions.Fire;
import sk.tuke.kpi.oop.game.characters.Armed;

public class ShooterController  implements KeyboardListener {

    private Armed armed;
    private boolean first;

    public ShooterController(Armed armed){
        this.armed = armed;
        first = true;
    }

    @Override
    public void keyPressed(@NotNull Input.Key key){
        if(key == Input.Key.SPACE && first){
            new Fire<>().scheduleFor(armed);
            first = false;
        }

    }

    @Override
    public void keyReleased(@NotNull Input.Key key){
        if(key == Input.Key.SPACE){

            first = true;
        }
    }
}
