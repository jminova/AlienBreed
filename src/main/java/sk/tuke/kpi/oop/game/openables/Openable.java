package sk.tuke.kpi.oop.game.openables;

import sk.tuke.kpi.gamelib.Actor;

public interface Openable extends Actor {

    public void open();
    public void close();
    public boolean isOpen();
}
