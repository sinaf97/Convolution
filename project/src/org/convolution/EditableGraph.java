package org.convolution;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * EditableGraph is a JFrame in which we can draw our own signal as input signals
 */

public class EditableGraph extends JFrame {
    private InputDataUI input;
    private boolean sina;


    public EditableGraph(InputDataUI input,boolean sina){  //Constructor
        super();
        this.input = input;
        this.sina = sina;
        if(sina)
            setTitle("Signal X");
        else
            setTitle("Signal H");
        this.setLayout(new BorderLayout());
        MyPanel graph = new MyPanel();
        JPanel footer = new JPanel();
        JButton confirm = new JButton("Confirm");
        JButton reset = new JButton("Reset");
        JButton close = new JButton("Close");
        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(sina) {
                    input.setX(graph.panel.plotCanvas.getMouseMovement());
                    input.main.setXDatas(graph.panel.plotCanvas.getMouseMovement());
                }
                else{
                    input.setH(graph.panel.plotCanvas.getMouseMovement());
                    input.main.setHDatas(graph.panel.plotCanvas.getMouseMovement());
                }
                setVisible(true);
            }
        });

        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                graph.panel.plotCanvas.reset();
                setVisible(true);
            }
        });
        close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        footer.add(confirm);
        footer.add(reset);
        footer.add(close);
        this.add(graph,BorderLayout.CENTER);
        this.add(footer,BorderLayout.SOUTH);
        this.setSize(600,400);
        this.setResizable(false);
        this.setVisible(true);
    }
}
