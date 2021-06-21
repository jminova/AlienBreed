package sk.tuke.kpi.oop.game;


import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.actions.ActionSequence;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.Wait;
import sk.tuke.kpi.gamelib.actions.When;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.List;


public class TimeBomb extends AbstractActor {
    private Animation normalAnimation;
    private float time;

    private Animation boom = new Animation("sprites/small_explosion.png",16, 16,0.2f , Animation.PlayMode.ONCE);
    private Animation off = new Animation("sprites/bomb.png");
    private Animation on = new Animation("sprites/bomb_activated.png",16,16,0.2f, Animation.PlayMode.LOOP_PINGPONG);

    public TimeBomb(float time) {
        this.time = time;
        normalAnimation = off;
        setAnimation(normalAnimation);
    }

    private void timesUp(){
            setAnimation(boom);
            new When<>(
                ()-> getAnimation().getCurrentFrameIndex() == getAnimation().getFrameCount()-1,
                new Invoke<>(this::remove)
            ).scheduleFor(this);

            int x = this.getPosX() + 8;
            int y = this.getPosY() +8;
            int x1,y1;
            Ellipse2D.Float det = new Ellipse2D.Float(x-51,y-51,102,102);
            List<Actor> bombs = getScene().getActors();

            for(int i=0; i<bombs.size(); i++){
                if(bombs.get(i) instanceof ChainBomb){
                    ChainBomb chainBomb = (ChainBomb) bombs.get(i);
                    x1 = chainBomb.getPosX();
                    y1 = chainBomb.getPosY();
                    Rectangle2D.Float bomb = new Rectangle2D.Float(x1,y1,16,16);
                    if((!chainBomb.isActivated()) && (det.intersects(bomb))){
                        chainBomb.activate();
                    }
                }
            }

    }
    private void remove(){
        getScene().removeActor(this);
    }

    public void activate(){
        normalAnimation = on;
        setAnimation(normalAnimation);
        new ActionSequence<>(
            new Wait<>(time),
            new Invoke<>(this::timesUp)
        ).scheduleFor(this);

    }
    boolean isActivated(){
        if(normalAnimation == on){
            return true;
        }else{
            return false;
        }
    }
}
