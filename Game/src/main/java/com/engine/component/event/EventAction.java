package com.engine.component.event;

import com.engine.objects.player.Player;
import com.engine.objects.rock.Rock;

import java.util.ArrayList;
import java.util.function.Consumer;

public class EventAction {


    private int x;
    private int y;

    protected int  index;

    public EventController.typeEvent status;

    private final ArrayList<Consumer<EventObject>> events = new ArrayList<>();

    public Rock rock;

    public Player player;

    public EventAction(Rock rock, int x, int y)
    {
        this.x = x;
        this.y = y;
        this.rock = rock;
        this.index = 0;
        this.status =  EventController.typeEvent.AVAILABLE;
    }

    public void add(Consumer<EventObject> event){
        events.add(event);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public final void execute(Player player)
    {
        this.status =  EventController.typeEvent.ACTIVE;
        this.player = player;
        this.run();
    }

    protected final void run()
    {
       events.get(index).accept(new EventObject(this));
    }

    public final int getSize(){
        return events.size();
    }
}
