package game.opengl.listener.events;

import java.io.Serializable;

public final class EventType<T extends Event> implements Serializable {

    private final T type;
    public EventType(T type){
        this.type = type;
    }


    protected void  add(EventHandler eventHandler){
        getType().getEvents().add(eventHandler);
    }

    public void invokeEvents(){
        getType().getEvents().forEach(e -> e.handle(getType()));
    }

    public T getType() {
        return type;
    }
}
