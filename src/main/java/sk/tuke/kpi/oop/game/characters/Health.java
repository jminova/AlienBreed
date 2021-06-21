package sk.tuke.kpi.oop.game.characters;

import java.util.ArrayList;
import java.util.List;

public class Health {

    private int initValue;
    private int maxValue;
    private List<ExhaustionEffect> effectList;

    public Health(int initValue){
        this.initValue = initValue;
        this.maxValue = initValue;
        this.effectList = new ArrayList<>();
    }
    public Health(int initValue,int maxValue){
        this.initValue  = initValue;
        this.maxValue = maxValue;
        this.effectList = new ArrayList<>();

    }

    public int getValue(){
            return this.initValue;
    }
    public void setValue(int initValue){
        this.initValue = initValue;
    }
    public int getMaxValue(){return this.maxValue;}



    public void refill(int amount){
        int sum = initValue+amount;
        if(sum >= maxValue){
            initValue = maxValue;
        }else{
            initValue = sum;
        }

    }
    public void restore(){
            initValue = maxValue;
    }
    public void drain(int amount){
        if(initValue == 0){
            return;
        }
        int sub = initValue-amount;
        if(sub <= 0){
            initValue = 0;
           /* for(ExhaustionEffect exhaustion: effectList){
                exhaustion.apply();
            }*/
        }else{
            initValue = sub;
        }
    }
    public void exhaust(){
        drain(getValue());
    }


    @FunctionalInterface
    public interface ExhaustionEffect {
        void apply();
    }

    public void onExhaustion(ExhaustionEffect effect){
        effectList.add(effect);
        effectList.forEach(ExhaustionEffect::apply);
    }


}
