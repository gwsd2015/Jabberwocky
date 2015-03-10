import java.awt.*;
import java.awt.image.*;

public class ImageTransformations {

    static ImageTool imTool = new ImageTool ();

    public static void main (String[] argv)
    {
        Image image = imTool.readImageFile ("mysteryperson.jpg");
        imTool.showImage (image, "original");

        double[][] lowPassKernel = {
            {0.111, 0.111, 0.111},
            {0.111, 0.111, 0.111},
            {0.111, 0.111, 0.111}
        };
        Image lowPassImage = applyKernel (image, lowPassKernel);
        imTool.showImage (lowPassImage, "low pass");

        double[][] highPassKernel = {
            {-1, -1, -1},
            {-1, 8, -1},
            {-1, -1, -1}
        };
        Image highPassImage = applyKernel (image, highPassKernel);
        imTool.showImage (highPassImage, "high pass");


	//of my own design
	//takes an pixel color value and multiplies it by -1 then adds 150 to it, if its over 255 then it is set to a lower number
	//if it is under 0 it is set to a higher value. Results in a different color scheme then was started with.
	Image image2 = imTool.readImageFile("comp.jpg");
	imTool.showImage(image2, "Before: original");
	Image effectImage=applyEffect(image2);
	imTool.showImage(effectImage,"After: Effect");
    }
    


    // INSERT YOUR CODE HERE.
    //Method to apply the transformations
    public static Image applyKernel(Image image,double[][]kernel)
    {
	//creates a double that is used to temporarily store the average during calculations
	double weight=0;
	//creates an array to store the pixels from an array
	int[][][] pixels = imTool.imageToPixels(image);
	// creates an array to store the pixels of the resultant image
	int[][][] result=new int[pixels.length][pixels[0].length][4];
	//creates a for loop to cycle through rows
	for(int i=0; i<pixels.length; i++)
	    {
		//for loop to cycle through columns
		for(int j=0; j<pixels[0].length; j++)
		    {
			//copies the alpha value for each pixel to the new array.
			result[i][j][0]=pixels[i][j][0];
			//for loop to cycle through red,blue,green for each column
			for(int c=1; c<4; c++)
			    {
				//tests if the square is the first square where it only takes thenumbers from the 3 bordering cells
				if(i==0 && j==0)
				    {
					//calculates the average of the squares
					weight=(pixels[i][j][c]*kernel[1][1]);
					weight=weight+(pixels[i+1][j][c]*kernel[2][1]);
					weight=weight+(pixels[i+1][j+1][c]*kernel[2][2]);
					weight=weight+(pixels[i][j+1][c]*kernel[1][2]);
					//forces the color value to be either 255 or 0 based on whether it is too high or low
					if(weight>=255)
					    {
						weight=255;
					    }
					else if(weight<=0)
					    {
						weight=0;
					    }
					//places the average in the resultant array
					result[i][j][c]=(int)weight;
				    }
				//tests if they are in the last element of the first row
				else if(i==0 && j==pixels[0].length-1)
				    {
					//calculates the average of the squares
					weight=(pixels[i][j][c]*kernel[1][1]);
					weight=weight+(pixels[i][j-1][c]*kernel[1][0]);
					weight=weight+(pixels[i+1][j-1][c]*kernel[2][0]);
					weight=weight+(pixels[i+1][j][c]*kernel[2][1]);
					//forces the color value to be either 255 or 0 based on whether it is too high or low
					if(weight>=255)
					    {
						weight=255;
					    }
					else if(weight<=0)
					    {
						weight=0;
					    }
					//places the average in the resultant array
					result[i][j][c]=(int)weight;
					
				    }
				//tests to see if they are at the first element of the last row
				else if(i==pixels.length-1 && j==0)
				    {
					//calculates the average of the squares
					weight=(pixels[i][j][c]*kernel[1][1]);
					weight=weight+(pixels[i][j+1][c]*kernel[1][2]);
					weight=weight+(pixels[i-1][j+1][c]*kernel[0][2]);
					weight=weight+(pixels[i-1][j][c]*kernel[0][1]);
					//forces the color value to be either 255 or 0 based on whether it is too high or low
					if(weight>=255)
					    {
						weight=255;
					    }
					else if(weight<=0)
					    {
						weight=0;
					    }
					//places the average in the resultant array
					result[i][j][c]=(int)weight;

				    }
				//tests to see if they are in the the last element of the last row
				else if(i==pixels.length-1 && j==pixels[0].length-1)
				    {
					//calculates the average of the squares
					weight=(pixels[i][j][c]*kernel[1][1]);
					weight=weight+(pixels[i][j-1][c]*kernel[1][0]);
					weight=weight+(pixels[i-1][j][c]*kernel[0][1]);
					weight=weight+(pixels[i-1][j-1][c]*kernel[0][0]);
					//forces the color value to be either 255 or 0 based on whether it is too high or low
					if(weight>=255)
					    {
						weight=255;
					    }
					else if(weight<=0)
					    {
						weight=0;
					    }
					//places the average in the resultant array
					result[i][j][c]=(int)weight;
				    }
				//tests to see if they are in one of the bordering elements of the array
				else if(i==0 || j==0 ||j==pixels[0].length-1 || i==pixels.length-1)
				    {
					//sets the average to the pixel value times the middle element of the kernel array
					weight=pixels[i][j][c]*kernel[1][1];
					//forces the color value to be either 255 or 0 based on whether it is too high or low
					if(weight>=255)
					    {
						weight=255;
					    }
					else if(weight<=0)
					    {
						weight=0;
					    }
					//System.out.println("c="+c+" avg="+weight );
					//places the average into the resultant array
					result[i][j][c]=pixels[i][j][c];
				    }
				//tests to see if they are in any of the middle elements of the array
				else if(i!=0 || j!=0)
				    {
					//calculates the average of the squares
					weight=(pixels[i][j][c]*kernel[1][1]);
					weight=weight+(pixels[i-1][j-1][c]*kernel[0][0]);
					weight=weight+(pixels[i-1][j][c]*kernel[0][1]);
					weight=weight+(pixels[i-1][j+1][c]*kernel[0][2]);
					weight=weight+(pixels[i][j-1][c]*kernel[1][0]);
					weight=weight+(pixels[i][j+1][c]*kernel[1][2]);
					weight=weight+(pixels[i+1][j-1][c]*kernel[2][0]);
					weight=weight+(pixels[i+1][j][c]*kernel[2][1]);
					weight=weight+(pixels[i+1][j+1][c]*kernel[2][2]);
					//forces the color value to be either 255 or 0 based on whether it is too high or low
					if(weight>=255)
					    {
						weight=255;
					    }
					else if(weight<=0)
					    {
						weight=0;
					    }
					//places the average in the resultant array
					result[i][j][c]=(int)weight;
				    }
			    }
		    }
	    }
	//returns the resultant array as an image
	return imTool.pixelsToImage(result);
    }
    public static Image applyEffect(Image image)
    {
	//creates an array to store the pixels from an array
	int[][][] pixels = imTool.imageToPixels(image);
	// creates an array to store the pixels of the resultant image
	int[][][] result=new int[pixels.length][pixels[0].length][4];
	int temp=0;
	//creates a for loop to cycle through rows
	for(int i=0; i<pixels.length; i++)
	    {
		//for loop to cycle through columns
		for(int j=0; j<pixels[0].length; j++)
		    {
			//copies the alpha value for each pixel to the new array.
			result[i][j][0]=pixels[i][j][0];
			//for loop to cycle through red,blue,green for each column
			for(int c=1; c<4; c++)
			    {
				//multiplies pixel value by -1
			       temp =pixels[i][j][c]*-1;
			       //then adds 150 to the pixel value
			       temp=temp+150;
			       //fixes out of bound values
			       if(temp<=0)
				   {
				       temp=115;
				   }
			       else if(temp>=255)
				   {
				       temp=200;
				   }
			       result[i][j][c]=temp;
			    }
		    }
	    }
	return imTool.pixelsToImage(result);
    }
}
