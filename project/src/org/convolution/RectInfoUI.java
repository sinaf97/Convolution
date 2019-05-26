package org.convolution;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedHashMap;

/**
 * A JFrame with which the user enters the basic data for Rect function
 */

public class RectInfoUI extends JFrame {
    public double start;
    public double end;
    public double height;
    public double step;
    private InputDataUI self;
    private String colorOfInterest;

    public RectInfoUI(InputDataUI self,boolean sina){ //Constructor
        super();
        this.self = self;
        setTitle("Rectangle function variable-set");
        setSize(300,150);
        setResizable(false);
        setLayout(new GridLayout(6,2));

        JLabel l1 = new JLabel("Start point:");
        JLabel l2 = new JLabel("End point:");
        JLabel l3 = new JLabel("Height:");
        JLabel l4 = new JLabel("Step:");
        JLabel l5 = new JLabel("Color:");
        JTextField t1 = new JTextField("-1");
        JTextField t2 = new JTextField("1");
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
                height = Double.valueOf(t3.getText());
                step = Double.valueOf(t4.getText());
                colorOfInterest = (String) color.getSelectedItem();
                LinkedHashMap<Double,Double> data = self.getRect(start,end,height,step);
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
