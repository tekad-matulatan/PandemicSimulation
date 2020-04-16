/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matulatan.simulation;

import java.awt.event.ActionEvent;

/**
 *
 * @author tekad_000
 */
public class SimulationMapPane extends javax.swing.JPanel {

    private java.util.concurrent.CopyOnWriteArrayList<Human> humans = new java.util.concurrent.CopyOnWriteArrayList<>();
    private java.util.ArrayList<Barrier> barriers = new java.util.ArrayList<>();
    private int width;
    private int height;
    private int numberOfHumans, sizeOfHuman;
    private java.util.ArrayList<Integer> counter;
    private int counting, prevCounting;

    private boolean startSimulation;
    private javax.swing.Timer counterTimer;

    private UpdateListener updateListener;

    public SimulationMapPane(){
        this(500,300,1);
    }

    /**
    *
    * @param width is the width of world
    * @param height is the height of world
    * @param numberOfHumans are number of population
    */
    public SimulationMapPane(int width, int height, int numberOfHumans){
        this.width = width;
        this.height = height;
        this.numberOfHumans = numberOfHumans;
        sizeOfHuman = 10;
        counter = new java.util.ArrayList<>();
        init();
    }

    private void init(){
        setSize(width, height);
        setPreferredSize(new java.awt.Dimension(width, height));

        counterTimer = new javax.swing.Timer(1000, new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateCounter();
            }
        });

        for (int i=0; i< 4; i++){
            double bWidth, bHeight;
            do {
                bWidth = Math.random()* width ;
                bHeight = Math.random()* height ;
            } while (bWidth*bHeight > width*10);
            barriers.add( new Barrier( (int) (Math.random()*width), 
                    (int) (Math.random()*height),
                    (int) bWidth, (int) bHeight));
        }
        barriers.add( new Barrier(-10,0,width, 10));
        barriers.add( new Barrier(-10,height-10,width, 10));
        barriers.add( new Barrier(0,-10,10,height));
        barriers.add( new Barrier(width -10 ,-10,10,height));

        for (int i=0; i<= numberOfHumans; i++){
            int x = (int) (Math.random() * width);
            int y = (int) (Math.random() * height);
            boolean overlap = false;
            for (Barrier barrier: barriers){
                if (barrier.intersects(x-1, y-1, sizeOfHuman+2, sizeOfHuman+2)){
                    overlap = true;
                    break;
                }
            }
            for (Human human: humans){
                if (human.intersects(x,y,sizeOfHuman,sizeOfHuman)){
                    overlap = true;
                    break;
                }
            }
            if (overlap) i--;
            else humans.add(new Human(x, y, sizeOfHuman));
        }
//        humans.get(0).setInfected(true);
        startSimulation = false;
    }

    public void start(){
        humans.get(0).setInfected(true);
        counterTimer.start();
        startSimulation = true;
    }

    public void reset(){
        counter = new java.util.ArrayList<>();
        humans = new java.util.concurrent.CopyOnWriteArrayList<>();
        barriers = new java.util.ArrayList<>();
        counterTimer.stop();
        init();
    }

    public void setUpdateListener(UpdateListener updateListener){
        this.updateListener = updateListener;
    }

    @Override
    public void paint(java.awt.Graphics g){
        java.awt.Graphics2D g2 = (java.awt.Graphics2D) g;

        barriers.forEach((barrier)->{
            g2.setColor(java.awt.Color.LIGHT_GRAY);
            g2.fill(barrier);
        });

        counting = 0;

        // functional operation style
        humans.forEach((human) -> {
            //java.awt.Color warna = human.isInfected() ?  java.awt.Color.red: java.awt.Color.black;
            java.awt.Color warna = java.awt.Color.black;

            if (human.isInfected()){
                counting++;
                warna = java.awt.Color.red;
            }
            if (human.isImmune())
                warna = java.awt.Color.cyan;
            if (human.isDie())
                humans.remove(human);

            java.awt.Graphics2D g3 = (java.awt.Graphics2D)
            g.create(human.getX(), human.getY(), human.getSize(), human.getSize());
            g3.setColor(warna);
            g3.fill(human);
            if (startSimulation)
                human.move();

            for (Barrier barrier: barriers){
                if (human.intersects(barrier.getBounds2D())){
                    human.reverse();
                    break;
                }                    
            };
            for (Human human1: humans ){
                if (human.intersects(human1.getBounds2D())){
                    if ((!human.isImmune()&&human1.isInfected())||
                        (human.isInfected()&&!human1.isImmune()))
                    {
                        human.setInfected(true);
                        human1.setInfected(true);
                    }
                }
            }
            
        });
        //--------------------

//        if (counting != prevCounting){
//            counter.add(counting);
//            // From Reference type ArrayList to primitive array
//            (updateListener).update(counter.stream().mapToInt(i->i).toArray());
//            //analyticInternalFrame.update(counter.stream().mapToInt(i->i).toArray());
//            //analyticInternalFrame.repaint();  
//            prevCounting = counting;
//        }
    }

    private void updateCounter(){
            counter.add(counting);
            // From Reference type ArrayList to primitive array
            (updateListener).update(counter.stream().mapToInt(i->i).toArray());       
    }

}