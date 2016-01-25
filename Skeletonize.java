package com.michalik;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created by michalik on 11.12.15.
 */
public class Skeletonize {

    //wartości masek
    private final static int [] wyciecia = {3, 5, 7, 12, 13, 14, 15, 20,
            21, 22, 23, 28, 29, 30, 31, 48,
            52, 53, 54, 55, 56, 60, 61, 62,
            63, 65, 67, 69, 71, 77, 79, 80,
            81, 83, 84, 85, 86, 87, 88, 89,
            91, 92, 93, 94, 95, 97, 99, 101,
            103, 109, 111, 112, 113, 115, 116, 117,
            118, 119, 120, 121, 123, 124, 125, 126,
            127, 131, 133, 135, 141, 143, 149, 151,
            157, 159, 181, 183, 189, 191, 192, 193,
            195, 197, 199, 205, 207, 208, 209, 211,
            212, 213, 214, 215, 216, 217, 219, 220,
            221, 222, 223, 224, 225, 227, 229, 231,
            237, 239, 240, 241, 243, 244, 245, 246,
            247, 248, 249, 251, 252, 253, 254, 255};

    private final static int [] czworki = {3, 6, 7, 12, 14, 15, 24, 28,
            30, 48, 56, 60, 96, 112, 120, 129,
            131, 135, 192, 193, 195, 224, 225, 240};

    private static int [][] maska = {{128, 64, 32}, {1, 0, 16}, {2, 4, 8}};
    private static int [][] sprawdzarka = {{128, 64, 32}, {1, 0, 16}, {2, 4, 8}};

    int[][] in;
    int[][] in_process;
    int[][] out;

    Skeletonize(int[][] in){
        this.in = in;
    }
    public static int[][] binarToLogical(int[][] image){
        for(int i=0; i<image.length; i++){
            for(int j=0; j<image[1].length; j++){
                if(image[i][j]==255){
                    image[i][j]=0;
                }
                else if (image[i][j]==0){
                    image[i][j]=1;
                }
            }
        }
        return image;
    }
    public static int[][] logicalToBinar(int[][] image){
        for(int i=0; i<image.length; i++){
            for(int j=0; j<image[1].length; j++){
                if(image[i][j]==0){
                    image[i][j]=255;
                }
                else if(image[i][j]==1){
                    image[i][j]=0;
                }
            }
        }
        return image;
    }
    void processToOut(int[][] image){
        for(int i=0; i<image.length; i++){
            for(int j=0; j<image[1].length; j++){
                out[i][j]=image[i][j];
            }
        }
    }

    void performKMM(){
        //ścienianie przez KMM
        //int counter=0;
        boolean changes;
        in_process=binarToLogical(in);
        do{
            changes=false;
            int waga = 0;
            //krok II - zmiana na 2 i na 3
            for(int i=1; i<in_process.length-1; i++){
                for(int j=1; j<in_process[1].length-1; j++){
                    if(in_process[i][j]==1){
                        if(in_process[i][j-1]==0 || in_process[i][j+1]==0 || in_process[i-1][j]==0 || in_process[i+1][j]==0){
                            in_process[i][j]=2;
                        }
                        else if(in_process[i-1][j-1]==0 || in_process[i+1][j-1]==0 || in_process[i-1][j+1]==0 || in_process[i+1][j+1]==0){
                            in_process[i][j]=3;

                        }
                    }
                }
            }
            System.out.println("Po kroku III");

            //krok IV - zmiana na 4
            for(int i=1; i<in_process.length-1; i++){
                for(int j=1; j<in_process[1].length-1; j++){
                    if(in_process[i][j]==2){
                        waga=0;
                        for(int a=i-1; a<i+1; a++){
                            for(int b=j-1; b<j+1; b++){
                                if(in_process[a][b]==1 || in_process[a][b]==2 || in_process[a][b]==3 || in_process[a][b]==4){
                                    waga+=maska[a-i+1][b-j+1];
                                }
                                for(int m=0; m<czworki.length; m++){
                                    if(czworki[m]==waga){
                                        in_process[i][j]=4;
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            System.out.println("Po kroku IV");

            //krok V - zmiana 4 na 0 lub 1
            for(int i=1; i<in_process.length-1; i++){
                for(int j=1; j<in_process[1].length-1; j++){
                    if(in_process[i][j]==4){
                        waga=0;
                        for(int a=i-1; a<i+1; a++){
                            for(int b=j-1; b<j+1; b++){
                                if(in_process[a][b]==1 || in_process[a][b]==2 || in_process[a][b]==3 || in_process[a][b]==4){
                                    waga+=maska[a-i+1][b-j+1];
                                }
                            }
                        }
                        in_process[i][j]=1;
                        for(int m=0; m<wyciecia.length; m++){
                            if(wyciecia[m]==waga){
                                in_process[i][j]=0;
                                changes=true;
                                break;
                            }
                        }
                    }
                }
            }
            System.out.println("Po kroku V");

            //krok VI - zmiana 2 na 0 lub 1
            for(int i=1; i<in_process.length-1; i++){
                for(int j=0; j<in_process[1].length-1; j++){

                    if(in_process[i][j]==2){
                        waga=0;
                        for(int a=i-1; a<(i+1); a++){
                            for(int b=j-1; b<j+1; b++){
                                if(in_process[a][b]==1 || in_process[a][b]==2 ||in_process[a][b]==3 || in_process[a][b]==4){
                                    waga+=maska[a+1-i][b+1-j];
                                }
                            }
                        }
                        in_process[i][j]=1;
                        for(int m=0; m<wyciecia.length; m++){
                            if(wyciecia[m]==waga){
                                in_process[i][j]=0;
                                changes=true;
                                break;
                            }
                        }
                    }
                }
            }
            System.out.println("Po kroku VI");

            //krok VII - zmiana 3 na - lub 1
            for(int i=1; i<in_process.length-1; i++){
                for(int j=0; j<in_process[1].length-1; j++){
                    if(in_process[i][j]==3){
                        waga=0;
                        for(int a=i-1; a<=(i+1); a++){
                            for(int b=j-1; b<=j+1; b++){
                                if(in_process[a][b]==1 || in_process[a][b]==2 ||in_process[a][b]==3 || in_process[a][b]==4){
                                    waga+=maska[a+1-i][b+1-j];
                                }
                            }
                        }
                        in_process[i][j]=1;
                        for(int m=0; m<wyciecia.length; m++){
                            if(wyciecia[m]==waga){
                                in_process[i][j]=0;
                                changes=true;
                                break;
                            }
                        }
                    }
                }
            }
            System.out.println("Po kroku VII");

        }
        while(changes);
        //this.out = logicalToBinar(in_process);


    }

    int[][] kmmThinning(){
        int[][] biezacy = new int[in.length+1][in[1].length+1];

        int[][] out = new int[in.length][in[1].length];

        biezacy=binarToLogical(in);

    return in;
    }
}
