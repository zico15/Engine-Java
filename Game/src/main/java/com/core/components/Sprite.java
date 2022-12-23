package com.core.components;


import com.core.base.ImageBuffer;
import com.core.render.Graphics2D;

import java.io.File;

public class Sprite extends ComponentBase {

    private File file;
    private transient ImageBuffer buffer;
    private int width;
    private int height;

    public Sprite() {
    }

    public Sprite(File file) {
        this.file = file;
        setBuffer(new ImageBuffer(file));
    }

    public Sprite(File file, int width, int height) {
        this.file = file;
        setBuffer(new ImageBuffer(file, width, height));
    }

    @Override
    public void render(Graphics2D graphics2D) {
        if (getBuffer() != null)
            graphics2D.drawImage(getBuffer(), getParent().vector.getX(), getParent().vector.getY());
    }

    public void load(File file) {
        if (file == null || !file.exists())
            return;
        this.file = file;
        if (width > 0 && height > 0)
            setBuffer(new ImageBuffer(file, width, height));
        else
            setBuffer(new ImageBuffer(file));
    }

    public final void load_system() {
        load(getFile());
    }

    public void setRotate(double rotation) {
        if (buffer != null)
            buffer.setRotate(rotation);
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
        if (buffer != null && width > 0)
            buffer.resize(width, height);
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
        if (buffer != null && height > 0)
            buffer.resize(width, height);
    }

    public ImageBuffer getBuffer() {
        return buffer;
    }

    public void setBuffer(ImageBuffer buffer) {
        this.buffer = buffer;
        if (getBuffer() != null) {
            this.width = getBuffer().getWidth();
            this.height = getBuffer().getHeight();
        }
    }

    public File getFile() {
        return file;
    }
}
