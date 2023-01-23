package game.opengl.mesh;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL15C;

import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL15C.*;


public class Mesh {

    private float vertices[];

    public Mesh(){
         vertices = new  float[]{
                            -0.5f, -0.5f, 0.0f,
                            0.5f, -0.5f, 0.0f,
                            0.0f,  0.5f, 0.0f
                                };

        IntBuffer VBO = BufferUtils.createIntBuffer(1);
        GL15C.glGenBuffers(VBO);
        glBindBuffer(GL_ARRAY_BUFFER, VBO.get(0));
        glBufferData(GL_ARRAY_BUFFER, vertices, GL_STATIC_DRAW);
    }


}
