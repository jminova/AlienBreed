package sk.tuke.kpi.oop.game.openables;

import sk.tuke.kpi.gamelib.graphics.Animation;


public class LockedDoor extends Door {
    private boolean locked;

    public LockedDoor(String name, Orientation orientation){
        super(name,orientation);
        setAnimation(new Animation("sprites/vdoor.png",16,32,0.1f, Animation.PlayMode.ONCE));
        getAnimation().stop();
        locked = true;
    }

    public void lock(){
        setLocked(true);
        close();
        //System.out.println(1);

    }
    public void unlock(){
        setLocked(false);
        open();

        //System.out.println(2);

    }
    public boolean isLocked(){
        return locked;
    }

    public void setLocked(boolean locked){
        this.locked = locked;
    }

    @Override
    public void open(){
        if(!this.isLocked()){
            super.open();
        }
       /* getScene().getMessageBus().publish(DOOR_OPENED,this);
        MapTile m1 = getScene().getMap().getTile(this.getPosX()/16,this.getPosY()/16);
        MapTile m2 = getScene().getMap().getTile(this.getPosX()/16,(this.getPosY()+15)/16);

        if(m1.isWall()){
            m1.setType(MapTile.Type.CLEAR);
        }
        if(m2.isWall()){
            m2.setType(MapTile.Type.CLEAR);
        }
        getAnimation().setPlayMode(Animation.PlayMode.ONCE);
        getAnimation().play();*/
    }

}
