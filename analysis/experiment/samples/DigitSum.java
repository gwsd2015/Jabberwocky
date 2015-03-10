import java.util.*;
import java.io.*;

public class DigitSum{

    public static void main(String[] args){
	try{
	    Scanner in = new Scanner(new File("digitSum.txt"));
	    String line = in.nextLine();
	    int j =0;
	    while((line != null) && !line.equals("0")){
		if(j==10) break;
		j++;
		Scanner getNums = new Scanner(line);
		int N = getNums.nextInt();
		int[] nums = new int[N];
		for(int i=0; i<N; i++){
		    nums[i] = getNums.nextInt();
		}
		getSum(N, nums);
		line = in.nextLine();
	    }
	}catch(FileNotFoundException e){
	    e.printStackTrace();
	}
    }

    public static void getSum(int N, int[] nums){
	//TODO: 0's can't be 1st digit (should all go immediately after smallest nonzero digit)
	//sort array of digits in ascending order
	Arrays.sort(nums);
	System.out.println("N = " + N + " nums = " + Arrays.toString(nums));
	//2 numbers
	//1st number (x) has N/2 (truncated) digits
	int xDigits = N/2;
	int x = 0;
	//2nd number (y) has N - N/2 digits
	int yDigits = N - (N/2);
	int y = 0;

	//1st digit of y = nums[0]
	//1st digit of x = nums[1]
	//2nd digit of y = nums[2]
	//....
	//y = nums[0] * 10^(yDigits-1) + nums[2] * 10^(yDigits-2) + nums[4]...
	int yPower = yDigits - 1;
	for(int i=0; i<N; i+=2){
	    y += nums[i] * Math.pow(10, yPower);
	    yPower--;
	}

	//x = nums[1] * 10^(xDigits-1) + nums[2] * 10^(xDigits-2) + nums[3]...
	int xPower = xDigits - 1;
	for(int i=1; i<N; i+=2){
	    x += nums[i] * Math.pow(10, xPower);
	    xPower--;
	}
	System.out.println(x + "+" + y + " = " + (x+y));
    }
}
