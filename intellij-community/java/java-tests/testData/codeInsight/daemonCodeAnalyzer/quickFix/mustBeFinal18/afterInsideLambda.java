// "Copy 'a' to temp final variable" "true"
class Test {
    public void test() {
        int a = 1;
        a = 2;
        final int finalA = a;
        Runnable r = () -> {
            System.out.println(finalA);
        };
    }
}
