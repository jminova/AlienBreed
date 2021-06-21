package sk.tuke.kpi.oop.game;


import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.framework.Player;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Helicopter extends AbstractActor {


    public Helicopter(){

        setAnimation(new Animation("sprites/heli.png",64,64,0.2f, Animation.PlayMode.LOOP_PINGPONG));
    }

    public void attacking(){
        Player player = (Player) getScene().getLastActorByName("Player");
        int e = player.getEnergy();
        int x = player.getPosX();
        int y = player.getPosY();
        int x1 = this.getPosX();
        int y1 = this.getPosY();
        if(intersects(player)){
            e = e-1;
        }else{
            if(x1 > x){
                x1 = x1-1;
            }else if(x1 < x){
                x1 = x1 + 1;
            }
            if(y1 > y){
                y1 = y1 - 1;
            }else if(y1 < y){
                y1 = y1 + 1;
            }
            this.setPosition(x1,y1);
        }
        player.setEnergy(e);
    }
    public void searchAndDestroy(){
        new Loop<>(new Invoke<>(this::attacking)).scheduleFor(this);
    }


}
