package sk.tuke.kpi.oop.game.scenarios;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.ActionSequence;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.Wait;
import sk.tuke.kpi.gamelib.actions.When;
import sk.tuke.kpi.gamelib.framework.Scenario;
import sk.tuke.kpi.oop.game.Cooler;
import sk.tuke.kpi.oop.game.Reactor;

public class TrainingGameplay extends Scenario {

    @Override
    public void setupPlay(@NotNull Scene scene) {
        Reactor reactor = new Reactor();
        Cooler cooler = new Cooler(reactor);
        //Hammer hammer = new Hammer();
        scene.addActor(reactor,147,255);
        reactor.turnOn();
        scene.addActor(cooler,230,255);
        new ActionSequence<>(
            new Wait<>(5),
            new Invoke<>(cooler::turnOn)
        ).scheduleFor(cooler);
        new Invoke<>(() -> {
            reactor.repair();
        });
        new When<>(
            () -> reactor.getTemperature() >= 3000,
            new Invoke<>(() -> reactor.repair())
        ).scheduleFor(reactor);
    }
}
