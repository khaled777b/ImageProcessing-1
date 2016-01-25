package com.michalik;

public class ImageDilate {

    public static int[][] Dylatacja_Okragla_3x3(int[][] in) {

        int[][] out = new int[in.length][in[1].length];
        //int width = out.getWidth();
        //int height = out.getHeight();
        int min = 0;
        int[][] out1 = new int[in.length][in[1].length];

        for (int j = 1; j < out[1].length - 1; j++) {
            for (int i = 1; i < out.length - 1; i++) {
                min = in[i][j];
                for (int g = -1; g < 2; g++) {
                    if (in[i+g][j] < min) {
                        min = in[i+g][j];
                    }

                    if (in[i][j+g] < min) {
                        min = in[i][j+g];
                    }
                    out[i][j]=min;
                }
            }
        }

        for (int j = 0; j < out1[1].length; j++) {
            for (int i = 0; i < out1.length; i++) {
                out1[i][j]=out[i][j];
            }
        }

        for (int i = 0; i < out1.length; i++) {
            out1[i][0]=-1;

        }

        for (int i = 0; i < out1[1].length; i++) {
            out[0][i]=-1;

        }

        return out1;
    }

}
