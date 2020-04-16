/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matulatan.simulation;

/**
 *
 * @author tekad_000
 */
public interface HumanMovementAI {

    /**
     *
     * @param location
     */
    public void goTo(Location location);
    /**
     *
     * @param location
     */
    public void moveTo(Location location);
    public void move();
    public void reverse();
    public void stop();
    public void perception(Object world);
}
