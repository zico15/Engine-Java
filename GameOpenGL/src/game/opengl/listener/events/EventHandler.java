package game.opengl.listener.events;

@FunctionalInterface
public interface EventHandler<T extends Event> extends EventListener {
    void handle(T var1);
}
