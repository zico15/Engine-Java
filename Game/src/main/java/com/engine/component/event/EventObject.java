package com.engine.component.event;

import com.engine.objects.player.Player;
import com.engine.objects.rock.Rock;

public class EventObject {

    private final EventAction action;

    private EventController.typeEvent status;


    public EventObject(EventAction action) {
        this.action = action;
        this.status = EventController.typeEvent.AVAILABLE;
        this.rock = action.rock;
        this.player = action.player;
    }

    public Player player;

    public Rock rock;

    public final void completed(){
        status = EventController.typeEvent.COMPLETED;
        action.status =  EventController.typeEvent.COMPLETED;
        EventController.remove(action);
        System.out.println("EventAction: COMPLETED -> next event -> " + EventController.getEvent());
    }

    public final void next(){
        if (action.status != EventController.typeEvent.COMPLETED){
            action.index++;
            if (action.index >= action.getSize())
                completed();
            else
                action.run();
        }
    }
}
