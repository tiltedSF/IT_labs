public class Palindrome {
    public static String reverseString(String str){

        String reverse = "";

        for(int i = str.length() - 1; i >= 0; i--){
            reverse += str.charAt(i);
        }

        return reverse;
    }

    public static boolean isPalindrome(String s){
        if(s.equals(reverseString(s))){return true;}
        return false;
    }

    public static void main(String[] args) {
        String str = "java Palindrome madam racecar apple kayak song noon";
        String[] array = str.split(" ");
        for (String i:array){
            if(isPalindrome(i)){
                System.out.println(i + ": a Palindrome");
            }else{
                System.out.println(i + ": not a Palindrome");
            }
        }

    }
}