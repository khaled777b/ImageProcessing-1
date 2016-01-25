package com.michalik;

class Binaryzacja{
    public static int[][] in;
    public static int[][] out;
    public static int[][] out1;
    private int c = 17;

    Binaryzacja(int[][] in){
        this.in = in;
        this.out = in;
    }

    int getC(){
        int a=c;
        return a;
    }

    public static void binarGlobalMean(){
        //liczę średnią obrazu i na podstawie tej średniej ustawiam próg globalny binaryzacji

        int p = ImageStatistick.globalMeanValue(in);

        for(int i=0; i<in.length; i++){
            for(int j=0; j<in[1].length; j++) {
                if (in[i][j] > p) {
                    out[i][j] = 255;
                } else {
                    out[i][j] = 0;
                }
            }
        }
    }
    public static void binarSimple(int p){
        for(int i=0; i<in.length; i++){
            for(int j=0; j<in[1].length; j++) {
                if (in[i][j] > p) {
                    out[i][j] = 255;
                } else {
                    out[i][j] = 0;
                }
            }
        }
    }

    public static void binarMean(int n){
        //n - długośc boku kwadratu którym przechodzimy po obrazku
        int[][] out1 = new int[in.length+2*n][in[1].length+2*n];

        //przepisać in do out1, zwiększyć z każdej strony obraz czarnymi o "n"

        for(int i=0; i<in.length; i++){
            for(int j=0; j<in[1].length; j++){
                out1[i+n][j+n]=in[i][j];
            }
        }

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
                Stat s = new Stat(tab);
                s.meanValue();
                int p = (int)s.mean;

                for(int ii=0; ii<n; ii++){
                    for(int jj=0; jj<n; jj++){
                        if(out1[i+ii][j+jj]>p){
                            out1[i+ii][j+jj]=255;
                        }
                        if(out1[i+ii][j+jj]<p){
                            out1[i+ii][j+jj]=0;
                        }
                    }

                }
            }
            //System.out.println("Buuu ["+i+"]");
        }
        //ImProcess.showImage(ImProcess.arrayToBF(out1));
        //przepisz do out właściwego
        for(int i=n; i<out1.length-n; i++){
            for(int j=n; j<out1[1].length-n; j++){
                out[i-n][j-n]=out1[i][j];
            }
        }

    }

    public static void binarSPmethod(int n){
        //n - długośc boku kwadratu którym przechodzimy po obrazku
        int[][] out1 = new int[in.length+2*n][in[1].length+2*n];

        //przepisać in do out1, zwiększyć z każdej strony obraz czarnymi o "n"

        for(int i=0; i<in.length; i++){
            for(int j=0; j<in[1].length; j++){
                out1[i+n][j+n]=in[i][j];
            }
        }

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
                Stat s = new Stat(tab);
                s.meanValue();
                s.standardDeviation();
                int p = (int)(s.mean*(1+0.5*s.sD/127));

                for(int ii=0; ii<n; ii++){
                    for(int jj=0; jj<n; jj++){
                        if(out1[i+ii][j+jj]>p){
                            out1[i+ii][j+jj]=255;
                        }
                        if(out1[i+ii][j+jj]<p){
                            out1[i+ii][j+jj]=0;
                        }
                    }

                }
            }
            //System.out.println("Buuu ["+i+"]");
        }
        //ImProcess.showImage(ImProcess.arrayToBF(out1));
        //przepisz do out właściwego
        for(int i=n; i<out1.length-n; i++){
            for(int j=n; j<out1[1].length-n; j++){
                out[i-n][j-n]=out1[i][j];
            }
        }

    }

    public static void binarNiblackmethod(int n){
        //n - długośc boku kwadratu którym przechodzimy po obrazku
        int[][] out1 = new int[in.length+2*n][in[1].length+2*n];

        //przepisać in do out1, zwiększyć z każdej strony obraz czarnymi o "n"

        for(int i=0; i<in.length; i++){
            for(int j=0; j<in[1].length; j++){
                out1[i+n][j+n]=in[i][j];
            }
        }

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
                Stat s = new Stat(tab);
                s.meanValue();
                s.standardDeviation();
                int p = (int)(s.mean+s.sD);

                for(int ii=0; ii<n; ii++){
                    for(int jj=0; jj<n; jj++){
                        if(out1[i+ii][j+jj]>p){
                            out1[i+ii][j+jj]=255;
                        }
                        if(out1[i+ii][j+jj]<p){
                            out1[i+ii][j+jj]=0;
                        }
                    }

                }
            }
            //System.out.println("Buuu ["+i+"]");
        }
        //ImProcess.showImage(ImProcess.arrayToBF(out1));
        //przepisz do out właściwego
        for(int i=n; i<out1.length-n; i++){
            for(int j=n; j<out1[1].length-n; j++){
                out[i-n][j-n]=out1[i][j];
            }
        }

    }
}
