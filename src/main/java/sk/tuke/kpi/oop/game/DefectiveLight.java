package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Disposable;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.ActionSequence;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.Wait;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import java.util.Random;

public class DefectiveLight extends Light implements Repairable {
    private Disposable disposable;
    private int r;

    public DefectiveLight(){}

    public int getR(){return this.r;}
    private void defect(){
        r=0;
        Random rand = new Random();
        int n;

        n = rand.nextInt(20);
        if(n==1){
            toggle();
        }
    }

    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        disposable = new Loop<>(new Invoke<>(this::defect)).scheduleFor(this);
    }
    @Override
    public boolean repair() {
        /*if(tool == null || tool.getRemainingUses() == 0){
            return false;
        }*/
        if(r==1){
            return false;
        }else {
            disposable.dispose();
            turnOn();
            r = 1;
            new ActionSequence<>(
                new Wait<>(10),
                new Loop<>(new Invoke<>(this::defect))
            ).scheduleFor(this);
            return true;
        }
    }



}
