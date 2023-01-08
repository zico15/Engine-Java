package game.opengl.listener.events;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Event {

    private  final List<EventHandler> events = new ArrayList<>();


    private static HashMap<EventType, EventHandler > es = new HashMap<>();

    public static final <T extends Event> void addEventHandler(EventType<T> var1, EventHandler<? super T> e) {
        var1.add(e);
    }

    public List<EventHandler> getEvents() {
        return events;
    }
}
