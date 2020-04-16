/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenprojectpandemicsimulation;

/**
 *
 * @author tekad_000
 */
public class MenuDialog extends javax.swing.JInternalFrame {
    private javax.swing.JButton startButton;
    private javax.swing.JButton resetButton;
    private javax.swing.JButton freeButton;
    private javax.swing.JButton socialDistancingButton;
    private javax.swing.JButton selfIsolationButton;
    java.awt.event.ActionListener act;
    
    public MenuDialog(java.awt.event.ActionListener act){
        this.act = act;
        init();
    }

    private void init(){
        setDefaultCloseOperation(javax.swing.JDialog.DO_NOTHING_ON_CLOSE);
        setResizable(false);
        setClosable(false);

        startButton = new javax.swing.JButton("Start");
        startButton.setActionCommand("start");
        startButton.addActionListener(act);
        resetButton = new javax.swing.JButton("Reset");
        resetButton.setActionCommand("reset");
        resetButton.addActionListener(act);
        freeButton = new javax.swing.JButton("Free world");
        freeButton.setActionCommand("free");
        freeButton.addActionListener(act);
        socialDistancingButton = new javax.swing.JButton("Social Distancing");
        socialDistancingButton.setActionCommand("social");
        socialDistancingButton.addActionListener(act);
        selfIsolationButton = new javax.swing.JButton("Self Isolation");
        selfIsolationButton.setActionCommand("self");
        selfIsolationButton.addActionListener(act);
                
        javax.swing.JPanel menuPanel = new javax.swing.JPanel();
        java.awt.GridLayout gridLayout = new java.awt.GridLayout(4,1);
        gridLayout.setVgap(10);
        gridLayout.setHgap(10);
        menuPanel.setLayout( gridLayout);
        menuPanel.add(startButton);
        menuPanel.add(resetButton);
        menuPanel.add(freeButton);
        menuPanel.add(socialDistancingButton);
        menuPanel.add(selfIsolationButton);
        setContentPane(menuPanel);

        pack();
        setVisible(true);
    }

}
