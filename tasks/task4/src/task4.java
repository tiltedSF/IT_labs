import java.util.*;
import java.util.logging.StreamHandler;

public class task4 {

    public static void Bessie(int n, int k, String str){

        String[] words = str.split(" ");
        String curr_words = words[0];
        int curr_len = curr_words.length();

        for(int i = 1; i < n; i++){
            if(curr_len + words[i].length() > k){
                System.out.println(curr_words);
                curr_words = words[i];
                curr_len = words[i].length();
            }else{
                curr_words = curr_words + " " + words[i];
                curr_len += words[i].length();
            }

        }
        if(curr_len < k) {
            System.out.println(curr_words);
        }

    }

    public static String[] split(String str){

        List<String> res = new ArrayList<>();
        String now = "";
        int now_len = 0;
        int len = 0;

        for(int i = 0; i < str.length(); i++){
            if(str.charAt(i) == ')'){
                now_len += 1;
                now += str.charAt(i);
                if(now_len == len) {
                    res.add(now);
                    now = "";
                    len = 0; now_len = 0;
                }
            }else{
                now += str.charAt(i);
                len += 1;
            }
        }
        System.out.println(res.toString());
        return res.toArray(new String[0]);

    }

    public static String toCamelCase(String str){
        String res = "";

        for(int i = 0;i < str.length(); i++){

            if(str.charAt(i) != '_'){
                res += str.charAt(i);
            }else{
                res += str.substring(i+1, i+2).toUpperCase();
                i += 1;
            }
        }
        return res;
    }

    public static String toSnakeCase(String str){
        String res = "";

        for(int i = 0; i < str.length(); i++){
            if(str.substring(i, i + 1).matches("^[A-Z]+$")){
                res += "_" + str.substring(i, i+1).toLowerCase();
            }else{
                res += str.charAt(i);
            }

        }
        return res;
    }

    public static String overTime(double[] arr){
        double res = (17 - arr[0])*arr[2];
        if(arr[1] > 17){
            res += (arr[1] - 17)*arr[2]*arr[3];
        }else{
            res -= (17 - arr[1])*arr[2];
        }

        return "$" + String.valueOf(res) + "0";

    }

    public static String BMI(String s1, String s2){
        double weight = Double.parseDouble(s1.substring(0, s1.lastIndexOf(" ")));
        if(s1.endsWith("pounds")){
            weight = weight * 0.453592;
        }

        double height = Double.parseDouble(s2.substring(0, s1.lastIndexOf(" ")));
        if(s2.endsWith("inches")){
            height = height * 0.0254;
        }

        double bmi = weight/(height*height);
        if     (bmi < 18.5){return String.valueOf(bmi) + " Underweight";}
        else if(bmi >= 25){return String.valueOf(bmi) + " Overweight";}
        else{return String.valueOf(bmi) + " Normal weight";}

    }

    public static int bugger(int n){
        String now = String.valueOf(n);
        int now_res = 1;
        int iters = 0;


        while(now.length() != 1){
            iters += 1;

            for(int i = 0;i < now.length();i++){
                now_res = now_res * Integer.parseInt(String.valueOf(now.charAt(i)));
            }

            now = String.valueOf(now_res);
            now_res = 1;
        }

        return iters;
    }

    public static String toStarShorthand(String chars){
        String res = "";
        int i = 0;

        while(i < chars.length()){
            char curr = chars.charAt(i);
            int count= 0;

            while(i < chars.length() && chars.charAt(i) == curr){
                i++;
                count++;
            }

            res += curr;
            if(count != 1){
                res += "*"+count;
            }
        }
        return res;
    }

    public static boolean doesRhyme(String s1, String s2){
        String word1 = s1.split(" ")[s1.split(" ").length - 1].toLowerCase();
        String word2 = s2.split(" ")[s2.split(" ").length - 1].toLowerCase();

        char[] glasn = new char[] {'a', 'e', 'i', 'o', 'u', 'w', 'y'};

        ArrayList<Integer> list1 = new ArrayList<>();
        ArrayList<Integer> list2 = new ArrayList<>();

        for(int i = 0; i < word1.length(); i++){
            for(int k = 0; k < glasn.length; k++){
                if(word1.charAt(i) == glasn[k]){list1.add((int)glasn[k]);}
            }
        }
        for(int i = 0; i < word2.length(); i++){
            for(int k = 0; k < glasn.length; k++){
                if(word2.charAt(i) == glasn[k]){list2.add((int)glasn[k]);}
            }
        }

        return list1.equals(list2);
    }

    public static boolean trouble(long n1, long n2){
        String s1 = String.valueOf(n1);
        String s2 = String.valueOf(n2);

        ArrayList<Integer> list1 = new ArrayList<>();
        ArrayList<Integer> list2 = new ArrayList<>();


        int i = 1;
        int cnt = 1;
        while(i < s1.length()){
            while(i < s1.length() && s1.charAt(i-1) == s1.charAt(i)){
                cnt += 1;
                i+= 1;
            }
            if(cnt > 2){list1.add(Integer.parseInt(String.valueOf(s1.charAt(i-1))));}
            cnt = 1;
            i += 1;
        }

        i = 1;
        cnt = 1;
        while(i < s2.length()){
            while(i < s2.length() && s2.charAt(i-1) == s2.charAt(i)){
                cnt += 1;
                i+= 1;
            }
            if(cnt > 1){list2.add(Integer.parseInt(String.valueOf(s2.charAt(i-1))));}
            cnt = 1;
            i += 1;
        }



        if(!list1.isEmpty()) {

            for (int j : list1) {
                if (list2.contains(j)) {
                    return true;
                }
            }
        }

        return false;

    }

    public static int countUniqueBooks(String stringSequence, char bookEnd){

        Set<String> set = new HashSet<String>();
        boolean flag = true;
        int k = 0;

        for (int i = 0; i < stringSequence.length(); i++) {
            if (stringSequence.charAt(i) == bookEnd){
                k += 1;
            }
            if (k % 2 != 0 && stringSequence.charAt(i) != bookEnd) {
                set.add(String.valueOf(stringSequence.charAt(i)));
            }
        }

        return set.size();
    }


    public static void main(String[] args) {
//        Bessie(10,7, "hello my name is Bessie and this is my essay");
//        split("((()))(())()()(()())");
//        System.out.println(toCamelCase("is_modal_open"));
//        System.out.println(toSnakeCase("getColor"));
//        System.out.println(overTime(new double[] {13.25, 15, 30, 1.5}));
//        System.out.println(BMI("205 pounds", "73 inches"));
//        System.out.println(bugger(999));
//        System.out.println(toStarShorthand("abbccc"));
        System.out.println(doesRhyme("and frequently do?", "you gotta move."));
//        System.out.println(trouble(666789, 12345667));
//        System.out.println(countUniqueBooks("ZZABCDEF", 'Z'));
    }
}