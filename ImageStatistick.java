package com.michalik;

public class ImageStatistick {
    public static int globalMeanValue(int image[][]) {
        double sum = 0;

        for (int i = 0; i < image.length; i++) {
            for (int j = 0; j < image[1].length; j++) {
                sum += image[i][j];
            }
        }
        double m = sum / (image.length * image[1].length);
        int mean = (int) m;
        return mean;
    }

    public static int[] Array2DTo1D(int[][] in) {
        int[] tab = new int[in.length * in[1].length];
        for (int i = 0; i < in.length; i++) {
            for (int j = 0; j < in[1].length; j++) {
                tab[i * j] = in[i][j];
            }
        }
        return tab;
    }

    /*public static int[][] mapaWariancji(int in[][]) {
        int n = 2;

        Statistick s = new Statistick(Array2DTo1D(in));
        s.meanValue();
        s.varValue();

        double wariancjaGlobalna = s.var;

        System.out.println("Wariancja globalna: " + wariancjaGlobalna);
        int[][] out = new int[in.length][in[1].length];
        int[][] out1 = new int[in.length + 2 * n][in[1].length + 2 * n];

        //przepisać in do out1, zwiększyć z każdej strony obraz czarnymi o "n"

        for (int i = 0; i < in.length; i++) {
            for (int j = 0; j < in[1].length; j++) {
                out1[i + n][j + n] = in[i][j];
            }
        }

        for (int i = 0; i < out1.length - n; i = i + n) {
            for (int j = 0; j < out1[1].length - n; j = j + n) {
                int[] tab = new int[n * n];
                int z = 0; //zmienna do przechodzenia po tablicy 1d

                for (int ii = 0; ii < n; ii++) {
                    for (int jj = 0; jj < n; jj++) {
                        tab[z] = out1[i + ii][j + jj];
                        //System.out.println(+tab[z]);
                        z++;
                    }
                }
                Statistick sm = new Statistick(tab);
                sm.meanValue();
                sm.varValue();
                //int p = sm.var;
                if (sm.var > wariancjaGlobalna) {
                    for (int ii = 0; ii < n; ii++) {
                        for (int jj = 0; jj < n; jj++) {
                            out1[i + ii][j + jj] = 255;
                        }
                    }
                }
                else{
                    for(int ii=0; ii<n; ii++){
                        for(int jj=0; jj<n; jj++){
                            out1[i+ii][j+jj]=0;
                        }
                    }
                }
                //System.out.println("Buuu ["+i+"]");
            }
        }
        //ImProcess.showImage(ImProcess.arrayToBF(out1));
        //przepisz do out właściwego
        for (int i = n; i < out1.length - n; i++) {
            for (int j = n; j < out1[1].length - n; j++) {
                    out[i - n][j - n] = out1[i][j];
                }
            }



        return out1;
    }*/
}
