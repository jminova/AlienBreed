package sk.tuke.kpi.oop.game;



import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.framework.Player;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.gamelib.graphics.Animation;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.List;


public class Teleport extends AbstractActor {

    private Teleport destination;
    private int n;

    public Teleport(Teleport destination){
        n=0;
        this.destination = destination;
        setAnimation(new Animation("sprites/lift.png"));
    }

    public Teleport getDestination(){
        return this.destination;
    }
    public void setN(){ this.n = 0;}

    public void setDestination(Teleport destinationTeleport){
        if(destinationTeleport == this){
            return;
        }
        this.destination = destinationTeleport;
    }

    public void play(){
        List<Actor> play = getScene().getActors();
        int xt = this.getPosX();
        int yt = this.getPosY();

        Ellipse2D.Float tel = new Ellipse2D.Float(xt+5,yt+5,25,25);

        for(int i=0; i<play.size(); i++){
            if(play.get(i) instanceof Player){
                Player player = (Player) play.get(i);

                int xp = player.getPosX();
                int yp  = player.getPosY();
                Rectangle2D.Float pla = new Rectangle2D.Float(xp,yp,10,10);

                if ((tel.intersects(pla)) && (n==0)) {
                    destination.teleportPlayer(player);
                }
                if((destination!= null) && (destination.destination!=null)) {
                    xp = player.getPosX();
                    yp  = player.getPosY();
                    pla = new Rectangle2D.Float(xp,yp,10,10);
                    Ellipse2D.Float dtel = new Ellipse2D.Float(destination.getPosX()+5, destination.getPosY()+5, 25, 25);
                    if((!dtel.intersects(pla)) && (destination.n==1)){
                        destination.setN();
                    }
                }

            }
        }

    }

    public void teleportPlayer(Player player){

        /*Teleport dest = getDestination();

        if(dest == null || this == dest){
            return;
        }*/

        int xd = this.getPosX()+8;
        int yd = xd;
        //int yd = this.getPosY()+9;
        player.setPosition(xd,yd);
        this.n=1;


    }

    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        new Loop<>(new Invoke<>(this::play)).scheduleFor(this);

    }
}
