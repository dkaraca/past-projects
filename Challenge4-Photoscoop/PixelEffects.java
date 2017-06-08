//UIUC CS125 SPRING 2014 MP. File: PixelEffects.java, CS125 Project: Challenge4-Photoscoop, Version: 2014-02-28T16:51:13-0600.813671000

/* A class to implement the various pixel effects.
 *
 * Todo: Put your netid (i.e. username) in the line below
 * 
 * @author dkaraca2
 */
public class PixelEffects {

	/** Copies the source image to a new 2D integer image */
	public static int[][] copy(int[][] source) {
		int srcW = source.length;
		int srcH = source[0].length;
		int tgtW = srcW;
		int tgtH = srcH;
		int [][] tgt = new int [tgtW][tgtH];
		for(int tgtX = 0; tgtX<tgtW; tgtX++){
			for(int tgtY = 0; tgtY<tgtH; tgtY++){
				int srcY = tgtY;
				int srcX = tgtX;
				tgt[tgtX][tgtY] = source[srcX][srcY];
			}
		}
		
		
		
		// Create a NEW 2D integer array and copy the colors across
		// See redeye code below
		return tgt; // Fix Me
	}
	/**
	 * Resize the array image to the new width and height
	 * You are going to need to figure out how to map between a pixel
	 * in the destination image to a pixel in the source image
	 * @param source
	 * @param newWidth
	 * @param newHeight
	 * @return
	 */
	public static int[][] resize(int[][] source, int newWidth, int newHeight) {
		
		int srcW = source.length;
		int srcH = source[0].length;
		int tgtW = newWidth;
		int tgtH = newHeight;
		int [][] tgt = new int [tgtW][tgtH];
		for(int tgtX = 0; tgtX<=tgtW-1; tgtX++){
			for(int tgtY = 0; tgtY<=tgtH-1; tgtY++){
				int srcY = (int) ((tgtY/(double)tgtH)*srcH);
				int srcX = (int) ((tgtX/(double)tgtW)*srcW);
				tgt[tgtX][tgtY] = source[srcX][srcY];
			}
		}
		
		
		return tgt; // Fix Me
		// Hints: Use two nested for loops between 0... newWidth-1 and 0.. newHeight-1 inclusive.
		// Hint: You can just use relative proportion to calculate the x (or y coordinate)  in the original image.
		// For example if you're setting a pixel halfway across the image, you should be reading half way across the original image too.
	}

	/**
	 * Half the size of the image. This method should be just one line! Just
	 * delegate the work to resize()!
	 */
	public static int[][] half(int[][] source) {
		
		int srcW = source.length;
		int srcH = source[0].length;
		int tgtW = srcW/2;
		int tgtH = srcH/2;
		int [][] tgt = new int [tgtW][tgtH];
		for(int tgtX = 0; tgtX<tgtW; tgtX++){
			for(int tgtY = 0; tgtY<tgtH; tgtY++){
				int srcY = (int) ((tgtY/(double)tgtH)*srcH);
				int srcX = (int) ((tgtX/(double)tgtW)*srcW);
				tgt[tgtX][tgtY] = source[srcX][srcY];
			}
		}
		
		
		return tgt; // Fix Me
	}
	
	/**
	 * Create a new image array that is the same dimesions of the reference
	 * array. The array may be larger or smaller than the source. Hint -
	 * this methods should be just one line - delegate the work to resize()!
	 * 
	 * @param source
	 *            the source image
	 * @param reference
	 * @return the resized image
	 */
	public static int[][] resize(int[][] source, int[][] reference) {
		
		int srcW = reference.length;
		int srcH = reference[0].length;
		int [][] tgt = PixelEffects.resize(source, srcW, srcH);
			
		
		
		return tgt; // Fix Me
	}

	/** Flip the image vertically */
	public static int[][] flip(int[][] source) {
		int srcW = source.length;
		int srcH = source[0].length;
		int tgtW = srcW;
		int tgtH = srcH;
		int [][] tgt = new int [tgtW][tgtH];
		for(int tgtX = 0; tgtX<tgtW; tgtX++){
			for(int tgtY = 0; tgtY<tgtH; tgtY++){
				int srcY = tgtH - 1 - tgtY;
				int srcX = tgtX;
				tgt[tgtX][tgtY] = source[srcX][srcY];
			}
		}
		
		return tgt;// Fix Me
	}

	/** Reverse the image horizontally */
	public static int[][] mirror(int[][] source) {
		
		int srcW = source.length;
		int srcH = source[0].length;
		int tgtW = srcW;
		int tgtH = srcH;
		int [][] tgt = new int [tgtW][tgtH];
		for(int tgtX = 0; tgtX<tgtW; tgtX++){
			for(int tgtY = 0; tgtY<tgtH; tgtY++){
				int srcY = tgtY;
				int srcX = tgtW - tgtX - 1;
				tgt[tgtX][tgtY] = source[srcX][srcY];
			}
		}
		
		
		return tgt;// Fix Me
	}

	/** Rotate the image */
	public static int[][] rotateLeft(int[][] source) {
		
		int srcW = source.length;
		int srcH = source[0].length;
		int tgtW = srcH;
		int tgtH = srcW;
		int [][] tgt = new int [tgtW][tgtH];
		for(int tgtX = 0; tgtX<tgtW; tgtX++){
			for(int tgtY = 0; tgtY<tgtH; tgtY++){
				int srcY = tgtX;
				int srcX = srcW - tgtY - 1;
				tgt[tgtX][tgtY] = source[srcX][srcY];
			}
		}
		
		return tgt;
	}

	/** Merge the red,blue,green components from two images */
	public static int[][] merge(int[][] sourceA, int[][] sourceB) {
		
		if(sourceA.length != sourceB.length || sourceA[0].length != sourceB[0].length){
			PixelEffects.resize(sourceA, sourceB.length, sourceB[0].length);
		}
		
		int width = sourceB.length, height = sourceB[0].length;
		int [][] result = new int[width][height];
		for(int i = 0; i < width; i++){
			for(int j = 0; j< height; j++){
				int rgbA = sourceA[i][j];
				int rgbB = sourceB[i][j];
				int redA = RGBUtilities.toRed(rgbA);
				int redB = RGBUtilities.toRed(rgbB);
				int greenA = RGBUtilities.toGreen(rgbA);
				int greenB = RGBUtilities.toGreen(rgbB);
				int blueA = RGBUtilities.toBlue(rgbA);
				int blueB = RGBUtilities.toBlue(rgbB);
				result[i][j] = RGBUtilities.toRGB((redA+redB)/2, (greenA+greenB)/2, (blueA+blueB)/2);
				
			}
		}
		
		
		
		// The output should be the same size as the input. Scale (x,y) values
		// when reading the background
		// (e.g. so the far right pixel of the source is merged with the
		// far-right pixel ofthe background).list
		return result;
	}

	/**
	 * Replace the green areas of the foreground image with parts of the back
	 * image
	 */
	public static int[][] chromaKey(int[][] foreImage, int[][] backImage) {
		
		if(foreImage.length != backImage.length || foreImage[0].length != backImage[0].length){
			PixelEffects.resize(foreImage, backImage.length, backImage[0].length);}
		
		int width = backImage.length, height = backImage[0].length;
		int [][] tmp = new int[width][height];
		
			for(int i = 0; i< backImage.length; i++){
				for(int j = 0; j< backImage[0].length; j++){
					tmp[i][j] = foreImage[i][j];
					
					int rgb = tmp[i][j];
					int green = RGBUtilities.toGreen(rgb);
					
				    if(green>0){
				    	tmp[i][j] = backImage[i][j];
				    }
				}
			}
		
		
		// If the image has a different size than the background use the
		// resize() method
		// create an image the same as the background size.
		return tmp;
	}

	/** Removes "redeye" caused by a camera flash. sourceB is not used */
	public static int[][] redeye(int[][] source, int[][] sourceB) {

		int width = source.length, height = source[0].length;
		int[][] result = new int[width][height];
		for (int i = 0; i < width; i++)
			for (int j = 0; j < height; j++) {
				int rgb = source[i][j];
				int red = RGBUtilities.toRed(rgb);
				int green = RGBUtilities.toGreen(rgb);
				int blue = RGBUtilities.toBlue(rgb);
				if (red > 4 * Math.max(green, blue) && red > 64)
					red = green = blue = 0;
				result[i][j] = RGBUtilities.toRGB(red, green, blue);
			}

		return result;
	}

	/* Upto you! do something fun to the image */
	public static int[][] funky(int[][] source, int[][] sourceB) {
		
		int srcW = source.length;
		int srcH = source[0].length;
		int tgtW = srcH;
		int tgtH = srcW;
		int [][] tgt = new int [tgtW][tgtH];
		for(int tgtX = 0; tgtX<tgtW; tgtX++){
			for(int tgtY = 0; tgtY<tgtH; tgtY++){
				int srcY = tgtW - tgtX - 1;
				int srcX = tgtY;
				tgt[tgtX][tgtY] = source[srcX][srcY];
			}
		}
		
		
		// You need to invent your own image effect
		// Minimum boring requirements to pass autograder:
		
		// Does not ask for any user input and returns a new 2D array
		// Todo: remove this return null
		return tgt;
	}
}
