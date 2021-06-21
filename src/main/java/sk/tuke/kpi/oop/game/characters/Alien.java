package sk.tuke.kpi.oop.game.characters;

import org.jetbrains.annotations.NotNull;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.ActionSequence;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.Wait;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.gamelib.graphics.Animation;

import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Movable;
import sk.tuke.kpi.oop.game.behaviours.Behaviour;



public class Alien extends AbstractActor implements Movable, Enemy, Alive {


    private int speed;
    private Health health;
    //private boolean dead;
    private Behaviour<? super Alien> behaviour;



    public Alien(int healthValue,Behaviour<? super Alien> behaviour){
        speed = 1;
        //dead = false;
        health = new Health(healthValue);
        setAnimation(new Animation("sprites/alien.png",32,32,0.1f, Animation.PlayMode.LOOP_PINGPONG));
        getAnimation().stop();
        this.behaviour = behaviour;
    }
   public Alien(){
        speed = 1;
        health = new Health(100);
        setAnimation(new Animation("sprites/alien.png",32,32,0.1f, Animation.PlayMode.LOOP_PINGPONG));
        getAnimation().stop();
    }



    @Override
    public int getSpeed() {
        return this.speed;
    }


    @Override
    public void startedMoving(Direction direction) {
        if (direction == null) {
            return;
        }
        //setAnimation(alive);
        //float angle = direction.getAngle();
        this.getAnimation().play();
        this.getAnimation().setRotation(direction.getAngle());

    }

    @Override
    public void stoppedMoving(){
        this.getAnimation().stop();
    }



    @Override
    public Health getHealth() {
        return health;
    }

    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        //List<Actor> actors = scene.getActors();
        if(this.behaviour != null){
            this.behaviour.setUp(this);
        }
       /* new Loop<>(new Invoke<>(() -> {
            if (health.getValue() <= 0) {
                health.onExhaustion(() ->
                    deadAlien()
                );
            }
        })).scheduleOn(scene);*/

        new Loop<>(new ActionSequence<>(new Wait<>(1),new Invoke<>(this::drainLife))).scheduleOn(scene);
    }



    public void drainLife(){
        for (Actor actor : getScene().getActors()) {
            if (this.intersects(actor) && actor instanceof Alive && actor != this && !(actor instanceof Enemy)) {
                ((Alive) actor).getHealth().drain(1);
            }
        }

    }


    //public static Topic<Alien> RIPLEY_DIED =Topic.create("Ripley died", Alien.class);
   /* public void deadAlien(){
        if(!isDead){
            CoinFactory cf = new CoinFactory();
            Coin coin = cf.createCoin(CoinType.NORMAL);

            getScene().addActor(coin, getPosX() + 4, getPosY() + 4);  //getWidth/2 - coin.getWidth

            getScene().removeActor(this);
        }
        isDead = true;
    }*/


}
