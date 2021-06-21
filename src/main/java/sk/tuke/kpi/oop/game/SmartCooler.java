package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.framework.actions.Loop;

public class SmartCooler extends Cooler {
    private Reactor reactor;

    public SmartCooler(Reactor reactor){
        super(reactor);
        this.reactor = reactor;
    }

    private void coolReactor(){
        if(reactor == null) return;
        int t = reactor.getTemperature();

        //if(reactor.isOn()) {
            if (t > 2500) {
                turnOn();
                reactor.decreaseTemperature(1);
            } else if (t < 1500) {
                turnOff();
            }
        //}

    }

    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        new Loop<>(new Invoke<>(this::coolReactor)).scheduleFor(this);
    }


}
