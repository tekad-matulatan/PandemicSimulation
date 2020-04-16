/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matulatan.simulation;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author tekad_000
 */
public class Human implements java.awt.Shape, HumanMovementAI{
    /*
    location is location person
    */
    private Location location;
    /*
    infected is status of person (true) infected and (false) not infected
    */
    private boolean infected;
    /*
    * if someone is immune
    */
    private boolean immune;
    /*
    * if someone no longer exist
    */
    private boolean die;
    /*
    moveX direction of x movement
    moveY direction of y movement
    */
    private int moveX, moveY;
    /*
    size is the size of dot human
    */
    private int size;
    /*
    speed of movement
    */
    private int speed;
    /*
    shape 0 = circle dot
    shape 1 = ellipse
    shape 2 = circle with ellipse
    shape 3 = animated raster
    */
    private int shape;
    /*
    * the direction of movement
    */
    private boolean up = false;
    /*
    * the direction of movement
    */
    private boolean left = false;

    private java.awt.geom.Ellipse2D dot;
    private java.awt.image.BufferedImage[] buffImg;
    private javax.swing.Timer timer;

    public Human(){
        this(-10,-10,10);
    }

    public Human(int x, int y, int size){
        location = new Location();
        location.x = x;
        location.y = y;
        this.size = size;
        this.shape = 0;
        this.speed = 1;
        decideMove();
        immune = false;
        die = false;
        dot = new java.awt.geom.Ellipse2D.Double(0, 0, size, size);
        
        timer = new javax.swing.Timer(7000, (ActionEvent e) -> {
            infected = false;
            immune = true;
            if (Math.random() < 0.1 ){
                die = true;
            }
            timer.stop();
        });
    }

    public Human(java.awt.image.BufferedImage[] buffImg){
        this(0,0,0);
        this.buffImg = buffImg;
    }

    /**
     * @return the x
     */
    public int getX() {
        return location.x;
    }

    /**
     * @param x the x to set
     */
    public void setX(int x) {
        location.x = x;
    }

    /**
     * @return the y
     */
    public int getY() {
        return location.y;
    }

    /**
     * @param y the y to set
     */
    public void setY(int y) {
        location.y = y;
    }

    /**
     * @param location the Location to set
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * @return location the Location to return
     */
    public Location getLocation() {
        return location;
    }

    /**
     * @return the infected
     */
    public boolean isInfected() {
        return infected;
    }

    /**
     * @param infected the infected to set
     */
    public void setInfected(boolean infected) {
        this.infected = infected;
        timer.start();
    }

    /**
     * @return the immune status
     */
    public boolean isImmune(){
        return immune;
    }

    /**
     * @return the die status
     */
    public boolean isDie(){
        return die;
    }

    /**
     * @return the moveX
     */
    public int getMoveX() {
        return moveX;
    }

    /**
     * @param moveX the moveX to set
     */
    public void setMoveX(int moveX) {
        this.moveX = moveX;
    }

    /**
     * @return the moveY
     */
    public int getMoveY() {
        return moveY;
    }

    /**
     * @param moveY the moveY to set
     */
    public void setMoveY(int moveY) {
        this.moveY = moveY;
    }

    /**
     * @return the size
     */
    public int getSize() {
        return size;
    }

    /**
     * @param size the size to set
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * @return the shape
     */
    public int getShape() {
        return shape;
    }

    /**
     * @param shape the shape to set
     */
    public void setShape(int shape) {
        this.shape = shape;
    }

    /**
     * @return the buffImg
     */
    public java.awt.image.BufferedImage[] getBuffImg() {
        return buffImg;
    }

    /**
     * @param buffImg the buffImg to set
     */
    public void setBuffImg(java.awt.image.BufferedImage[] buffImg) {
        this.buffImg = buffImg;
    }

    /**
     * @param speed the speed to set
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    /**
     * @return speed the speed to get
     */
    public int getSpeed() {
        return this.speed;
    }

    @Override
    public Rectangle getBounds() {
        return new java.awt.Rectangle(location.x, location.y, size, size);
    }

    @Override
    public Rectangle2D getBounds2D() {
        return new java.awt.geom.Rectangle2D.Double(location.x, location.y, size, size);
    }

    @Override
    public boolean contains(double x, double y) {
        double centerX = location.x + (size / 2);
        double centerY = location.y + (size / 2);
        double radius = size / 2;
        return Math.sqrt( Math.pow(x - centerX, 2) + Math.pow(y - centerY, 2) ) <= radius ;
    }

    @Override
    public boolean contains(Point2D p) {
        return contains(p.getX(), p.getY());
    }

    @Override
    public boolean intersects(double x, double y, double w, double h) {
        return (location.x >= x && location.x <= x+w && location.y >= y && location.y <= y+h)
            || (location.x + size >= x && location.x + size <= x+w && location.y >= y && location.y <= y+h)    
            || (location.x >= x && location.x <= x+w && location.y + size >= y && location.y + size <= y+h)
            || (location.x + size >= x && location.x + size <= x+w && location.y + size >= y && location.y + size <= y+h)    
                ;
    }

    @Override
    public boolean intersects(Rectangle2D r) {
        return intersects(r.getX(), r.getY(), r.getWidth(), r.getHeight());
    }

    @Override
    public boolean contains(double x, double y, double w, double h) {
        return contains(x,y)||contains(x+w,y+h)||contains(x+w,y)||contains(x,y+h);
    }

    @Override
    public boolean contains(Rectangle2D r) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PathIterator getPathIterator(AffineTransform at) {
        //return gp.getPathIterator(at);
        return dot.getPathIterator(at);
    }

    @Override
    public PathIterator getPathIterator(AffineTransform at, double flatness) {
        //return gp.getPathIterator(at, flatness);
        return dot.getPathIterator(at, flatness);
    }

    @Override
    public void goTo(Location location) {
        if ( location.x - this.location.x < 0 )
            moveX = -speed;
        else if ( location.x - this.location.x > 0 )
            moveX = speed;
        else 
            moveX = 0;
        if ( location.y - this.location.y < 0 )
            moveY = -speed;
        else if ( location.y - this.location.y > 0 )
            moveY = speed;
        else
            moveY = 0;
        move();
    }

    private void decideMove(){
        left = Math.random() < 0.5;
        up = Math.random() < 0.5;        
    }

    @Override
    public void moveTo(Location location){
        
    }

    @Override
    public void move(){
        moveX = left ?  -1 * speed : speed;
        moveY = up ? -1 * speed : speed;
        location.x += moveX;
        location.y += moveY;
        double acak = Math.random();
        if (acak > 0.9)
            decideMove();
        else if (acak > 0.75){
            speed = 0;
        }
        else {
            speed = 1;
        }
        
    }

    @Override
    public void reverse(){
        moveX *= -1;
        moveY *= -1;
        location.x += moveX;
        location.y += moveY;  

    }

    @Override
    public void stop(){
        speed = 0;
    }

    @Override
    public void perception(Object world){
        
    }

}
