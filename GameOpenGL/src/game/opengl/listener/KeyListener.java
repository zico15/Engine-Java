package game.opengl.listener;

import game.opengl.listener.events.Event;
import game.opengl.listener.events.EventType;

import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;

public class KeyListener extends Event {

    public static final EventType<KeyListenerPressed> KEY_PRESSED = new EventType<>(new KeyListenerPressed());
    public static final EventType<KeyListenerReleased>   KEY_RELEASED = new EventType<>(new KeyListenerReleased());


    private final static  boolean keyPressed[] = new boolean[350];

    private  int keyCode;
    public static void keyCallback(long window, int key, int scancode, int action, int mods ){
        if (action == GLFW_PRESS) {
            keyPressed[key] = true;
            KEY_PRESSED.getType().setKeyCode(key);
            KEY_PRESSED.invokeEvents();
        }
        else if (action == GLFW_RELEASE) {
            keyPressed[key] = false;
            KEY_RELEASED.getType().setKeyCode(key);
            KEY_RELEASED.invokeEvents();
        }
    }

    public boolean isKeyPressed(int keyCode){
        return keyPressed[keyCode];
    }

    public int getKeyCode(){
        return keyCode;
    }

    public void setKeyCode(int keyCode) {
        this.keyCode = keyCode;
    }

    public static class KeyListenerPressed extends KeyListener {
    }


    public static class KeyListenerReleased extends KeyListener {
    }

}
