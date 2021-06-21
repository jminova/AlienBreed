package sk.tuke.kpi.oop.game.items;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import sk.tuke.kpi.gamelib.ActorContainer;

import java.util.*;

public class Backpack  implements ActorContainer<Collectible>{

    private List<Collectible> list;
    private int capacity;
    private String name;

    public Backpack(String name, int capacity){
        this.capacity = capacity;
        this.name = name;
        this.list = new ArrayList<Collectible>(capacity);
    }

    @Override
    public @NotNull List<Collectible> getContent() {

        return List.copyOf(list);
    }

    @Override
    public int getCapacity() { return this.capacity; }

    @Override
    public int getSize() { return this.list.size(); }

    @Override
    public @NotNull String getName() { return this.name; }

    @Override
    public void add(@NotNull Collectible actor) {
        if(actor == null){
            return;
        }
        if(list.size()<this.capacity){
            list.add(actor);
        }else{
            throw new IllegalStateException(this.name+" is full");
        }
    }

    @Override
    public void remove(@NotNull Collectible actor) {
            if(actor == null){
                return;
            }
            if(!(list.isEmpty())) {
                this.list.remove(actor);
            }else if(list.isEmpty()){
                return;
            }
            else{
                throw new IndexOutOfBoundsException(this.name+" is empty");
            }


    }

    @Nullable
    @Override
    public Collectible peek() {
        if (!list.isEmpty()) {
            return list.get(list.size() - 1);
        }else{
            //throw new IndexOutOfBoundsException(this.name+ " is empty");
            return null;
        }

    }

    @Override
    public void shift() {
        if(!list.isEmpty() && list.size() <  capacity) {
            Collections.rotate(this.list, 1);
        }/*else if(list.isEmpty()){
            throw new IndexOutOfBoundsException(this.name+ " is empty");
        }else if(list.size() == this.capacity){
            throw new IllegalStateException(this.name+ "is full");
        }*/
    }

    @NotNull
    @Override
    public Iterator<Collectible> iterator() {
        return list.iterator();
    }
}
