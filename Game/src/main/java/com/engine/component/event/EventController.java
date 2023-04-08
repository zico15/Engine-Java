package com.engine.component.event;

import java.util.ArrayList;

import static com.engine.component.event.EventController.typeEvent.AVAILABLE;

public class EventController {

    public enum typeEvent{
        STOP,
        AVAILABLE,
        ACTIVE,
        COMPLETED
    }

    private static ArrayList<EventAction> events = new ArrayList();



    public static final void addE(EventAction event){
        events.add(event);
    }

    public static final void remove(EventAction event)
    {
        events.remove(event);
    }

    public static EventAction getEvent(){

        for (EventAction e : events)
        {
            if (e.status == AVAILABLE)
                return (e);
        }
        return  null;
    }


}
