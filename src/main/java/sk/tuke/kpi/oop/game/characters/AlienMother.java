package sk.tuke.kpi.oop.game.characters;


import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.behaviours.Behaviour;

public class AlienMother extends Alien {

        private int speed;
        private Health health;


        public AlienMother(int initHealth,Behaviour<? super Alien> behaviour){
            super(initHealth,behaviour);
            //health = new Health(200);
            speed = 2;

            setAnimation(new Animation("sprites/mother.png",112,162,0.2f, Animation.PlayMode.LOOP_PINGPONG));
            getAnimation().stop();
        }
    public AlienMother(){

        health = new Health(200);
        speed = 2;

        setAnimation(new Animation("sprites/mother.png",112,162,0.2f, Animation.PlayMode.LOOP_PINGPONG));
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
}

