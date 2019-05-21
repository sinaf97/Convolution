package org.convolution;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedHashMap;

/**
 * ConvolutionUI is the base of the application
 * it creates all the required Panels
 */
public class ConvolutionUI extends JFrame {
    private InputDataUI input;
    public ResultUI result;
    private Footer footer;



    public ConvolutionUI(){ //Constructor
        super();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.input = new InputDataUI(this);
        this.result = new ResultUI(0);
        this.footer = new Footer(this);
        JPanel center = new JPanel();
        center.setLayout(new GridLayout(2,1));
        center.setSize(500,400);
        center.add(this.input);
        center.add(this.result);
        this.add(center,BorderLayout.CENTER);
        this.add(this.footer,BorderLayout.SOUTH);
        this.setSize(1000,500);
        this.setVisible(true);
    }

    /**
     * sets both signal x an h
     * @param x
     * @param h
     */
    public void setDatas(LinkedHashMap<Double,Double> x,LinkedHashMap<Double,Double> h){
        this.input.setH(h);
        this.input.setX(x);
        this.result.modifyResultGraph(x,h);
        this.setVisible(true);
    }

    /**
     * sets the signal x
     * @param x
     */
    public void setXDatas(LinkedHashMap<Double,Double> x){
        this.input.setX(x);
        this.result.modifyXResultGraph(x);
        this.setVisible(true);
    }

    /**
     * sets the signal h
     * @param h
     */
    public void setHDatas(LinkedHashMap<Double,Double> h){
        this.input.setH(h);
        this.result.modifyHResultGraph(h);
        this.setVisible(true);
    }


}
