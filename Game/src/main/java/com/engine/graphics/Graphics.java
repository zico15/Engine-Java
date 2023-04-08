package com.engine.graphics;

import com.engine.system.Windows;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

public class Graphics {

    public static GraphicsContext gc;
    public static Canvas canvas = new Canvas();

    public Graphics(GraphicsContext context)
    {
        Graphics.gc = context;
        //Graphics.gc =  canvas.getGraphicsContext2D();
    }


    public void render(){
        gc.clearRect(0, 0 , Windows.getWidth(), Windows.getHeight());
    }

    public void closePath(){
        gc.closePath();
    }

    public void drawImage(Image image, double x, double y){
        gc.drawImage(image, x, y);
    }

    public void drawImage(Image image, Rectangle cut, double dx, double dy, double dw, double dh){
        drawImage(image, cut.getX(), cut.getY(), cut.getWidth(), cut.getHeight(), dx, dy, dw, dh);
    }


    public void drawImage(Image image, double x, double y, double width, double height){
        gc.drawImage(image, x, y, width, height);
    }

    public void drawImage(Image image, double sx, double sy, double swidth, double sheight, double dx, double dy, double dwidth, double dheight){
        gc.drawImage(image, sx, sy, swidth, sheight, dx, dy, dwidth, dheight);
    }

    public void setScale(double scale){
        gc.scale(scale, scale);
    }

    public GraphicsContext getContext(){
        return  gc;
    }
  
}
