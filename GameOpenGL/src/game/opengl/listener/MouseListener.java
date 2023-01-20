package game.opengl.listener;

import game.opengl.listener.events.Event;
import game.opengl.listener.events.EventType;

import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;

public class MouseListener extends Event {


    public static final EventType<MouseListenerPressed> MOUSE_PRESSED = new EventType<>(new MouseListenerPressed());
    public static final EventType<MouseListenerReleased>   MOUSE_RELEASED = new EventType<>(new MouseListenerReleased());
    public static final EventType<MouseListenerMove>   MOUSE_MOVE = new EventType<>(new MouseListenerMove());

    private static double  scrollX, scrollY;
    private static double  xPos, yPos, lastX, lastY;
    private static boolean mouseButtonPressed[] = new boolean[3];
    private static boolean isDragging;

    private MouseListener(){
        this.scrollX = 0.0;
        this.scrollY = 0.0;
        this.xPos = 0.0;
        this.yPos = 0.0;
        this.lastX = 0.0;
        this.lastY = 0.0;
    }


    public static void mousePostCallback(long window, double xPos, double yPost){
        MouseListener.lastX = MouseListener.xPos;
        MouseListener.lastY = MouseListener.yPos;
        MouseListener.xPos = xPos;
        MouseListener.yPos = yPost;
        MOUSE_MOVE.invokeEvents();
    }

    public static void mouseButtonCallback(long window, int button, int action, int mods){
        if (button < MouseListener.mouseButtonPressed.length) {
            if (action == GLFW_PRESS) {
                MouseListener.mouseButtonPressed[button] = true;
                MOUSE_PRESSED.invokeEvents();
            }
            else if (action == GLFW_RELEASE) {
                MouseListener.mouseButtonPressed[button] = false;
                MouseListener.isDragging = false;
                MOUSE_RELEASED.invokeEvents();
            }
            MouseListener.isDragging = MouseListener.mouseButtonPressed[0] || MouseListener.mouseButtonPressed[1] || MouseListener.mouseButtonPressed[2];
        }
    }

    public static void mouseScrollCallback(long window, double xOffset, double yOffset){
        MouseListener.scrollX = xOffset;
        MouseListener.scrollY = yOffset;
    }

    public static void endFrame(){
        MouseListener.scrollX = 0;
        MouseListener.scrollY = 0;
        MouseListener.lastX = MouseListener.xPos;
        MouseListener.lastY = MouseListener.yPos;
    }

    public float getX(){
        return (float) MouseListener.xPos;
    }

    public float getY(){
        return (float) MouseListener.yPos;
    }

    public float getDX(){
        return (float) (MouseListener.lastX - MouseListener.xPos);
    }

    public float getDY(){
        return (float) (MouseListener.lastY - MouseListener.yPos);
    }

    public float getScrollX(){
        return (float) MouseListener.scrollX;
    }

    public float getScrollY(){
        return  (float) MouseListener.scrollY;
    }

    public boolean isDragging(){
        return MouseListener.isDragging;
    }

    public boolean mouseButtonDown(int button){
        if (button >= MouseListener.mouseButtonPressed.length)
            return false;
        return MouseListener.mouseButtonPressed[button];
    }

    public static class MouseListenerPressed extends MouseListener {
    }


    public static class MouseListenerReleased extends MouseListener {
    }

    public static class MouseListenerMove extends MouseListener {
    }

}
