package com.michalik;

public class ImageErode {

    public static int[][] Erozja_Okragla_3x3(int[][] in) {
        int[][] out = new int[in.length][in[1].length];
        
        //int width = out.getWidth();
        //int height = out.getHeight();
        int max = 0;
        int[][] out1 = new int[in.length][in[1].length];

        for (int j = 1; j < in[1].length - 1; j++) {
            for (int i = 1; i < in.length - 1; i++) {
                max = in[i][j];
                
                for (int g = -1; g < 2; g++) {
                    if (in[i][j + g] > max) {
                        max = in[i][j+g];
                    }

                    if (in[i+g][j] > max) {
                        max = in[i+g][j];
                    }
                    out[i][j]=max;
                }
            }
        }

        for (int j = 0; j < out1[1].length; j++) {
            for (int i = 0; i < out1.length; i++) {
                out1[i][j]=out[i][j];
            }
        }

        for (int i = 0; i < out1.length; i++) {
            //out1.setRGB(i, 0, -1);
            out1[i][0]=-1;

        }

        for (int i = 0; i < out1[1].length; i++) {
            out[0][i]=-1;

        }

        return out1;
    }

}
