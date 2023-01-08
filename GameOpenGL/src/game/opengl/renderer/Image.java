package game.opengl.renderer;


import org.lwjgl.BufferUtils;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.Raster;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;

import static org.lwjgl.glfw.GLFW.glfwGetWindowSize;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11C.glTexParameteri;
import static org.lwjgl.stb.STBImage.stbi_image_free;
import static org.lwjgl.stb.STBImage.stbi_load;

public class Image {

    private final String filePath;
    private int id;

    private int width;
    private int height;



    public Image(String filePath){

        this.filePath = filePath;
        // Generate texture on GPU
        id = glGenTextures();
        glBindTexture(GL_TEXTURE_2D, getId());
        // Set texture parametersq
        // Repeate image in both directions
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);
        // When stretching the image, pixelate
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        // When stretching an image, pixelate
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

        IntBuffer width = BufferUtils.createIntBuffer(1);
        IntBuffer height = BufferUtils.createIntBuffer(1);
        IntBuffer channels = BufferUtils.createIntBuffer(1);
        ByteBuffer image = stbi_load(filePath, width, height, channels, 0);
        if (image != null)
            glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width.get(0), height.get(0), 0, GL_RGBA, GL_UNSIGNED_BYTE, image);
        else
            assert false: "Error: (Texture) Could not load image' " + filePath + "'";
        this.width = width.get(0);
        this.height = height.get(0);
        stbi_image_free(image);
    }

    public static ByteBuffer getByteBuffer(BufferedImage bufferedImage) {
        Raster raster = bufferedImage.getRaster();
        DataBufferByte dataBufferByte = (DataBufferByte) raster.getDataBuffer();
        byte[] data = dataBufferByte.getData();

        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(data.length);
        byteBuffer.order(ByteOrder.nativeOrder());
        byteBuffer.put(data, 0, data.length);
        byteBuffer.flip();//from   w  ww .  j  ava 2s. co m
        return byteBuffer;
    }
    public void load(BufferedImage bufferedImage){

            width = bufferedImage.getWidth();
            height = bufferedImage.getHeight();
            ByteBuffer pixels = getByteBuffer(bufferedImage);
            glBindTexture(GL_TEXTURE_2D, id);
            // Set texture parameters
            // Repeate image in both directions
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);
            // When stretching the image, pixelate
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
            // When stretching an image, pixelate
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
            glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, getWidth(), getHeight(), 0, GL_RGBA,  GL_UNSIGNED_BYTE, pixels);
            glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, getWidth(), getHeight(), 0, GL_RGBA, GL_UNSIGNED_BYTE, pixels);
            stbi_image_free(pixels);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void bind(){
        glBindTexture(GL_TEXTURE_2D, getId());
    }

    public void unbind(){
        glBindTexture(GL_TEXTURE_2D, 0);
    }



    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Image{" +
                "filePath='" + filePath + '\'' +
                ", id=" + id +
                ", width=" + width +
                ", height=" + height +
                '}';
    }
}
