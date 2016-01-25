package com.michalik;

import static java.lang.Math.*;

/**
 * Created by michalik on 03.12.15.
 */
//tutaj przeprowadzam obliczenia statystyczne
public class Stat {
    int[] tab;
    double mean;
    double var;
    double sD;
    double max;
    double min;
    Stat(int[] tab){
        this.tab=tab;
    }
    void meanValue(){
        for(int i=0; i<tab.length; i++){
            mean+=tab[i];
        }
        mean=mean/tab.length;
    }
    void varValue(){
        for(int i=0; i<tab.length; i++){
            var=(tab[i]-mean)*(tab[i]-mean);
        }
        var=var/(tab.length-1);
    }
    void standardDeviation(){
        sD= sqrt(var);
    }
    void maxValue(){
        max=tab[0];
        for(int i=1; i<tab.length; i++){
            if(tab[i]>max){
                max=tab[i];
            }
        }
    }
    void minValue(){
        min=tab[0];
        for(int i=0; i<tab.length; i++){
            if(tab[i]<min){
                min=tab[i];
            }
        }
    }
}
