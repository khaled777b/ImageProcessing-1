package com.michalik;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class ImProcess extends JFrame{
/*
	voidy do:
	-zapisywania do pliku
	-wyświetlenie JFrame
	
	int[][]:
	-przepisanie BufferedImage do tablicy intów
	
	int[][], double[][]
	-zamiana 0:255 na 0:1
	-i odwrotnie
	
	
zawrzeć RGB Szczepańskiego
*/

	/*
	*save array RGB image to jpg
	*/

	public static void saveAsFile(int[][] imR, int[][] imG, int[][] imB, String name){
		
		try{
			BufferedImage out = new BufferedImage(imR.length, imR[1].length, 1);
			
			for(int i=0; i<imR.length; i++){
				for(int j=0; j<imR[1].length; j++){
					
					int px=RGB.toRGB(imR[i][j],imG[i][j],imB[i][j]);
        	        		out.setRGB(i,j,px);
        	        		
				}
			}
			ImageIO.write(out, "jpg",new File(name));
		}
		
		catch(IOException e){
			System.out.println("Failed to save as image");
		}
	}
	
	/*
	*save grayscale image as jpg
	*/
	public static void saveAsFile(int[][] imGrey, String name){
		
		try{
			BufferedImage out = new BufferedImage(imGrey.length, imGrey[1].length, 1);
			
			for(int i=0; i<imGrey.length; i++){
				for(int j=0; j<imGrey[1].length; j++){
					
					int px=RGB.toRGB(imGrey[i][j],imGrey[i][j],imGrey[i][j]);
        	        		out.setRGB(i,j,px);
        	        		
				}
			}
			ImageIO.write(out, "jpg",new File(name));
		}
		
		catch(IOException e){
			System.out.println("Failed to save as image");
		}
	}
	public static void saveAsFile(int[][] imGrey, String name, Boolean compress){
	if(compress){
		saveAsFile(imGrey, name);
	}
	else {
		try {
			BufferedImage out = new BufferedImage(imGrey.length, imGrey[1].length, 1);

			for (int i = 0; i < imGrey.length; i++) {
				for (int j = 0; j < imGrey[1].length; j++) {

					int px = RGB.toRGB(imGrey[i][j], imGrey[i][j], imGrey[i][j]);
					out.setRGB(i, j, px);

				}
			}
			ImageIO.write(out, "bmp", new File(name));
		} catch (IOException e) {
			System.out.println("Failed to save as image");
		}
	}
	}

	public static void showImage(BufferedImage img){
	
		//BufferedImage img = ImageIO.read(new File(IMG_PATH));
	         ImageIcon icon = new ImageIcon(img);
	         JLabel label = new JLabel(icon);
	         JOptionPane.showMessageDialog(null, label);

	}

	/*
	*convert image from [0;255] integer values to [0;1] real values
	*/
	
	public static double[][] intToDouble(int[][] in){

		double[][] out = new double[in.length][in[1].length];
	
		for(int i=0; i<in.length; i++){
			for(int j=0; j<in[1].length; j++){
				out[i][j]=(double)in[i][j]/255;
			}
		}
	
		return out;
	}
	
	/*
	*convert image from [0;1] real values to [0;255] integer values
	*/
	
	public static int[][] doubleToInt(double[][] in){

		int[][] out = new int[in.length][in[1].length];
	
		for(int i=0; i<in.length; i++){
			for(int j=0; j<in[1].length; j++){
				out[i][j]=(int)(in[i][j]*255);
			}
		}
	
		return out;	
	}
	
	/*
	*Zapisuję wybrany channel int ch do tablicy int[][]
	*domyślnie (inna liczba niż 1-3) - zapisuję kanał czerwony
	*save channel specified by "int ch" to int[][] array
	*default: save Red channel
	*/

	
	public static int[][] channelToArray(BufferedImage in, int ch){
		
		int[][] out = new int[in.getWidth()][in.getHeight()];
	
		switch(ch) {
			case 1: {
				for (int i = 0; i < out.length; i++) {
					for (int j = 0; j < out[1].length; j++) {
						out[i][j] = RGB.getR(in.getRGB(i, j));
					}
				}
				return out;

			}

			case 2: {
				for (int i = 0; i < out.length; i++) {
					for (int j = 0; j < out[1].length; j++) {
						out[i][j] = RGB.getG(in.getRGB(i, j));
					}
				}
				return out;

			}

			case 3: {
				for (int i = 0; i < out.length; i++) {
					for (int j = 0; j < out[1].length; j++) {
						out[i][j] = RGB.getB(in.getRGB(i, j));
					}
				}
				return out;

			}
			default: {
				for (int i = 0; i < out.length; i++) {
					for (int j = 0; j < out[1].length; j++) {
						out[i][j] = RGB.getR(in.getRGB(i, j));
					}
				}
				return out;

			}
		}
	}
	public static BufferedImage arrayToBF(int[][] imR, int[][] imG, int[][] imB){

			BufferedImage out = new BufferedImage(imR.length, imR[1].length, 1);
			
			for(int i=0; i<imR.length; i++){
				for(int j=0; j<imR[1].length; j++){
					
					int px=RGB.toRGB(imR[i][j],imG[i][j],imB[i][j]);
        	        		out.setRGB(i,j,px);
        	        		
				}
			}
			return out;		
		}
	public static BufferedImage arrayToBF(int[][] imGrey){
			
			BufferedImage out = new BufferedImage(imGrey.length, imGrey[1].length, 1);
			
			for(int i=0; i<imGrey.length; i++){
				for(int j=0; j<imGrey[1].length; j++){
					
					int px=RGB.toRGB(imGrey[i][j],imGrey[i][j],imGrey[i][j]);
        	        		out.setRGB(i,j,px);
        	        		
				}
			}
			return out;		
		}

	/*
	*Zawrzeć metody do rysowania kółka na obrazie o zadanym promieniu
	 */

	public static int[][] drawCircle(int[][] in, int i, int j, int r){
		double alpha = 0;
		while(alpha<2*Math.PI){
			int x = (int)(i+r*Math.cos(alpha));
			int y = (int)(j+r*Math.sin(alpha));
			in[x][y]=255;
			alpha+=(1/((double)r));
		}
		return in;
	}
	public static int[][] addImages(int[][] in1, int[][] in2) {
		//działamy wtedy i tylko wtedy, gdy obrazy sa takiej same wielkości
		int[][] out = new int[in1.length][in1[1].length];
		if (in1.length != in2.length || in1[1].length != in2[1].length) {
			System.out.println("Ktoś tu próbuje zrobić coś niedobrego...");
		} else {


			for (int i = 0; i < in1.length; i++) {
				for (int j = 0; j < in1[1].length; j++) {
					out[i][j] = in1[i][j] + in2[i][j];
					if (out[i][j] > 255) {
						out[i][j] = 255;
					}
				}
			}
		}
		return out;
	}
		public static int[][] addImages(int[][] in1, int[][] in2, boolean normalise){
			if(normalise){
				System.out.println("Use method without 'normalise'");

			}
			//działamy wtedy i tylko wtedy, gdy obrazy sa takiej same wielkości
			int[][] out = new int[in1.length][in1[1].length];
			if(in1.length != in2.length || in1[1].length!=in2[1].length){
				System.out.println("Something, something out of bounds...");
			}
			else{


				for(int i=0; i<in1.length; i++){
					for(int j=0; j<in1[1].length; j++){
						out[i][j]=in1[i][j]+in2[i][j];
					}
				}
			}
			return out;
		}
	public static int[][] substractImage(int[][] in1, int[][] in2){
		int[][] out = new int[in1.length][in1[1].length];
		if(in1.length != in2.length || in1[1].length!=in2[1].length){
			System.out.println("Something, something out of bounds...");
		}
		else{
			for(int i=0; i<in1.length; i++){
				for(int j=0; j<in1[1].length; j++){
					out[i][j]=in1[i][j]-in2[i][j];
					if(out[i][j]<0){
						out[i][j]=0;
					}
				}
			}
		}
		return out;
	}
}
