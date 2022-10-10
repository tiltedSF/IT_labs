public class task1 {

    public static int remainder(int num1, int num2) {
        return num1 % num2;
    }

    public static int triArea(int num1, int num2) {
        return (num1 * num2 / 2);
    }

    public static int animals(int chickens, int cows, int pigs) {
        return (chickens*2 + cows*4 + pigs*4);
    }

    public static boolean profitableGamble(double prob, int prize, int pay) {
        return (prob*prize >pay);
    }

    public static String operation(int N, int a, int b) {
        String str = "";

        if     (a+b == N){str = "added";}
        else if(a-b == N){str = "subtracted";}
        else if(a*b == N){str = "multiplication";}
        else if(a/b == N){str = "division";}
        else             {str = "none";}

        return str;
    }

    public static int ctoa(char ch){
        int ASCII = ch;
        return ASCII;
    }

    public static int addUpTo(int n) {
        int s = 0;

        for(int i = 1; i<=n; i++){
            s += i;
        }

        return s;
    }

    public static int nextEdge(int a, int b) {
        return (a+b-1);
    }
    public static int sumOfCubes(int [] arr) {
        int num1 = arr[0];
        int num2 = arr[1];
        int num3 = arr[2];

        return (num1*num1*num1 + num2*num2*num2 + num3*num3*num3);
    }

    public static boolean abcmath (int num1, int num2, int num3) {

        for(int i = 1; i <= num2; i++) {
            num1 *= 2;
        }

        return (num1%num3 == 0);
    }

    public static void main(String[] args) {
        System.out.println(remainder(1, 3));
        System.out.println(triArea(3,2));
        System.out.println(animals(2,3,5));
        System.out.println(profitableGamble(0.2, 50, 9));
        System.out.println(operation(24, 15, 9));
        System.out.println(ctoa('A'));
        System.out.println(addUpTo(3));
        System.out.println(nextEdge(8, 10));
        System.out.println(sumOfCubes(new int[]  {1, 5, 9}));
        System.out.println(abcmath(42, 5, 10));
    }
}
