package com.michalik;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
	// example use of ImProcess
        try{
            BufferedImage in = ImageIO.read(new File(args[0])); //read image path
            int[][] imR = ImProcess.channelToArray(in, 1); //separate channels
            int[][] imG = ImProcess.channelToArray(in, 2);
            int[][] imB = ImProcess.channelToArray(in, 3);

            ImProcess.showImage(ImProcess.arrayToBF(imB)); //show image (greyscale) in window
            ImProcess.showImage(ImProcess.arrayToBF(imB, imR, imG)); //mix channels and show in window
            //ImProcess.saveAsFile(imR, "example.jpg"); //save as jpg

            //convolution

            //show edges using laplace filter

            ImProcess.showImage(ImProcess.arrayToBF(Convolve2d.laplace(imR)));

            //process image using median filtering 7x7 (you can use minimal and maximal also)
            ImProcess.showImage(ImProcess.arrayToBF(StatFiltr.median(imG, 7)));


            //add images
            ImProcess.showImage(ImProcess.arrayToBF(ImProcess.addImages(imR, imB))); //with normalisation to [0;255]

            //substract images
            ImProcess.showImage(ImProcess.arrayToBF(ImProcess.substractImage(imR, imG)));


            //adaptive binar

            Binaryzacja b = new Binaryzacja(imG);
            b.binarSPmethod(23); //for optical character recognition
            ImProcess.showImage(ImProcess.arrayToBF(b.out));

        }
        catch (IOException e){
            System.out.println(e);
        }
    }
}
