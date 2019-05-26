package org.convolution;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ResultColor extends JFrame {
    ConvolutionUI main;

    public ResultColor(ConvolutionUI main){
        super();
        this.main = main;
        setLayout(new GridLayout(2,2));
        JLabel l1 = new JLabel("Result color: ");
        String[] colors = { "Blue", "Red", "Green", "Black", "Yellow" };
        JComboBox color = new JComboBox(colors);
        JButton confirm = new JButton("Confirm");
        JButton close = new JButton("Close");
        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.result.result2.changePlotColor(0,main.getColor((String)color.getSelectedItem()));
                setVisible(false);
            }
        });
        close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        add(l1);
        add(color);
        add(confirm);
        add(close);
        setSize(300,100);
        setResizable(false);
        setVisible(true);
    }
}
