package edu.sber.other;

public class ExceprionTest {
    int test() {
        try {
            return 0;

        } finally {
            try {
                return 4;
            } finally {
                return 2;
            }
          //  return 1;
        }
    }

    public static void main(String[] args) {
        ExceprionTest et = new ExceprionTest();
       // System.out.println(et.test());
        test(7654346799L);
    }
    static void test(long n){
        System.out.println("1");
    }
    static void test(int i){
        System.out.println("2");}
}
