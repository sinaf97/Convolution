package org.convolution;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.System.exit;

/**
 * Footer creates the bottom part of the application window
 */
public class Footer extends JPanel {
    private ConvolutionUI main;

    public Footer(ConvolutionUI main){  //Constructor
        super();
        this.main = main;
        JButton exit = new JButton("Exit");
        JButton sv = new JButton("Static view");
        JButton dv = new JButton("Dynamic view");
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
              exit(0);
            }
        });

        sv.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                main.result.result.plotCanvas.ActionMode = 0;
                main.result.modifyResultGraph(main.result.x,main.result.h);
            }
        });

        dv.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                main.result.result.plotCanvas.ActionMode = 2;
                main.result.result.plotCanvas.mouseMovement = main.result.h;
                main.result.result.plotCanvas.mouseMovement2 = main.result.h;
            }
        });
        this.add(sv);
        this.add(dv);
        this.add(exit);
    }
}

