package sk.tuke.kpi.oop.game.openables;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.gamelib.map.MapTile;
import sk.tuke.kpi.gamelib.messages.Topic;
import sk.tuke.kpi.oop.game.items.Usable;

public class Door extends AbstractActor implements Openable, Usable<Actor> {

    public enum Orientation {HORIZONTAL,VERTICAL};
    private int open;


    public Door(String name, Orientation orientation){
        super(name);
        if(orientation == Orientation.HORIZONTAL){
            setAnimation(new Animation("sprites/hdoor.png",32,16,0.1f, Animation.PlayMode.ONCE));
        }else if(orientation == Orientation.VERTICAL){
            setAnimation(new Animation("sprites/vdoor.png",16,32,0.1f, Animation.PlayMode.ONCE));
        }
        getAnimation().stop();
        open = 0;

    }


    @Override
    public void open() {

        if(isOpen()){
            return;
        }
        open = 1;
        getScene().getMessageBus().publish(DOOR_OPENED,this);
        MapTile m1;
        MapTile m2;
        if(getWidth()<getHeight()) {
            m1 = getScene().getMap().getTile(this.getPosX() / 16, this.getPosY() / 16);
            m2 = getScene().getMap().getTile(this.getPosX() / 16, (this.getPosY() + 15) / 16);
        }else{
            m1 = getScene().getMap().getTile((this.getPosX()+15) / 16, this.getPosY() / 16);
            m2 = getScene().getMap().getTile(this.getPosX() / 16, this.getPosY() / 16);
        }
        if(m1.isWall()){
            m1.setType(MapTile.Type.CLEAR);
        }
        if(m2.isWall()){
            m2.setType(MapTile.Type.CLEAR);
        }
        getAnimation().setPlayMode(Animation.PlayMode.ONCE);
        getAnimation().play();

    }

    @Override
    public void close() {
        //
        if(!isOpen()){
            return;
        }
        open = 0;
        getScene().getMessageBus().publish(DOOR_CLOSED,this);
        MapTile m1;
        MapTile m2;
        if(getWidth()<getHeight()) {
            m1 = getScene().getMap().getTile(this.getPosX() / 16, this.getPosY() / 16);
            m2 = getScene().getMap().getTile(this.getPosX() / 16, (this.getPosY() + 15) / 16);
        }else{
            m1 = getScene().getMap().getTile((this.getPosX()+15) / 16, this.getPosY() / 16);
            m2 = getScene().getMap().getTile(this.getPosX() / 16, this.getPosY() / 16);
        }

        if (!m1.isWall()) {
            m1.setType(MapTile.Type.WALL);
        }

        if(!m2.isWall()){
            m2.setType(MapTile.Type.WALL);
        }

        getAnimation().setPlayMode(Animation.PlayMode.ONCE_REVERSED);
        getAnimation().play();

    }

    @Override
    public boolean isOpen() {

        if(open == 1){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public void useWith(Actor Actor) {
        if (Actor == null) {
            return;
        }
            if (!isOpen()) {
                open();
            }else {

                close();



        }

    }

    @Override
    public Class<Actor> getUsingActorClass() {
        return Actor.class;
    }
    public static final Topic<Door> DOOR_OPENED = Topic.create("door opened", Door.class);
    public static final Topic<Door> DOOR_CLOSED = Topic.create("door closed", Door.class);

    public Orientation getOrientation(){
        if(getWidth()>getHeight()){
            return Orientation.HORIZONTAL;
        }else{
            return Orientation.VERTICAL;
        }
    }

    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        close();
    }


}
