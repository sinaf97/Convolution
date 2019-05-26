package org.convolution;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedHashMap;

/**
 * A JFrame with which the user enters the basic data for Sin and Cos function
 */
public class SinInfoUI extends JFrame {
    public double start;
    public double end;
    public double omega;
    public double step;
    private InputDataUI self;
    private String colorOfInterest;

    /**
     * Constructor
     * @param self
     * @param sina determins whether it's for signal x(t) or h(t)
     * @param sin determins whether to get Sin data or Cos data
     */
    public SinInfoUI(InputDataUI self,boolean sina,boolean sin){ //Constructor
        super();
        this.self = self;
        setTitle("Rectangle function variable-set");
        setSize(300,150);
        setResizable(false);
        setLayout(new GridLayout(6,2));

        JLabel l1 = new JLabel("Start point:");
        JLabel l2 = new JLabel("End point:");
        JLabel l3 = new JLabel("Omega:");
        JLabel l4 = new JLabel("Step:");
        JLabel l5 = new JLabel("Color:");
        JTextField t1 = new JTextField("-5");
        JTextField t2 = new JTextField("5");
        JTextField t3 = new JTextField("1");
        JTextField t4 = new JTextField("0.01");
        String[] colors = { "Blue", "Red", "Green", "Black", "Yellow" };
        JComboBox color = new JComboBox(colors);
        JButton confirm = new JButton("Confirm");
        JButton close = new JButton("Close");
        add(l1);
        add(t1);
        add(l2);
        add(t2);
        add(l3);
        add(t3);
        add(l4);
        add(t4);
        add(l5);
        add(color);

        confirm.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0) {
                start = Double.valueOf(t1.getText());
                end = Double.valueOf(t2.getText());
                omega = Double.valueOf(t3.getText());
                step = Double.valueOf(t4.getText());
                colorOfInterest = (String) color.getSelectedItem();
                LinkedHashMap<Double,Double> data;
                if(sin) {
                    data = self.getSin(start,end,omega,step);
                } else {
                    data = self.getCos(start,end,omega,step);
                }
                if (sina) {
                    self.main.setXDatas(data,colorOfInterest);
                } else {
                    self.main.setHDatas(data,colorOfInterest);
                }
                setVisible(true);
            }
        });
        close.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0) {
                setVisible(false);
            }
        });
        add(confirm);
        add(close);
        setVisible(true);
    }
}
