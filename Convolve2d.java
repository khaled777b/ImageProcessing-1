package com.michalik;
import java.util.Arrays;

public class Convolve2d{


	public static int[][] gaussianBlur(int[][] in){
	int[][] tab = {
	{1,2,1},
	{2,4,2},
	{1,2,1}
	};
	
		int[][] out = new int[in.length][in[1].length];
		out = convolution(in, tab);
	
	return out;
	}
	public static int[][] meanFilter(int[][] in){
		int[][] tab = {
				{1,1,1},
				{1,1,1},
				{1,1,1}
		};
		int[][] out = new int[in.length][in[1].length];
		out = convolution(in, tab);
		return out;
	}
	
	public static int[][] sharpen(int[][] in){
	
	int[][] tab = {
	{0,-2,0},
	{-2,11,-2},
	{0,-2,0}
	};
	
	int[][] out = new int[in.length][in[1].length];
		out = convolution(in, tab);
	
	return out;
	}

	public static int[][] laplace(int[][] in){
		int[][] tab = {
		{-1, -1, -1},
		{-1, 8, -1},
		{-1, -1, -1}
		};
		
		
		int[][] out = new int[in.length][in[1].length];
		out = convolution(in, tab);
		
	return out;	
	}

	public static int[][] prewitt(int[][] in){
	
		int[][] tab1 = {
		{1, 0, -1},
		{1, 0, -1},
		{1, 0, -1}
		};
		
		int[][] tab2 = {
		{-1, -1, -1},
		{0, 0, 0},
		{1, 1, 1}
		};
		
		int[][] out1 = convolution(in, tab1);
		int[][] out2 = convolution(in, tab2);
		int[][] out = new int[in.length][in[1].length];
			
			for(int i=0; i<out1.length; i++){
				for(int j=0; j<out1[1].length; j++){
					out[i][j]=out1[i][j]+out2[i][j];
				}
			}		
	
	return out;
	}

	public static int[][] robertsCross(int[][] in){
	
		int[][] tab1 = {
		{0,0,1},
		{0,0,0},
		{-1,0,0}
		};

			
		int[][] tab2 = {
		{1,0,0},
		{0,0,0},
		{0,0,-1}
		};	
				
		int[][] out1 = convolution(in, tab1);
		int[][] out2 = convolution(in, tab2);
		int[][] out = new int[in.length][in[1].length];
			
			for(int i=0; i<out1.length; i++){
				for(int j=0; j<out1[1].length; j++){
					out[i][j]=out1[i][j]+out2[i][j];
				}
			}
		
	return out;
	}

	public static int[][] sobel(int[][] in){

		int[][] tab1 = {
		{-1, 0, 1},
		{-2, 0, 2},
		{-1, 0, 1}
		};
	
		int[][] tab2 = {
		{1,2,1},
		{0,0,0},
		{-1,-2,-1}
		};

		int[][] out1 = convolution(in, tab1);
		int[][] out2 = convolution(in, tab2);

		int[][] out = sqrtImage(out1, out2);

	return out;

	}

	public static int[][] sqrtImage(int[][] in1, int[][] in2){
		int[][] out = new int[in1.length][in1[1].length];
		
		for(int i=0; i<out.length; i++){
			for(int j=0; j<out[1].length; j++){
				double px = Math.sqrt(in1[i][j]*in1[i][j]+in2[i][j]*in2[i][j]);
				out[i][j]=(int)px;
			}
		}
		return out;
	}

	public static int[][] convolution(int[][] in, int[][] maska){
		/*
		Najpierw na skali szarości - jeden kanał
		
		Nowa wartośc punktu o współrzędnych (i,j) będzie się wyrażać średnią ważoną maski i obrazu
		*/
		int[][] out = new int[in.length][in[1].length];
		int maskSum=0;
		
		for(int ii=0; ii<maska.length; ii++){
		
			for(int jj=0; jj<maska.length; jj++){
				maskSum=maskSum+maska[ii][jj];
			}
		
		}
		if(maskSum<0){
			maskSum = Math.abs(maskSum);
		}
		if(maskSum == 0){
			maskSum = 1;
		}
		
		
		for(int i=1; i<(in.length-1); i++){
		
			for(int j=1; j<(in[1].length-1); j++){
			
				for(int ii=1; ii<2; ii++){
				
					for(int jj=1; jj<2; jj++){
					
						//MNOŻĘ ELEMENT PRZEZ ELEMENT MU ODPOWIADAJĄCY
						int x1=in[i][j];
						int px1=x1*maska[ii][jj];
						
						int x2=in[i-1][j];
						int px2=x2*maska[ii-1][jj];
						
						int x3=in[i-1][j-1];
						int px3=x3*maska[ii-1][jj-1];
						
						int x4=in[i][j-1];
						int px4=x4*maska[ii][jj-1];
						
						int x5=in[i+1][j];
						int px5=x5*maska[ii+1][jj];
						
						int x6=in[i+1][j+1];
						int px6=x6*maska[ii+1][jj+1];
						
						int x7=in[i][j+1];
						int px7=x7*maska[ii][jj+1];
						
						int x8=in[i+1][j-1];
						int px8=x8*maska[ii+1][jj-1];
						
						int x9=in[i-1][j+1];
						int px9=x9*maska[ii-1][jj+1];
						
						int pxS=(px1+px2+px3+px4+px5+px6+px7+px8+px9)/maskSum;
						
						if(pxS<0){
							pxS=0;
						}
						
						if(pxS>255){
							pxS=255;
						}
						
						
		
						//System.out.println(+pxS);
						
						//int px=RGB.toRGB(pxS,pxS,pxS); //przesunięcie bitowe
					
						out[i][j]=pxS;
						//out.setRGB(i,j,px);
	
						
						
						//DZIELĘ PRZEZ SUMĘ ELEMENTÓW MASKI, JEŚLI JEST RÓŻNA OD ZERA 
					
						}
				
					}
			
				}
			
			}
	
		return out;
	}
}	
		
