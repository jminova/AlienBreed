package sk.tuke.kpi.oop.game.actions;

import org.jetbrains.annotations.Nullable;
import sk.tuke.kpi.gamelib.actions.Action;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Movable;


public class Move <A extends Movable> implements Action <A> {

    private float duration;
    private Direction direction;
    private A actor;
    private boolean done;
    private boolean first;


    public Move(Direction direction, float duration) {
        this.duration = duration;
        this.direction = direction;
        done = false;
        first = false;

    }
    public Move(Direction direction) {
        this.duration = 0;
        this.direction = direction;
        done = false;
        first = false;
    }

    @Override
    public @Nullable A getActor() {
        return actor;
    }

    @Override
    public void setActor(@Nullable A actor) {
            this.actor = actor;
    }

    @Override
    public boolean isDone() {

        return done;
    }

    @Override
    public void execute(float deltaTime) {
        if(actor == null){
            return;
        }

        if(!isDone()) {
            done = false;
            if(!first){
                first = true;
                actor.startedMoving(direction);
            }

            int x = direction.getDx()*actor.getSpeed();
            int y = direction.getDy()*actor.getSpeed();
            int ax = actor.getPosX();
            int ay = actor.getPosY();

            actor.setPosition(ax+x,ay+y);
            //actor.getAnimation().setRotation(direction.getAngle());

            /*if (x == 0 && y == 1) {
                actor.setPosition(ax, ay + s);

            }
            if (x == 1 && y == 0) {
                actor.setPosition(ax + s, ay);

            }
            if (x == 0 && y == -1) {
                actor.setPosition(ax, ay - s);

            }
            if (x == -1 && y == 0) {
                actor.setPosition(ax - s, ay);
            }

            if (x == -1 && y == 1) {
                actor.setPosition(ax-s, ay + s);
            }
            if (x == 1 && y == 1) {
                actor.setPosition(ax + s, ay+s);
            }
            if (x == -1 && y == -1) {
                actor.setPosition(ax-s, ay - s);
            }
            if (x == 1 && y == -1) {
                actor.setPosition(ax + s, ay-s);
            }*/

            if(actor.getScene().getMap().intersectsWithWall(actor)){
                actor.stoppedMoving();
                actor.collidedWithWall();

            }
            duration = duration-deltaTime;
            if (duration <= 1e-10) {
                stop();
            }

        }
    }

    @Override
    public void reset() {
            this.done = false;
    }
    public void stop(){
        this.done = true;
        if(actor == null) return;
        actor.stoppedMoving();

    }



    public void collidedWithWall(){
        actor.setPosition(actor.getPosX()-(direction.getDx()*actor.getSpeed()),actor.getPosY()
            -(direction.getDy()*actor.getSpeed()));
        //actor.stoppedMoving();
    }

}
