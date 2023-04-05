package com.engine.component.event;

import com.engine.objects.player.Player;
import com.engine.objects.rock.Rock;

public class EventObject {

    private final EventAction action;
    private int index;

    private EventController.typeEvent status;

    public EventObject(EventAction action) {
        this.action = action;
        this.index = 0;
        this.status = EventController.typeEvent.AVAILABLE;
        this.rock = action.rock;
        this.player = action.player;
    }

    public Player player;

    public Rock rock;

    public final void next(){
        index++;
        if (index >= action.getSize())
            status = EventController.typeEvent.COMPLETED;
        action.run(index);
    }
}
