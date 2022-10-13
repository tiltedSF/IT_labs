import java.util.Arrays;

public class task2 {
    public static String repeat(String str, int n){
        String res = "";
        for(int i = 0; i < str.length(); i++){
            for(int j = 0; j < n; j++){
                res += str.charAt(i);
            }
        }
        return res;
    }

    public static int differenceMaxMin(int[] arr){
        return (Arrays.stream(arr).max().getAsInt() - Arrays.stream(arr).min().getAsInt());
    }

    public static boolean isAvgWhole(int[] arr){
        int s = 0;
        for(int i: arr){
            s += i;
        }

        return s % arr.length == 0;

    }

    public static int[] cumulativeSum(int[] arr){
        int[] res = new int[arr.length];
        res[0] = arr[0];

        for(int i = 1; i < arr.length; i++){
            res[i] = arr[i] + res[i-1];
        }
        return res;
    }

    public static int getDecimalPlaces(String str){
        for(int i = str.length() - 1; i > 0; i--){
            if (str.charAt(i) == '.'){
                return str.length() - 1 - i ;
            }
        }
        return 0;
    }

    public static int Fibonacci(int n){
        if (n == 1){
            return 1;
        }
        if (n == 2){
            return 2;
        }
        return Fibonacci(n-2) + Fibonacci(n-1);
    }

    public static boolean isValid(String str){
        if (str.length() > 5){
            return false;
        }
        for(char i = 0; i < str.length(); i++){
            if ((int)(str.charAt(i)) < 48 || (int)(str.charAt(i)) > 57){
                return false;
            }
        }
        return true;
    }

    public static boolean isStrangePair(String s1, String s2){
        if (s1.isEmpty() && s2.isEmpty()) {
            return true;
        }
        if ((s1.charAt(0) == s2.charAt(s2.length() - 1)) && (s2.charAt(0) == s1.charAt(s1.length() - 1))){
            return true;
        }
        return false;
    }

    public static boolean isPrefix(String word, String prefix){
        for(int i = 0; i < prefix.length() - 1; i++){
            if (word.charAt(i) != prefix.charAt(i)){
                return false;
            }
        }

        return true;
    }

    public static boolean isSuffix(String word, String suffix){
        for(int i = suffix.length() - 1; i > 0; i--) {
            if (word.charAt(word.length() - suffix.length() + i) != suffix.charAt(i)){
                return false;
            }
        }

        return true;
    }

    public static int boxSeq(int n){
        int res = 0;

        for(int i = 1; i <= n; i++){
            if(i%2 == 1){
                res += 3;
            }
            else{
                res -= 1;
            }
        }

        return res;
    }

    public static void main(String[] args) {
        System.out.println(repeat("mice", 5));
        System.out.println(differenceMaxMin(new int[] {10, 4, 1, 4, -10, -50, 32, 21}));
        System.out.println(isAvgWhole(new int[] {1, 3}));
        int [] cumulativeSumArray = cumulativeSum(new int[] {1, 2, 3});
        for(int i= 0; i < cumulativeSumArray.length; i++){
            System.out.println(i + ":" + cumulativeSumArray[i]);
        }
        System.out.println(getDecimalPlaces("43.20"));
        System.out.println(Fibonacci(3));
        System.out.println(isValid("732 32"));
        System.out.println(isStrangePair("ratio", "orator"));
        System.out.println(isPrefix("retrospect", "sub-"));
        System.out.println(isSuffix("vocation", "-logy"));
        System.out.println(boxSeq(5));
    }
}