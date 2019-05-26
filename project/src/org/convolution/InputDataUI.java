package org.convolution;

import org.math.plot.Plot2DPanel;
import org.math.plot.canvas.Points;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedHashMap;

/**
 * InputDataUI creates the top part of the application in which we can see our input signals and
 * we can modify them with the provided buttons
 */
public class InputDataUI extends JPanel {
    public ConvolutionUI main;
    public Plot2DPanel xt;
    public Plot2DPanel ht;
    private InputDataUI self = this;



    public InputDataUI(ConvolutionUI main){  //Constructor
        super();
        this.main = main;
        this.setLayout(new GridLayout(1,4));
        double[] min = {-10.0,-10.0};
        double[] max = {10.0,10.0};
        this.xt = new Plot2DPanel(min,max);
        this.ht = new Plot2DPanel(min,max);
        this.add(this.xt);
        JPanel btns = createBtns(true);
        this.add(btns);
        this.add(this.ht);
        JPanel btns1 = createBtns(false);
        this.add(btns1);
        this.setSize(500,220);

    }

    /**
     * Changes signal h and update the graph
     * @param h
     */
    public void setH(LinkedHashMap<Double,Double> h){
        Points points = new Points(h);
        this.ht.removeAllPlots();
        if(!main.descrete)
            this.ht.addLinePlot("Signal h",points.getX(),points.getY());
        else
            this.ht.addBarPlot("Signal h",points.getX(),points.getY());
        this.ht.addLegend("NORTH");
        this.ht.changePlotColor(0,Color.GREEN);

    }

    /**
     * Changes signal x and update the graph
     * @param x
     */

    public void setX(LinkedHashMap<Double,Double> x){
        Points points = new Points(x);
        this.xt.removeAllPlots();
        if(!main.descrete)
            this.xt.addLinePlot("Signal x",points.getX(),points.getY());
        else
            this.xt.addBarPlot("Signal x",points.getX(),points.getY());
        this.xt.addLegend("NORTH");
        this.xt.changePlotColor(0,Color.RED);
    }

    /**
     * Creates the  main buttons for input signals in order to modify them
     * @param sina determins whether buttons are for signal x or h
     *             true : signal x(t)
     *             false : signal h(t)
     * @return
     */
    public JPanel createBtns(boolean sina){
        JPanel btns = new JPanel();
        btns.setLayout(new GridLayout(4,1));
        JButton b1 = new JButton("sin");
        JButton b2 = new JButton("cos");
        JButton b3 = new JButton("rect");
        JButton b4 = new JButton("Custom");
        b1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0) {
                new SinInfoUI(self,sina,true);
            }
        });
        b2.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0) {
                new SinInfoUI(self,sina,false);
            }
        });
        b3.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0) {
                new RectInfoUI(self,sina);

            }
        });
        b4.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0) {
                if(sina)
                    new EditableGraph(self,true);
                else
                    new EditableGraph(self,false);
            }
        });
        btns.add(b1);
        btns.add(b2);
        btns.add(b3);
        btns.add(b4);
        return btns;
    }

    /**
     * returns Sin function datas in the wanted boundries
     * @param start
     * @param end
     * @param omega
     * @param step
     * @return
     */
    public LinkedHashMap<Double,Double> getSin(double start,double end,double omega,double step){
        LinkedHashMap<Double,Double> result = new LinkedHashMap<>();
        for(double i = start;i<=end;i+=step)
            result.put(i,Math.sin(omega*i));
        return result;
    }

    /**
     * returns Cos function datas in the wanted boundries
     * @param start
     * @param end
     * @param omega
     * @param step
     * @return
     */
    public LinkedHashMap<Double,Double> getCos(double start,double end,double omega,double step){
        LinkedHashMap<Double,Double> result = new LinkedHashMap<>();
        for(double i = start;i<=end;i+=step)
            result.put(i,Math.cos(omega*i));
        return result;
    }

    /**
     * returns Rect function datas in the wanted boundries
     * @param start
     * @param end
     * @param height
     * @param step
     * @return
     */
    public LinkedHashMap<Double,Double> getRect(double start,double end,double height,double step){
        LinkedHashMap<Double,Double> result = new LinkedHashMap<>();
        for(double i = start;i<=end;i+=step)
            result.put(i,height);
        return result;
    }
}
