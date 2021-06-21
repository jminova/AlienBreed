package sk.tuke.kpi.oop.game.controllers;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Input;
import sk.tuke.kpi.gamelib.KeyboardListener;

import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Movable;
import sk.tuke.kpi.oop.game.actions.Move;


import java.util.ArrayList;

import java.util.List;
import java.util.Map;



public class MovableController implements KeyboardListener {

    private Movable actor;
    private List<Input.Key> directions;
    private Map<Input.Key, Direction> keyDirectionMap = Map.ofEntries(
        Map.entry(Input.Key.UP, Direction.NORTH),
        Map.entry(Input.Key.DOWN,Direction.SOUTH),
        Map.entry(Input.Key.LEFT, Direction.WEST),
        Map.entry(Input.Key.RIGHT, Direction.EAST)
    );
    private Move<Movable> move;

    public MovableController(Movable actor){
        this.actor = actor;
        move =null;
        directions = new ArrayList<>();
    }

    @Override
    public void keyPressed(@NotNull Input.Key key) {
        if(keyDirectionMap.containsKey(key)){
            if(move != null && !move.isDone()){
                move.stop();
            }

            directions.add(key);
            directionWalk();
            /*move = new Move<Movable>(keyDirectionMap.get(key));
            Scene scene = actor.getScene();
            scene.scheduleAction(move,actor);*/
        }
    }

    @Override
    public void keyReleased(@NotNull Input.Key key) {
        if(keyDirectionMap.containsKey(key) && (move != null)) {

                directions.remove(key);
                //move.stop();
                directionWalk();

        }
    }

   public void directionWalk(){
        if(directions.isEmpty()){
            move.stop();
            return;
        }
        if(move!=null){
            move.stop();
        }
        Direction dir = keyDirectionMap.get(directions.get(0));

        /*for(Direction direction : directions){
            dir.combine(direction);
       }*/
        if(directions.size() > 1) {
            dir = dir.combine(keyDirectionMap.get(keyDirectionMap.get(directions.get(1))));
        }
        move = new Move<Movable>(dir,1000);
        actor.getScene().scheduleAction(move,actor);


    }
}
