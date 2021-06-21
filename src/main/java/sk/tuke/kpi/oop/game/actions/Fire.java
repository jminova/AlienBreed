package sk.tuke.kpi.oop.game.actions;

import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.characters.Armed;
import sk.tuke.kpi.oop.game.weapons.Bullet;


public class Fire <A extends Armed> extends AbstractAction<A> {


    @Override
    public void execute(float deltaTime) {
        //Bullet bullet = new Bullet();
        if (getActor() == null){
            setDone(true);
            return;
        }


        if(getActor() != null && getActor() instanceof Armed){
            Armed armed = (Armed) getActor();
            Direction dir = Direction.fromAngle(armed.getAnimation().getRotation());
            Bullet bullet = (Bullet) armed.getFirearm().fire();
            if(bullet == null) return;
            armed.getScene().addActor(bullet,armed.getPosX()+(armed.getWidth()/2) - bullet.getWidth()/2,
                armed.getPosY()+(armed.getHeight()/2) - bullet.getHeight()/2);
            //bullet.setPosition(armed.getPosX()+bullet.getPosY()+8, armed.getPosY()+bullet.getPosY()+8);
            new Move<>(dir,1000).setActor(bullet);
           // bullet.startedMoving(Direction.fromAngle(armed.getAnimation().getRotation()));

            setDone(true);


        }
    }
}
