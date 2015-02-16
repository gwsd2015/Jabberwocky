// default package (CtPackage.TOP_LEVEL_PACKAGE_NAME in Spoon= unnamed package)



public class Test {
    public static void main(java.lang.String[] args) {
        java.lang.System.out.println("Hello ");
        java.lang.System.out.println("World");
        int summer = Test.sum(5, 2);
        java.lang.System.out.println(("Sum is " + summer));
    }

    public static int sum(int a, int b) {
        if (a > b) {
            java.lang.System.out.println(((a + " is bigger! than ") + b));
        } else
            java.lang.System.out.println(((b + " is bigger! than ") + a));
        
        return a + b;
    }
}

