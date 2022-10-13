import java.util.HashSet;
import java.util.Set;

public class task3 {

    public static int solutions(double a, double b, double c){
        double d = b*b - 4*a*c;
        if     (d < 0) {return 0;}
        else if(d == 0){return 1;}
        else  /*d > 0*/{return 2;}
    }

    public static int findZip(String str){
        int idxZip = str.indexOf("zip");
        String supstr = str.substring(idxZip + 3);

        if(!supstr.contains("zip")){
            return -1;
        }

        return supstr.indexOf("zip") + idxZip + 3;
    }

    public static boolean checkPerfect(int n){
        int res = 1;

        for(int i = 2;i < Math.sqrt(n); i++){
            if (n % i == 0){
                res += i + n/i;
            }
        }

        return n == res;
    }

    public static String flipEndChars(String str){
        if(str.length() < 2){
            return "несовместимо.";
        }
        if(str.charAt(0) == str.charAt(str.length()-1)) {
            return "два-это пара.";
        }
        return str.charAt(str.length() - 1) + str.substring(1, str.length() - 1) + str.charAt(0);
    }

    public static boolean isValidHexCode(String str){
        if((str.charAt(0) != '#') || (str.length() != 7)){
            return false;
        }

        for(int i = 1; i < str.length(); i++){
            if (!(((int)str.charAt(i) >= 48 && (int)str.charAt(i) <= 57) ||
                   ((int)str.charAt(i) >= 65 && (int)str.charAt(i) <= 70)||
                   ((int)str.charAt(i) >= 97 && (int)str.charAt(i) <= 102))) {
                return false;
            }
        }

        return true;
    }

    public static boolean same(int[] arr1, int[] arr2){
        Set<Integer> set1 = new HashSet<>();
        for(int i: arr1){
            set1.add(i);
        }

        Set<Integer> set2 = new HashSet<>();
        for (int i: arr2){
            set2.add(i);
        }

        return set1.size() == set2.size();
    }

    public static boolean isKaprekar(int n){
        String square = Integer.toString(n*n);
        if (square.length() < 2) {
            return n == n*n;
        }

        String left = "";
        String right = "";

        for(int i = 0; i < (square.length()/2); i++){
            left += square.charAt(i);
        }

        right = square.substring(left.length());

        return Integer.parseInt(left) + Integer.parseInt(right) == n;
    }

    public static String longestZero(String str){
        int max = 0;
        int cnt = 0;

        for(int i = 0; i < str.length(); i++){
            if(str.charAt(i) == '0'){
                cnt += 1;
                max = Math.max(cnt, max);
            }else{
                cnt = 0;
            }
        }
        String res = "";
        for(int i = 0; i < max; i++){
            res += "0";
        }
        return res;
    }

//  This func used in next func
    public static boolean isPrime(int n) {
        for (int i = 2; i < n; ++i) {
            if (n % i == 0) {
                return false;
            }
        }

        return true;
    }

    public static int nextPrime(int start){
        for(int i = start; i < start*start; i++){
            if(isPrime(i)){
                return i;
            }
        }
        return -1;
    }

    public static boolean rightTriangle(int x, int y, int z){
        return (x*x == y*y + z*z) || (y*y == x*x + z*z) || (z*z == x*x + y*y);
    }

    public static void main(String[] args) {
        System.out.println(solutions(1, 0, 1));
        System.out.println(findZip("all zip files are zipped"));
        System.out.println(checkPerfect(12));
        System.out.println(flipEndChars("z"));
        System.out.println(isValidHexCode("#CD5C5Z"));
        System.out.println(same(new int[] {9, 8, 7, 6},new int[] {4, 4, 3, 1}));
        System.out.println(isKaprekar(3));
        System.out.println(longestZero("1110000001010001000100101000"));
        System.out.println(nextPrime(11));
        System.out.println(rightTriangle(70, 130, 110));
    }
}