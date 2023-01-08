package game.opengl.renderer;


import game.core.components.Sprite;
import game.core.objects.Scene;
import game.opengl.listener.KeyListener;
import game.opengl.listener.MouseListener;

import game.opengl.renderer.Graphics2D;
import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.*;

import java.nio.*;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;

public class Window {

    // The window handle
    public static long window;

    public static float width;
    public static float  height;

    private final Graphics2D graphics2D;

    private Scene scene;
    private String title;

    public Window(String title){
        graphics2D = new Graphics2D();
        this.title = title;
        init(title);
    }
    public void run() {
        loop();
        // Free the window callbacks and destroy the window
        glfwFreeCallbacks(window);
        glfwDestroyWindow(window);
        // Terminate GLFW and free the error callback
        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }

    private void init(String title) {
        // Setup an error callback. The default implementation
        // will print the error message in System.err.
        GLFWErrorCallback.createPrint(System.err).set();

        // Initialize GLFW. Most GLFW functions will not work before doing this.
        if ( !glfwInit() )
            throw new IllegalStateException("Unable to initialize GLFW");
        // Configure GLFW
        glfwDefaultWindowHints(); // optional, the current window hints are already the default
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE); // the window will stay hidden after creation
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE); // the window will be resizable
       // glfwWindowHint(GLFW_MAXIMIZED, GLFW_TRUE);
        // Create the window
        window = glfwCreateWindow(300, 300, title, NULL, NULL);

        if ( window == NULL )
            throw new RuntimeException("Failed to create the GLFW window");

        // Setup a key callback. It will be called every time a key is pressed, repeated or released.
        /*glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
            if ( key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE )
                glfwSetWindowShouldClose(window, true); // We will detect this in the rendering loop
        });*/

        // Callbacks
        glfwSetCursorPosCallback(window, MouseListener::mousePostCallback);
        glfwSetMouseButtonCallback(window, MouseListener::mouseButtonCallback);
        glfwSetScrollCallback(window, MouseListener::mouseScrollCallback);
        glfwSetKeyCallback(window, KeyListener::keyCallback);

        // Get the thread stack and push a new frame
        try ( MemoryStack stack = stackPush() ) {
            IntBuffer pWidth = stack.mallocInt(1); // int*
            IntBuffer pHeight = stack.mallocInt(1); // int*

            // Get the window size passed to glfwCreateWindow
            glfwGetWindowSize(window, pWidth, pHeight);

            // Get the resolution of the primary monitor
            GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());


            // Center the window
            glfwSetWindowPos(
                    window,
                    (vidmode.width() - pWidth.get(0)) / 2,
                    (vidmode.height() - pHeight.get(0)) / 2
            );
        } // the stack frame is popped automatically

        // Make the OpenGL context current
        glfwMakeContextCurrent(window);
        // Enable v-sync
        glfwSwapInterval(1);

       glfwSetWindowSizeCallback(window, new GLFWWindowSizeCallback() {
            @Override
            public void invoke(long window, int argWidth, int argHeight) {
                glViewport(0, 0, argWidth,argHeight);
                updateSize();
            }
        });
        updateSize();
        // Make the window visible
        glfwShowWindow(window);
        // This line is critical for LWJGL's interoperation with GLFW's
        // OpenGL context, or any context that is managed externally.
        // LWJGL detects the context that is current in the current thread,
        // creates the GLCapabilities instance and makes the OpenGL
        // bindings available for use.
        GL.createCapabilities();
        glEnable(GL_TEXTURE_2D);
    }

    private static void updateSize(){
        IntBuffer width = BufferUtils.createIntBuffer(1);
        IntBuffer height = BufferUtils.createIntBuffer(1);
        glfwGetWindowSize(Window.window, width, height);
        Window.width = width.get(0);
        Window.height = height.get(0);
    }
    private void loop() {
        // Set the clear color
        glClearColor(1.0f, 0.0f, 0.0f, 0.0f);
        // Run the rendering loop until the user has attempted to close
        // the window or has pressed the ESCAPE key.
       Image image = new Image("assets/tes.png");
        while ( !glfwWindowShouldClose(window) ) {

            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer
            //render
            if (scene != null) {
                scene.update();
                scene.render(graphics2D);

              //graphics2D.draw(image, 1, 0, 100, 100);
            }
            glfwSwapBuffers(window); // swap the color buffers
            // Poll for window events. The key callback above will only be
            // invoked during this call.
            glfwPollEvents();
        }
    }


    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }
}