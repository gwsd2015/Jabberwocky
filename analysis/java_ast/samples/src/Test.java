public class Test{


    public static void main(String[] args)
    {
        System.out.println("Hello ");
        System.out.println("World");
        int summer = sum(5,2);

        System.out.println("Sum is "+summer);


    }


    public static int sum(int a, int b){
    
        if(a>b)
        {
            System.out.println(a+" is bigger! than "+b);
        }
        else System.out.println(b+" is bigger! than "+a);

        return a+b;

    }


}
