/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenprojectpandemicsimulation;

import java.awt.event.WindowEvent;
import matulatan.simulation.AnalyticInternalFrame;
import matulatan.simulation.SimulationInternalFrame;

/**
 *
 * @author tekad_000
 */
public class Main extends javax.swing.JFrame {
    private javax.swing.JDesktopPane desktopPane;

    public Main(){
        init();
    }
    
    private void init(){
        setExtendedState( javax.swing.JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Pandemic Simulation");
        setIconImage(new javax.swing.ImageIcon(getClass().getClassLoader()
                .getResource("ico/app.png")).getImage());
        addWindowListener( new java.awt.event.WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
                exitApp();
            }

            @Override
            public void windowClosed(WindowEvent e) {
            }

            @Override
            public void windowIconified(WindowEvent e) {
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
            }

            @Override
            public void windowActivated(WindowEvent e) {
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
            }
        });
        //java.awt.image.BufferedImage bi = null;
        //javax.swing.JDesktopPane desktopPane = null;
        try {
            java.awt.image.BufferedImage bi = javax.imageio.ImageIO.read(
                getClass().
                getClassLoader().
                getResource("img/background.png"));
            desktopPane = new javax.swing.JDesktopPane(){
                @Override
                public void paintComponent(java.awt.Graphics g){
                    super.paintComponent(g);
                    g.drawImage(bi, 0, 0, getWidth(), getHeight(), this);
                }
            };
        }
        catch (java.io.IOException ex){
            assert(true);
        }

        AnalyticInternalFrame analyticInternalFrame = new AnalyticInternalFrame();
        analyticInternalFrame.setLocation(1000, 20);
        desktopPane.setLayer(analyticInternalFrame, javax.swing.JDesktopPane.MODAL_LAYER);
        desktopPane.add(analyticInternalFrame);

        SimulationInternalFrame simulationIF = new SimulationInternalFrame(1000,400,200);
        simulationIF.setLocation(50, 50);
        simulationIF.setUpdateListener(analyticInternalFrame);
        desktopPane.setLayer(simulationIF, javax.swing.JDesktopPane.MODAL_LAYER);
        desktopPane.add(simulationIF);

        MenuDialog menuDialog = new MenuDialog(simulationIF);
        desktopPane.add(menuDialog);

        setContentPane(desktopPane);


    }

    private void exitApp(){
        if (javax.swing.JOptionPane.showConfirmDialog(rootPane, "Yakin berhenti ?", 
                "Berhenti dari aplikasi", 
                javax.swing.JOptionPane.YES_NO_OPTION, 
                javax.swing.JOptionPane.WARNING_MESSAGE)
                == javax.swing.JOptionPane.YES_OPTION)
        System.exit(0);
    }

    public static void main(String[] args){
        javax.swing.SwingUtilities.invokeLater(new java.lang.Runnable() {
            @Override
            public void run() {
                new Main().setVisible(true);
            }
        });
    }
}
