package sk.tuke.kpi.oop.game.weapons;

public class Gun extends Firearm {

    public Gun(int initValue, int maxValue){
        super(initValue,maxValue);
    }

    @Override
    protected Fireable createBullet() {
        return new Bullet();
    }
}
