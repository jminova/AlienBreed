package sk.tuke.kpi.oop.game.characters;



import sk.tuke.kpi.gamelib.GameApplication;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.gamelib.graphics.Overlay;
import sk.tuke.kpi.gamelib.messages.Topic;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Keeper;
import sk.tuke.kpi.oop.game.Movable;
import sk.tuke.kpi.oop.game.items.Backpack;
import sk.tuke.kpi.oop.game.weapons.Firearm;
import sk.tuke.kpi.oop.game.weapons.Gun;


public class Ripley extends AbstractActor implements Movable, Keeper, Alive, Armed {
    private int speed;

    private int ammo;
    private int info;
    private Backpack backpack;
    private Health health;
    private Firearm weapon;
    //private Animation alive = new Animation("sprites/player.png",32,32,0.1f,Animation.PlayMode.LOOP_PINGPONG);
    //private Animation dead = new Animation("sprites/player_die.png",32,32);

    public Ripley(){
        super("Ellen");
        setAnimation(new Animation("sprites/player.png",32,32,0.1f,Animation.PlayMode.LOOP_PINGPONG));
        speed = 2;
        health = new Health(10);
        ammo = 50;
        info = 0;
        backpack = new Backpack("Ripley's backpack",10);
        getAnimation().stop();
        weapon = new Gun(100,100);
    }

   /* public int getEnergy(){return this.energy;}

    public void setEnergy(int energy){
        this.energy = energy;
        if(energy == 0){
            setAnimation(new Animation("sprites/player_die.png",32,32,0.1f, Animation.PlayMode.ONCE));
            getScene().getMessageBus().publish(RIPLEY_DIED,this);
        }
    }*/

    public int getAmmo(){return this.ammo;}

    public void setAmmo(int ammo){this.ammo = ammo;}

    public int getInfo(){return this.info;}
    public void setInfo(int info){this.info = info;}

    @Override
    public int getSpeed() {
        return this.speed;
    }

    @Override
    public void startedMoving(Direction direction) {
        if (direction == null){
            return;
        }
        //setAnimation(alive);
        //float angle = direction.getAngle();
        this.getAnimation().play();
        this.getAnimation().setRotation(direction.getAngle());

    }

    @Override
    public void stoppedMoving() {
        this.getAnimation().stop();
    }

    @Override
    public Backpack getBackpack() {

        return this.backpack;
    }



    public void showRipleyState(Scene scene){
        int windowHeight = scene.getGame().getWindowSetup().getHeight();
        int yTextPos = windowHeight - GameApplication.STATUS_LINE_OFFSET;
        Overlay overlay = scene.getGame().getOverlay();
        overlay.drawText("Energy: "+ this.health.getValue(), 100, yTextPos);
        overlay.drawText("Ammo: "+this.getFirearm().getAmmo(), 230,yTextPos);
        scene.getGame().pushActorContainer(this.backpack);
        if(health.getValue() <=0) {
            health.onExhaustion(() -> {
                getScene().getMessageBus().publish(RIPLEY_DIED, this);
                setAnimation(new Animation("sprites/player_die.png", 32, 32, 0.1f, Animation.PlayMode.ONCE));
                scene.cancelActions(this);
            });
        }
    }

   /* public void setLowerEnergy(){
        energy = energy-1;

    }*/

    public static Topic<Ripley> RIPLEY_DIED =Topic.create("Ripley died", Ripley.class);

    @Override
    public Health getHealth() {

        return health;

    }


    @Override
    public Firearm getFirearm() {
        return weapon;
    }

    @Override
    public void setFirearm(Firearm firearm) {
        weapon = firearm;
    }
}
