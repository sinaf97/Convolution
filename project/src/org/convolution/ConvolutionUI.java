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
    public boolean descrete = false;



    public ConvolutionUI(boolean descrete){ //Constructor
        super();
        this.descrete = descrete;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.input = new InputDataUI(this);
        this.result = new ResultUI(0,this);
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

    /**
     * sets the signal x
     * @param x
     */
    public void setXDatas(LinkedHashMap<Double,Double> x,String color){
        Color h = new Color(1);
        try {
            h = this.result.result.getPlotColor(1);
        }catch(Exception e){
        }
        this.input.setX(x);
        setColor(color,"xt");
        this.result.modifyXResultGraph(x);
        Color c = getColor(color);
        this.result.result.changePlotColor(0,c);
        this.result.result.changePlotColor(1,h);
        this.setVisible(true);
    }

    /**
     * sets the signal h
     * @param h
     */
    public void setHDatas(LinkedHashMap<Double,Double> h,String color){
        Color x = new Color(1);
        try {
            x = this.result.result.getPlotColor(0);
        }catch(Exception e){
        }
        this.input.setH(h);
        setColor(color,"ht");
        this.result.modifyHResultGraph(h);
        Color c = getColor(color);
        this.result.result.changePlotColor(0,x);
        this.result.result.changePlotColor(1,c);
        this.setVisible(true);
    }

    public void setColor(String color,String mode){
        Color c = getColor(color);
        if(mode.equals("xt")) {
            this.input.xt.changePlotColor(0, c);
        }
        else {
            this.input.ht.changePlotColor(0, c);
        }
    }

    public Color getColor(String color){
        Color c = new Color(1);
        switch (color){
            case "Blue":
                c = Color.BLUE;
                break;
            case "Red":
                c = Color.RED;
                break;
            case "Green":
                c = Color.GREEN;
                break;
            case "Black":
                c = Color.BLACK;
                break;
            case "Yellow":
                c = Color.YELLOW;
                break;
        }
        return c;
    }


}
