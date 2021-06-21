package sk.tuke.kpi.oop.game.weapons;


public abstract class Firearm {

    private int initAmmo;
    private int maxAmmo;
    public Firearm(int InitValue){
        initAmmo = InitValue;
        maxAmmo = InitValue;
    }
    public Firearm(int InitValue, int MaxValue){
        initAmmo = InitValue;
        maxAmmo = MaxValue;
    }

    public int getAmmo(){
        return this.initAmmo;
    }
    public void reload(int newAmmo){
        if(initAmmo == maxAmmo) return;
        int New = this.initAmmo+newAmmo;
        if(New >= maxAmmo){
            initAmmo = maxAmmo;
        }
        initAmmo = New;

    }
    public Fireable fire(){
        if(initAmmo <= 0){
            return null;
        }
        /*if(initAmmo-1 <= 0){
            initAmmo = 0;
        }else{
            initAmmo--;
        }*/
        initAmmo--;
        return createBullet();

    }

    protected abstract Fireable createBullet();
}
