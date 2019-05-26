package org.convolution;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
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
//        setLayout(new GridLayout(1,4));
        JButton exit = new JButton("Exit");
        JRadioButton cont = new JRadioButton("C.T",true);
        JRadioButton descrete = new JRadioButton("D.T",false);
        ButtonGroup bg = new ButtonGroup();
        bg.add(cont);
        bg.add(descrete);
        JButton sv = new JButton("Static view");
        JButton dv = new JButton("Dynamic view");
        JButton colorChange = new JButton("Result color");
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
        colorChange.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ResultColor(main);
            }
        });
        ChangeListener changeListener = new ChangeListener() {
            public void stateChanged(ChangeEvent changEvent) {
                AbstractButton aButton = (AbstractButton)changEvent.getSource();
                ButtonModel aModel = aButton.getModel();
                if(cont.isSelected())
                    main.descrete = false;
                else
                    main.descrete = true;
            }
        };

        this.add(colorChange);
        this.add(sv);
        this.add(dv);
        this.add(exit);
        cont.addChangeListener(changeListener);
        descrete.addChangeListener(changeListener);
        add(cont);
        add(descrete);
    }
}

