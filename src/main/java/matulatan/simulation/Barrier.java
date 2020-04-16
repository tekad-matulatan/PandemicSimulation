/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matulatan.simulation;

import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author tekad_000
 */
public class Barrier implements java.awt.Shape {
    private Location location;
    private int width, height;
    private java.awt.geom.Rectangle2D barrier;
    /*
    barrierType
    0 - wall (red)
    1 - gate (blue)
    */
    public int barrierType;

    public Barrier(){
        this(0,0,0,0);
    }

    public Barrier(int x, int y, int width, int height){
        location = new Location();
        location.x = x;
        location.y = y;
        this.width = width;
        this.height = height;
        barrierType = 0;
        barrier = new java.awt.geom.Rectangle2D.Double(x, y, width, height);
    }

    @Override
    public Rectangle getBounds() {
        return barrier.getBounds();
    }

    @Override
    public Rectangle2D getBounds2D() {
        return barrier.getBounds2D();
    }

    @Override
    public boolean contains(double x, double y) {
        return barrier.contains(x, y);
    }

    @Override
    public boolean contains(Point2D p) {
        return barrier.contains(p);
    }

    @Override
    public boolean intersects(double x, double y, double w, double h) {
        return barrier.intersects(x, y, w, h);
    }

    @Override
    public boolean intersects(Rectangle2D r) {
        return barrier.intersects(r);
    }

    @Override
    public boolean contains(double x, double y, double w, double h) {
        return barrier.contains(x, y, w, h);
    }

    @Override
    public boolean contains(Rectangle2D r) {
        return barrier.contains(r);
    }

    @Override
    public PathIterator getPathIterator(AffineTransform at) {
        return barrier.getPathIterator(at);
    }

    @Override
    public PathIterator getPathIterator(AffineTransform at, double flatness) {
        return barrier.getPathIterator(at, flatness);
    }
    
}
