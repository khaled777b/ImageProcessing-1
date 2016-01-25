package com.michalik;

/**
 * Created by michalik on 11.12.15.
 */
public class StatFiltr{
    public static int[][] maximal(int[][] in, int n){
        int[][] out = new int[in.length][in[1].length];
        int[][] out1 = new int[in.length+2*n][in[1].length+2*n];
        //n - otoczenie, które rozpatrujemy

        //przepisać in do out1, zwiększyć z każdej strony obraz czarnymi o "n"

        for(int i=0; i<in.length; i++){
            for(int j=0; j<in[1].length; j++){
                out1[i+n][j+n]=in[i][j];
            }
        }

        //teraz EPLAJ FILTER

        for(int i=0; i<out1.length-n; i=i+n){
            for(int j=0; j<out1[1].length-n; j=j+n){
                int[] tab = new int[n*n];
                int z=0; //zmienna do przechodzenia po tablicy 1d

                for(int ii=0; ii<n; ii++){
                    for(int jj=0; jj<n; jj++){
                        tab[z]=out1[i+ii][j+jj];
                        //System.out.println(+tab[z]);
                        z++;
                    }
                }
                Statistick s = new Statistick(tab);
                s.maxValue();

                for(int ii=0; ii<n; ii++){
                    for(int jj=0; jj<n; jj++){
                        out1[i+ii][j+jj]=(int)s.max;
                    }

                }
            }
            //System.out.println("Buuu ["+i+"]");
        }

        //przepisz do out właściwego
        for(int i=n; i<out1.length-n; i++){
            for(int j=n; j<out1[1].length-n; j++){
                out[i-n][j-n]=out1[i][j];
            }
        }
        return out;
    }

    public static int[][] minimal(int[][] in, int n){
        int[][] out = new int[in.length][in[1].length];
        int[][] out1 = new int[in.length+2*n][in[1].length+2*n];
        //n - otoczenie, które rozpatrujemy

        //przepisać in do out1, zwiększyć z każdej strony obraz czarnymi o "n"

        for(int i=0; i<in.length; i++){
            for(int j=0; j<in[1].length; j++){
                out1[i+n][j+n]=in[i][j];
            }
        }

        //teraz EPLAJ FILTER

        for(int i=0; i<out1.length-n; i=i+n){
            for(int j=0; j<out1[1].length-n; j=j+n){
                int[] tab = new int[n*n];
                int z=0; //zmienna do przechodzenia po tablicy 1d

                for(int ii=0; ii<n; ii++){
                    for(int jj=0; jj<n; jj++){
                        tab[z]=out1[i+ii][j+jj];
                        //System.out.println(+tab[z]);
                        z++;
                    }
                }
                Statistick s = new Statistick(tab);
                s.minValue();

                for(int ii=0; ii<n; ii++){
                    for(int jj=0; jj<n; jj++){
                        out1[i+ii][j+jj]=(int)s.min;
                    }

                }
            }
            //System.out.println("Buuu ["+i+"]");
        }

        //przepisz do out właściwego
        for(int i=n; i<out1.length-n; i++){
            for(int j=n; j<out1[1].length-n; j++){
                out[i-n][j-n]=out1[i][j];
            }
        }
        return out;
    }

    public static int[][] median(int[][] in, int n){
        int[][] out = new int[in.length][in[1].length];
        int[][] out1 = new int[in.length+2*n][in[1].length+2*n];
        //n - otoczenie, które rozpatrujemy

        //przepisać in do out1, zwiększyć z każdej strony obraz czarnymi o "n"

        for(int i=0; i<in.length; i++){
            for(int j=0; j<in[1].length; j++){
                out1[i+n][j+n]=in[i][j];
            }
        }

        //teraz EPLAJ FILTER

        for(int i=0; i<out1.length-n; i=i+n){
            for(int j=0; j<out1[1].length-n; j=j+n){
                int[] tab = new int[n*n];
                int z=0; //zmienna do przechodzenia po tablicy 1d

                for(int ii=0; ii<n; ii++){
                    for(int jj=0; jj<n; jj++){
                        tab[z]=out1[i+ii][j+jj];
                        //System.out.println(+tab[z]);
                        z++;
                    }
                }
                Statistick s = new Statistick(tab);
                s.medianValue();;

                for(int ii=0; ii<n; ii++){
                    for(int jj=0; jj<n; jj++){
                        out1[i+ii][j+jj]=(int)s.median;
                    }

                }
            }
            //System.out.println("Buuu ["+i+"]");
        }

        //przepisz do out właściwego
        for(int i=n; i<out1.length-n; i++){
            for(int j=n; j<out1[1].length-n; j++){
                out[i-n][j-n]=out1[i][j];
            }
        }
        return out;
    }
}
