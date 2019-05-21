package org.convolution;

import javax.swing.*;
import java.awt.*;
import java.util.*;

/**
 * This Class is determined to calculate convolution of two given 1D signals
 */


public class Convolution{


    public Convolution(){  //Constructor

    }

    /**
     * This method is static so any class can access it without creating an instance of it
     * it calculates the convolution convolution of two given 1D signals
     * @param x signal x
     * @param h signal y
     * @return
     */

    public static LinkedHashMap<Double,Double> calculate(LinkedHashMap<Double,Double> x, LinkedHashMap<Double,Double> h){
        LinkedHashMap<Double,Double> result = new LinkedHashMap<>();
        double first = x.keySet().iterator().next();//first key of signal x
        double last = 0.0;//last key of signal x default to 0
        Iterator it = x.keySet().iterator();
        while (it.hasNext())
            last =(double) it.next();//finding last key
        double step = (last - first)/(x.size()-1);//step of the descrete numbers


        LinkedHashMap<Double,Double> saber = new LinkedHashMap<>();//variable which satisfy proper bounderies for convolution
        double sina = step;
        for (int i = h.size(); i > 0; i--) {
            saber.put(first - i*sina, 0.0);
        }
        for (Map.Entry<Double, Double> entry : x.entrySet())
            saber.put(entry.getKey(),entry.getValue());
        for (int i = 0; i < h.size(); i++) {
            saber.put(last + i*sina, 0.0);
        }

        LinkedHashMap<Integer,Double> newX = new LinkedHashMap<>();
        LinkedHashMap<Integer,Double> newH = new LinkedHashMap<>();
        //convert keys to integers
        for (Map.Entry<Double, Double> entry : saber.entrySet())
            newX.put((int) (Math.round(entry.getKey().doubleValue()*Math.pow(step,-1))),entry.getValue());
        for (Map.Entry<Double, Double> entry : h.entrySet())
            newH.put((int) (Math.round(entry.getKey().doubleValue()*Math.pow(step,-1))),entry.getValue());
        double temp = 0;//result at a specific point
        for (int key : newX.keySet()){
            for (int hkey : newH.keySet()) {
                try {
                    temp += newX.get(hkey)*newH.get(key-hkey)*step;
                }
                catch(Exception e){
                    continue;
                }
            }
            result.put((double) key*step,temp);//filling result
            temp = 0;
        }
        return result;
    }

    /**
     * this function returns the part of convolution result where h(t) and x(t) have overlap
     * @param incomeResult
     * @param h
     * @return
     */
    public static LinkedHashMap<Double,Double> calculatePart(LinkedHashMap<Double,Double> incomeResult, LinkedHashMap<Double,Double> h){
        LinkedHashMap<Double,Double> result = new LinkedHashMap<>();
        double lastKey = h.keySet().iterator().next();
        h = reverse(h);
        double firstKey = -h.keySet().iterator().next();

        for (Map.Entry<Double, Double> entry : incomeResult.entrySet()){
            try {
                if (entry.getKey() > (lastKey+firstKey)/2.0)
                    break;
                result.put(entry.getKey(), entry.getValue());
            }catch(Exception e){
                continue;
            }
        }
        return result;
    }

    /**
     * This function returns the mirror signal of input signal by the y-axis
     * @param h
     * @return
     */

    public static LinkedHashMap<Double,Double> reverse(LinkedHashMap<Double,Double> h){
        Double[] dkeys = new Double[h.size()];
        try {
             h.keySet().toArray(dkeys);//putting h keys in dkey array
        }catch(Exception e){
        }
        ArrayList<Double> keys = new ArrayList<>(Arrays.asList(dkeys));//converting dkey to arraylist
        Collections.reverse(keys);//reversing the array
        LinkedHashMap<Double,Double> result = new LinkedHashMap<>();
        for (Double key:keys)
            result.put(-key,h.get(key));//mirroring the signal
        return result;
    }


}
