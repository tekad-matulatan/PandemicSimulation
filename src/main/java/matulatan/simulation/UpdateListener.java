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
public interface UpdateListener extends java.util.EventListener{
    public void updating(UpdateEvent evt);
    public void update(int[] data);
}
