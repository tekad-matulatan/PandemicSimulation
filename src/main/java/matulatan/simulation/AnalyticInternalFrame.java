/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matulatan.simulation;

import java.awt.Color;

/**
 *
 * @author tekad_000
 */
public class AnalyticInternalFrame extends javax.swing.JInternalFrame
        implements UpdateListener{

    private AnalyticPanel analyticPanel;

    public AnalyticInternalFrame(){
        init();
    }

    private void init(){
        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        analyticPanel = new AnalyticPanel();
        setContentPane(analyticPanel);
        pack();
        setVisible(true);
    }

    /**
     *
     * @param data
     */
    @Override
    public void update(int[] data){
        if (data == null)
            analyticPanel.yAxis[0] = 0;
        else analyticPanel.yAxis = data;
        repaint();
    }

    @Override
    public void updating(UpdateEvent evt){
        
    }

}

class AnalyticPanel extends javax.swing.JPanel{
    public int[] yAxis;
    private int[] xPoly, yPoly;

    public AnalyticPanel(){
        init();
    }

    private void init(){
        setSize(300,400);
        setPreferredSize(new java.awt.Dimension(300,400));
        yAxis = new int[0];
    }

    @Override
    public void paint(java.awt.Graphics g){
        g.setColor(Color.black);
        g.drawLine(20, 380, 280, 380);
        g.drawLine(20, 380, 20, 50);
        g.setColor(Color.red);
        int numOfAxis = yAxis.length;
        if (numOfAxis>0){
            xPoly = new int[ numOfAxis + 1];
            yPoly = new int[ numOfAxis + 1];
            xPoly[0] = 20;
            yPoly[0] = 380;
            double tickX = (280-20) / numOfAxis;
            // FINDING maximun number in array
            int maxY = java.util.Arrays.stream(yAxis).summaryStatistics().getMax();
            System.out.println("Max Y:" + maxY );
            double tickY = (380-50) / maxY; 
            int tix = 1;
            for (int i: yAxis){
                xPoly[tix] = ((int) ( tix * tickX )) + 20;
                yPoly[tix] = 380 - ((int) ( i * tickY ));
                tix++;
            }
            g.drawPolyline(xPoly, yPoly, numOfAxis + 1);            
        }
    }
}