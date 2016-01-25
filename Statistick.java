package com.michalik;

import java.util.Arrays;

import static java.lang.Math.sqrt;

/**
 * Created by michalik on 10.12.15.
 */
public class Statistick {
    double mean, var, sD;
    int max, min, median;
    int[] tab;
    Statistick(int tab[]){
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
    void medianValue(){
        int[] tab2 = new int[tab.length];
        for(int i=0; i<tab.length; i++){
            tab2[i]=tab[i];
        }
        Arrays.sort(tab2);
        // 1 3 5 6 7
        if(tab2.length%2!=0){
            median = tab2[(tab2.length+1)/2];
        }
        if(tab2.length%2==0){
            median = tab2[tab2.length/2];
        }
    }
}
