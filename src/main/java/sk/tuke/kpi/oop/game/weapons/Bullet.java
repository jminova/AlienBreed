package sk.tuke.kpi.oop.game.weapons;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.characters.Alive;
import sk.tuke.kpi.oop.game.characters.Ripley;

public class Bullet extends AbstractActor implements Fireable {

    private int speed;
    public Bullet(){
        setAnimation(new Animation("sprites/bullet.png",16,16));
        speed = 4;
    }

    @Override
    public void startedMoving(Direction direction) {

        this.getAnimation().setRotation(direction.getAngle());
        this.getAnimation().play();



    }

    @Override
    public void stoppedMoving(){
        this.getAnimation().stop();
    }

    @Override
    public int getSpeed() {
        return this.speed;
    }

    @Override
    public void collidedWithWall(){

        if(getScene().getMap().intersectsWithWall(this)){
            getScene().removeActor(this);
        }


    }

    public void shot(){
        for(Actor enemy : getScene()){
            if(enemy.intersects(this) && enemy instanceof Alive && !(enemy instanceof Ripley))
                ((Alive) enemy).getHealth().drain(10);
                getScene().removeActor(this);
        }
    }

    @Override
    public void addedToScene(@NotNull Scene scene){
        super.addedToScene(scene);
        new Loop<>(new Invoke<>(this::shot)).scheduleOn(scene);

    }
}
