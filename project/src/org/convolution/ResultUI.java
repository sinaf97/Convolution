package org.convolution;

import org.math.plot.Plot2DPanel;
import org.math.plot.canvas.Points;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedHashMap;

/**
 * Creates JPanel of result graph
 */

public class ResultUI extends JPanel {
    public LinkedHashMap<Double, Double> h;
    public LinkedHashMap<Double, Double> x;
    public Plot2DPanel result;

    /**
     * Constructor
     * @param action determines whether the graph is EDITABLE or ZOOMABLE or DRAGGABLE
     *               0 : EDITABLE
     *               1 : ZOOMABLE
     *               2 : DRAGGABLE
     *
     */
    public ResultUI(int action) {   //Constructor
        super();
        this.setLayout(new BorderLayout());
        double[] min = {-5.0, -5.0};
        double[] max = {5.0, 5.0};
        this.result = new Plot2DPanel(min, max, action,this);
        this.result.addLegend("WEST");
        this.add(this.result, BorderLayout.CENTER);
        this.setBackground(Color.BLUE);
        this.setSize(500, 220);
        this.h = new LinkedHashMap<>();
        this.x = new LinkedHashMap<>();
    }

    /**
     * Modifies Result Graph
     * @param x
     * @param h
     */
    public void modifyResultGraph(LinkedHashMap<Double, Double> x, LinkedHashMap<Double, Double> h) {
        this.h = h;
        this.x = x;
        drawResultGraph(x, h);

    }

    /**
     * Modifies signal x of Result Graph
     * @param x
     */
    public void modifyXResultGraph(LinkedHashMap<Double, Double> x) {
        this.x = x;
        drawResultGraph(this.x, h);

    }

    /**
     * Modifies signal h of Result Graph
     * @param h
     */
    public void modifyHResultGraph(LinkedHashMap<Double, Double> h) {
        this.h = h;
        drawResultGraph(x, this.h);

    }

    /**
     * Draws the result graph
     * @param x
     * @param h
     */
    public void drawResultGraph(LinkedHashMap<Double, Double> x, LinkedHashMap<Double, Double> h) {
        LinkedHashMap<Double, Double> result = Convolution.calculate(x, h);
        Points points = new Points(result);
        this.result.removeAllPlots();
        this.result.addLinePlot("Convolution result", points.getX(), points.getY());
        points = new Points(x);
        this.result.addLinePlot("Signal x", points.getX(), points.getY());
        points = new Points(h);
        this.result.addLinePlot("Signal h", points.getX(), points.getY());
    }

}
