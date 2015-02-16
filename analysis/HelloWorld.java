import java.util.*;
//import java.tools.*;

public class HelloWorld{


	public static void main(String[] args)
	{
		System.out.println("Hello World");
		int x = 5;
		int y = 7;
		int z = adder(x,y);
		System.out.println("The value of z is "+z);
        String hello = "Hello World";
        for(int i = 0 ; i < hello.length(); i ++ )
        {
            System.out.println("Hello");
        }
        
        
        if( 1 == 2 ){
            System.out.println("Cool");
        }
        
        if ( x == y )
        {
            x++;
        }
        
        while (x<1)
        {
        	System.out.println(2);
        }

        //while(x>1){}


        while(x<1)
        {
        	x--;
        }

        do{
        	x++;

        }while(False)
        
        
        for(int i = 0; i< 5; i++)
        {
            x++;
        }

	}



	public static int adder(int a, int b)
	{
		int c;
		c = a +b;
		c = sub((a+b)+(a+b),c);
        
        if( true)
        {
            c++;
        }
        
		return c;
	}


	public static int sub(int a, int b)
	{
		int c;
		c = b-a;
		return c;
	}

}