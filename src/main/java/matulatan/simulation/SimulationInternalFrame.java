/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matulatan.simulation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author tekad_000
 */
public class SimulationInternalFrame extends javax.swing.JInternalFrame 
        implements java.awt.event.ActionListener{

    private SimulationMapPane simulationPane;
    private javax.swing.Timer timer;

    public SimulationInternalFrame(){
        simulationPane = new SimulationMapPane();
        init();
    }

    public SimulationInternalFrame(int width, int height, int population){
        simulationPane = new SimulationMapPane(width, height, population);
        init();
    }

    private void init(){

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setContentPane(simulationPane);
        pack();
        setVisible(true);
        /*
        Using lambda expression rather than anonymous class
        */
        timer = new javax.swing.Timer(10, (ActionEvent e) -> {
            repaint();
        });
        //timer.start();
    }

    public void setUpdateListener(UpdateListener updateListener){
        simulationPane.setUpdateListener(updateListener);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case "start":
                timer.start();
                simulationPane.start();
                break;
            case "reset":
                timer.stop();
                simulationPane.reset();
                repaint();
                break;
        }
    }
    
}
