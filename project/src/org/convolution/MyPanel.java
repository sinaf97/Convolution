package org.convolution;

import org.math.plot.Plot2DPanel;

import javax.swing.*;
import java.awt.*;

/**
 * Provides drawable graph for Editable class
 */
public class MyPanel extends JPanel {


    double[] min = {-10.0,-10.0};
    double[] max = {10.0,10.0};
    Plot2DPanel panel;

    public MyPanel(ResultUI result) {  //Constructor

        setBorder(BorderFactory.createLineBorder(Color.black));
        this.setLayout(new BorderLayout());
        panel = new Plot2DPanel(min,max,1,result);
        this.add(this.panel,BorderLayout.CENTER);

    }

    public Dimension getPreferredSize() {
        return new Dimension(250,200);
    }

}