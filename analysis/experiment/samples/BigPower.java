
import java.math.*;

public class BigPower {

    static int wayOne=0;
    static int wayTwo=0;

    public static void main (String[] argv)
    {
        BigInteger X = new BigInteger ("3");
        BigInteger Y = new BigInteger ("2");
        BigInteger Z = power (X, Y);
        BigInteger Z2 = power2 (X, Y);
        System.out.println (X + "^" + Y + " = " + Z + " Z2=" + Z2);
	System.out.println("Total operations for power()="+wayOne+" Total operations for power2()="+wayTwo);
	
	wayOne=0;
	wayTwo=0;
        X = new BigInteger ("2");
        Y = new BigInteger ("8");
        Z = power (X, Y);
        Z2 = power2 (X, Y);
        System.out.println (X + "^" + Y + " = " + Z + " Z2=" + Z2);
	System.out.println("Total operations for power()="+wayOne+" Total operations for power2()="+wayTwo);

	wayOne=0;
	wayTwo=0;
        X = new BigInteger ("2");
        Y = new BigInteger ("1000");
        Z = power (X, Y);
        Z2 = power2 (X, Y);
        System.out.println (X + "^" + Y + " = " + Z + "\n\n Z2=" + Z2);
	System.out.println("Total operations for power()="+wayOne+" Total operations for power2()="+wayTwo);
    }


    static BigInteger zero = new BigInteger ("0");
    static BigInteger one = new BigInteger ("1");

    static BigInteger power (BigInteger A, BigInteger B)
    {
        if ( B.equals(zero) ) {
            return new BigInteger ("1");
        }
        BigInteger BMinus1 = B.subtract (one);
	wayOne++;
        BigInteger temp = power (A, BMinus1);
        BigInteger P = A.multiply (temp);
	wayOne++;
        return P;
    }
    //new constant number two
    static BigInteger two =new BigInteger("2");

    //method to calculate power slightly faster, the wayTwo variable is increased after every operation to keep track
    static BigInteger power2(BigInteger A, BigInteger B)
    {
	//drop out case
	if(B.equals(zero))
	    {
		return new BigInteger("1");
	    }
	//checks if the power is even by modding B by two and checking if the result is equal to zero
	if((B.mod(two)).equals(zero))
	    {
		wayTwo++;
		//divides the B by the constant two
		BigInteger nDivide2 = B.divide(two);
		wayTwo++;
		//squares A by multiplying against itself
		BigInteger square= A.multiply(A);
		wayTwo++;
		//recursively calls itself to keep going
		BigInteger temp=power2(square,nDivide2);
		//returns the result
		return temp;
	    }
	//if its not even then it is odd
	else
	    {
		wayTwo++;
		//subtracts the constant one from b
	        BigInteger Bminus1 = B.subtract(one);
		wayTwo++;
		//divides the previous subtraction(Bminus1) by the constant two
		BigInteger expone = Bminus1.divide(two);
		wayTwo++;
		//squares A  by multiplying against itself
		BigInteger square2=A.multiply(A);
		wayTwo++;
		//recursivley calls itself to keep going
		BigInteger temp2=power2(square2,expone);
		//multiplies temp2 by A
		BigInteger result=temp2.multiply(A);
		wayTwo++;
		//returns the result
		return result;
	    }
    }

}
