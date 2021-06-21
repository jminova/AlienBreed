package sk.tuke.kpi.oop.game.behaviours;


import sk.tuke.kpi.gamelib.actions.ActionSequence;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.Wait;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Movable;
import sk.tuke.kpi.oop.game.actions.Move;

import java.util.Random;

public class RandomlyMoving implements Behaviour<Movable>{

    private boolean firstCall;

    public RandomlyMoving() {

    }

    @Override
    public void setUp(Movable actor) {
        if(actor == null) return;

        if (actor != null) {
            new Loop<>(
                new ActionSequence<>(
                    new Wait<>(1),
                    new Invoke<>(() -> randomMove(actor)))).scheduleFor(actor);
        }
    }

    public void randomMove(Movable movable) {
        Random random = new Random();
        int r = random.nextInt(9);
        Direction dir;
        switch (r) {
            case 0:
                dir = Direction.NORTH;
                break;
            case 1:
                dir = Direction.EAST;
                break;
            case 2:
                dir = Direction.SOUTH;
                break;
            case 3:
                dir = Direction.WEST;
                break;
            case 4:
                dir = Direction.NORTHEAST;
                break;
            case 5:
                dir = Direction.NORTHWEST;
                break;
            case 6:
                dir = Direction.SOUTHEAST;
                break;
            case 7:
                dir = Direction.SOUTHWEST;
                break;
            default:
                dir = Direction.NONE;
                break;
        }
        new Move<>(dir, 1000).scheduleFor(movable);
    }
}
