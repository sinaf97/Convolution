package org.math.plot.canvas;

import org.math.plot.utils.Array;

import java.util.*;

public class Points {
    private double[] x;
    private double[] y;

    public Points(LinkedHashMap<Double,Double> x){
        this.x = new double[x.size()];
        this.y = new double[x.size()];
        this.setData(x);
    }

    public double[] getX() {
        return x;
    }

    public double[] getY() {
        return y;
    }

    private void setData(LinkedHashMap<Double,Double> data){
        ArrayList<Double> sina = new ArrayList<>();
        for (Map.Entry<Double, Double> entry : data.entrySet())
            sina.add(entry.getKey());
        Collections.sort(sina);
        LinkedHashMap<Double,Double> sortedData = new LinkedHashMap<>();
        for (Double e:sina)
            sortedData.put(e,data.get(e));
        data = sortedData;
        int i = 0;
        for (Map.Entry<Double, Double> entry : data.entrySet()) {
            this.x[i] = entry.getKey();
            this.y[i] = entry.getValue();
            i++;
        }
    }

}
